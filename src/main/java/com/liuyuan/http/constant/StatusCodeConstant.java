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
	SMS_SEND_FAILED(3, "短信通知发送异常 原因："),
	SMS_GATEWAY_FAILED(4, "短信通知发送异常 原因：可能短信网关连接超时"),
	EMAIL_SEND_FAILED(5, "邮件发送异常"),

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
/**
 * 表单有关
 * 1000 - 1699
 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		FORM_PARAMS_ERROR(1001, "表单参数错误，请检查参数个数、大小写、数据类型等"),
		FORM_VALID_FAILURE(1002, "表单验证失败"),
		FORM_PARAMS_NULL(1003, "表单内容信息为空，请检查表单数据"),
		FORM_REGISTER_THIRD_FAILURE(1004, "表单验证失败：第三方注册时，第三方平台和第三方账号不能为空"),
		FORM_REGISTER_VERIFICATIONCODE_FAILURE(1005, "表单验证失败：手机或邮箱注册时，验证码不能为空"),
		FORM_EDIT_BIND_MOBILE_FAILURE(1006, "此手机号已被绑定不允许修改，请先解绑"),
		FORM_EDIT_BIND_EMAIL_FAILURE(1007, "此邮箱已被绑定不允许修改，请先解绑"),
		FORM_QUERY_START_END(1008,"开始日期不能大于截止日期"),
		FORM_QUERY_IS_NULL(1009,"查询条件不能为空"),
		FORM_VALIDATE_EMAIL_ERROR(1010, "邮箱地址格式错误"),
		FORM_VALIDATE_PHONE_ERROR(1011, "电话号码格式错误"),
		FORM_VALIDATE_WEBSITE_ERROR(1012, "网址格式错误"),
		FORM_VALIDATE_FINISH_MISSION(1013, "您当前尚未完成活动任务，无法提交。"),
		FORM_VALIDATE_INTO_RANK(1014, "您当前不在榜单内，无法提交。"),
		FORM_ACTIVITY_IS_NULL(1015, "表单不存在。"),
		FORM_ACTIVITY_IS_UNSTART(1016, "当前表单尚未到开始填写的时间，无法提交。"),
		FORM_ACTIVITY_IS_END(1017, "填写表单时间已过期，无法提交。"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 其他（无法归类的放入该类别，禁止将已归类的放入该区间）
* 1700 - 1999
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		WEB_SYSTEM_CONTENT_DUPLICATION(1700, "页面类型已存在"),
		WEB_SYSTEM_CONTENT_NOT_EXISTS(1701, "页面不存在,请刷新后再试"),
		ACTOR_BUSINESSLICENCE_NAME_REQUIRED(1702, "营业执照名称不能为空"),
		ACTOR_BUSINESSLICENCE_TYPE_REQUIRED(1703, "营业执照类型不能为空"),
		CPA_ITEM_ID_NOT_NULL(1704, "配置项定义不能为空"),
		CPA_PAGEFROM_NOT_NULL(1705, "必须指定页面来源"),
		WEB_SYSTEM_CONTENT_NOT_NULL(1706, "页面内容不能为空"),
		FILE_UPLOAD_ERROR(1707, "图片上传异常"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 系统配置
* 2000 - 2299
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		AUTH_ERROR_MENU(2001,"权限挂单不存在"),
		AUTH_ERROR_SUBSYSTEM(2002,"子系统不一致"),
		AUTH_ERROR_ITEM(2003,"权限项不存在"),
		AUTH_ERROR_MENUITEM(2004,"有关联权限项存在"),
		//AUTH_ERROR_USERROLE_EXIST(2005, "用户角色已经存在"),
		//AUTH_ERROR_USERROLE_NOTEXIST(2006, "用户角色不存在"),
		AUTH_ERROR_ITEMROLE_EXIST(2007, "角色权限项已存在"),
		AUTH_ERROR_MUNU_PARANT(2008,"菜单的父菜单不存在"),
		AUTH_ERROR_MUNU_SON(2009,"菜单存在子菜单挂载，不能删除"),
		AUTH_ERROR_ROLE(2010, "角色不存在"),
		SENSITIVE_NOT_EXIST(2011, "不存在该敏感词"),
		RECORD_NOT_EXIST(2012, "目标数据不存在"),
		USER_CONFIG_ITEM_NOT_NULL(2013, "用户配置项不能为空"),
		ITEM_DEF_PK_NOT_EXIST(2014, "该项字典定义的父级不存在"),
		ITEM_DEFINE_KEY_REQUIRED(2015, "配置项key不能为空"),
		ITEM_DEFINE_EXISTS(2016, "该项字典定义已存在"),
		ITEM_DEFINE_REQUIRED(2017, "该项数据的字典定义不存在"),
		ITEM_VALUE_EXIST(2018, "该项字典明细已存在"),
		ITEM_VALUE_NOT_EXISTS(2019, "该项数据字典明细不存在"),
		ITEM_VALUE_PK_NOT_EXISTS(2010, "该项字典明细的父级不存在"),
		ITEM_DEF_HAS_CHILDRED(2020, "该项字典定义已有明细"),
		ITEM_VALUE_HAS_CHILDRED(2021, "该项字典明细仍有子级"),

		SYS_CFG_EXISTED(2022, "该项系统配置已存在"),

		NOT_UPDATE_RECORD(2023, "记录状态不允许此修改"),
		//NOT_DELETE_RECORD(2024, "记录状态不允许删除记录"),
		PUSH_NEED_CYCLESECOND(2025, "周期性推送时backpushCycleSecond字段必须填大于0的值"),
		PUSH_CYCLETYPE(2026, "BackpushCycleType错误"),
		CURRENT_USER_NOT_VIEW_RIGHTS(2027, "没有查看权限"),
		CURRENT_USER_NOT_EDIT_RIGHTS(2028, "没有编辑权限"),
		CURRENT_USER_NOT_DELETE_RIGHTS(2029, "没有删除权限"),

		APP_VERSION_NOT_EXISTED(2100,"终端版本号不存在"),
		VERSION_NOT_EXISTED(2101,"版本号不存在"),
		AUTH_CORP_PACK_ROLE_NOT_EXISTED(2102,"公司套餐不存在"),
		AUTH_CORP_PACK_ROLE_EXISTED(2102,"公司套餐已存在"),
		T_PACKAGE_ROLE_NOT_EXISTED(2103,"公司套餐授权不存在"),
		T_PACKAGE_ROLE_EXISTED(2104,"公司套餐授权已存在"),
		T_PACKAGE_NOT_EXISTED(2105,"套餐模板不存在"),
		CORP_PACKAGE_NOT_EXISTED(2106,"公司套餐不存在"),
		CORP_PACKAGE_EXISTED(2107,"公司套餐已存在"),
		CORP_PACKAGE_AUTH_NOT_EXISTED(2108,"公司套餐授权不存在"),
		CORP_PACKAGE_AUTH_EXISTED(2109,"公司套餐授权已存在"),
		CSS_PUBLIC_NUMBER_NOT_EXISTED(2110,"公众号不存在"),
		CSS_PUBLIC_NUMBER_EXISTED(2111,"公众号已存在"),
		T_APP_CONFIG_NOT_EXISTED(2112,"app应用注册不存在"),
		T_BUSI_CODE_NOT_EXISTED(2113,"业务(事件)代码定义不存在"),
		T_BUSI_CODE_EXISTED(2114,"业务(事件)代码定义已存在"),
		T_BUSIMESSAGE_IMAGETEXT_NOT_EXISTED(2115,"业务图文消息配置不存在"),
		T_BUSINESS_NOTIFY_NOT_EXISTED(2116,"业务通知配置不存在"),
		T_BUSINESS_NOTITY_DETAIL_NOT_EXISTED(2117,"业务通知配置扩展表不存在"),
		T_BUSINESS_NOTITY_DETAIL_EXISTED(2118,"业务通知配置扩展表已存在"),
		T_CHEAT_CONFIG_NOT_EXISTED(2119,"防作弊刷票配置不存在"),
		T_IDENTITY_NOT_EXISTED(2120,"用户身份定义不存在"),
		T_IDENTITY_SERVICE_NOT_EXISTED(2121,"tIdentityService不存在"),
		T_IDENTITY_SERVICE_EXISTED(2122,"tIdentityService已存在"),
		T_ITEM_DEFINE_NOT_EXISTED(2123,"系统字典定义表不存在"),
		T_ITEM_DEFINE_EXISTED(2124,"系统字典定义表已存在"),
		T_ITEM_VALUE_NOT_EXISTED(2125,"系统字典明细表不存在"),
		T_ITEM_VALUE_EXISTED(2126,"系统字典明细表已存在"),
		T_JOB_CFG_NOT_EXISTED(2127,"定时任务不存在"),
		T_JOB_CFG_EXISTED(2128,"定时任务已存在"),
		T_PACKAGE_NAME_EXISTED(2129,"套餐配置名称已存在"),
		T_PACKAGE_CHANGED(2130,"套餐模板已经改变"),

		T_RULE_DEFINE_NOT_EXISTED(2150,"规则定义不存在"),
		T_STATUS_NOT_EXISTED(2151,"传入状态配置不存在"),
		T_STATUS_TABLE_IS_EXISTED(2152,"状态分类配置已存在"),
		T_CONFIG_SERVICE_IS_EXISTED(2152,"服务配置CODE已存在"),

		ENABLED_AUTH_ROLE_CAN_NOT_ENABLED(2153, "已禁用用户不能在禁用"),
		T_ITEM_DEFINE_EXISTED_SUB_DEFINE(2154, "当前字典值有下级或字典子值，无法删除，请先删除下级值或字典子值。"),
		T_PACKAGE_REFERRAL_IS_EXOTED(2155,"套餐模板中已有推荐套餐"),
		T_PACKAGE_IS_REFERRAL(2156,"该套餐模板为推荐套餐，不可删除"),
		T_BUSINESS_PARTITION_ERROR(2157,"系统配置业务指标错误"),

		HIGH_T_APP_VERSION_IS_UPDATE(2158,"更高的版本为更新版本"),
		HIGH_T_APP_VERSION_CAN_NOT_CLOSE(2159,"不能关闭最新版本"),
		NOT_HIGH_VERSION(2160,"不是最新版本或没有要更新的低版本"),
		UPDATE_VERSION_CAN_NOT_UPDATE(2161,"更新版本不能更改"),
		APP_VERSION_OUTSIDE_VERSION_IS_EXISTED(2162,"此外部版本号已存在"),

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 用户配置
* 2300-2499
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 登陆注册相关
* 2500-2699
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	REGISTER_VERIFICATIONCODE_INVALID(2500, "验证码失效"),
	REGISTER_VERIFICATIONCODE_ERROR(2501, "验证码错误"),
	REGISTER_IFDENTIFICATIONNUMBER_ERROR(2502, "此号已被注册或绑定使用"),
	REGISTER_IFDENTIFICATIONNUMBER_NOT_EXISTS(2503, "此号未被注册或绑定使用"),
	REGISTER_ACCOUNTPLATFROMID_FAILURE(2504, "第三方账号已被其他用户绑定使用"),
	REGISTER_FLOOR_FAILURE(2505, "注册太过频繁，请稍后再试。"),
	ACCOUNT_NOT_BIND_3RD_PARTY(2506, "账号未绑定第三方帐号"),

	ACCOUNT_BIND_USER_NOT_SESSION_USER(2507, "第三方账户绑定用户和登陆用户不一致"),
	REGISTER_BLACK_FAILURE(2508, "注册黑名单用户，请联系管理员。"),

	LOGIN_FIND_USER_FAILURE(2509, "账户不存在或不可用"),
	LOGIN_PASSWORD_FAILURE(2510, "账户或密码错误"),
	LOGIN_USER_STATUS(2511,"账号状态异常"),
	LOGIN_OPEN_UNBIND(2512,"第三方登陆账号未绑定本站用户"),

	EMAIL_CANT_UNBIND(2513,"注册使用的EMAIL不能解除绑定"),
	MOBILE_CANT_UNBIND(2514,"注册使用的手机不能解除绑定"),
	MOBILE_EMAIL_NOT_VALID(2515,"解绑的手机、邮箱号码与用户资料中的不一致"),
	USER_MOBILE_REQUIRED(2516, "用户手机号码不能为空"),
	VALIDATE_INVALID(2517, "验证无效"),
	VALIDATE_FAIL(2518, "验证失败"),
	VALIDATE_FAIL_TIP(2519, "请扫码验证身份"),
	AUTHCODE_INVALID(2520, "授权码无效"),
	AUTHCODE_BINDED(2521, "授权码已经绑定过"),
	AUTHCODE_DISABLED(2522, "数据已被禁用"),
	AUTHCODE_BLOCK_UP(2523, "数据已被停用"),
	SCAN_USER_DISABLED(2524, "很抱歉，该微信用户已被禁用，请换一个微信试一试吧。如需帮助，请联系销大师，咨询电话：400-8556 888。"),
	MOBILE_BIND(2525, "该手机号已绑定，请重新输入"),
	REPETITION_SEND_MESSAGE(2526, "请勿重复发送短信"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
 * 用户
 * 3000 - 3499
 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		USER_NOT_EXITS(3000, "用户不存在"),
		USER_COMPANY_NOT_EXITS(3001, "用户所属公司不存在"),
		//USER_WRONG_PWD(3002, "输入的用户名/ID,或者密码错误"),
		CANT_UNBIND_REGISTER_3RD_PTY_ACCT(3003, "当前账户的第三方帐号绑定方式是注册绑定,不可解绑"),
		USER_NOT_LOGGED(3004, "当前用户未登录"),
		USER_NICKNAME_IS_EXIST(3006, "用户昵称已经存在"),
		USER_THIS_TYPE(3007, "用户已经是该状态"),
		USER_THIS_COVER(3008, "用户已经是该封面照"),
		IDENTIFY_NUM_TYPE_WRONG(3009, "识别号类型不正确"),
		USER_LOGIN_NAME_IS_EXIST(3010, "用户登录名已经存在"),
		USER_INFO_INCOMPLETE(3011, "用户信息不完整"),
		USER_DISABLED(3012, "用户被禁用"),
		USER_NOT_EXITS_OR_NOT_ACTIVE(3013, "用户不存在或不可用"),
		USER_NOT_MANAGER(3014, "当前修改的用户非管理员"),

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
 * 公司，部门, 职位，员工, 套餐
 * 3500--3999
 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		POSITION_NAME_DUPLICATION(3500, "职位名称重复"),
		DEPT_NAME_DUPLICATION(3501, "部门名称重复"),
		COMPANY_INFO_INCOMPLETE(3502, "公司信息不完整"),
		COMPANY_NOT_EXISTS(3503, "公司不存在"),
		DEPARTMENT_IS_REQUIRED(3504, "无法获取部门信息"),
		EMPLOYEE_NOT_EXISTS(3505, "员工信息不存在或不可用"),
		EMPLOYEE_NAME_REQUIRED(3506, "员工姓名不能为空"),
		EMPLOYEE_IDCARD_REQUIRED(3507, "员工身份证号不能为空"),
		EMPLOYEE_LEAVEDATE_REQUIRED(3508, "离职日期不能为空"),
		EMPLOYEE_HANDOVER_REQUIRED(3509, "业务交接人不能为空"),
		EMPLOYEE_NOT_AUDIT(3510,"此员工已审核"),
		EMPLOYEE_NOT_JOIN(3511,"此员工已加入公司"),
		COMPANY_STATUS_ERROR(3512,"此公司状态不为启用"),
		COMPANY_NOT_JOIN(3513,"不能加入自己创建的公司"),
		COMPANY_ITEM_FAILED(3514, "公司配置失败"),
		COMPANY_REGISTER_ERROR(3515, "此公司已被注册"),
		CORP_APPLY_NOT_EXISTS(3516, "申请不存在或已过期"),
		CORP_APPLY_EXISTS_1(3517, "已申请加入该公司，请等待审核"),
		CORP_APPLY_EXISTS_2(3518, "请24小时后再申请"),
		CORP_APPLY_EXISTS_3(3519, "您已通过注册创建公司！"),
		COMPANY_NOT_EXISTS_OR_WAIT(3520, "公司不存在或不可用"),
		CORP_COMPOSITION_NOT_EXISTS(3521, "公司职位不存在"),
		CORP_DEPT_NOT_EXISTS(3522, "公司部门不存在"),
		CORP_COMPOSITION_NOT_NULL(3523, "公司职位名称不能为空"),
		CORP_DEPT_NOT_NULL(3524, "公司部门不能为空"),
		EMPLOYEE_ENTRYDATE_REQUIRED(3525, "入职日期不能为空"),
		EMPLOYEE_STARTDATE_REQUIRED(3526, "当前合同起止日期不能为空"),
		EMPLOYEE_IDENTITYCODE_REQUIRED(3527, "员工身份信息不能为空"),
		CORP_APPLY_TOO_MUCH(3528, "最多可创建5个企业"),
		CORP_APPLY_NOT_ADD(3529, "企业未添加公众号"),
		CORP_DELETE_CAN_NOT_DISABLED(3530,"删除公司不可禁用"),
		CORP_TIME_EXPIRE(3531,"公司时间到期"),
		COMPANY_APPLY_NOT_EXISTS(3532, "公司申请不存在"),
		DEPT_MAX_NUM(3533, "超过部门最大数量"),
		DEPT_MAX_LEVEL(3534, "超过部门最大层级"),
		DEPT_EXIST_EMPLOYEE(3535, "部门存在员工"),
		DEPT_EXIST_DEPT(3536, "部门下面存在部门"),
		CORP_PACKAGE_NOT_EXISTS(3537, "公司套餐不存在"),
		CORP_PACKAGE_IS_TOP_LIMIT(3538, "您的套餐所拥有的坐席名额已达上限，暂无可添加的坐席，如您想要更多坐席，请升级套餐。"),
		CORP_PACKAGE_MAX(3539, "您的套餐所拥有的员工名额已达上限，暂无可添加的员工名额，如您想要更多员工名额，请升级套餐。"),
		DISABLED_EMPLOYEE_CAN_ACTIVE(3541, "停用用户才能启用"),
		EMPLOYEE_APPLY_NOT_EXISTS(3453, "员工申请不存在"),
		TOP_EMPLOYEE_CAN_CANCEL_TOP(3544,"置顶员工才能取消置顶"),
		CORP_PACKAGE_AUTH_IS_NULL(3545,"坐席权限不存在于公司套餐权限下"),
		EMPLOYEE_INVALID(3546, "无效坐席"),
		INVITE_EMPLOYEE_INVALID(3547, "此二维码或链接已失效，如需要加入你所在的企业，请联系你的企业管理员"),
		INVITE_EMPLOYEE_ERROR(3548, "此二维码或链接已绑定其他微信用户，如需要加入你所在的企业，请联系你的企业管理员"),
		ADMIN_CAN_NOT_UPDATE_STATUS(3549, "管理员不能修改状态"),
		EMPLOYEE_UNADD(3550, "员工未加入"),
		EMPLOYEE_MOBILE_ADDED(3551, "公司员工手机号已经存在"),
	    DISABLED_COMPANY_CAN_NOT_FORBIDDEN(3552, "停用公司不能禁用"),
	    COMPANY_ORDER_CAN_NOT_CREATE(3554, "停用和禁用下不可创建"),
	    COMPANY_CAN_NOT_CREATE(3555, "请先将未付款的企业完成付款"),
	    COMPANY_CAN_NOT_DELETE(3556, "只能删除新创建公司"),
	    COMPANY_STATAUS_DISABLED(3560,"公司已停用"),
	    COMPANY_STATAUS_FORBIDDEN(3561,"公司已禁用"),
	    COMPANY_STATAUS_EXPIRE(3562,"公司已过期"),
	    EMPLOYEE_STATAUS_BACKUP(3563, "员工已停用"),
		NOT_CORP_EMPLOYEE_OR_IDEENTITY_NOT_ADMIN(3564, "不是该公司员工或员工身份不为管理员"),
		CORP_NOT_NEW(3565, "该公司非新创建公司"),
	    COMPANY_STATAUS_NO_ACTIVE(3566,"公司为非启用状态"),
		CORP_IS_NEW(3567, "新创建企业"),

		COMPANY_NOT_IS_NEW(3580, "公司不为新公司"),
		COMPANY_NO_AC_OR_EX(3581, "公司状态非启用或过期，无法续费升级"),
		DEPT_NOT_SAME_PARENT(3582,"部门不在同一级"),
		ONT_LEVEL_DEPT_CAN_NOT_CREATE(3583,"不能创建一级部门"),
		COMPANY_FORBIDDEN_OPERATION(3584,"公司在此状态下不可操作"),
		CORP_PACK_FORBIDDEN_OPERATION(3585,"公司套餐在此状态下不可操作"),
		REBUY_ERROR(3586,"续费时未查询到公司上次购买的公司套餐记录"),
		EMPLOYEE_STATUS_ERROR(3587, "您当前的员工状态已更改，无法进行操作"),
		NOT_CORP_EMPLOYEE(3588, "传入的员工不是该公司员工"),
		CORP_PART_IS_NULL(3589, "公司分区不存在"),
		CORP_FASN_TO_ES_ING(3590, "公司粉丝正在同步到es"),
		KNOWLEDGE_BASE_GROUP_MAX_NUM(3591, "公司知识库分组最多创建50个"),
		EMPLOYEE_IS_QUIT(3592, "员工已退出"),
		CORP_SET_VALUE_NOT_NUMERIC(3593, "设置时间值不为以逗号分隔的数字"),
		CORP_NOT_AVAILABLE(3594, "当前公司正在初始化中，请稍后登陆。"),
        EE_EMPLOYEE_CAN_NOT_UPDATE_ADMIN_EMPLOYEE(3595,"普通员工不能修改管理员员工"),
        FORCED_TO_CHAT_NUM_MAX(3596,"强制发送次数已达最大值"),
        CORP_DEPT_CREATE_MEDIUM(3597,"已有部门正在创建中"),
		CORP_KNOWLEDGEBASE_UNCLASSIFIED_CONNOT_DELETE(3598,"未分组不能删除"),
		CORP_LESS_THAN_RESOURCES_USE_NUM(3599,"修改值不能少于当前公司使用数"),
    CORP_DEPT_NOT_DELETE(3600, "该部门不能删除"),
    EMPLOYEE_ADD_FAIL(3601,"员工添加失败"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
 * 客户
 * 4000--4149
 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		CUSTOM_SET_TYPE_EXISTS(4000, "客户设置类型名称已存在"),
		CUSTOM_SET_TYPE_ERROR(4001, "设置不存在"),
		CUSTOM_SET_TYPE_NOT_EXISTS(4002, "客户设置不存在"),
		CUSTOM_ABBREVIATION_EXISTS(4003, "客户简称已存在"),
		CUSTOM_ABBREVIATION_NULL(4004, "客户简称不能为空"),
		CUSTOM_SET_VALUE_NAME_EXISTS(4005, "名称已存在"),
		CUSTOM_NOT_EXISTS(4006, "客户不存在"),
		CUSTOM_PARENT_NULL(4007, "找不到上级公司"),
		CUSTOM_INVOICE_NOT_EXISTS(4008, "发票不存在"),
		CUSTOM_EMPLOYEE_NOT_EXISTS(4009, "员工不存在"),
		CUSTOM_SET_CAN_NOT_MODFIY(4010, "不能创建或者修改此设置名称"),
		CUSTOM_SET_CAN_NOT_DELETE(4011, "设置不能删除"),
		CUSTOM_NAME_EXISTS(4012, "客户公司名称已存在"),
		CUSTOM_NAME_IS_NULL(4013, "客户公司名称不能为空"),
		CUSTOM_SET_TYPE_VALUE_NOT_EXISTS(4014, "客户设置选项不存在"),
		CUSTOM_SAVE_FAILE(4015, "客户保存失败"),
		CUSTOM_CAN_NOT_DELETE(4016, "客户删除失败"),
		CUSTOM_INVOICE_TYPE_ERROR(4017, "发票类型错误"),
		CUSTOM_INVOICE_NAME_CODE_ERROR(4018, "发票抬头或纳税人识别码不能为空"),
		CUSTOM_PARENT_COMPANY_NOT_SON(4019, "不能选当前客户或其下级作为父公司"),
		CUSTOM_PARENT_COMPANY_SAME(4020,"不能选自己作为上级公司"),
		CUSTOM_BUSINESS_USERED(4021, "客户在业务中有使用"),
		CUSTOM_MEGERID_NOT_NULL(4022, "客户维护人不能为空"),


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 微信素材
* 4150--4299
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		MEDIA_TYPE_ERROR(4151, "媒体类型错误"),
		CSS_WX_TEMPLATE_NOT_EXISTS(4152, "模板消息不存在"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 客服
* 4300--4399
* */
		CONVR_ERROR(4300, "错误的请求！"),
		CONVR_SPEED_ERROR(4301, "聊天间隔太短！"),
		CONVR_ONLIME_ERROR(4302, "坐席不在线！"),
		CONVR_SEAT_ERROR(4303, "坐席不可用！"),
		CONVR_CORP_ERROR(4304, "公司不可用！"),
		CONVR_APPACCOUNT_ERROR(4305, "坐席资源不可用！"),
		CONVR_MSGID_ERROR(4306, "资源不可用，请重试！"),
		CONVR_SEAT_SCRAMBLE_ORDER_ERROR(4307, "已有其他客服在抢单，请重试！"),
		QDC_CONVR_SCRIBEL_REPETITION(4308,"抢单重复,请重试"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 坐席
* 5000--5099
* * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	SEAT_NOT_NULL(5001, "坐席不存在或不可用"),
    SEAT_NAME_IS_NULL(5002, "坐席名称不能为空"),
	SEAT_ID_IS_NULL(5003, "坐席ID不能为空"),
	SEAT_APPID_OR_AUTH_IS_NULL(5004, "坐席权限或坐席公众号不能为空"),
	SEAT_IS_DISABLED_STATUS(5005, "坐席为禁用状态不可修改"),
	SEAT_DISTRIBUTIONED(5006, "坐席已分配员工"),
	SEAT_IS_NOT_BLOCK_UP(5007, "坐席非停用状态不能启用"),
	SEAT_IS_NOT_ACTIVE(5008, "坐席非启用状态不能停用"),
	SEAT_NOT_ACTIVE_OR_BLOCK_UP(5009, "坐席非启用或停用状态不能解除"),
	SEAT_NOT_STATUS_ERROR(5010, "传入坐席状态错误"),
	SEAT_INVALID(5012, "无效坐席"),
	DISABLED_SEAT_CAN_NOT_BLOCK_UP(5013,"禁用坐席不能停用"),
	SEAT_ALREADY_EXISTED(5014,"坐席已存在"),
	DISABLED_SEAT_CAN_NOT_DISABLED(5015,"禁用坐席不能禁用"),
	ACTIVE_SEAT_CAN_NOT_ACTIVE(5016,"启用坐席不能启用"),
	BLOCK_UP_SEAT_CAN_NOT_BLOCK_UP(5017,"停用坐席不能停用"),
	EMPLOYEE_HAVED_SEAT(5018,"员工已有坐席"),
	SEAT_NOT_ACTIVE(5019, "坐席不为启用状态，无法分配坐席"),
	SEAT_IS_NULL_BY_CORP(5020, "当前公司不存在此坐席"),
	SEAT_UN_DISTRIBUTIONED(5021, "坐席未分配员工"),
	IM_NOT_AVAILABLE_NODE(5024,"IM可用节点不可用"),
	SEAT_STATUS_NOT_ACTIVE(5022,"坐席不为启用"),
	NOT_EMPLOYEE_SEAT(5023,"不是该员工坐席"),
	RECEPTION_GROUP_SWITCH_NONEXISTENCE(5024,"接待组客服开关不存在"),
	RECEPTION_GROUP_SWITCH_NONEXISTENCE_VALUE(5025,"接待组客服开关不存在值"),
	SEAT_IS_NULL(5026,"该公众号绑定的公司没有有效客服"),
	COMPANY_DID_NOT_SET_SEAT(5027,"公司没有开启个性化坐席"),
	SEAT_APP_ACCOUNT_IS_NULL(5028,"坐席未分配公众号"),
	SEAT_IS_BLOCK_UP(5029,"坐席已停用"),
	SEAT_IS_DISABLED(5030,"坐席已禁用"),
	EE_SEAT_IS_UN_DISTRIBUTION(5031,"员工未分配坐席"),
	SEAT_REPLY_CONTENT_ERROR(5032,"坐席自动回复内容不能超过200个字符"),
    SEAT_CAN_NOT_CREATR_CONVERSION(5033,"当前坐席无法发起对话"),
    SEAT_PEOPLE_IS_NULL(5034,"该操作人与粉丝之间不存在客服关系"),
	NOT_ADMIN_CAN_NOT_SET_OTHERS_OWNER_SEAT(5035,"不是管理员不能设置别人的所属坐席"),
	SEAT_PEOPLE_IS_NOT_OWNER_SEAT(5036,"该坐席不是当前粉丝的所属客服"),
	SEAT_FREQUENT_REQUESTS_INCR_UNREAD(5037,"请求修改未读数量频繁"),
	FANS_CAN_NOT_BELONG_CURRENT_SEAT(5038,"该粉丝不是你的所属粉丝,不可发消息"),
	SEAT_CAN_NOT_HAVE_AUTH(5039,"您暂无此权限,不可发消息"),
	FANS_HAVE_OWNER_SEAT(5040,"该粉丝已有所属客服"),
	SEAT_NOT_EXIST_IN_QDC_LIST(5041,"您不在该粉丝的抢单坐席列表中"),
	CONVR_IS_QDCING(5042,"已有其他客服抢单,请稍后再试"),
    SEAT_NOT_BIND_APP_ACCOUNT(5043,"坐席未绑定当前应用号"),
    COOPERATION_SEAT_PEOPLE_IS_NULL(5044,"协作客服与该公众无关联关系"),
	SEAT_NOT_ACTIVE_STATUS(5045,"客服权限异常"),
	SEAT_AUTH_ERROR(5046,"无客服权限"),
	NOT_ADMIN_OR_AGENT_ADMIN_CAN_NOT_SET_OTHERS_OWNER_SEAT(5047,"不是超级管理员或者不是代理商管理员不能设置别人的所属坐席"),
	THE_POSTER_IS_BEING_GENERATED(5048,"裂变海报正在生成"),


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 聊天系统
* 5100--5199
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	SOCKETCLIENT_FORCED_OFFLINE(5101,"当前客户端已被强制下线，不能重复登录"),
	SOCKETIO_ERR_2(5102,"连接IM失败，请重新登陆!"),
	SOCKETIO_ERR_3(5103,"连接被移除，请重试!"),
	SOCKETIO_ERR_4(5104,"连接失效或不存在，请重试!"),
	SOCKETIO_ERR_5(5105,"连接不存在，请重试!"),
	SOCKETIO_REQ_1(5150,"服务器请求"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 二维码,海报，智能接待
* 5200--5299
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	QR_CODE_NUM_NOT_EXITED(5201,"当前套餐无可用二维码，请升级套餐"),
	QR_CODE_THEME_NOT_EXITED(5202,"二维码主题不存在"),
	QR_CODE_IS_SAVING(5203,"二维码正在创建"),
	POSTER_TEMPLATE_ACTIVITY_VALIDITY_PERIOD_MORE_THAN_PACKAGE_TIME(5204,"海报模板活动时间超出套餐时间"),
	POSTER_TEMPLATE_FANS_POSTER_TIME_MORE_THAN_ACTIVITY_TIME(5204,"海报有效期超出模板活动时间"),
	GINTELLIGENT_RECEPTION_TASK_MAX(5205,"新建失败，最多只能新建10条"),
	GINTELLIGENT_RECEPTION_TASK_SAVING(5206,"智能接待任务创建中"),
	GINTELLIGENT_RECEPTION_MODE_CAN_NOT_USE(5207,"智能接待场景不可用，请尝试升级套餐在使用"),
	QR_CODE_REQUEST_ERROR(5208,"二维码获取失败，请刷新页面重新获取"),
	POSTER_TRIGGER_KEY_WORD(5209,"该海报触发关键词已经被使用,请重新设置"),
	POSTER_IS_NULL(5210,"海报不存在"),
	FISSION_TEMPLATE_IS_NULL(5211,"裂变活动不存在"),
	FISSION_ACTIVITY_NOT_START(5212,"裂变活动未开始"),
	FISSION_ACTIVITY_END(5213,"裂变活动已结束"),
	FISSION_AREA_GENDER_ERROR(5214,"粉丝所在地区或者性别不符合活动条件"),
	FISSION_POSTER_EXPIRED(5215,"裂变海报已过期"),
	UN_SUPPORT_IMAGE_TYPE(5216,"图片编码格式不支持"),



//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 云空间
* 5300--5399
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	CLOUD_FILE_IS_DELETEING(5301,"云空间资源正在清理"),
	CLOUD_FILE_NOT_EXITED(5302,"云空间资源不存在"),

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/**
* 坐席
* 5400--5499
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * 套餐
	 * 5500--5999
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 CORP_PACKAGE_IS_NULL(5501, "公司套餐不存在"),
     CORP_PACKAGE_IS_NOT_ACTIVE(5502, "公司套餐不可用"),
	 PACKAGE_STATS_IS_NOT_ACTIVE(5503,"套餐模板不可用"),
	 PACKAGE_CLOUD_SIZE_MAX(5504,"套餐云空间不存在或达到最大值"),
	 PACKAGE_IS_NULL(5505,"购买功能列表不能为空"),

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * 公众号
	 * 6000--6499
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	PUBLIC_ACCOUNT_IS_NULL(6000, "公众号不存在！"),
	AUTOREPLY_ROLE_IS_NULL(6001, "自动回复规则不存在"),
	PUBLIC_ACCOUNT_TOKEN_IS_NULL(6002, "公众号token不存在！"),
	PUBLIC_ACCOUNT_IN_THE_SYNC(6003, "公众号正在同步中，不可重复操作！"),
	PUBLIC_ACCOUNT_NOT_AUTH(6004, "公众号未授权"),
	AUTH_OR_STATUS_ERROR(6005, "公众号非停用状态或未授权"),
	PUBLIC_ACCOUNT_CAN_NOT_DISABLED(6006, "公众号为禁用或删除状态无法禁用"),
	PUBLIC_ACCOUNT_CAN_NOT_ACTIVE(6007, "公众号非禁用或停用状态，无法启用"),
	PUBLIC_ACCOUNT_NO_ALIAS(6008, "公众号微信号不存在"),
	PUBLIC_ACCOUNT_STATUS_ERROR(6009, "禁用状态或未授权下无法解除授权"),
	PUBLIC_ACCOUNT_UN_AUTHORIZATION_ERROR(6010, "停用未授权状态无法解除授权"),
	PUBLIC_ACCOUNT_IS_DELETED_ERROR(6011, "当前已为删除状态"),
	PUBLIC_ACCOUNT_IS_NOT_ACTIVE(6012, "公众号非正常已授权状态"),
	PUBLIC_ACCOUNT_DELETED_ERROR(6013, "您的企业在禁用或启用状态下无法直接删除"),
	PUBLIC_ACCOUNT_GROUP_ALREADY(6014,"公众号分组已存在"),
	PUBLIC_ACCOUNT_IMG_URL_GROUP_CAN_NOT_MOVE(6015,"公众号文章配图分组不能移动"),
	PUBLIC_ACCOUNT_GROUP_NOT_EXITED(6016,"公众号分组不存在"),
	PUBLIC_ACCOUNT_GROUP_MAX(6017,"公众号分组达到最大值"),
	PUBLIC_ACCOUNT_TITLE_ALREADY(6018,"公众号标题已存在"),
	PUBLIC_ACCOUNT_FILE_SPACE_NOT_EXITED(6019,"公众号云服务空间不存在"),
    PUBLIC_ACCOUNT_FILE_SPACE_MAX(6020,"云服务空间达到最大值"),
	CSS_MEDIA_SIZE_MAX(6021,"微信媒体大小超过最大值"),
	CSS_MEDIA_COUNT_MAX(6022,"微信媒体数量超过最大值"),
	PUBLIC_ACCOUNT_DEFAULT_GROUP_CAN_NOT_UPDATE(6023,"默认分组不可修改"),
	CSS_MEDIA_LENGTH_MAX(6024,"微信媒体时长超过最大值"),
	CSS_MEDIA_COUNT_NOT_EXITED(6025,"微信媒体数量不存在"),
	INSERT_MEDIA_NEWS_ERROR(6026, "插入图文媒体失败"),
	ARTICLE_OR_EXPRESSSION_GROUP_CANT_REMOVE(6027, "文章配图或表情分组不可移动"),
	CSS_DEFAULT_GROUP_NOT_EXITED(6028,"默认分组不存在"),
	PUBLIC_ACCOUNT_ERROR(6029, "公众号不存在或不可用"),
	PUBLIC_ACCOUNT_NOT_INITLILIZED(6030, "公众号未初始化，初始化同步中"),
	AUTOREPLY_ROLE_MAX_NUMBER(6031,"关键词回复数量已达最大值"),
	AUTOREPLY_ROLE_COTENT_IS_NULL(6032, "自动回复规则内容不存在"),
	WX_TEMPLATE_IS_NULL(6033,"微信模板消息不存在"),
	GROUP_SENDING_CANNOT_DELETE(6034,"该群消息不可删除"),
	GROUP_SENDING_CANNOT_CANCEL(6034,"该群消息不可取消"),
	PUBLIC_ACCOUNT_MEDIA_SIZE_MAX(6035,"微信媒体大小达到最大值"),
	SEQ_TOKEN_NOT_EXITED(6036,"缓存不存在"),
	PUBLIC_ACCOUNT_MEDIA_NUM_MAX(6037,"微信媒体数量大小达到最大值"),
	CLOUD_MEDIA_ALREAD_SUBMIT(6038,"云空间资源已经提交过"),
	CLOUD_MEDIA_NOT_SAVE(6038,"云空间资源未保存"),
	CHANNEL_QR_CODE_REPLY_MAX_NUMBER(6039,"渠道二维码回复条数超过最大值"),
	CSS_GROUP_EXISTENCE_CONTENT(6040,"该分组下存在内容不可删除"),
	WECHAT_PUBLIC_ACCOUNT_TYPE_NOT_SERVICE(6041,"不为服务号"),
	WECHAT_PUBLIC_ACCOUNT_VERIFY_TYPE_INFO_NOT_AUTH(6042,"未认证公众号"),
	CHANNEL_FANS_POSTER_TEMPLATE_REPLY_MAX_NUMBER(6043,"粉丝海报关注回复条数超过最大值"),
	GROUP_SENDING_MAX_NUMBER(6044,"群发数量达到最大值"),
	PUBLIC_STATUS_UN_AUTH(6045,"当前绑定的公众号未认证，请先认证。"),
	PUBLIC_STATUS_ERROR(6046,"当前绑定的公众号状态异常，请联系管理员处理。"),
	GROUP_SENDING_STANDARD_ERROR(6047,"订阅号每天标准群发只能发送一次。"),
	AUTOREPLY_RULE_MAX_COUNT(6048,"最多可添加10条关键词回复"),
	SENIOR_GROUP_MIN_NUMBER(6049,"按条件筛选对象时发送人数不能低于2人"),
	SENIOR_GROUP_NOT_MATERIAL(6050,"群发消息素材不存在"), 
	SENIOR_GROUP_NOT_MSG_TYPE(6051,"群发消息不支持该消息类型"),
	AUTOREPLY_ROLE_CONTENT_NOTNULL(6052,"关键词回复内容不能为空"),
	CSS_FOLLOWED_REPLY_MAX_NUMBER(6053,"被关注回复条数超过最大值"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	* 应用账号
	* 6500--6600
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	APP_ACCOUNT_IS_NULL(6500, "应用账号不存在！"),
	APP_ACCOUNT_DISABLED(6501, "应用账号已被禁用！"),
	APP_ACCOUNT_TOKEN_ERR(6502, "应用账号生成token失败！"),
	APP_ACCOUNT_TICKET_ERR(6503, "ticket不存在！"),
	APP_ACCOUNT_AUTHORIZERREFRESHTOKEN_ERR(6504, "Authorizerrefreshtoken不存在,请重新第三方授权！"),
	APP_ACCOUNT_NOT_ACTIVE(6505,"应用账号不为启用"),
	APP_ACCOUNT_ERROR(6506,"应用账号不存在或不可用"),
	APP_ACCOUNT_ERROR_BY_SEAT_ID(6507,"传入的应用账号与坐席下应用账号不符"),
	APP_ACCOUNT_UN_AUTH(6508,"公众号未授权"),
	APP_ACCOUNT_STOP_OR_DISABLED(6509,"公众号停用或不可用"),
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * 订单
	 * 6601--6700
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	ORDER_IS_NULL(6601, "订单不存在！"),
	PAY_ORDER_IS_NULL(6602, "支付订单不存在！"),
	NOT_REMITTANCE_ORDER(6603, "不是银行汇款订单！"),
	REMITTANCE_ORDER_STATUS_NOT_TO_BE_AUTH(6604, "订单状态不为待审核！"),
	ORDER_IS_CLOSED(6605, "订单已关闭！"),
	NOT_WAIT_PAY_ORDER(6606, "不为代付款订单！"),
	ORDER_PACKAGE_IS_NULL(6607, "订单套餐不存在！"),
	PAY_ORDER_STATUS_NOT_SUBMITTED(6608,"交易订单状态不为已提交"),

	PACKAGE_NOT_ORDER(6621, "套餐不能订购"),
	PACKAGE_ORDER_NUM_LIMIT(6622, "套餐订购数量超限"),
	PACKAGE_ORDER_PRICE_ERROR(6623, "订购金额有误"),
	PACKAGE_ORDER_NOT_CANCEL(6624, "订单不能取消"),
	PACKAGE_ORDER_NOT_DELETE(6625, "自助订单不能删除"),
	PACKAGE_ORDER_NOT_ACCEPT(6626, "自助订单不能确认"),
	PACKAGE_ORDER_STATUS_NOT_ACCEPT(6627, "非审核成功订单不能确认"),
	ORIGINAL_PACKAGE_HAVE_ERROR(6629, "原套餐配置错误!"),


	PACKAGE_ORDER_NOT_EXIST(6628, "订单不存在"),
	PACKAGE_ORDER_CORP_NOT_MATCH(6629, "公司与订单的公司不匹配"),

	ORDER_PAG_IS_NULL(6800, "订单套餐不存在"),
	ORDER_IS_PAY(6801, "订单已支付"),
	ORDER_PAG_AUTH_IS_NULL(6802, "订单套餐权限信息不存在"),
	ORDER_STATUS_ERROR(6803, "订单状态异常"),
	ORDER_IS_TIMEOUT(6804, "该订单已超时"),
	PAY_ORDER_PAY_FAILED(6805, "支付宝订单支付失败"),
	PAY_ORDER_PAY_TYPE_ERROR(6806, "传入支付方式错误"),
	WX_PAY_ORDER_IS_NULL(6807, "微信端不存在此订单"),
	WX_COMMUNICATION_ERROR(6808, "微信通信失败"),
	WX_BUSINESS_ERROR(6809, "微信业务失败"),
	NOT_ORDER_SUBMITTED(6810, "非待付款交易单"),
	PACKAGE_REPEATED_SELECTION(6627, "免费套餐已使用"),
	PACKAGE_NO_SELL(6628, "您所选的套餐刚刚已被下架，请重新选择其他套餐版本购买。"),
	PACKAGE_FUNCTION_NO_SELL(6629, "该功能已下架"),
	PACKAGE_FUNCTION_NO_BUY(6630, "未开通该功能"),
	PACKAGE_FUNCTION_IS_NULL(6631, "该功能记录不存在"),
	PACKAGE_FUNCTION_IS_EXPIRED(6632, "该功能已过期"),
	PACKAGE_AGENT_DISCOUNT_ERROR(6633, "套餐折扣错误"),
	ORDER_IS_EXIST_ERROR(6634, "订单已存在"),


//~~~~ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * 微信 菜单
	 * 6701--6800
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     WX_MENU_NULL(6701, "自定义菜单不存在！"),
     WX_MENU_CONTEXT_ERROR(6702, "提交正确的数据内容！"),
     WX_MENU_ONE_ERROR(6702, "一级菜单最多3个"),
     WX_MENU_TWO_ERROR(6703, "二级菜单最多5个"),
	 WX_MENU_RULE_IS_EXIST(6704, "个性化菜单规则已存在"),
	 WX_MENU_NOT_EXIST(6705, "请先创建默认菜单"),
	WX_MENU_ARE_SYNCING(6706, "自定义菜单正在同步中"),
	WX_MENU_PROGRAM_APPID(6707, "小程序appid不能为空"),
	WX_MENU_PROGRAM_PAGEPATH(6708, "小程序pagepath不能为空"),
	WX_MENU_PROGRAM_URL(6709, "小程序url不能为空"),
	WX_MENU_PROGRAM_VALUE_NULL(6710, "菜单的内容不能为空"),
//~~~~ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * 标签/标签分组/粉丝
	 * 6801--6900
	 * */
//~~~~ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	WX_GROUP_NUM_ERROR(6802, "标签分组个数不能超过50个"),
	WX_GROUP_TYPE_ERROR(6803, "分组类型错误"),
	WX_GROUP_IS_NULL(6804, "标签分组不存在"),
	WX_GROUP_HAS_TAG(6805, "请先删除标签分组内的标签。"),
	WX_GROUP_IS_EXIST(6806, "当前所选择的分组已存在"),
	WX_TAG_IS_EXIST(6807, "当前所选择的标签已存在"),
	WX_TAG_IS_NULL(6808, "微信标签不存在"),
	WX_TAG_FANS_TOO_MANY(6809, "该标签下的粉丝超过10万，不可直接删除，请先在粉丝身上取消该标签，直到改粉丝被使用的粉丝数不超过10万，才能直接删除该标签。"),
	WX_USER_IS_NULL(6810, "粉丝用户不存在"),
	WX_GROUP_NAME_IS_EXIST(6811, "分组名称已存在"),
	WX_GROUP_IS_SPECIAL_GROUP(6812, "原分组不为互斥标签时，不能设置为互斥标签"),
	WX_GROUP_NAME_IS_NULL(6813, "分组名称是空"),
	WX_GROUP_ORDER_ERROR(6814, "传入分组排序级别错误"),
	WX_TAG_GROUP_SYS_ERROR(6815, "微信标签分组正在复制中"),
	WX_PUBLIC_ACCOUNT_ONLY_ONE(6816, "当前公司下暂无其他可用公众号，无法从其他公众号复制标签。"),
	WX_TAG_SYS_ERROR(6817, "微信标签正在复制中"),
	WX_TAG_NUM_ERROR(6818, "标签个数不能超过98个"),
	WX_TAG_ORDER_ERROR(6819, "传入标签排序级别错误"),
    WX_TAG_NAME_ERROR(6820, "修改时传入标签的名称不能超过两个"),
    WX_TAG_NAME_IS_EXIST(6821, "传入标签的名称已存在"),
    WX_TAG_ADD_ERROR(6822, "调用微信接口添加标签失败"),
    WX_TAG_UPDATE_ERROR(6823, "调用更新微信标签失败"),
    WX_TAG_GET_ERROR(6824, "调用获取微信标签列表失败"),
	WX_TAG_NOT_EXIST(6825, "微信标签不存在无法删除"),
	WX_TAG_DELETE_ERROR(6826, "微信标签删除失败"),
	WX_USER_NOT_EXIST(6827, "粉丝不存在"),
	WX_USER_TAG_TOO_MANY(6828, "一个用户最多只能打20个标签"),
	WX_USER_TAG_SYS_ERROR(6829, "正在为用户打标签，请稍后操作"),
	WX_USER_TOO_MANY(6830, "最多只能为20个用户打标签"),
	WX_USER_TAG_IS_SYS(6831, "粉丝与粉丝标签正在同步中，请稍后操作"),
	WX_FANS_GROUP_IS_NULL(6832, "粉丝分组不存在"),
	WX_FANS_GROUP_NUM_ERROR(6833, "粉丝分组个数不能超过50个"),
	WX_FANS_NAME_IS_EXIST(6834, "粉丝分组名称已存在"),
	WX_FANS_NAME_NOT_DELETED(6835, "粉丝分组下存在粉丝不允许删除"),
	WX_FANS_NAME_SYS_NOT_DELETED(6836, "粉丝分组为默认不允许删除"),
	WX_USER_IS_COOPERATION_FANS(6832, "该粉丝不可以设置为专属粉丝"),
	WX_GROUP_SEAT_ID_ERROR(6833, "传入粉丝分组有误"),
	WX_USER_GROUP_IS_NULL(6834, "粉丝分组不存在"),
	WX_GROUP_USER_IS_NULL(6835, "分组粉丝不存在"),
	WX_USER_IS_SUBSCRIBE(6836, "当前粉丝为关注状态，不能删除"),
	WX_SELECT_NAME_IS_EXIST(6837, "查询条件名称已存在"),
	WX_SELECT_CONDITIONS_NOT_EXIST(6838, "该查询条件不存在"),
	WX_SELECT_NUM_TOO_MANY(6839, "最多只能保存十条筛选条件"),
	WX_EE_NOT_HAVE_SELECT_CONDITIONS(6840, "当前登陆员工没有此条查询条件"),
	WX_FANS_INFO_ERROR(6841, "粉丝信息异常"),
	WX_GROUP_INSERT_ERROR(6842, "插入客服粉丝分组信息异常"),
	WX_TAG_GROUP_INSERT_ERROR(6843, "插入粉丝标签分组信息异常"),
	WX_FANS_TAG_INSERT_ERROR(6844, "插入粉丝标签信息异常"),
    APPACCOUNT_FASN_TO_ES_ING(6845, "公众号粉丝正在同步到es"),
	WX_ACCOUNT_NOT_AVAILABLE(6846, "当前公司下暂无其他可用公众号，无法从其他公众号复制标签。"),
	WX_TAG_NAME_LENGTH_ERROR(6847, "传入的标签名称长度不能超过30个字符"),
	OWNER_FANS_NOT_EXITED(6848,"所属坐席不存在"),
	WX_USER_NOT_SUBSCRIBE(6849, "该粉丝已取消关注"),
	CORP_FANS_AMOUNT_IS_MAX(6850, "粉丝数量达到粉丝包上线,请购买粉丝包"),
	CORP_FANS_AMOUNT_IS_MAX_NOT_BUY(6851, "已使用粉丝包数量超过当前额度，无法下单，请购买粉丝包。"),

 /**
 *	腾讯云cos 错误
 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     OBJECTSTORE_ERROR(7000,"腾讯云cos错误"),


	/**
	 *	收藏 7101-7200
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	COLLECT_MAX(7101,"收藏达到最大值"),

	/**
	 * 会话相关 7201-7400
	 */
	CONVER_STORETED_SET_TYPE_IS_NULL(7201,"会话类型为空"),
	CONVER_FORCED_NOT_NEWS(7202,"强制发送不能发送该类型消息，请重新选择"),

	/**
	 * 小程序相关 7401-7600
	 */
	MP_CODE_ILLEGAL(7401,"获取sessionKey失败"),
	MP_UNIONID_IS_NULL(7402,"用户的UnionID不存在"),
	MP_ACCOUNT_IS_NULL(7403,"微信小程序未绑定"),
	MP_SIGN_IS_ERROR(7404,"签名错误"),
	MP_APPID_IS_ERROR(7405,"appid不一致"),
	MP_CAN_CREATE_CORP(7406,"可创建免费企业"),
	MP_SIGNATURE_ERROR(7410,"签名校验错误"),
	MP_DECRYPT_ERROR(7411,"校验参数异常"),
	MP_DATA_ERROR(7412,"解密参数与用户信息不一致"),
	MP_CORP_IS_NULL_HAS_CREATE_CORP(7413,"用户公司列表为空，并且已创建过免费企业"),
	MP_CORP_IS_NULL_NO_CREATE_CORP(7414,"用户企业列表为空，加入过企业后退出，有创建免费企业资格"),
	MP_CORP_IS_NULL_NO_CREATE_NO_JOIN_CORP(7415,"用户公司列表为空，未加入过企业，有创建免费企业资格"),
	
	/**
	 *	活动 7601-7800
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	MISSION_REWARDS_RELATION_ACTIVITY_DELETE(7601,"任务奖励已关联活动无法删除"),
	MISSION_REWARDS_RELATION_ACTIVITY_UPDATE_NOTICE_TYPE(7602,"任务奖励已关联活动不能修改通知类型"),
	MISSION_REWARDS_RELATION_ACTIVITY_UPDATE_APPACOUNT(7603,"任务奖励已关联活动不能修改公众号"),
	MISSION_REWARDS_WRITTEN_OFF(7604,"该任务已核销"),
	MISSION_REWARDS_DO_NOT_EXIST(7605,"该任务不存在"),
	FISSION_ACTIVITY_DO_NOT_EXIST(7606,"该裂变活动不存在"),
	COMPLAINT_EEID_NOT_EXIST(7607,"预警通知员工不能为空"),

	/**
	 *	排行榜 8001-8200
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	H5_CODE_INVALID(8001,"code获取accesstoken失败"),
	H5_USER_INFO_INVALID(8002,"获取用户信息失败"),
	RANK_IS_NULL(8003,"排行榜不存在"),
	SHARE_TEMPLATE_IS_NULL(8004,"分享模板不存在"),
	RANK_IS_END(8005,"排行榜已经结束"),
	
	/**
	 *	代理商 8201-8400
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	AGENT_NOT_EXITS(8201, "代理商不存在"),
	AGENT_NOT_ADD_AGENT(8202, "代理商无添加代理商权限"),
    AGENT_STATUS_NOT_ACTIVE(8209, "代理商状态不可用"),
    AGENT_IS_EXPIRED(8203, "代理商已到期"),
    AGENT_IS_NULL(8204, "代理商不存在"),
    AGENT_EE_NUM_MAX(8205, "代理商员工数量达到最大数量"),
    AGENT_NOT_SUPERADMINISTRATORS(8206, "不是超级管理员无此权限操作"),
    AGENT_PARAMETER_ERROR(8207, "员工数量不能超过最大员工数量"),
    AGENT_TELEPHONE_ERROR(8208, "加入员工不能修改手机号码"),
	AGENT_ID_ERROR(8209, "代理商id不能为空"),
	AGENT_NOT_AGENTEMPLOYEE(8210, "不是代理商员工无此操作权限"),
	AGENT_NOT_SUBAGENT(8211, "被转帐员工不是子代理商"),
	AGENT_NOT_LASTSCHRIFT(8212, "该代理商无划账操作"),
	AGENT_NOT_BALANCE(8213, "余额不足"),
	AGENT_NOT_DEFAULT_GROUP(8214, "代理商默认分组不存在"),
	AGENT_NOT_EMPLOYEE(8215, "代理商未加入公司员工"),

	/**
	 *	H5裂变 8401-8600
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	ACTIVITY_NOT_UPDATE_APPID(8401, "营销活动类型不能修改公众号"),
	ACTIVITY_NOT_UPDATE_URL(8402, "营销活动类型不能修改链接地址"),
	FISSION_NOT_EXITS(8403, "裂变不存在"),
	WRITTEN_OFF(8404, "该用户已核销"),
	FISSION_CHANNEL_MAX_NUMBER(8405,"裂变渠道超过最大数量"),

	/**
	 *	智能表单 8601-8800
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	RELATED_ACTIVITIES_NOT_UPDATE(8601,"已关联活动不能修改授权类型或公众号"),
	FR_TEMPLATE_NOT_EXITS(8602,"表单模板不存在"),

	/**
	 *	砍价8801-9000
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	BARGAIN_ACTIVITY_IS_NULL(8801,"砍价活动不存在"),
	BARGAIN_GOODS_IS_NULL(8802,"砍价商品不存在"),
	BARGAIN_REPEATED_INITIATION(8803,"砍价重复发起"),
	BARGAIN_GOODS_STOCK_INSUFFICIENT(8804,"砍价商品库存不足"),
	BARGAIN_ACTIVITY_NOT_START(8805,"砍价活动未开始"),
	ORIGINATOR_IS_NULL(8806,"发起人不存在"),
    ACTIVITY_OFF_SHELVES(8807,"该活动已经下架"),
    GOODS_NOT_PRICE(8808,"商品价格异常"),
    BARGAIN_ACTIVITY_INITIATED(8809,"已发起该活动"),
    BARGAIN_IS_LOW_PRICE(8810,"已砍至低价"),
    BARGAIN_IS_HAVE_HELPED(8811,"已帮砍过"),
    BARGAIN_ACTIVITY_IS_END(8812,"砍价活动已结束"),
    BARGAIN_NOT_PROBABILITY(8813,"砍价概率码异常"),
    BARGAIN_HELP_FAIL(8814,"砍价失败"),
    BARGAIN_GOODS_IS_END(8815,"商品砍价时间已结束"),
    BARGAIN_IS_FI_POSTER_FOLLOW(8816,"只有通过裂变海报扫码或扫码关注才能帮助砍价"),
    UNABLE_TO_PARTICIPATE_BARGAIN_ACTIVITY(8817,"不能参加该活动"),
    BARGAIN_GOODS_IS_DOWN(8818,"砍价商品已下架"),
    BARGAIN_TIME_IS_ERROR(8819,"砍价活动时间异常"),
    CSS_PUBLIC_NUMBER_IS_ERROR(8820,"公众号异常"),
    APP_ACCOUNT_OR_FISSION_EX(8821,"当前活动无法生成海报图片，请稍后再试。"),
    CORP_GOODS_NOT_DELETE(8822,"公司默认商品无法删除"),

	/**
	 *	拼团9001 - 9200
	 * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    SPELL_GROUP_ACTIVITY_IS_NULL(9001,"拼团活动不存在"),
    PARTICIPANT_IS_NULL(9002,"参与人不存在"),
    SPELL_GROUP_ACTIVITY_NOT_START(9003,"拼团活动未开始"),
    SPELL_GROUP_ACTIVITY_IS_END(9004,"抱歉，当前活动拼团已到期，无法参加此团。"),
    SPELL_GROUP_GOODS_IS_NULL(9005,"拼团商品不存在"),
    SPELL_GROUP_GOODS_STOCK_INSUFFICIENT(9006,"抱歉，当前商品已售罄，无法参加活动"),
    SPELL_GROUP_ACTIVITY_INITIATED(9007,"抱歉，您已参加该商品的拼团，不可再次参加。"),
    SPELL_GROUP_IS_NULL(9008,"团不存在"),
    SPELL_GROUP_IS_SUCCESS(9009,"团已成功"),
    SPELL_GROUP_TIME_IS_ERROR(9010,"拼团活动时间异常"),
    SPELL_GROUP_MAX_NUM(9011,"抱歉，当前活动的拼团数量已满，无法发起拼团，您可直接参加正在拼团的团。"),//活动开团数量限制
    SPELL_GROUP_JOIN_GOODS_MAX_NUM(9012,"拼团活动参加商品数量已达最大值"),
    SPELL_GROUP_OPEN_GOODS_MAX_NUM(9013,"拼团活动开团商品数量已达最大值"),
    SPELL_GROUP_LEADER_GROUP_MAX_NUM(9014,"您对该商品发起拼团次数达到最大值，无法对该商品再次发起拼团，您可参加或发起其他商品。"), //商品团长开团限制的
    SPELL_GROUP_GOODS_RULE_IS_NULL(9015,"拼团类型不存在"),
    SPELL_GROUP_GOODS_MUTEX(9016,"抱歉，您当前已参加其他商品的拼团，无法参加此商品的拼团。"),
    SPELL_GROUP_ACTIVITY_PARTICIPATE(9017,"抱歉，您已参加该商品的拼团，不可再次参加。"),
    SPELL_GROUP_IS_FI_POSTER_FOLLOW(9018,"只有通过裂变海报扫码或扫码关注才能参团"),
    SPELL_GROUP_JOIN_GROUP_MAX_NUM(9019,"拼团活动该商品参团数量已达最大值"),//商品参团限制的
    SPELL_GROUP_MEANWHILE_GROUP(9020,"抱歉，您当前已参加或发起该商品的团，无法再次参加此商品的团。"),
    HEADER_PRICE_NOT_NULL(9021,"团长优惠团长价不能为空"),
    UNABLE_TO_PARTICIPATE_SPELL_GROUP_ACTIVITY(9022,"抱歉，您当前不符合活动条件，无法参加。"),
    SPELL_GROUP_IS_END_NO_UPDATE(9023,"抱歉，活动已结束，无法修改"),
    SPELL_GROUP_IS_START_NO_UPDATE(9023,"抱歉，活动已开始，部分参数无法修改"),

    /**
     *	话术9201 - 9400
     * */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    SPEECHCRAFT_ACTIVITY_IS_NULL(9201,"话术不存在"),

    /**
* 短信发生错误的时候如果不成功返回的错误代码,去掉头一个数字 9 为短信供应商返回的实际代码
* */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 SENDSMS_ERROR_899999(899999 , "短信发送失败！"),
	 SENDSMS_ERROR_909000(909000 , "发送短信未能识别的错误代码"),
     SENDSMS_ERROR_909001(909001 , "签名格式不正确"),
     SENDSMS_ERROR_909002(909001,"参数未赋值"),
     SENDSMS_ERROR_909003(909003,"手机号码格式不正确 "),
     SENDSMS_ERROR_909006(909006,"用户accessKey不正确 "),
     SENDSMS_ERROR_909007(909007,"IP白名单限制"),
     SENDSMS_ERROR_909009(909009,"短信内容参数不正确"),
     SENDSMS_ERROR_909010(909010,"用户短信余额不足"),
     SENDSMS_ERROR_909011(909011,"用户帐户异常"),
     SENDSMS_ERROR_909012(909012,"日期时间格式不正确 "),
     SENDSMS_ERROR_909013(909013,"不合法的语音验证码，4~8位的数字"),
     SENDSMS_ERROR_909014(909014,"超出了最大手机号数量" ),
     SENDSMS_ERROR_909015(909015,"不支持的国家短信"),
     SENDSMS_ERROR_909016(909016,"无效的签名或者签名ID"),
     SENDSMS_ERROR_909017(909017,"无效的模板ID"),
     SENDSMS_ERROR_909018(909018,"单个变量限制为1-20个字 "),
     SENDSMS_ERROR_909019(909019,"内容不可以为空"),
     SENDSMS_ERROR_909021(909021,"主叫和被叫号码不能相同"),
     SENDSMS_ERROR_909022(909022,"手机号码不能为空"),
     SENDSMS_ERROR_909023(909023,"手机号码黑名单"),
     SENDSMS_ERROR_909024(909024,"手机号码超频"),
     SENDSMS_ERROR_910001(910001,"内容包含敏感词"),
     SENDSMS_ERROR_910002(910002,"内容包含屏蔽词"),
     SENDSMS_ERROR_910003(910003,"错误的定时时间 "),
     SENDSMS_ERROR_910004(910004,"自定义扩展只能是数字且长度不能超过4位 "),
     SENDSMS_ERROR_910005(910005,"模版类型不存在"),
     SENDSMS_ERROR_910006(910006,"模版和内容不匹配"),
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