package com.liuyuan.http.http;

import org.apache.http.impl.client.CloseableHttpClient;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public interface HttpClientCallback<V> extends HttpCallback<CloseableHttpClient,V>{

}
