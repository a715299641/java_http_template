package com.liuyuan.http.http;

import java.lang.reflect.Type;

/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public interface HttpResult {
    public   <T> T convertTenCentLoaction(String data, Type clazz);
}
