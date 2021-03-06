package com.liuyuan.http.service.impl;


import com.liuyuan.http.api.ThirdApiHttpTask;
import com.liuyuan.http.constant.BusiConstant;
import com.liuyuan.http.http.HttpClientTemplate;
import com.liuyuan.http.req.EtcOrderBo;
import com.liuyuan.http.req.EtcOrderQueryRequest;
import com.liuyuan.http.req.PingData;
import com.liuyuan.http.service.TencentApiService;
import com.liuyuan.http.service.ThridApiService;
import com.liuyuan.http.util.JsonUtil;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
@Service("tencentServiceImpl")
public class TencentServiceImpl implements TencentApiService {


    @Autowired
    private HttpClientTemplate tencentHttpClientTemplate;


    @Override
    public PingData sendTencentRequest(EtcOrderQueryRequest req) throws Throwable {
        ThirdApiHttpTask<String> task = new ThirdApiHttpTask<>();
        task.setUrl(BusiConstant.URL_2);
        task.setClazz(PingData.class);
        task.setData(JsonUtil.seriazileAsString(req));
        task.setContentType(ContentType.APPLICATION_JSON);
        return tencentHttpClientTemplate.doPost(task);
    }

}
