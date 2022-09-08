package com.revature.reimbapi.daos;

import com.revature.reimbapi.models.ERS_Role;
import com.revature.reimbapi.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ERS_RoleDAO implements CrudDAO<ERS_Role>{
    @Override
    public void save(ERS_Role role) {

    }

    @Override
    public List<ERS_Role> getAll() {
        return null;
    }

    public String getRoleById(String roleId) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT role FROM ers_user_roles WHERE role_id = ?");
            ps.setString(1, roleId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getString("role");

            }

        } catch(SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public void update(ERS_Role role) {

    }

    @Override
    public void delete(String id) {

    }
}
