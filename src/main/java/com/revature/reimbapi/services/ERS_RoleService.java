package com.revature.reimbapi.services;

import com.revature.reimbapi.daos.ERS_RoleDAO;
import com.revature.reimbapi.utils.customexceptions.ResourceConflictException;

public class ERS_RoleService {
    private final ERS_RoleDAO roleDAO;

    public ERS_RoleService(ERS_RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public String getRoleById(String id) {

        String role = roleDAO.getRoleById(id);

        if( role == null) { throw new ResourceConflictException("This is not a valid user role."); }

        return role;

    }

}
