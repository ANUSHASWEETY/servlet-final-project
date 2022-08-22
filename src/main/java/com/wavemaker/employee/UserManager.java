package com.wavemaker.employee;

import com.wavemaker.employee.model.User;

import java.util.List;
import java.util.ListIterator;

public interface UserManager {
     void addUser(User user);
    ListIterator<User> updateUser(User user);
    List<User> listUsers();
    void deleteUser(User user);
    List<User> searchUsers(String searchword);

}