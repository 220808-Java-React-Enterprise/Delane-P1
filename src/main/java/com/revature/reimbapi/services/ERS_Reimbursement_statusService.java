/*
Project: P1
Program Name: Reimbursement Api
Purpose: To create a reimbursement api.
Module Name: ERS_Reimbursement_statusService
Programmer: Delane Green
Created: 08/26/2022
Last modified: 09/2/2022
*/

package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_Reimbursements_statusDAO;
import com.revature.reimbapi.utils.customexceptions.InvalidTypeException;

public class ERS_Reimbursement_statusService {
    private ERS_Reimbursements_statusDAO statusDAO;

    public ERS_Reimbursement_statusService(ERS_Reimbursements_statusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    public String getStatusById( String id ) {
        String status = statusDAO.getStatusById(id);

        if(status == null) { throw new InvalidTypeException("That is not a valid reimbursement status.");  }

        return status;

    }

}
