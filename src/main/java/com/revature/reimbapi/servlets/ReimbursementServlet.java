/*
Project: P1
Program Name: Reimbursement Api
Purpose: To create a reimbursement api.
Module Name: ReimbursementServlet
Programmer: Delane Green
Created: 08/26/2022
Last modified: 09/2/2022
*/

package com.revature.reimbapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbapi.dtos.requests.NewReimbursementRequest;
import com.revature.reimbapi.dtos.requests.StatusChangeRequest;
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
import java.util.UUID;

public class ReimbursementServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final ERS_ReimbursementService reimbursementService;
    private final TokenService tokenService;

    public ReimbursementServlet(ObjectMapper objectMapper, TokenService tokenService, ERS_ReimbursementService reimbursementService) {
        this.objectMapper = objectMapper;
        this.tokenService = tokenService;
        this.reimbursementService = reimbursementService;

    }

 /*   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetAllReimbursementsRequest getAllReimbursementsRequest = objectMapper.readValue(req.getInputStream(), GetAllReimbursementsRequest.class);

        List<ERS_Reimbursement> list = reimbursementService.getAllReimbursementsOrderBy(getAllReimbursementsRequest.getColumnName(), getAllReimbursementsRequest.getOrder());


        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(list));

    }*/


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatusChangeRequest statusChange = objectMapper.readValue(req.getInputStream(), StatusChangeRequest.class);
        //String[] path = req.getRequestURI().split("/");
        //if(path[path.length - 1].equals("StatusChange")) {}
        resp.getWriter().write(objectMapper.writeValueAsString(statusChange));

        reimbursementService.managerUpdateReimbursementStatus(UUID.fromString(statusChange.getReimb_id()), statusChange.getStatus_id());

    }


}
