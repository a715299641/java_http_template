package com.liuyuan.http.http;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class HttpClientConstant {
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String UTF_8 = "UTF-8";
	public static final String ISO8859_1 = "iso8859-1";
	public static final String SHA_1 = "SHA-1";



	public static enum HeaderType {

		CONTENT_TYPE_KEY("Content-Type");

		private String type;

		private HeaderType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public static enum HttpContentType {
		KEY_Content_JSON("application/json"), KEY_Content_XML("application/xml"), KEY_Content_PLAIN("text/plain");

		private String type;

		private HttpContentType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}
}
