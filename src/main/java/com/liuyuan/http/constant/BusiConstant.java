package com.liuyuan.http.constant;


public class BusiConstant {
	public static final String ALL = "ALL";


	public static final String URL_1 = "http://127.0.0.1:9001/test3";
	public static final String URL_3 = "http://127.0.0.1:9001/test3";
	public static final String URL_4 = "http://127.0.0.1:8080/v1/dev/ping";
	public static final String URL_2 = "https://vas.wlhyos.dev.kuaihuoyun.com/v1/dev/ping";


	public static enum YesNo {
		NO(0), YES(1);

		private Integer value;

		private YesNo(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

	}






	/**
	 * 符号及字符串枚举值
	 */
	public static enum SymbolEnum {
		/**
		 * 英文点
		 */
		POINT("\\."),
		/**
		 * 负1
		 */
		MINUS_NOE("-1"),
		/**
		 * 负2
		 */
		MINUS_TWO("-2"),
		/**
		 * 逗号
		 */
		COMMA(","),
		/**
		 * 负号
		 */
		MINUS("-"),
		/**
		 * 下划线
		 */
		UNDERLINE("_"),
		/**
		 * 0
		 */
		ZERO("0"),
		/**
		 * 1
		 */
		ONE("1"),

		HTTP_STR("http://"),
		HTTPS_STR("https://"),
		/**
		 * 中国
		 */
		CHINA("中国"),

		/**
		 * 左括号
		 */
		LEFT_PARENTHESIS("("),
		/**
		 * 右括号
		 */
		RIGHT_PARENTHESIS(")"),
		;

		private String value;

		private SymbolEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}


}