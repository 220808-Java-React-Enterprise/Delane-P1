package com.revature.reimbapi.models;

public class ERS_User {
    //Datafields
    private String userId;
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private boolean isActive = false;
    private String roleId = "E";

    //Constructors
    public ERS_User() {}


    public ERS_User(String userId, String username, String email, String password, String givenName, String surname, String roleId) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.roleId = roleId;
    }

    //Constructor for database retrieval
    public ERS_User(String userId, String username, String email, String password, String givenName, String surname, boolean isActive, String roleId) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    //Getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    //toString
    @Override
    public String toString() {
        return "ERS_User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
