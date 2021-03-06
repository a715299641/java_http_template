package com.liuyuan.http.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuyuan.http.constant.BusiConstant;
import com.liuyuan.http.http.LocationHttpResult;
import com.liuyuan.http.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class ThirdApiResult extends LocationHttpResult {

    protected static Logger logger = LoggerFactory.getLogger(ThirdApiResult.class);

    public ThirdApiResult() {

    }

    public ThirdApiResult(int status, String message) {
        super(status, message);
    }
    /**
     * 文本转换
     * @param data
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertTenCentLoaction(String data, Type clazz) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }

        // 报文中包含 "status":0
        if (data.contains("\"errcode\": 0,")) {
            return JsonUtil.deserializeAsObject(data, clazz);
        } else if (data.contains("\"errcode\":")) {
            ThirdApiResult tenCentResult = JsonUtil.deserializeAsObject(data, ThirdApiResult.class);
            ThirdApiResultEnum tenCentResultEnum = ThirdApiResultEnum.getThirdApiResultEnumByCode(tenCentResult.getStatus());
            tenCentResult.setMessage((tenCentResultEnum.getCode() == ThirdApiResultEnum.EX_1.getCode())
                    ? (ThirdApiResultEnum.EX_1.getMsg() + " : " + data) : tenCentResultEnum.getMsg());
            return (T) tenCentResult;
        }else {
            return JsonUtil.deserializeAsObject(data, clazz);
        }
    }

    public enum ThirdApiResultEnum {
        EX__1(-1,"系统繁忙"),
        EX_0(0,"请求成功"),
        EX_7(110,"请求来源未被授权，此次请求无来源信息"),
        EX_2(301,"缺少必要字段key"),
        EX_3(306,"address和location必须且只能存在一个"),
        EX_4(360,"get_poi参数只能是0或1"),
        EX_5(310,"location格式错误"),
        EX_6(311,"key格式错误"),
        EX_1(503,"请求量超出限制");

        private int code;

        private String msg;

        private ThirdApiResultEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public static ThirdApiResultEnum getThirdApiResultEnumByCode(int code) {
            try {
                return ThirdApiResultEnum.valueOf(code >= 0 ? "EX_" + code : "EX__" + Math.abs(code));
            } catch (Exception e) {
                return EX_1;
            }
        }
    }
}
