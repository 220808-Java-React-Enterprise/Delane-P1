package com.revature.reimbapi.dtos.requests;

public class NewUserRequest {
    private String username;
    private String email;
    private String password;
    private String password2;
    private String givenName;
    private String surname;

    public NewUserRequest() {}

    public NewUserRequest(String username, String email, String password, String password2, String givenName, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.givenName = givenName;
        this.surname = surname;
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
