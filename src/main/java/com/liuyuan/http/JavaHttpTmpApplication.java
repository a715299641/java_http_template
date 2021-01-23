package com.liuyuan.http;

import com.liuyuan.http.api.ApiHttpClientOperation;
import com.liuyuan.http.api.TencentHttpClientOperation;
import com.liuyuan.http.http.HttpClientFactoryBean;
import com.liuyuan.http.http.HttpClientTemplate;
import com.liuyuan.http.service.impl.ThridApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
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
        ApiHttpClientOperation apiHttpClientOperation = new ApiHttpClientOperation();
        apiHttpClientOperation.setHttpClientFactoryBeanKey("apiHttpClientFactoryBean");
        template.setHttpClientOperation(apiHttpClientOperation);
        return template;
    }

    @Bean(name = "apiHttpClientOperation")
    public ApiHttpClientOperation getApiHttpClientOperation() {
        ApiHttpClientOperation operation = new ApiHttpClientOperation();
        return operation;
    }

    @Bean(name = "apiHttpClientFactoryBean")
    public HttpClientFactoryBean getHttpClientFactoryBean() {
        HttpClientFactoryBean factoryBean = new HttpClientFactoryBean();
        factoryBean.setMaxTotal(100);
        factoryBean.setRetry(1);
        factoryBean.setSocketTimeout(2000);
        return factoryBean;
    }



    @Bean(name = "tencentHttpClientTemplate")
    public HttpClientTemplate getTencentHttpClientTemplate() {
        HttpClientTemplate template = new HttpClientTemplate();
        TencentHttpClientOperation apiHttpClientOperation = new TencentHttpClientOperation();
        apiHttpClientOperation.setHttpClientFactoryBeanKey("tencentHttpClientFactoryBean");
        template.setHttpClientOperation(apiHttpClientOperation);
        return template;
    }

    @Bean(name = "tencentHttpClientOperation")
    public TencentHttpClientOperation getTencentHttpClientOperation() {
        TencentHttpClientOperation operation = new TencentHttpClientOperation();
        return operation;
    }

    @Bean(name = "tencentHttpClientFactoryBean")
    public HttpClientFactoryBean getTencentHttpClientFactoryBean() {
        HttpClientFactoryBean factoryBean = new HttpClientFactoryBean();
        factoryBean.setMaxTotal(100);
        factoryBean.setSocketTimeout(3000);
        return factoryBean;
    }

    @Bean
    public ApplicationRunner runner() throws Throwable {
//        EtcOrderQueryRequest request = new EtcOrderQueryRequest();
//        request.setPage(1L);
//        request.setSize(50L);
//        PingData pingData = thridApiService.sendApiRequest(request);
        return args -> System.out.println(123);
    }
}
