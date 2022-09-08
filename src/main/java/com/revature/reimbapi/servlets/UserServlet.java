package com.revature.reimbapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbapi.dtos.requests.NewUserRequest;
import com.revature.reimbapi.dtos.responeses.Principal;
import com.revature.reimbapi.services.ERS_UserService;
import com.revature.reimbapi.utils.customexceptions.InvalidRequestException;
import com.revature.reimbapi.utils.customexceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final ERS_UserService userService;

    public UserServlet(ObjectMapper objectMapper, ERS_UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //if(String.valueOf(req.getRequestURI()).equals("http://localhost:8080/reimbapi/user/signup")) {}
        try {
            NewUserRequest newUser = objectMapper.readValue(req.getInputStream(), NewUserRequest.class);

            Principal principal = userService.registerUser(newUser);

            resp.setStatus(200);
            resp.setContentType("application/json");
            //resp.getWriter().write(objectMapper.writeValueAsString(principal));
            resp.getWriter().write("User successfully registered awaiting admin approval for account activation.");



        } catch(InvalidRequestException e) {
            resp.setStatus(400); //Invaildinput
            resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));

        } catch (ResourceConflictException e) {
            resp.setStatus(409);
            resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));

        } catch (Exception e) {
            resp.setStatus(404);
        }
    }
}
