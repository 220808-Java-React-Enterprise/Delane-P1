
package com.revature.reimbapi.dtos.requests;

import java.sql.Blob;

public class NewReimbursementRequest {
    private Double amount;
    private String description;
    private String author_id;
    private String type_id;

    public NewReimbursementRequest() {
    }

    public NewReimbursementRequest(Double amount, String description, String author_id, String type_id) {
        this.amount = amount;
        this.description = description;
        this.author_id = author_id;
        this.type_id = type_id;
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

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
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
                ", author_id='" + author_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}
