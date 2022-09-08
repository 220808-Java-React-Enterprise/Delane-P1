
package com.revature.reimbapi.dtos.requests;

public class GetReimbursementsRequest {
    //Data fields
    private String columnName;
    private String columnValue;
    private boolean order;

    public GetReimbursementsRequest() {
    }

    public GetReimbursementsRequest(String columnName, String columnValue, boolean order) {
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.order = order;
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

    public boolean isOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "GetAllReimbursementsRequest{" +
                ", columnName='" + columnName + '\'' +
                ", column_id='" + columnValue + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
