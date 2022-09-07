package com.revature.reimbapi.daos;

import com.revature.reimbapi.models.ERS_User;
import com.revature.reimbapi.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        List<ERS_User> list = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users");

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new ERS_User(rs.getString("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("given_name"), rs.getString("surname"), rs.getBoolean("is_active"), rs.getString("role_id")));

            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }


    public List<ERS_User> getAllUnactivated() {

        List<ERS_User> list = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE is_active = false");

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new ERS_User(rs.getString("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("given_name"), rs.getString("surname"), rs.getBoolean("is_active"), rs.getString("role_id")));

            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    public ERS_User getUserByUsernameAndPassword(String username, String password) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE user_id = ? AND password = ?");

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new ERS_User(rs.getString("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("given_name"), rs.getString("surname"), rs.getBoolean("is_active"), rs.getString("role_id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    public ERS_User getUserByUsername(String username) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE username = ?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) { return new ERS_User(rs.getString("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("given_name"), rs.getString("surname"), rs.getBoolean("is_active"), rs.getString("role_id")); }

        } catch(SQLException e) {
            e.printStackTrace();

        }

        return null;

    }


    public boolean doesUserExist(String username) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_users WHERE username = ?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) { return true; }

        } catch(SQLException e) {
            e.printStackTrace();

        }

        return false;

    }

    @Override
    public void update(ERS_User user) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_users SET is_active = ? WHERE user_id = ?");

            ps.setBoolean(1, user.isActive());
            ps.setString(2, user.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void updateActivation(String username, boolean activate) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_users SET is_active = ? WHERE username = ?");

            ps.setBoolean(1, activate);
            ps.setString(2, username);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void delete(String id) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM ers_users WHERE user_id = ?");

            ps.setString(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
