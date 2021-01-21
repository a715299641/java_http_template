package com.liuyuan.http.http;

/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class LocationHttpResult {

    /**
     * 返回码
     */
    private int status;
    /**
     * 返回信息
     */
    private String message;

    public LocationHttpResult() {
    }

    public LocationHttpResult(int status, String message) {
        this.status = status;
        this.message = message;
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
}
