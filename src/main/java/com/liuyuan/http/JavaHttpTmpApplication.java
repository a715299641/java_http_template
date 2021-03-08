package com.liuyuan.http;

import com.liuyuan.http.api.ApiHttpClientOperation;
import com.liuyuan.http.api.TencentApiResult;
import com.liuyuan.http.api.TencentHttpClientOperation;
import com.liuyuan.http.api.ThirdApiResult;
import com.liuyuan.http.http.HttpClientFactoryBean;
import com.liuyuan.http.http.HttpClientTemplate;
import com.liuyuan.http.service.impl.ThridApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
@SpringBootApplication
public class JavaHttpTmpApplication {
    @Autowired
    private ThridApiServiceImpl thridApiService;

    public static void main(String[] args) {
        SpringApplication.run(JavaHttpTmpApplication.class, args);
    }

    @Bean(name = "apiHttpClientTemplate")
    public HttpClientTemplate getApiHttpClientTemplate() {
        HttpClientTemplate template = new HttpClientTemplate();
        ApiHttpClientOperation apiHttpClientOperation = getApiHttpClientOperation();
        template.setHttpClientOperation(apiHttpClientOperation);
        return template;
    }

    @Bean
    public ApiHttpClientOperation getApiHttpClientOperation() {
        ApiHttpClientOperation operation = new ApiHttpClientOperation();
        operation.setHttpClientFactoryBeanKey("apiHttpClientFactoryBean");
        operation.setHttpResult(getThirdApiResult());
        return operation;
    }

    @Bean(name = "apiHttpClientFactoryBean")
    public HttpClientFactoryBean getHttpClientFactoryBean() {
        HttpClientFactoryBean factoryBean = new HttpClientFactoryBean();
        factoryBean.setMaxTotal(1);
        factoryBean.setSocketTimeout(20000);
        factoryBean.setRetry(4);
        return factoryBean;
    }

    @Bean
    public  ThirdApiResult  getThirdApiResult() {
        ThirdApiResult result = new ThirdApiResult();
        return result;
    }



    @Bean(name = "tencentHttpClientTemplate")
    public HttpClientTemplate getTencentHttpClientTemplate() {
        HttpClientTemplate template = new HttpClientTemplate();
        TencentHttpClientOperation apiHttpClientOperation = getTencentHttpClientOperation();
        template.setHttpClientOperation(apiHttpClientOperation);
        return template;
    }

    @Bean
    public TencentHttpClientOperation getTencentHttpClientOperation() {
        TencentHttpClientOperation operation = new TencentHttpClientOperation();
        operation.setHttpClientFactoryBeanKey("tencentHttpClientFactoryBean");
        operation.setHttpResult(getTencentApiResult());
        return operation;
    }

    @Bean(name = "tencentHttpClientFactoryBean")
    public HttpClientFactoryBean getTencentHttpClientFactoryBean() {
        HttpClientFactoryBean factoryBean = new HttpClientFactoryBean();
        factoryBean.setMaxTotal(1);
        factoryBean.setRetry(5);
        factoryBean.setSocketTimeout(20000);
        return factoryBean;
    }

    @Bean
    public TencentApiResult getTencentApiResult() {
        TencentApiResult result = new TencentApiResult();
        return result;
    }

}
