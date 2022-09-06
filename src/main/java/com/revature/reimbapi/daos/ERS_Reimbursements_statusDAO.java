/*
Project: P1
Program Name: Reimbursement Api
Purpose: To create a reimbursement api.
Module Name: ERS_Reimbursements_statusDAO
Programmer: Delane Green
Created: 08/26/2022
Last modified: 09/2/2022
*/

package com.revature.reimbapi.daos;

import com.revature.reimbapi.models.ERS_Reimbursement_status;
import com.revature.reimbapi.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ERS_Reimbursements_statusDAO {

    public void save(Object obj) {

    }


    public List<ERS_Reimbursement_status> getAll() {
        return null;
    }

    public String getStatusById(String id) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT status FROM ers_reimbursement_statuses WHERE status_id = ? ");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("status");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;

    }


    public void update(Object obj) {

    }


    public void delete(String id) {

    }
}
