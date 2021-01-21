package com.liuyuan.http.http;

import org.springframework.beans.factory.InitializingBean;

import static org.springframework.util.Assert.notNull;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class HttpClientTemplate implements InitializingBean, HttpClientOperation{

	private HttpClientOperation httpClientOperation;
	
	@Override
	public <T, P> T doPost(HttpTask<P> task) throws Throwable {
		return httpClientOperation.doPost(task);
	}

	@Override
	public <T, P> T doGet(HttpTask<P> task) throws Throwable {
		return httpClientOperation.doGet(task);
	}
	


	@Override
	public void afterPropertiesSet() throws Exception {
		notNull(httpClientOperation, "Property 'httpClientOperation' is required");
	}

	public void setHttpClientOperation(HttpClientOperation httpClientOperation) {
		this.httpClientOperation = httpClientOperation;
	}

	
}
