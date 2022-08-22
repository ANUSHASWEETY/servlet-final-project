package com.wavemaker.employee.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wavemaker.employee.UserManager;
import com.wavemaker.employee.constants.PersistenceType;
import com.wavemaker.employee.implementation.UserMemoryManagerImpl;
import com.wavemaker.employee.manager.UserInstanceFactory;
import com.wavemaker.employee.model.User;
import com.wavemaker.employee.implementation.UserDatabaseManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Enumeration;
import java.io.*;
import static com.wavemaker.employee.manager.UserInstanceFactory.getUserManager;

public class UserServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserManager userManager = getUserManager(PersistenceType.DB);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users=userManager.listUsers();
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));

       Enumeration<String> headerNames=request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName=headerNames.nextElement();
            String headerValue= request.getHeader(headerName);
            System.out.println("header name"+headerName);
            System.out.println("header value"+headerValue);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = objectMapper.readValue(request.getReader(), User.class);
        userManager.addUser(user);
        try {
            userManager.addUser(user);
        } catch (UnknownError e) {
            response.setStatus(400);
            response.getWriter().write("bad request");
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("Internal server error");
        }

    }
    protected void doDelete(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        User user=objectMapper.readValue(request.getReader(), User.class);
        userManager.deleteUser(user);
        try {
            userManager.deleteUser(user);
        } catch (UnknownError e) {
            response.setStatus(400);
            response.getWriter().write("bad request");
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("Internal server error");
        }
    }
}