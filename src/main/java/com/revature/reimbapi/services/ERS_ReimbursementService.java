/*
Project: P1
Program Name: Reimbursement Api
Purpose: To create a reimbursement api.
Module Name: ERS_ReimbursementService
Programmer: Delane Green
Created: 08/26/2022
Last modified: 09/2/2022
*/

package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_ReimbursementDAO;
import com.revature.reimbapi.dtos.requests.NewReimbursementRequest;
import com.revature.reimbapi.dtos.requests.UpdateReimbEmployeeRequest;
import com.revature.reimbapi.models.ERS_Reimbursement;
import com.revature.reimbapi.utils.customexceptions.PlaceHolderException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ERS_ReimbursementService {

    private final ERS_ReimbursementDAO reimDAO;

    public ERS_ReimbursementService(ERS_ReimbursementDAO reimDAO ) {
        this.reimDAO = reimDAO;

    }

    public boolean verifyReimbursementFormCompletion(ERS_Reimbursement r) {

        if(r.getReimb_id() == null) { return false; }
        else if(r.getAmount() == null) { return false; }    //TODO: check what the default value of a bigdecimal is.
        else if(r.getSubmitted() == null) { return false; }
        else if(r.getDescription() == null) { return false; }
        else if(r.getAuthor_id() == null) { return false; }
        else if(r.getType_id() == null) { return false; }


        return true;
    }
//Next TODO: change over from model type to request type.
    public ERS_Reimbursement saveReimbursementRequest(NewReimbursementRequest reimbRequest) {
        //TODO: call verify form complete

        try {
            //Converting request class to model class
            ERS_Reimbursement newReimbRequest = new ERS_Reimbursement(UUID.randomUUID(), BigDecimal.valueOf(reimbRequest.getAmount()), Timestamp.valueOf(LocalDateTime.now()), null, reimbRequest.getDescription(), reimbRequest.getReceipt(), reimbRequest.getPayment_id(), UUID.fromString(reimbRequest.getAuthor_id()), null, "P", reimbRequest.getType_id());

            //reimbRequest.setStatus_id("PENDING_ID"); //Fill in once ids are decided.
            reimDAO.save(newReimbRequest);

            return newReimbRequest;

        } catch(Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<ERS_Reimbursement> getReimbursementByEmployee_Id(UUID id) {

        List<ERS_Reimbursement> list = reimDAO.getAllByAuthorId(id);
        if(list.isEmpty()) {throw new PlaceHolderException("There are no saved reimbursements for this employee.");} //Placeholder exception.

        return list;
    }

    public void employeeUpdateReimbursement(UpdateReimbEmployeeRequest reimbUpdate) {
        //STUB //Wondering whether to check if status is pending here or in the method that calls this one.
        ERS_Reimbursement reimb = new ERS_Reimbursement(UUID.fromString(reimbUpdate.getReimb_id()), BigDecimal.valueOf(reimbUpdate.getAmount()), Timestamp.valueOf(LocalDateTime.now()), null, reimbUpdate.getDescription(), reimbUpdate.getReceipt(), reimbUpdate.getPayment_id(), reimbUpdate.getType_id());
        reimDAO.update(reimb);

    }

    public void managerUpdateReimbursementStatus(UUID reimb_id, String status_id) {
        //STUB
        reimDAO.updateStatus(reimb_id, status_id);

    }

    public String getReimbursementStatusId(UUID reimb_id) {
        //STUB

        return reimDAO.getStatusByReimbId(reimb_id);

    }

    public List<ERS_Reimbursement> getAllReimbursementsOrderBy(String columnName, boolean order) {

        //For order True = Ascending False = Descending.
        List<ERS_Reimbursement> list;

        if(order) {
            list = reimDAO.getAllOrderByAscending(columnName);

        }
        else {
            list = reimDAO.getAllOrderByDescending(columnName);

        }

        if(list.isEmpty()) { throw new PlaceHolderException("There are no reimbursements at this time."); } //Consider replacing with a printStatement.

        return list;

    }

    //TODO: If time allows; create method to check if a reimbursement has already been resolved and return the name or id of the resolver along with the updated status.

}
