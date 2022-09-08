package com.revature.reimbapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbapi.dtos.requests.GetUnactivatedRequest;
import com.revature.reimbapi.dtos.requests.UpdateActiveRequest;
import com.revature.reimbapi.dtos.responeses.Principal;
import com.revature.reimbapi.models.ERS_User;
import com.revature.reimbapi.services.ERS_UserService;
import com.revature.reimbapi.services.TokenService;
import com.revature.reimbapi.utils.customexceptions.AuthenticationException;
import com.revature.reimbapi.utils.customexceptions.InvalidRequestException;
import com.revature.reimbapi.utils.customexceptions.NotFoundException;
import com.revature.reimbapi.utils.customexceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final TokenService tokenService;
    private final ERS_UserService userService;

    public AdminServlet(ObjectMapper objectMapper, TokenService tokenService, ERS_UserService userService) {
        this.objectMapper = objectMapper;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] path = req.getRequestURI().split("/");
        if(path[path.length-1].equals("viewunactivated")) {

            String token = req.getHeader("Authorization");
            Principal principal = tokenService.extractRequesterDetails(token);

            try {
                if (principal.getRole().equals("Admin")) {
                    GetUnactivatedRequest getUnactivatedRequest = objectMapper.readValue(req.getInputStream(), GetUnactivatedRequest.class);

                    if (getUnactivatedRequest.isViewUnactivated()) {
                        List<ERS_User> unactivatedList = userService.getUnactivatedUsers();
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(objectMapper.writeValueAsString(unactivatedList));
                    }
                } else {
                    throw new AuthenticationException("Admin permissions needed.");

                }


            } catch (AuthenticationException e) {
                resp.setStatus(403);
                resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));

            } catch (NotFoundException e) {
                resp.setStatus(404);
                resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));

            } catch (Exception e) {
                resp.setStatus(400);

            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);

        try {
            if (principal.getRole().equals("Admin")) {
                UpdateActiveRequest activateRequest = objectMapper.readValue(req.getInputStream(), UpdateActiveRequest.class);

                userService.ActivateUser(activateRequest.getUsername(), activateRequest.isActivate());
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.getWriter().write("User account activated.");


            } else {
                throw new AuthenticationException("Admin permissions needed.");

            }

        } catch (ResourceConflictException e) {
            resp.setStatus(409);
            resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));

        } catch (AuthenticationException e) {
            resp.setStatus(403);
            resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        } catch (NullPointerException e) {
            resp.setStatus(401); // UNAUTHORIZED
            resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        } catch (InvalidRequestException e) {
            resp.setStatus(400);
            resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        }
    }
}
