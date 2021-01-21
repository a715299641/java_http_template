package com.liuyuan.http.api;


import com.liuyuan.http.http.LocationHttpResult;
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
        return null;
    }

    public enum TenCentResultEnum {
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

        private TenCentResultEnum(int code, String msg) {
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

        public static TenCentResultEnum getTenCentResultEnumByCode(int code) {
            try {
                return TenCentResultEnum.valueOf(code >= 0 ? "EX_" + code : "EX__" + Math.abs(code));
            } catch (Exception e) {
                return EX_1;
            }
        }
    }
}
