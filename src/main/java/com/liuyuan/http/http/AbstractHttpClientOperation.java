package com.liuyuan.http.http;


import com.liuyuan.http.exception.DefaultHttpResponseException;
import com.liuyuan.http.util.JsonUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 抽象httpclient操作类实现，实现了共有的操作， 有差异的操作留给子类去实现
 *
 * @author liuyuan
 */
public abstract class AbstractHttpClientOperation implements HttpClientOperation {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** 默认的请求类型 */
	private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.APPLICATION_JSON;

	protected ContentType getContentType() {
		return DEFAULT_CONTENT_TYPE;
	}

	@Override
	public <T, P> T doPost(final HttpTask<P> task) throws Throwable {
		return exec(new HttpClientCallback<T>() {
			@Override
			public T doCallback(CloseableHttpClient client) throws Throwable {
				if (task == null) {
					return null;
				}
				return doSendPostRequest(client, task);
			}
		}, task);
	}

	@Override
	public <T, P> T doGet(final HttpTask<P> task) throws Throwable {
		return exec(new HttpClientCallback<T>() {
			@Override
			public T doCallback(CloseableHttpClient client) throws Throwable {
				if (task == null) {
					return null;
				}
				return doSendGetRequest(client, task);
			}
		}, task);
	}

	/**
	 * 实现类负责实现具体的发送请求
	 *
	 * @param httpClient
	 * @param tasks
	 * @return
	 * @throws Throwable
	 */
	public abstract <T, P> T doSendGetBasicRequest(CloseableHttpClient httpClient, HttpTask<P> task, HttpResponseCallback<T> callback)
			throws Throwable;
	/**
	 * 实现类负责实现具体的发送请求
	 *
	 * @param httpClient
	 * @param tasks
	 * @return
	 * @throws Throwable
	 */
	public abstract <T, P> T doSendPostBasicRequest(CloseableHttpClient httpClient, HttpTask<P> task, HttpResponseCallback<T> callback)
			throws Throwable;




	/**
	 * 实现类负责发送get请求
	 * 
	 * @param httpClient
	 * @param tasks
	 * @return
	 * @throws Throwable
	 */
	public abstract  <T, P> T doSendGetRequest(CloseableHttpClient httpClient, HttpTask<P> task)
			throws Throwable;
	
	/**
	 * 实现类负责发送post请求
	 * 
	 * @param httpClient
	 * @param tasks
	 * @return
	 * @throws Throwable
	 */
	public abstract <T, P> T doSendPostRequest(CloseableHttpClient httpClient, HttpTask<P> task)
			throws Throwable;

	

	/**
	 * 
	 * @param callback
	 * @return
	 */
	public abstract <T, P> T exec(final HttpClientCallback<T> callback, HttpTask<P> task) throws Throwable;

	/**
	 * 重试次数
	 */
	public abstract int getRetryMaxTimes();

	/**
	 * 是否业务重试
	 */
	public abstract boolean isBizRetry(DefaultHttpResponseException e, Object obj);


	/**
	 * 重试回调
	 * @author liuyuan
	 *
	 * @param <T>
	 */
	protected interface Callback<T> {
		T doCallback() throws Throwable;
	}
	
	/**
	 * 进入重试
	 * 
	 * @param callback
	 * @throws HttpResponseErrorMsgException
	 */
	protected <T, V> T doCallbackWhenLockFailedToRetry(Callback<T> callback, Object errorParams) throws Throwable {
		int _retry_times = 0;

		do {
			try {
				T result = callback.doCallback();
				//错误处理之后进行业务重试   默认不重试  如果需要重试  联系liuyuan@kuaihuoyun.com
				if(retryCondition(result)){
					throw new DefaultHttpResponseException(result);
				}

				//返回结果
				return result;
			}
			catch (DefaultHttpResponseException e) {
				//异常处理
				if (errorParams != null) {
					// 以下业务 不重试
					if (!isBizRetry(e, errorParams)) {
						throw e;
					}
				}
				//其他异常处理
			} catch (Throwable e) {
				logger.warn("An error occurred in the http task, \n errorParams: " + errorParams, e);
				//throw e;
				return null;
			}
		} while (++_retry_times <= getRetryMaxTimes());
		// retry fail error log
		return null;
	}

	public abstract  <T> boolean retryCondition(T result);



}
