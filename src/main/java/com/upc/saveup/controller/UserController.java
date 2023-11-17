package com.upc.saveup.controller;

import com.upc.saveup.exception.ValidationException;
import com.upc.saveup.model.User;
import com.upc.saveup.repository.UserRepository;
import com.upc.saveup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/api/creditovehicular/v1")
public class UserController {
    @Autowired
    private UserService userService;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    //EndPoint: localhost:8080/api/saveup/v1/companies
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    /*
    //EndPoint: localhost:8080/api/saveup/v1/sale/{companyId}/data
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/sale/{companyId}/data")
    public List<Map<String, Object>> getPurchaseData(@PathVariable int companyId) {
        return userService.getSaleData(companyId);
    }*/

    //EndPoint: localhost:8080/api/saveup/v1/companies/login
    //Method: POST
    @Transactional(readOnly = true)
    @PostMapping("/users/login")
    public ResponseEntity<User> getUserByEmailAndPassword(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        User company = userRepository.findByEmailAndPassword(email, password);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return null;
        }
    }

    //EndPoint: localhost:8080/api/saveup/v1/companies/recover
    //Method: POST
    @Transactional(readOnly = true)
    @PostMapping("/users/recover")
    public ResponseEntity<User> getUserByEmail(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");

        User company = userRepository.findByEmail(email);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return null;
        }
    }

    //EndPoint: localhost:8080/api/saveup/v1/companies
    //Method: POST
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        validateUser(user);
        existsUserByEmail(user);
        return new ResponseEntity<>(userService.createCompany(user), HttpStatus.CREATED);
    }

    //EndPoint: localhost:8080/api/saveup/v1/companies/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id,@RequestBody User user){
        boolean isExist = userService.isCompanyExist(id);
        if(isExist){
            validateUser(user);
            user.setId(id);
            userService.updateCompany(user);
            return new ResponseEntity<>("User is updated succesfully", HttpStatus.OK);
        }else{
            throw new ValidationException("Error al actualizar el user");
        }
    }


    //EndPoint: localhost:8080/api/saveup/v1/companies/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id){
        boolean isExist = userService.isCompanyExist(id);
        if(isExist){
            userService.deleteCompany(id);
            return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
        }else{
            throw new ValidationException("Error al eliminar el user");
        }
    }

    private void validateUser(User user){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(hotmail|gmail)\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(user.getEmail()).matches()) {
            throw new ValidationException("El email debe ser de tipo hotmail.com o gmail.com");
        }else if(user.getEmail() == null || user.getEmail().trim().isEmpty()){
            throw new ValidationException("El email debe ser obligatorio");
        }

        if(user.getName() == null || user.getName().trim().isEmpty()){
            throw new ValidationException("El nombre debe ser obligatorio");
        }
        if(user.getLastName() == null || user.getLastName().trim().isEmpty()){
            throw new ValidationException("El apellido debe ser obligatorio");
        }



        if(user.getPassword() == null || user.getPassword().trim().isEmpty()){
            throw new ValidationException("La contraseña debe ser obligatorio");
        }

        if(user.getRepeatPassword() == null || user.getRepeatPassword().trim().isEmpty()){
            throw new ValidationException("La confirmación de contraseña debe ser obligatorio");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 12) {
            throw new ValidationException("La contraseña debe tener entre 8 y 12 caracteres");
        }

        if (!user.getPassword().equals(user.getRepeatPassword())){
            throw new ValidationException("La contraseña y la confirmación de contraseña no coinciden");
        }
    }


    private void existsUserByEmail(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ValidationException("No se puede registrar el usuario porque existe una cuenta con el email");
        }
    }
}