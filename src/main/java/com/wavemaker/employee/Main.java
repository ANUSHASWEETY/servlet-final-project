package com.wavemaker.employee;
import com.wavemaker.employee.implementation.UserMemoryManagerImpl;
import com.wavemaker.employee.implementation.UserDatabaseManagerImpl;
import com.wavemaker.employee.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Main Program");
        System.out.println("enter your choice 1.memory    2.jdbc");
        Scanner ch = new Scanner(System.in);
        int execute = Integer.parseInt(ch.next());
        if (execute == 1) {
            UserMemoryManagerImpl userManager = new UserMemoryManagerImpl();
            Scanner user_id = new Scanner(System.in);
            Scanner user_name = new Scanner(System.in);
            Scanner companyName = new Scanner(System.in);
            User user = new User();
            int i = 0;
            do {
                System.out.println("1.inserting user");
                System.out.println("2.displaying users");
                System.out.println("3.searching user");
                System.out.println("4.updating use");
                System.out.println("5.deleting");
                System.out.println("6.exiting");

                int choice = user_id.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter User Id:");
                        user.setId(user_id.nextInt());
                        System.out.print("Enter User name:");
                        user.setName(user_name.nextLine());
                        System.out.println("enter company name");
                        user.setCompany_name(companyName.nextLine());
                        userManager.addUser(user);
                        break;
                    case 2:
                       List<User> userList=userManager.listUsers();
                        System.out.println(userList);
                        break;
                    case 3:
                        System.out.println("Search username");
                        String user_name1=ch.next();
                        userManager.searchUsers(user_name1);
                        System.out.println("found user name"+user_name1);
                        break;
                    case 4:

                        System.out.println("enter user id which you want to update");
                         int u_id=ch.nextInt();

                        System.out.println("enter company name");
                        String comp_name=ch.next();
                        userManager.updateUser(user);
                        break;
                    case 5:
                        System.out.println("enter userid which you want to delete");
                        int id = user_id.nextInt();
                        userManager.deleteUser(user);
                        break;
                    case 6:
                        System.out.println("exiting");
                        break;

                }
            } while (i != 6);
        } else {

            UserDatabaseManagerImpl userManager = new UserDatabaseManagerImpl() ;
                Scanner user_id = new Scanner(System.in);
                Scanner user_name = new Scanner(System.in);
                Scanner companyName = new Scanner(System.in);
                User user = new User();
                int j = 0;
              do
                {
                    System.out.println("1.inserting user");
                    System.out.println("2.displaying users");
                    System.out.println("3.searching user");
                    System.out.println("4.updating use");
                    System.out.println("5.deleting");
                    System.out.println("6.exiting");

                    int choice = user_id.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Enter User Id:");
                            user.setId(user_id.nextInt());
                            System.out.print("Enter User name:");
                            user.setName(user_name.nextLine());
                            System.out.println("enter company name");
                            user.setCompany_name(companyName.nextLine());
                            userManager.addUser(user);
                            break;
                        case 2:
                            System.out.println(userManager.listUsers());
                            break;
                        case 3:
                            System.out.println("Search username");
                            String uName=ch.next();
                            List<User> userlist=userManager.searchUsers(uName);
                            System.out.println("user found"+user_name);
                            break;
                        case 4:
                            User newUser = new User();
                            System.out.println("update username");
                            newUser.setId(1);
                            newUser.setName("kavya");
                            newUser.setCompany_name("pramati");
                            userManager.updateUser(newUser);
                            break;
                        case 5:
                            System.out.println("enter userid which you want to delete");
                            int id = user_id.nextInt();
                            userManager.deleteUser(user);
                            break;
                        case 6:
                            System.out.println("exiting");
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value: " + choice);
                    }
                }while (j != 6) ;
        }
    }
}