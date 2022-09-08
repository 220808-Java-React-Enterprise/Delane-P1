package com.revature.reimbapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbapi.dtos.requests.LoginRequest;
import com.revature.reimbapi.dtos.responeses.Principal;
import com.revature.reimbapi.services.ERS_UserService;
import com.revature.reimbapi.services.TokenService;
import com.revature.reimbapi.utils.customexceptions.AuthenticationException;
import com.revature.reimbapi.utils.customexceptions.InvalidRequestException;
import com.revature.reimbapi.utils.customexceptions.NotFoundException;
import com.revature.reimbapi.utils.customexceptions.ResourceConflictException;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final ERS_UserService userService;

    public AuthenticationServlet(ObjectMapper mapper, TokenService tokenService, ERS_UserService userService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LoginRequest loginRequest = mapper.readValue(req.getInputStream(), LoginRequest.class);
            Principal principal = userService.Login(loginRequest);

            String token = tokenService.generateToken(principal);

            resp.setStatus(200);
            resp.setHeader("Authorization", token);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(principal));

        } catch (AuthenticationException e) {
            resp.setStatus(401);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (NotFoundException e) {
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        }
    }
}
