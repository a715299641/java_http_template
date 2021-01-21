package com.liuyuan.http.http;

/**
 * 定义http操作接口
 * @author liuyuan
 *
 */
public interface HttpClientOperation {
	
	/**
	 * POST
	 * @param task
	 * @throws Throwable
	 */
	public <T, P> T doPost(HttpTask<P> task)throws Throwable;
	/**
	 * GET
	 * @param task
	 * @return
	 * @throws Throwable
	 */
	public <T, P> T doGet(HttpTask<P> task)throws Throwable;



	
}
