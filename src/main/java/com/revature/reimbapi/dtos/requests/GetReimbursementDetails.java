
package com.revature.reimbapi.dtos.requests;

public class GetReimbursementDetails {
    //Data fields
    private String reimb_id;
    private String user_id;

    //Constructors
    public GetReimbursementDetails() {}

    public GetReimbursementDetails(String reimb_id, String user_id) {
        this.reimb_id = reimb_id;
        this.user_id = user_id;
    }

    //Getters and Setters
    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    //toString
    @Override
    public String toString() {
        return "GetReimbursementDetails{" +
                "reimb_id='" + reimb_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
