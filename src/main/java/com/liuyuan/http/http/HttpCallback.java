package com.liuyuan.http.http;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public interface HttpCallback<P,V> {

	
	/**
	 * 该回调函数可能返回值,但返回值无法确定,
	 * 因此这里使用了泛型由调用者确定具体返回值的类型
	 * 
	 * @param param
	 * @return
	 * @throws Throwable 
	 */
	 V doCallback(P param) throws Throwable;
	
}
