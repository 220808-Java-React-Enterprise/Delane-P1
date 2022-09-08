package com.revature.reimbapi.models;

public class ERS_Role {
    private String roleId;
    private String role;

    public ERS_Role() {}

    public ERS_Role(String roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ERS_Role{" +
                "roleId='" + roleId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
