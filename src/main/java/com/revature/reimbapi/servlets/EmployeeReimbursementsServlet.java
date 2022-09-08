package com.revature.reimbapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbapi.dtos.requests.GetReimbursementsRequest;
import com.revature.reimbapi.dtos.requests.NewReimbursementRequest;
import com.revature.reimbapi.dtos.requests.UpdateReimbEmployeeRequest;
import com.revature.reimbapi.dtos.responeses.Principal;
import com.revature.reimbapi.models.ERS_Reimbursement;
import com.revature.reimbapi.services.ERS_ReimbursementService;
import com.revature.reimbapi.services.TokenService;
import com.revature.reimbapi.utils.customexceptions.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.ToDoubleBiFunction;

public class EmployeeReimbursementsServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final TokenService tokenService;

    private final ERS_ReimbursementService reimbursementService;

    public EmployeeReimbursementsServlet(ObjectMapper objectMapper, TokenService tokenService, ERS_ReimbursementService reimbursementService) {
        this.objectMapper = objectMapper;
        this.tokenService =tokenService;
        this.reimbursementService = reimbursementService;

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);

        //??
        try {
            if(principal.getRole() != null) {
                NewReimbursementRequest newReimbursementRequest = objectMapper.readValue(req.getInputStream(), NewReimbursementRequest.class);

                String userId = principal.getUserId();
                ERS_Reimbursement reimb = reimbursementService.saveReimbursementRequest(newReimbursementRequest, userId);


                resp.setContentType("application/json");

                resp.getWriter().write(objectMapper.writeValueAsString(reimb.getReimb_id()));
                resp.getWriter().write(objectMapper.writeValueAsString(System.getProperty("user.dir")));
            }else {throw new AuthenticationException("You must login before you can do this.");}


        }catch(AuthenticationException e) {

            resp.setStatus(401);
            resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));

        }catch(Exception e) {

            resp.setStatus(404);
            // e.printStackTrace();
            // resp.getWriter().println( e.getCause());
        }

    }

   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] path = req.getRequestURI().split("/");

        if(path[path.length - 1].equals("get")) {

            String token = req.getHeader("Authorization");
            Principal principal = tokenService.extractRequesterDetails(token);

            try {
                GetReimbursementsRequest getReimbReq = objectMapper.readValue(req.getInputStream(), GetReimbursementsRequest.class);

                List<ERS_Reimbursement> reimbList = reimbursementService.getAllReimbursementsOrderBy(getReimbReq.getColumnName(), getReimbReq.getOrder());

                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(reimbList));
                //resp.setStatus(200);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] path = req.getRequestURI().split("/");

        if(path[path.length - 1].equals("viewhistory")) {

            String token = req.getHeader("Authorization");
            Principal principal = tokenService.extractRequesterDetails(token);

            try {

                if(principal.getUserId() != null) {

                    List<ERS_Reimbursement> reimbList = reimbursementService.getReimbursementByEmployee_Id(UUID.fromString(principal.getUserId()));

                    resp.setContentType("application/json");
                    resp.getWriter().write(objectMapper.writeValueAsString(reimbList));
                    resp.setStatus(200);
                }else { throw new AuthenticationException("You need to login before accessing this resource.");}

            } catch(AuthenticationException e) {
                resp.setStatus(401);
                resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] path = req.getRequestURI().split("/");

        if(path[path.length - 1].equals("put")) {
            try {
                UpdateReimbEmployeeRequest updateReimbReq = objectMapper.readValue(req.getInputStream(), UpdateReimbEmployeeRequest.class);

                reimbursementService.employeeUpdateReimbursement(updateReimbReq);
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(updateReimbReq));
                //resp.setStatus(200);
            } catch(Exception e) {
                e.printStackTrace();

            }
        }
    }


}
