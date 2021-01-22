package com.liuyuan.http.api;


import com.liuyuan.http.exception.DefaultHttpResponseException;
import com.liuyuan.http.http.*;
import com.liuyuan.http.util.JAXBUtils;
import com.liuyuan.http.util.JsonUtil;
import com.liuyuan.http.util.StringHelper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: ly
 * @Data: 2018/12/29 11:16
 */
public class ApiHttpClientOperation extends AbstractHttpClientOperation
        implements InitializingBean, BeanFactoryAware {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static final String DEFAULT_HTTPCLIENT_FACTORY_BEAN_KEY = "httpClientFactoryBean";

    private static String httpClientFactoryBeanKey = DEFAULT_HTTPCLIENT_FACTORY_BEAN_KEY;


    private static HttpClientFactoryBean httpClientFactoryBean;

    private BeanFactory beanFactory;

    private static RequestConfig defaultRequestConfig;


    public void setHttpClientFactoryBeanKey(String httpClientFactoryBeanKey) {
        ApiHttpClientOperation.httpClientFactoryBeanKey = httpClientFactoryBeanKey;
    }

    public void setHttpClientFactoryBean(HttpClientFactoryBean httpClientFactoryBean) {
        ApiHttpClientOperation.httpClientFactoryBean = httpClientFactoryBean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void setDefaultRequestConfig(RequestConfig defaultRequestConfig) {
        ApiHttpClientOperation.defaultRequestConfig = defaultRequestConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
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
        task.setUrl(getTenCentUrl((ThirdApiHttpTask<P>) task));
        HttpGet httpGet = new HttpGet(/*task.getData() != null
                ? StringHelper.concatUri(task.getUrl(), StringHelper.parseURLPair(task.getData()))
                : */task.getUrl());
//        if (task.getContentType() != null) {
//            httpGet.setHeader(HttpClientConstant.HeaderType.CONTENT_TYPE_KEY.getType(),
//                    task.getContentType().getMimeType());
//        }
        try {
            return httpClient.execute(httpGet, callback, task.getContext());
        } catch (Throwable e) {
            httpGet.abort();
            throw e;
        }
    }

    @Override
    public <T, P> T doSendPostRequest(CloseableHttpClient httpClient, final HttpTask<P> task) throws Throwable {
        return doSendPostBasicRequest(httpClient, task, new HttpResponseCallback<T>() {
            @Override
            public T doCallback(CloseableHttpResponse response) throws Throwable {
                String retStr = EntityUtils.toString(response.getEntity(), HttpClientConstant.UTF_8);
//                if (logger.isDebugEnabled()) {
                    logger.debug("doSendPostRequest request data:{}; response data: {}",
                            JsonUtil.seriazileAsString(task), retStr);
//                }
                return ThirdApiResult.convertTenCentLoaction(retStr, task.getClazz());
            }
        });
    }

    @Override
    public <T, P> T doSendGetRequest(CloseableHttpClient httpClient, final HttpTask<P> task) throws Throwable {
        return doSendGetBasicRequest(httpClient, task, new HttpResponseCallback<T>() {
            @Override
            public T doCallback(CloseableHttpResponse response) throws Throwable {
                String retStr = EntityUtils.toString(response.getEntity(), HttpClientConstant.UTF_8);
//                if (logger.isDebugEnabled()) {
                    logger.debug("doSendGetRequest request data:{}; response data: {}",
                            JsonUtil.seriazileAsString(task), retStr);
//                }
                return ThirdApiResult.convertTenCentLoaction(retStr, task.getClazz());
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
}
