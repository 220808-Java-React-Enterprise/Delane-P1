/*
Project: P1
Program Name: Reimbursement Api
Purpose: To create a reimbursement api.
Module Name: ERS_ReimbursementDAO
Programmer: Delane Green
Created: 08/26/2022
Last modified: 09/2/2022
*/

package com.revature.reimbapi.daos;

import com.revature.reimbapi.models.ERS_Reimbursement;
import com.revature.reimbapi.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ERS_ReimbursementDAO implements CrudDAO<ERS_Reimbursement> {
    @Override
    public void save(ERS_Reimbursement obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ers_reimbursements (reimb_id, amount, submitted, resolved, description, payment_id, author_id,resolver_id, status_id, type_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

            ps.setString(1, String.valueOf(obj.getReimb_id()));
            ps.setBigDecimal(2, obj.getAmount());
            ps.setTimestamp(3, obj.getSubmitted());
            ps.setTimestamp(4, obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setString(6, obj.getPayment_id());
            ps.setString(7, String.valueOf(obj.getAuthor_id()));
            ps.setString(8, String.valueOf(obj.getResolver_id()));
            ps.setString(9, obj.getStatus_id());
            ps.setString(10, obj.getType_id());

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public List<ERS_Reimbursement> getAll() {

        List<ERS_Reimbursement> list = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERS_Reimbursement reim = new ERS_Reimbursement(UUID.fromString(rs.getString("reimb_id")), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"), UUID.fromString(rs.getString("author_id")), UUID.fromString(rs.getString("resolver_id")), rs.getString("status_id"), rs.getString("type_id"));
                list.add(reim);

            }

        } catch(Exception e) {
            e.printStackTrace();

        }

        return list;
    }


    public List<ERS_Reimbursement> getAllOrderByAscending(String columnName) { //TODO: check if both ascending and descending can fit in one method.

        List<ERS_Reimbursement> list = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements ORDER BY ? ASC");

            ps.setString(1, columnName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERS_Reimbursement reim = new ERS_Reimbursement(UUID.fromString(rs.getString("reimb_id")), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"), UUID.fromString(rs.getString("author_id")), UUID.fromString(rs.getString("resolver_id")), rs.getString("status_id"), rs.getString("type_id"));
                list.add(reim);

            }

        } catch(Exception e) {
            e.printStackTrace();

        }

        return list;
    }


    public List<ERS_Reimbursement> getAllOrderByDescending(String columnName) { //TODO: check if both ascending and descending can fit in one method.

        List<ERS_Reimbursement> list = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements ORDER BY ? DESC");

            ps.setString(1, columnName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERS_Reimbursement reim = new ERS_Reimbursement(UUID.fromString(rs.getString("reimb_id")), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"), UUID.fromString(rs.getString("author_id")), UUID.fromString(rs.getString("resolver_id")), rs.getString("status_id"), rs.getString("type_id"));
                list.add(reim);

            }

        } catch(Exception e) {
            e.printStackTrace();

        }

        return list;
    }


    public List<ERS_Reimbursement> getAllByAuthorId(UUID author_id) {

        List<ERS_Reimbursement> list = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE author_id = ?");
            ps.setString(1, String.valueOf(author_id));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERS_Reimbursement reim = new ERS_Reimbursement(UUID.fromString(rs.getString("reimb_id")), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"), UUID.fromString(rs.getString("author_id")), UUID.fromString(rs.getString("resolver_id")), rs.getString("status_id"), rs.getString("type_id"));
                list.add(reim);

            }

        } catch(Exception e) {
            e.printStackTrace();

        }

        return list;
    }

    public List<ERS_Reimbursement> getAllByAuthorIdAndStatusId(UUID author_id, String status_id) {

        List<ERS_Reimbursement> list = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE author_id = ? AND status_id = ?");
            ps.setString(1, String.valueOf(author_id));
            ps.setString(2, status_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERS_Reimbursement reim = new ERS_Reimbursement( UUID.fromString(rs.getString("reimb_id")), rs.getBigDecimal("amount"), rs.getTimestamp("submitted"), rs.getTimestamp("resolved"), rs.getString("description"), rs.getBlob("receipt"), rs.getString("payment_id"), UUID.fromString(rs.getString("author_id")), UUID.fromString(rs.getString("resolver_id")), rs.getString("status_id"), rs.getString("type_id"));
                list.add(reim);

            }

        } catch(Exception e) {
            e.printStackTrace();

        }

        return list;

    }

    public String getStatusByReimbId(UUID reimb_id) {


        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT status_id FROM ers_reimbursements WHERE reimb_id = ? ");
            ps.setString(1, String.valueOf(reimb_id));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return  rs.getString("status_id");

            }

        } catch(Exception e) {
            e.printStackTrace();

        }

        return null;
    }


    @Override
    public void update(ERS_Reimbursement obj) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE ers_reimbursements SET ( amount = ?, submitted = ?, resolved = ?, description = ?, receipt = ?, payment_id = ?, revsolver_id = ?, status_id = ?, type_id = ? WHERE reimb_id = ?");

            ps.setBigDecimal(1, obj.getAmount());
            ps.setTimestamp(2, obj.getSubmitted());
            ps.setTimestamp(3, obj.getResolved());
            ps.setString(4, obj.getDescription());
            ps.setBlob(5, obj.getReceipt());  //temp needs to be changed to a proper datatype.
            ps.setString(6, obj.getPayment_id());
            ps.setString(7, String.valueOf(obj.getAuthor_id()));
            ps.setString(8, String.valueOf(obj.getResolver_id()));
            ps.setString(9, obj.getStatus_id());
            ps.setString(10, obj.getType_id());
            ps.setObject(11, obj.getReimb_id());

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();

        }

    }


    //TODO: remove resolved and resolver_id from save and and to update instead. change the name of this update method to reflect those changes.
    public void updateStatus(UUID reimb_id, String status_id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE ers_reimbursements SET status_id = ? WHERE reimb_id = ?");

            ps.setString(1, status_id);
            ps.setString(2, String.valueOf(reimb_id));

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void delete(String reimb_id) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM ers_reimbursements WHERE reimb_id = ? ");
            ps.setString(1, reimb_id);
            ps.executeUpdate();


        } catch(Exception e) {
            e.printStackTrace();

        }

    }
}
