package com.liuyuan.http;

import com.liuyuan.http.req.EtcOrderBo;
import com.liuyuan.http.req.EtcOrderQueryRequest;
import com.liuyuan.http.req.PingData;
import com.liuyuan.http.service.impl.TencentServiceImpl;
import com.liuyuan.http.service.impl.ThridApiServiceImpl;
import com.liuyuan.http.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
@Controller
public class TestController {


    @Autowired
    private ThridApiServiceImpl thridApiService;
    @Autowired
    private TencentServiceImpl tencentService;

    @GetMapping("/test")
    @ResponseBody
    public void test() throws Throwable {
        EtcOrderQueryRequest request = new EtcOrderQueryRequest();
        request.setPage(1L);
        request.setSize(50L);
        EtcOrderBo pingData = thridApiService.sendApiRequest(request);
        System.out.println(JsonUtil.seriazileAsString(pingData));

    }

    @GetMapping("/test1")
    @ResponseBody
    public void test1() throws Throwable {
        EtcOrderQueryRequest request = new EtcOrderQueryRequest();
        request.setPage(1L);
        request.setSize(50L);
        PingData pingData = thridApiService.sendApiRequest1(request);
        System.out.println(JsonUtil.seriazileAsString(pingData));
        System.out.println(1);
    }

    @GetMapping("/test2")
    @ResponseBody
    public void test2() throws Throwable {
        EtcOrderQueryRequest request = new EtcOrderQueryRequest();
        request.setPage(1L);
        request.setSize(50L);
        PingData etcOrderBo1 = tencentService.sendTencentRequest(request);
        System.out.println(2);
    }

    @PostMapping("/test3")
    @ResponseBody
    public EtcOrderBo test3(@RequestBody EtcOrderQueryRequest etcOrderQueryRequest) throws Throwable {
        EtcOrderBo etcOrderBo = new EtcOrderBo();
        etcOrderBo.setCompanyId(1L);
        etcOrderBo.setActualDestAddr("destAddr");
        etcOrderBo.setDestAddr("DestAddr");
        etcOrderBo.setEndTime(new Date());
        etcOrderBo.setId(1L);
        etcOrderBo.setExternalId("externalId");
        etcOrderBo.setFee(1L);
        etcOrderBo.setInvoiceStatus("invoice");
        etcOrderBo.setNotifyStatus("notify");
        return etcOrderBo;
    }
}
