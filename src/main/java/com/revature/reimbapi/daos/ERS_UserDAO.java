package com.revature.reimbapi.daos;

import com.revature.reimbapi.models.ERS_User;
import com.revature.reimbapi.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ERS_UserDAO implements CrudDAO<ERS_User>{
    @Override
    public void save(ERS_User user) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_users (user_id, username, email, password, given_name, surname, is_active, role_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getGivenName());
            ps.setString(6, user.getSurname());
            ps.setBoolean(7, user.isActive());
            ps.setString(8, user.getRoleId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public List<ERS_User> getAll() {

        return null;
    }

    @Override
    public void update(ERS_User obj) {

    }

    @Override
    public void delete(UUID id) {

    }
}
