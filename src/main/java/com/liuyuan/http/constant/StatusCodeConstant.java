package com.liuyuan.http.constant;

/**
 * 系统错误代码定义
 * 新的错误区间开始必须以当前已定义的最大区间开始，预留500截至，禁止在已定义区间中新增加区间。
 * 区间内可以自行划分起始，不同类型错误需要归类描述
 *
 * @author liuyuan
 */
public enum StatusCodeConstant {

	//http相关450-469
	HTTP_RESPONSE_ERR(450, "HTTP响应错误！"),
 ;


	private int code;

	private String desc;

	private StatusCodeConstant(int code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static StatusCodeConstant getInstanceByCode(int code) {
		StatusCodeConstant[] values = StatusCodeConstant.values();
		for (StatusCodeConstant item : values) {
			if(item.getCode() == code){
			   return item;
			}
		}
		return null;
	}

}