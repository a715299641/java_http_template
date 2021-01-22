package com.liuyuan.http.service;

import com.liuyuan.http.req.EtcOrderBo;
import com.liuyuan.http.req.EtcOrderQueryRequest;
import com.liuyuan.http.req.PingData;


/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public interface TencentApiService {


    EtcOrderBo sendTencentRequest(EtcOrderQueryRequest req) throws Throwable;
}
