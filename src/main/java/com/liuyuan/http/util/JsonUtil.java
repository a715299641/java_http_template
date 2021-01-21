package com.liuyuan.http.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;



import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public final class JsonUtil {

	final static SerializerFeature[] STANDARD_FEATURES = new SerializerFeature[] { 
		SerializerFeature.DisableCircularReferenceDetect
	};
	
	private static final FastJsonUtil FJU = new FastJsonUtil();
	
	/**
	 * TODO 目前因为fastjson丢复杂的json时，使用了"$ref"无法正常解析，因此这里禁用了这个优化
	 * 如果fastjson修复了，则应该立即启用
	 * 
	 * @param object
	 * @return
	 */
	public static String seriazileAsString(Object object) {
		return FJU.seriazileAsString(object, STANDARD_FEATURES);
	}
	
	/**
	 * 标准的序列化接口
	 */
	public static String seriazileAsStringWithStandard(Object object) {
		return FJU.seriazileAsString(object, STANDARD_FEATURES);
	}
	
	public static <T> T deserializeAsObject(String jsonString, Type clazz) {
		return FJU.deserializeAsObject(jsonString, clazz);
	}
	
	public static <T> T deserializeAsObject(String jsonString, Class<T> clazz) {
		return FJU.deserializeAsObject(jsonString, clazz);
	}
	
	public static <T> T deserializeAsObject(String jsonString, TypeReference<T> clazz) {
		return FJU.deserializeAsObject(jsonString, clazz);
	}


	public static Object jsonObjectAsObjectType(JSONObject object, String type) throws Throwable {
		return JsonUtil.deserializeAsObject(object.toJSONString(), Class.forName(type)) ;
	}
	
	
	/**
	 * 将一个对象，转换成对象
	 * @param obj 只能是Object或者Map，不能是数组类型
	 * @return
	 */
	public static Map<String, Object> toJsonMap(Object obj){
		if(null == obj)
		   return null;
		String objString = com.alibaba.fastjson.JSON.toJSONString(obj);
		return com.alibaba.fastjson.JSON.parseObject(objString, getType());
	}

	/**
	 * 将一个对象，转换成Map String
	 * @param obj 只能是Object或者Map，不能是数组类型
	 * @return
	 */
	public static Map<String, String> toJsonMapString(Object obj){
		if(null == obj)
			return null;
		String objString = com.alibaba.fastjson.JSON.toJSONString(obj);
		return com.alibaba.fastjson.JSON.parseObject(objString, getTypeString());
	}

	private static Type getTypeString(){
		return new TypeReference<Map<String, String>>(){}.getType();
	}
	
	public static Map<String, Object> toJsonMap(String s){
		if(null == s)
		   return null;
		return com.alibaba.fastjson.JSON.parseObject(s, getType());
	}
	
	private static Type getType(){
		return new TypeReference<Map<String, Object>>(){}.getType();
	}
	
	
	/**
	 * 扩展fastjson序列化
	 */
	private static class FastJsonUtil{
		
		/**
		 * java-object as json-string
		 * 
		 * @param object
		 * @return
		 */
		public String seriazileAsString(Object object, SerializerFeature ... features) {
			if (null == object) {
				return StringHelper.EMPTY;
			}
			try {
				return JSON.toJSONString(object, features);
			} catch (Exception ex) {
				return null;
			}
		}

		/**
		 * json-string to java-object
		 * 
		 * @param str
		 * @return
		 */
		public <T> T deserializeAsObject(String jsonString, Type clazz) {
			if (jsonString == null || clazz == null) {
				return null;
			}
			try {
				return JSON.parseObject(jsonString, clazz);
			} catch (Exception ex) {
				return null;
			}
		}
		
		public <T> T deserializeAsObject(String jsonString, TypeReference<T> clazz) {
			if (jsonString == null || clazz == null) {
				return null;
			}
			try {
				return JSON.parseObject(jsonString, clazz);
			} catch (Exception ex) {
				return null;
			}
		}
		
		public <T> T deserializeAsObject(String jsonString, Class<T> clazz) {
			if (jsonString == null || clazz == null) {
				return null;
			}
			try {
				return JSON.parseObject(jsonString, clazz);
			} catch (Exception ex) {
				return null;
			}
		}
	}
}
