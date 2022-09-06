
package com.revature.reimbapi.dtos.requests;

import java.sql.Blob;

public class UpdateReimbEmployeeRequest {
    //datafields
    private String reimb_id;
    private double amount;
    private String description;
    private Blob receipt;     //using string as a temporary placeholder until a matching datatype is found.
    private String payment_id;
    private String type_id;

    public UpdateReimbEmployeeRequest() {}

    public UpdateReimbEmployeeRequest(String reimb_id, double amount, String description, Blob receipt, String payment_id, String type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.payment_id = payment_id;
        this.type_id = type_id;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getReceipt() {
        return receipt;
    }

    public void setReceipt(Blob receipt) {
        this.receipt = receipt;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "UpdateReimbursementEmployee{" +
                "reimb_id='" + reimb_id + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", payment_id='" + payment_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}
