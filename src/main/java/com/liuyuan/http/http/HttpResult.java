package com.liuyuan.http.http;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class HttpResult {

	/**
     * 返回码
     */
    private int errcode;
    /**
     * 返回信息
     */
    private String errmsg;

    public HttpResult() {
	}

	public HttpResult(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
