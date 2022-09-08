package com.revature.reimbapi.dtos.requests;

public class GetUnactivatedRequest {
    private boolean viewUnactivated = false;

    public GetUnactivatedRequest(boolean viewUnactivated) {
        this.viewUnactivated = viewUnactivated;
    }

    public boolean isViewUnactivated() {
        return viewUnactivated;
    }

    public void setViewUnactivated(boolean viewUnactivated) {
        this.viewUnactivated = viewUnactivated;
    }

    @Override
    public String toString() {
        return "GetUnactivatedRequest{" +
                "viewUnactivated=" + viewUnactivated +
                '}';
    }
}
