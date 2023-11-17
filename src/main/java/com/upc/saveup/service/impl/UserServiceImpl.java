package com.upc.saveup.service.impl;

import com.upc.saveup.model.User;
import com.upc.saveup.repository.UserRepository;
import com.upc.saveup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createCompany(User company) {
        return userRepository.save(company);
    }

    @Override
    public void updateCompany(User company){
        userRepository.save(company);
    }

    @Override
    public User getUser(int id){
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteCompany(int id){
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @Override
    public boolean isCompanyExist(int id){
        return userRepository.existsById(id);
    }

    @Override
    public List<Map<String, Object>> getSaleData(int companyId) {
        String query = "SELECT cu.name, cu.last_name, o.id as orders, pr.name as producto, pr.price, o.date, co.id as company_id " +
                "FROM saveup.company co " +
                "INNER JOIN saveup.product pr ON co.id = pr.company_id " +
                "INNER JOIN saveup.cart ca ON pr.id = ca.product_id " +
                "INNER JOIN saveup.orders o ON ca.order_id = o.id " +
                "INNER JOIN saveup.pay pa ON o.pay_id = pa.id " +
                "INNER JOIN saveup.customer cu ON pa.customer_id = cu.id " +
                "WHERE co.id = ? " +
                "ORDER BY orders ASC;";
        return jdbcTemplate.queryForList(query, companyId);
    }

    @Override
    public boolean isUserExist(int id) {
        return userRepository.existsById(id);
    }
}