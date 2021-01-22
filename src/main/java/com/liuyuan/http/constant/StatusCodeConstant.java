package com.liuyuan.http.constant;

/**
 * 系统错误代码定义
 * 新的错误区间开始必须以当前已定义的最大区间开始，预留500截至，禁止在已定义区间中新增加区间。
 * 区间内可以自行划分起始，不同类型错误需要归类描述
 *
 * @author liuyuan
 */
public enum StatusCodeConstant {
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
 * 由系统发出的代码定义
 * 0-99
 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	SYSTEM_UNKNOW_EXCEPTION(0, "系统未知异常，不可预料的异常，发生该类异常标志着一个BUG"),
	RESPONSE_SUCCESS(1, "业务正常被执行，并成功响应完成"),

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
 * 通用
 * 100 - 999
 *  */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//通用错误100-199
	CONTROLLER_RETURN_ROOT(100, "控制器的返回值必须是Root类型!"),
	RECORD_NOT_EXISTS(101,"记录不存在"),
	OPERATION_NOT_SUPPORT(102,"操作类型不支持"),
	CPA_STATE_NAME_REQUIRED(103, "状态名称不能为空"),
	PLATFORM_TYPE_NON_MANAGE(110, "无法使用该用户进行操作！"),
	SAME_OPERATION_IS_IN_PROGRESS(111, "同一操作进行中！"),


	//部分SQL错误  以 83_81_76_   是  (SQL) char的 数字值
	SQL_ERROR_DUPLICATE_KEY(83_81_76_001,"主键/唯一索引冲突"),
	SQL_ERROR_UNHANDLED(83_81_76_999,"未捕获的SQL异常"),
	// Session有关的状态码200 - 299 前端在得到改错误码后会将用户session注销后跳转到登陆页面，请不要轻易抛出该异常
	SESSION_NOT_EXIST_OR_INVALID(200, "Session不存在或已失效"),
	SESSION_INVALID_REQUEST(201, "无效的请求"),
	SESSION_NOT_EXIST_GET_USEROBJ(202, "Session不存在,无法获取用户信息!"),
	SESSION_CONSISTENCY_ERROR(204, "Session和用户id不一致!"),

	//系统数据配置错误300-350
	DATA_CONFIG_ERR(300, "数据配置错误!"),
	PAGE_LIMIT_MUST_GREATER_THAN_ZERO(301, "分页显示记录数必须大于0"),
	//文件相关410 - 419
	FILE_WRONG_STATUS(410,"文件状态不正确,可能已经被删除"),
	FILE_NOT_EXISTS(411,"文件不存在"),
	FILE_EXIST_ALREADY(412,"文件已存在"),
	//短信相关420 - 429
	SENDSMS_NETWORK_EXCEPTION(420, "发送短信网络异常"),
	//消息配置 430 - 439
	MsgCfgNotExists(430,"消息配置不存在"),
	MsgCfgTypeWrong(431,"消息配置类型不正确"),
	MsgCfgEventParamWrong(432,"事件消息配置参数不正确"),
	MsgUsedCantChangeType(433,"消息已被引用，不能修改类型"),
	MsgTargetTypeWrong(434,"消息的目标类型错误"),
	MsgPlatformWrong(435,"消息的平台配置错误"),
	//通用 400 - 450
	//敏感词禁止提交
	SENSITIVE_FORBBIDEN_ERROR(400,"内容存在非法词组，禁止提交"),

	//http相关450-469
	HTTP_RESPONSE_ERR(450, "HTTP响应错误！"),
	//微信http 相关 469-480
    WECHAT_REQUEST_INVALID(454, "微信无效请求！"),
	WECHAT_PARAM_ERR(455, "微信请求参数错误！"),

	//邮件相关
	//邮件模板500 - 520
	MAIL_TEMPLATE_TYPE_NOT_NULL(500, "邮件模板业务类型不能为空"),
	MAIL_TEMPLATE_TYPE_DEFINE_NULL(501, "邮件模板业务类型定义不能为空"),
	MAIL_TEMPLATE_DEFINE_NULL(502, "邮件模板定义为空"),
	MAIL_TEMPLATE_DEFINE_CONTENT_NULL(503, "邮件模板定义内容为空"),
	VALI_CODE_SEND_MAIL_FAILED(504, "发送邮件验证码失败,可能是错误的邮箱地址"),
	TEMPLATE_NAME_REQUIRED(505, "模板名称不能为空"),
	TEMPLATE_TYPE_REQUIRED(506, "模板类型不能为空"),
	TEMPLATE_ID_REQUIRED(507, "引用模板不能为空"),
	CURRENT_USER_NOT_ENOUGH_RIGHTS(508, "没有操作权限"),
	//聊天消息(550-600
	ChatMsgTypeRequired(550, "消息类型不能为空"),
	ChatMsgBusiIdRequired(551, "消息业务id不能为空"),
	ChatMsgContentRequired(552, "消息内容不能为空"),
	ChatMsgContentTooLong(553, "消息内容长度不能超过120个字"),
	//权限600-650
	USER_NOT_ENOUGH_AUTH(600, "用户没有权限"),

	ALIPAY_PAY_FORM_ERR(700, "创建支付宝付款参数错误"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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