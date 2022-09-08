/*
Project: P1
Program Name: Reimbursement Api
Purpose: To create a reimbursement api.
Module Name: ERS_Reimbursement
Programmer: Delane Green
Created: 08/26/2022
Last modified: 09/2/2022
*/

package com.revature.reimbapi.models;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class ERS_Reimbursement {
    //datafields
    private UUID reimb_id;
    private BigDecimal amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private Blob receipt;     //using string as a temporay placeholder until a matching datatype is found.
    private String payment_id;
    private UUID author_id;
    private UUID resolver_id = UUID.fromString("184ad055-6162-416c-8600-fd5977191cec");
    private String status_id = "P";
    private String type_id;

    //constructors

    public ERS_Reimbursement() {}

    public ERS_Reimbursement(UUID reimb_id, BigDecimal amount, Timestamp submitted, Timestamp resolved, String description, Blob receipt, String payment_id, UUID author_id, UUID resolver_id, String status_id, String type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public ERS_Reimbursement(UUID reimb_id, BigDecimal amount, Timestamp submitted, Object o, String description, Blob receipt, String payment_id, String type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.receipt = receipt;
        this.payment_id = payment_id;
        this.type_id = type_id;

    }

    public ERS_Reimbursement(UUID reimb_id, BigDecimal amount, Timestamp submitted, String description, String payment_id, UUID author_id, String type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.type_id = type_id;
    }

    public ERS_Reimbursement(UUID randomUUID, BigDecimal valueOf, Timestamp valueOf1, Object o, String description, Blob receipt, String payment_id, UUID fromString, String p, String type_id) {
    }

    //getters and setters
    public UUID getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(UUID reimb_id) {
        this.reimb_id = reimb_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
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

    public UUID getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(UUID author_id) {
        this.author_id = author_id;
    }

    public UUID getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(UUID resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }


    //toString
    @Override
    public String toString() {
        return "ERS_Reimbursments{" +
                "reimb_Id='" + reimb_id + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", resolver_id='" + resolver_id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }


}
