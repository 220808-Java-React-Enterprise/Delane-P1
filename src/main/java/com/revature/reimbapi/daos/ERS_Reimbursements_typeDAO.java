/*
Project: P1
Program Name: Reimbursement Api
Purpose: To create a reimbursement api.
Module Name: ERS_Reimbursements_typeDAO
Programmer: Delane Green
Created: 08/26/2022
Last modified: 09/2/2022
*/

package com.revature.reimbapi.daos;


import com.revature.reimbapi.models.ERS_Reimbursement_type;
import com.revature.reimbapi.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ERS_Reimbursements_typeDAO {

    public void save(Object obj) {  //Is this necessary? maybe an added feature?

    }

    public List getAll() {
        return null;
    }

    public String getTypeById(String typeId) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT type FROM ers_reimbursement_types WHERE type_id = ?");
            ps.setString(1, typeId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getString("type");

            }

        } catch(SQLException e) {
            e.printStackTrace();

        }

        return null;

    }

    public void update(ERS_Reimbursement_type type) {

    }

    public void delete(String id) {

    }


}
