
package com.revature.reimbapi.dtos.requests;

public class DemoTokenRequest {
    private String userId;
    private String roleId;

    public DemoTokenRequest() {
    }

    public DemoTokenRequest(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "DemoTokenRequest{" +
                "userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
