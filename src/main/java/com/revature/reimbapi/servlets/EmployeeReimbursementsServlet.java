package com.revature.reimbapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbapi.dtos.requests.GetReimbursementsRequest;
import com.revature.reimbapi.dtos.requests.UpdateReimbEmployeeRequest;
import com.revature.reimbapi.models.ERS_Reimbursement;
import com.revature.reimbapi.services.ERS_ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeeReimbursementsServlet extends HttpServlet {
    private final ObjectMapper objectMapper;

    private final ERS_ReimbursementService reimbursementService;

    public EmployeeReimbursementsServlet(ObjectMapper objectMapper, ERS_ReimbursementService reimbursementService) {
        this.objectMapper = objectMapper;
        this.reimbursementService = reimbursementService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] path = req.getRequestURI().split("/");

        if(path[path.length - 1].equals("get")) {
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
