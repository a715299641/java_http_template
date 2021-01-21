package com.liuyuan.http;

import com.liuyuan.http.req.EtcOrderBo;
import com.liuyuan.http.req.EtcOrderQueryRequest;
import com.liuyuan.http.service.impl.ThridApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
@Controller
public class TestController {


    @Autowired
    private ThridApiServiceImpl thridApiService;

    @GetMapping("/test")
    @ResponseBody
    public void test() throws Throwable {
        EtcOrderQueryRequest request = new EtcOrderQueryRequest();
        request.setPage(1L);
        request.setSize(50L);
        EtcOrderBo etcOrderBo = thridApiService.sendApiRequest(request);
        EtcOrderBo etcOrderBo1 = thridApiService.sendTencentRequest(request);
    }
}