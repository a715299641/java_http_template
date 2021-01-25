package com.liuyuan.http.api;


import com.liuyuan.http.exception.DefaultHttpResponseException;
import com.liuyuan.http.http.*;
import com.liuyuan.http.util.JAXBUtils;
import com.liuyuan.http.util.JsonUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * @Author: ly
 * @Data: 2018/12/29 11:16
 */
public class ApiHttpClientOperation extends BaseHttpClientOperation {





    /**
     * @param task
     * @return
     * @throws Throwable
     */
    private String getTenCentUrl(ThirdApiHttpTask<?> task) throws Throwable {
        return task.getUrl();
    }

    @Override
    public <T, P> T doSendPostBasicRequest(CloseableHttpClient httpClient, HttpTask<P> task,
                                           HttpResponseCallback<T> callback) throws Throwable {
        System.out.println(httpClientFactoryBeanKey);
        // 获取wechat token url
        task.setUrl(task.getUrl());
        // post 请求
        HttpPost post = new HttpPost(task.getUrl());
        // 设置header ContentType
        if (task.getContentType() == null) {
            task.setContentType(getContentType());
        }
        post.setHeader(HttpClientConstant.HeaderType.CONTENT_TYPE_KEY.getType(), task.getContentType().getMimeType());
        // 设置entity
        if (task.getData() != null) {
            String reqMimeType = task.getContentType().getMimeType();
            // 设置string 类型 请求
            String dataStr = (task.getData() instanceof String) ? String.valueOf(task.getData())
                    : ContentType.APPLICATION_JSON.getMimeType().equals(reqMimeType)
                    ? JsonUtil.seriazileAsString(task.getData())
                    : ContentType.APPLICATION_XML.getMimeType().equals(reqMimeType)
                    ? JAXBUtils.modelToXml(task.getData())
                    : String.valueOf(task.getData());
            // 设置entity
            if (task.getContentType() != null) {
                post.setEntity(new StringEntity(dataStr,
                        ContentType.create(task.getContentType().getMimeType(), HttpClientConstant.UTF_8)));
            } else {
                post.setEntity(new StringEntity(dataStr, ContentType.DEFAULT_TEXT));
            }
        }
        try {
            T execute = httpClient.execute(post, callback, task.getContext());

            return execute;
        } catch (Throwable e) {
            post.abort();
            throw e;
        }
    }

    @Override
    public <T, P> T doSendGetBasicRequest(CloseableHttpClient httpClient, HttpTask<P> task,
                                          HttpResponseCallback<T> callback) throws Throwable {
        System.out.println(httpClientFactoryBeanKey);
        System.out.println(httpClientFactoryBean);
        task.setUrl(getTenCentUrl((ThirdApiHttpTask<P>) task));
        HttpGet httpGet = new HttpGet(/*task.getData() != null
                ? StringHelper.concatUri(task.getUrl(), StringHelper.parseURLPair(task.getData()))
                : */task.getUrl());
//        if (task.getContentType() != null) {
//            httpGet.setHeader(HttpClientConstant.HeaderType.CONTENT_TYPE_KEY.getType(),
//                    task.getContentType().getMimeType());
//        }
        try {
            T execute = httpClient.execute(httpGet, callback, task.getContext());
            return execute;
        } catch (Throwable e) {
            httpGet.abort();
            throw e;
        }
    }


//    @Override
//    public <T, P> T doSendPostRequest(CloseableHttpClient httpClient, final HttpTask<P> task) throws Throwable {
//        return doSendPostBasicRequest(httpClient, task, new HttpResponseCallback<T>() {
//            @Override
//            public T doCallback(CloseableHttpResponse response) throws Throwable {
//                String retStr = EntityUtils.toString(response.getEntity(), HttpClientConstant.UTF_8);
//                logger.debug("doSendPostRequest request data:{}; response data: {}",
//                        JsonUtil.seriazileAsString(task), retStr);
//                return ThirdApiResult.convertTenCentLoaction(retStr, task.getClazz());
//            }
//        });
//    }
//
//    @Override
//    public <T, P> T doSendGetRequest(CloseableHttpClient httpClient, final HttpTask<P> task) throws Throwable {
//        return doSendGetBasicRequest(httpClient, task, new HttpResponseCallback<T>() {
//            @Override
//            public T doCallback(CloseableHttpResponse response) throws Throwable {
//                String retStr = EntityUtils.toString(response.getEntity(), HttpClientConstant.UTF_8);
//                logger.debug("doSendGetRequest request data:{}; response data: {}",
//                        JsonUtil.seriazileAsString(task), retStr);
//                return ThirdApiResult.convertTenCentLoaction(retStr, task.getClazz());
//            }
//        });
//    }



    @Override
    public boolean isBizRetry(DefaultHttpResponseException e, Object obj) {
        return false;
    }
}
