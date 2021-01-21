package com.liuyuan.http.http;

import com.liuyuan.http.exception.DefaultHttpResponseException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * http 响应处理
 * 
 * @author liuyuan
 *
 * @param <V>
 */
public abstract class HttpResponseCallback<V> implements HttpCallback<CloseableHttpResponse, V>, ResponseHandler<V> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public V handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		// 前置检查
		if (response == null || response.getEntity() == null) {
			throw new DefaultHttpResponseException("http response is null!");
		}
		StatusLine statusLine = response.getStatusLine();
		if (!(statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() < 400)) {
			throw new DefaultHttpResponseException(statusLine);
		}

		if (logger.isDebugEnabled()) {
			StringBuffer str = new StringBuffer();
			for (Header header : response.getAllHeaders()) {
				str.append(header.toString() + ";");
			}
			logger.debug(str.toString());
		}
		// 返回结果
		try {
			return this.doCallback((CloseableHttpResponse) response);
		} catch (DefaultHttpResponseException e) {
			//logger.error("wechat http fail. ", e.getMessage());
			throw e;
		} catch (Throwable e) {
			//logger.error("wechat http fail. ", e.getCause());
			throw new DefaultHttpResponseException(e.getMessage());
		}
	}
}
