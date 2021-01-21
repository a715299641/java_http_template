package com.liuyuan.http.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.protocol.HttpContext;

import java.lang.reflect.Type;
import java.util.Map;

/**
 *
 * Http请求任务
 *
 * @author liuyuan
 */
public class HttpTask<T> {

	/**
	 * 请求url
	 */
	private String url;
	/**
	 * 请求上下文
	 */
	private HttpContext context;
	/**
	 * 请求数据
	 */
	private T data;
	/**
	 * 请求内容类型
	 */
	private ContentType contentType;
	/**
	 * 返回的类型
	 */
	private Type clazz;
	/**
	 * 上传entity
	 */
	private HttpEntity httpEntity;
	/**
	 * 上传参数
	 */
	private Map<String, String> contentBodyMap;
	
	private String fileName;
	/**
	 * 媒体byte
	 */
	private byte[] media;
	
	private HttpMultipartMode mode;
	
	/**
	 * 自定义http client 请求参数
	 */
	private RequestConfig requestConfig;

	public HttpMultipartMode getMode() {
		return mode;
	}

	public void setMode(HttpMultipartMode mode) {
		this.mode = mode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public RequestConfig getRequestConfig() {
		return requestConfig;
	}

	public void setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
	}

	public HttpEntity getHttpEntity() {
		return httpEntity;
	}

	public void setHttpEntity(HttpEntity httpEntity) {
		this.httpEntity = httpEntity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpContext getContext() {
		return context;
	}

	public void setContext(HttpContext context) {
		this.context = context;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public Type getClazz() {
		return clazz;
	}

	public void setClazz(Type clazz) {
		this.clazz = clazz;
	}

	public Map<String, String> getContentBodyMap() {
		return contentBodyMap;
	}

	public void setContentBodyMap(Map<String, String> contentBodyMap) {
		this.contentBodyMap = contentBodyMap;
	}

	public byte[] getMedia() {
		return media;
	}

	public void setMedia(byte[] media) {
		this.media = media;
	}
}
