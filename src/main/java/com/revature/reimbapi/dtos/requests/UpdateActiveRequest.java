package com.revature.reimbapi.dtos.requests;

public class UpdateActiveRequest {
    private String username;
    private boolean activate;

    public UpdateActiveRequest(String username, boolean activate) {
        this.username = username;
        this.activate = activate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    @Override
    public String toString() {
        return "UpdateActiveRequest{" +
                "username='" + username + '\'' +
                ", activate=" + activate +
                '}';
    }
}
