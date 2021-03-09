package com.liuyuan.http.http;


import com.liuyuan.http.api.ApiHttpClientOperation;
import com.liuyuan.http.api.ThirdApiHttpTask;
import com.liuyuan.http.api.ThirdApiResult;
import com.liuyuan.http.exception.DefaultHttpResponseException;
import com.liuyuan.http.util.JsonUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;



/**
 * @Author: ly
 * @Data: 2018/12/29 11:16
 *
 */



public class BaseHttpClientOperation extends AbstractHttpClientOperation implements InitializingBean, BeanFactoryAware {

    public HttpResult httpResult;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static final String DEFAULT_HTTPCLIENT_FACTORY_BEAN_KEY = "httpClientFactoryBean";

    public   String httpClientFactoryBeanKey = DEFAULT_HTTPCLIENT_FACTORY_BEAN_KEY;


    public   HttpClientFactoryBean httpClientFactoryBean;

    public  BeanFactory beanFactory;

    public   RequestConfig defaultRequestConfig;

    public void setHttpResult(HttpResult httpResult) {
        this.httpResult = httpResult;
    }

    public void setHttpClientFactoryBeanKey(String httpClientFactoryBeanKey) {
        this.httpClientFactoryBeanKey = httpClientFactoryBeanKey;
    }


    public void setHttpClientFactoryBean(HttpClientFactoryBean httpClientFactoryBean) {
        this.httpClientFactoryBean = httpClientFactoryBean;
    }



    public void setDefaultRequestConfig(RequestConfig defaultRequestConfig) {
        this.defaultRequestConfig = defaultRequestConfig;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            System.out.println(1);
            httpClientFactoryBean = beanFactory.getBean(httpClientFactoryBeanKey, HttpClientFactoryBean.class);
            defaultRequestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(httpClientFactoryBean.getConnectionRequestTimeout())
                    .setConnectTimeout(httpClientFactoryBean.getConnectTimeout())
                    .setSocketTimeout(httpClientFactoryBean.getSocketTimeout()).setRedirectsEnabled(true).build();
        } catch (BeansException be) {
            logger.error("[" + httpClientFactoryBeanKey + "] not in the spring container.");
            throw be;
        }
    }

    /**
     * 业务执行
     */
    @Override
    public <T, P> T exec(final HttpClientCallback<T> callback, final HttpTask<P> task) throws Throwable {
        return doCallbackWhenLockFailedToRetry(new Callback<T>() {
            @Override
            public T doCallback() throws Throwable {
                try {
                    CloseableHttpClient httpClient = httpClientFactoryBean.getHttpClient(task.getRequestConfig() == null ? defaultRequestConfig : task.getRequestConfig());

                    return callback.doCallback(httpClient);
                } catch (Exception e) {
                    throw e;
                }
            }
        }, task);
    }


    @Override
    public <T, P> T doSendPostBasicRequest(CloseableHttpClient httpClient, HttpTask<P> task,
                                           HttpResponseCallback<T> callback) throws Throwable {
        return null;
    }

    @Override
    public <T, P> T doSendGetBasicRequest(CloseableHttpClient httpClient, HttpTask<P> task,
                                          HttpResponseCallback<T> callback) throws Throwable {
        return null;
    }

    @Override
    public <T, P> T doSendPostRequest(CloseableHttpClient httpClient, final HttpTask<P> task) throws Throwable {
        return doSendPostBasicRequest(httpClient, task, new HttpResponseCallback<T>() {
            @Override
            public T doCallback(CloseableHttpResponse response) throws Throwable {
                String retStr = EntityUtils.toString(response.getEntity(), HttpClientConstant.UTF_8);
                    logger.debug("doSendPostRequest request data:{}; response data: {}",
                            JsonUtil.seriazileAsString(task), retStr);
                return httpResult.convertTenCentLoaction(retStr, task.getClazz());
            }
        });
    }

    @Override
    public <T, P> T doSendGetRequest(CloseableHttpClient httpClient, final HttpTask<P> task) throws Throwable {
        return doSendGetBasicRequest(httpClient, task, new HttpResponseCallback<T>() {
            @Override
            public T doCallback(CloseableHttpResponse response) throws Throwable {
                String retStr = EntityUtils.toString(response.getEntity(), HttpClientConstant.UTF_8);
                    logger.debug("doSendGetRequest request data:{}; response data: {}",
                            JsonUtil.seriazileAsString(task), retStr);
                return httpResult.convertTenCentLoaction(retStr, task.getClazz());
            }
        });
    }



    @Override
    public int getRetryMaxTimes() {
        return httpClientFactoryBean.getBusiRetry();
    }

    @Override
    public boolean isBizRetry(DefaultHttpResponseException e, Object obj) {
        return false;
    }

    @Override
    public <T> boolean retryCondition(T result) {
        return false;
    }
}
