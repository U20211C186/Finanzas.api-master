package com.upc.saveup.controller;

import com.upc.saveup.dto.VehicleCreditDto;
import com.upc.saveup.exception.ValidationException;
import com.upc.saveup.model.VehicleCredit;
import com.upc.saveup.repository.VehicleCreditRepository;
import com.upc.saveup.service.UserService;
import com.upc.saveup.service.VehicleCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/api/creditovehicular/v1")
public class VehicleCreditController {
    @Autowired
    private VehicleCreditService vehicleCreditService;
    @Autowired
    private UserService userService;
    private final VehicleCreditRepository vehicleCreditRepository;

    public VehicleCreditController(VehicleCreditRepository vehicleCreditRepository){
        this.vehicleCreditRepository = vehicleCreditRepository;
    }

    //EndPoint: localhost:8080/api/saveup/v1/carts
    //Method: POST
    @Transactional
    @PostMapping("/vehiclecredits")
    public ResponseEntity<VehicleCreditDto> createVehicleCredit(@RequestBody VehicleCreditDto vehicleCreditDto){
        int userId = vehicleCreditDto.getUserId(); // Obtén el userId del objeto VehicleCreditDto
        vehicleCreditDto.setUserId(userId); // Asigna el userId al VehicleCreditDto
        return new ResponseEntity<>(vehicleCreditService.createVehicleCredit(vehicleCreditDto), HttpStatus.CREATED);
    }

    //EndPoint: localhost:8080/api/saveup/v1/products/company/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/vehiclescredits/user/{id}")
    public ResponseEntity<List<VehicleCredit>> getVehiclesCreditsById(@PathVariable("id") int userId){
        return new ResponseEntity<List<VehicleCredit>>(vehicleCreditService.getVehiclesCreditsByUser(userId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/vehiclescredits/user/{id}")
    public ResponseEntity<Object> deleteVehiclesCredits(@PathVariable("id") int userId){
        boolean userExists = userService.isUserExist(userId); // Asume que userService tiene un método para verificar la existencia del usuario.

        if (userExists) {
            vehicleCreditService.deleteVehiclesCreditsForUser(userId); // Asume que productService tiene un método para eliminar los "vehiclescredits" de un usuario.
            return new ResponseEntity<>("Todos los vehiclescredits del usuario han sido eliminados exitosamente", HttpStatus.OK);
        } else {
            throw new ValidationException("El usuario no existe o no se encontraron vehiclescredits asociados al usuario.");
        }
    }
}
