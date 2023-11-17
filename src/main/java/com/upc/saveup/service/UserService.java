package com.upc.saveup.service;

import com.upc.saveup.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public abstract User createCompany(User user);
    public abstract void updateCompany(User user);
    public abstract void deleteCompany(int id);
    public abstract User getUser(int id);
    public abstract List<User> getAllUsers();
    public abstract boolean isCompanyExist(int id);
    public List<Map<String, Object>> getSaleData(int userId);
    public abstract boolean isUserExist(int id);

}