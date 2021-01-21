package com.liuyuan.http.exception;


import com.liuyuan.http.constant.StatusCodeConstant;
import com.liuyuan.http.util.JsonUtil;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */

@SuppressWarnings("serial")
public class DefaultHttpResponseException extends HttpResponseException {

	private Object msgObj;

	private static StatusCodeConstant statusCodeConstant = StatusCodeConstant.HTTP_RESPONSE_ERR;;

	public DefaultHttpResponseException(StatusCodeConstant statusCode) {
		super(statusCodeConstant.getCode(), "code: " + statusCode.getCode() + ", desc: " + statusCode.getDesc());
	}

	public DefaultHttpResponseException(Object msgObj) {
		super(statusCodeConstant.getCode(), statusCodeConstant.getDesc() + " desc: " + JsonUtil.seriazileAsString(msgObj));
		this.msgObj = msgObj;
	}

	public DefaultHttpResponseException(StatusLine status) {
		super(statusCodeConstant.getCode(), "code: " + status.getStatusCode() + ", desc: " + status.getReasonPhrase());
	}

	public StatusCodeConstant getStatusCodeConstant() {
		return statusCodeConstant;
	}

	public Object getMsgObj() {
		return msgObj;
	}

}
