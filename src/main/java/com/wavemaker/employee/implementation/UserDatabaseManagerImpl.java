package com.wavemaker.employee.implementation;
import com.wavemaker.employee.connectivity.Mysqlconnection;
import com.wavemaker.employee.UserManager;
import com.wavemaker.employee.model.User;
import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class UserDatabaseManagerImpl implements UserManager {
    Mysqlconnection mysqlconnection=new Mysqlconnection();
    public List<User> listUsers() {
        List<User> UserList = new ArrayList<>();
        Connection connection = Mysqlconnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String company_name = resultSet.getString("company_name");
                UserList.add(new User(id, name, company_name));
            }
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }
        return UserList;
    }
    public void addUser(User user){
        //TDOO

        Connection connection = Mysqlconnection.getConnection();
        try {
            String sql = "INSERT INTO  USERS(id, name,company_name) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getCompany_name());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        }
        catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }

    }
    public void deleteUser(User user) {
        Connection connection = Mysqlconnection.getConnection();
        try {
            String sql = "DELETE FROM USERS WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("user was deleted successfully!");
            }
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }

    }
    public ListIterator<User> updateUser(User user){
        Connection connection = Mysqlconnection.getConnection();
        try {
            String sql = "UPDATE USERS SET  name=? WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setInt(3, user.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<User> searchUsers(String Search){
        Connection connection= Mysqlconnection.getConnection();
        try {
            List<User> userList =new ArrayList<>();
            String sql ="select * from USERS where name like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Search);
            ResultSet rowsInserted = statement.executeQuery();
            while(rowsInserted.next()){
                int id = rowsInserted.getInt("id");
                String name = rowsInserted.getString("name");
                String companyName= rowsInserted.getString("company_name");
                userList.add(new User(id,name,companyName));
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("exception ");
        }

        return null;
    }
}


