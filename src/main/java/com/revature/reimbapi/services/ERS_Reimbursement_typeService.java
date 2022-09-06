/*
Project: P1
Program Name: Reimbursement Api
Purpose: To create a reimbursement api.
Module Name: ERS_Reimbursement_typeService
Programmer: Delane Green
Created: 08/26/2022
Last modified: 09/2/2022
*/

package com.revature.reimbapi.services;


import com.revature.reimbapi.daos.ERS_Reimbursements_typeDAO;
import com.revature.reimbapi.utils.customexceptions.InvalidTypeException;

public class ERS_Reimbursement_typeService {

    private final ERS_Reimbursements_typeDAO typeDAO;

    public ERS_Reimbursement_typeService(ERS_Reimbursements_typeDAO typeDAO) {
        this.typeDAO = typeDAO;

    }

    public String getTypeById(String id) {

        String type = typeDAO.getTypeById(id);

        if( type == null) { throw new InvalidTypeException("This is not a valid reimbursement type.");
        }

        return type;

    }

}
