
package com.revature.reimbapi.dtos.requests;

import java.sql.Blob;

public class NewReimbursementRequest {
    private Double amount;
    private String description;
    private String payment_id;
    private String type_id;

    public NewReimbursementRequest() {
    }

    public NewReimbursementRequest(Double amount, String description, String payment_id, String type_id) {
        this.amount = amount;
        this.description = description;
        this.payment_id = payment_id;
        this.type_id = type_id;
    }

    public NewReimbursementRequest(Double amount, String description) {
        this.amount = amount;
        this.description = description;

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "NewReimbursementRequest{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}
