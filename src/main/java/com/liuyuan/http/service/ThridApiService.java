package com.liuyuan.http.service;

import com.liuyuan.http.req.EtcOrderBo;
import com.liuyuan.http.req.EtcOrderQueryRequest;
import com.liuyuan.http.req.PingData;


/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public interface ThridApiService {

    /**
     * 提供由坐标到坐标所在位置的文字描述的转换。输入坐标返回地理位置信息和附近poi列表。
     * @param req
     * @return
     */
    PingData  sendApiRequest(EtcOrderQueryRequest req) throws Throwable;


    PingData  sendApiRequest1(EtcOrderQueryRequest req) throws Throwable;


}
