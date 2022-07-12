package com.autodb_api.response;

public class ResponseHTTP {
    private int status;
    private String message;
    private Object data;

    private Boolean success;

    public ResponseHTTP() {
    }

    public ResponseHTTP(int status, String message, Object data, Boolean success) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
