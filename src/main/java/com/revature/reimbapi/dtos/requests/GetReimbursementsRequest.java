
package com.revature.reimbapi.dtos.requests;

public class GetReimbursementsRequest {
    //Data fields
    private String userId;
    private String roleId;
    private String columnName;
    private String columnValue;
    private boolean order;

    public GetReimbursementsRequest() {
    }

    public GetReimbursementsRequest(String userId, String roleId, String columnName, String columnValue, boolean order) {
        this.userId = userId;
        this.roleId = roleId;
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.order = order;
    }

    public GetReimbursementsRequest(String userId, String columnName, String columnValue, boolean order) {
        this.userId = userId;
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.order = order;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public boolean getOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public boolean isOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "GetAllReimbursementsRequest{" +
                "user_id='" + userId + '\'' +
                ", cloumnName='" + columnName + '\'' +
                ", column_id='" + columnValue + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
