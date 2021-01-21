package com.liuyuan.http.util;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * <code>java.lang.String</code>辅助类, 对字符串的一系列函数。
 *
 @Author: liuyuan
  * @Date: 2021/1/16 3:47 下午
 */
public class StringHelper {
	/**
	 * 下划线,很多情况下它作为字符串的分隔符.
	 */
	public static final String UNDERLINE = "_";

	/**
	 * 字符 A 的 int值.取字符的时候计算使用.
	 */
	private static final int CHAR_START_POSITION = 65;

	/**
	 * 随机生成器.
	 */
	private static Random random = new Random(47);

	/**
	 * 仅使用数字。
	 */
	public static final int NUMBER_ONLY = 0x1;

	/**
	 * 仅使用字母.
	 */
	public static final int ALPHABETIC_ONLY = 0x2;

	/**
	 * 数字字母混合.
	 */
	public static final int MIXED = 0x3;

	public static final String EMPTY = "";
	
	public static final String BLANK = " ";

	/**
	 * @Deprecated 不要使用,应用启动一次,随机数就会重复
	 * 生成随机码,默认是六位.由数字和字符组成(英文的,包括大小写).
	 * 
	 * @return
	 */
	@Deprecated
	public static String genCodeWithCharsAndNumbers() {
		try {
			return genRandomCodes(6, MIXED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取随机码,可以指定位数, 类型（1.数字，2.字母，3.数字和字母的组合）
	 * 
	 * @param length
	 *            要声称的字符串的长度.
	 * @param type
	 *            类型 （1.数字，2.字母，3.数字和字母的组合）</br>
	 *            <font color="red">如果指定的值不等1 也不等于2,将按3来处理，即 按照数字字母混合来处理.</font>
	 * @return
	 */
	public static String genRandomCodes(int length, int type) throws IllegalArgumentException {
		if (length < 6) { // 六位起,太少了纯蛋疼,不伺候
			throw new IllegalArgumentException("要生成随机码好歹也六位以上啊");
		}

		if (type != NUMBER_ONLY && type != ALPHABETIC_ONLY) {
			type = MIXED;
		}
		StringBuilder sb = new StringBuilder();
		if (type == MIXED) {// 首先保证必须有一个数字一个字符
			boolean numberFirst = random.nextBoolean();
			if (numberFirst) {
				sb.append(getOneNumber(false));
				sb.append(getOneChar());
			} else {
				sb.append(getOneChar());
				sb.append(getOneNumber(false));
			}
			length = length - 2;
		}
		for (int index = 0; index < length; index++) {
			if (type == NUMBER_ONLY) {
				sb.append(getOneNumber(false));
			} else {
				// 混合型的情况下,听天由命
				boolean useNum = MIXED == type && random.nextBoolean();
				if (useNum) {
					sb.append(getOneNumber(false));
				} else {
					sb.append(getOneChar());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @param useZero
	 *            使用使用0. 获取一个 1-9之间的数字.不取 0 这个值,避免与 字母 0 分不清.
	 * @return
	 */
	private static int getOneNumber(boolean useZero) {
		if (useZero) {
			return Math.abs(random.nextInt()) % 10;
		}
		return (Math.abs(random.nextInt()) % 9) + 1;
	}

	/**
	 * 生成一个字符.
	 * 
	 * @param useChar
	 * @param startPosition
	 * @return
	 */
	private static char getOneChar() {
		int orginalCharIntValue = genAcharExclude_i_InUpperCase();
		boolean useLower = random.nextBoolean();// 使用小写？？
		if (useLower) {
			orginalCharIntValue += 32;
		}
		return (char) orginalCharIntValue;
	}

	/**
	 * 获取一个确保不是 大写字母 I 的字母.
	 * 
	 * @param startPosition
	 * @return
	 */
	private static int genAcharExclude_i_InUpperCase() {
		int charInInt = 73;// 大写的 I
		while (charInInt == 73) {// 排除 大写的 I
			charInInt = CHAR_START_POSITION + Math.abs(random.nextInt()) % 26;
		}
		return charInInt;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @return boolean 空返回true 不为空返回lase
	 */
	public static boolean isEmpty(String str) {
		if (str == null || EMPTY.equals(str.trim()) || "null".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符不为NULL，""，null值
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String trim(String str) {
		return isEmpty(str) ? str : str.trim();
	}

	/**
	 * 字符串为空，返回null
	 * 
	 * @param str
	 * @return
	 */
	public static String setNull(String str) {
		if (StringHelper.isEmpty(str)) {
			return null;
		}
		return str;
	}

	/**
	 * 从开始位置，删除指定个数的字符串
	 * 
	 * @return
	 */
	public static String delStartChar(String src, int len) {
		if (null != src && src.length() > 0) {
			src = src.substring(len);
		}
		return src;
	}

	/**
	 * 从结束位置，删除指定个数的字符串
	 * 
	 * @param src
	 *            源字符串
	 * @param len
	 *            要删除的长度
	 * @return
	 */
	public static String delEndChar(String src, int len) {
		if (null != src && src.length() > 0) {
			src = src.substring(0, src.length() - len);
		}
		return src;
	}

	/**
	 * 将给定的params按顺序拼接起来
	 * 
	 * @param params
	 *            需要拼接的参数
	 * @return
	 */
	public static String append(Serializable... params) {
		StringBuilder sb = new StringBuilder(100);
		for (Serializable s : params) {
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 如果是null或者空字符串,返回默认值，否则返回字符串本身.
	 * 
	 * @param source
	 *            原字符串.
	 * @param defaultValue
	 *            默认值.
	 * @return
	 */
	public static String makeSureNotEmpty(String source, String defaultValue) {
		return isNotEmpty(source) ? source : defaultValue;
	}

	/**
	 * 如果是null或者空字符串,返回""，否则返回字符串本身.
	 * 
	 * @param source
	 * @return
	 */
	public static String makeSureNotEmpty(String source) {
		return isNotEmpty(source) ? source : EMPTY;
	}

	/**
	 * 清空左右两边空格
	 * 
	 * @param cs
	 * @return
	 */
	public static String lrTrim(CharSequence cs) {
		if (null == cs)
			return null;
		if (cs instanceof String)
			return ((String) cs).trim();
		int length = cs.length();
		if (length == 0)
			return cs.toString();
		int l = 0;
		int last = length - 1;
		int r = last;
		for (; l < length; l++) {
			if (!Character.isWhitespace(cs.charAt(l)))
				break;
		}
		for (; r > l; r--) {
			if (!Character.isWhitespace(cs.charAt(r)))
				break;
		}
		if (l > r)
			return EMPTY;
		else if (l == 0 && r == last)
			return cs.toString();
		return cs.subSequence(l, r + 1).toString();
	}

	/**
	 * 字符串左边补0
	 * 
	 * @param str
	 *            字符串
	 * @param num
	 *            补0后长度
	 * @return
	 */
	public static String leftWithZero(String str, int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num - str.length(); i++) {
			sb.append("0");
		}
		return sb.append(str).toString();
	}
	
	/**
	 * 填充字符
	 * @param source
	 * @param fillSize
	 * @param fill 填充的字符串
	 * @param left true：左填充  false：右填充
	 * @return
	 */
	public static String fill(String source, int fillSize, String fill, boolean left) {
		if(left) {
		   source = StringUtils.leftPad(source, fillSize, fill);
		}else {
		   source = StringUtils.rightPad(source, fillSize, fill);
		}
		return source;
	}
	
	/**
	 * 是否数字
	 * 数字返回true 非数字返回false
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if(isEmpty(str)) return false;
		for (int i = str.length(); --i >= 0;) {
			if(i == str.length() || i == 0) {
			   if('.' == str.charAt(i)) return false;
			}
			if(!Character.isDigit(str.charAt(i)) && '.' != str.charAt(i)) {
			   return false;
			}
		}
		return true;
	}

	/**
	 * 用分隔符将字符串数组连接成字符串
	 * 
	 * @param args
	 *            字符串数组
	 * @param sep
	 *            分隔符
	 * @return
	 */
	public static final String join(String[] args, String sep) {
		StringBuilder buf = new StringBuilder(256);
		int j = args.length - 1;
		for (int i = 0; i < j; i++) {
			buf.append(args[i]).append(sep);
		}
		buf.append(args[j]);
		return buf.toString();
	}

	/**
	 * 清除特殊字符
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll(EMPTY).trim();
	}
	
	public static String filterRegEx(final String source, final String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		StringBuilder filter = new StringBuilder();
		while (matcher.find()){
			filter.append(matcher.group()).append(";");
		}
		return filter.toString();
	}

	/**
	 * 比较两个字符串是否都为空或者equals
	 * 
	 * @param str1
	 * @param str2
	 */
	public static boolean bothEmptyOrEquals(String str1, String str2) {
		if (isEmpty(str1) && isEmpty(str2)) {
			return true;
		}
		return (isEmpty(str1) ? EMPTY : str1).equals(isEmpty(str2) ? EMPTY : str2);
	}

	/**
	 * 将字符串首字母大写
	 * 
	 * @param s
	 *            字符串
	 * @return 首字母大写后的新字符串
	 */
	public static String upperFirst(CharSequence s) {
		if (null == s)
			return null;
		int len = s.length();
		if (len == 0)
			return EMPTY;
		char c = s.charAt(0);
		if (Character.isUpperCase(c))
			return s.toString();
		return new StringBuilder(len).append(Character.toUpperCase(c)).append(s.subSequence(1, len)).toString();
	}

	/**
	 * 首字母小写.
	 * 
	 * @param str
	 * @return
	 */
	public static String lowerFirst(String str) {
		String charAt = String.valueOf(str.charAt(0));
		return str.replaceFirst(charAt, charAt.toLowerCase());
	}

	/**
	 * 判断char 是不是大写英文字母的.
	 * 
	 * @param chr
	 * @return
	 */
	private static boolean isUpperEnglishCharacter(char chr) {
		int upperStart = 65;// 'A'
		int upperEnd = 90;// 'Z'
		int code = (int) chr;
		return code >= upperStart && code <= upperEnd;
	}

	/**
	 * 判断char 是不是大写英文字母的.
	 * 
	 * @param chr
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean isLowerEnglishCharacter(char chr) {
		int lowerStart = 97;// 'a'
		int lowerEnd = 122;// 'z'
		int code = (int) chr;
		return code >= lowerStart && code <= lowerEnd;
	}

	/**
	 * 按照驼峰命名法格式化字符串.默认的分隔符是 "_"
	 * 
	 * @param orgi
	 * @return
	 */
	public static String camelCase(String orgi) {
		return camelCase(orgi, UNDERLINE);
	}

	/**
	 * 按照驼峰命名法格式化字符串.
	 * 
	 * @param orgi
	 * @param split
	 * @return
	 */
	public static String camelCase(String orgi, String split) {
		if (isEmpty(split)) {
			split = UNDERLINE;
		}
		String[] arrs = orgi.split(split);
		String result = arrs[0];// 先取出第一个
		for (String sub : arrs) {
			if (sub.equals(result)) {// 排除第一个
				continue;
			}
			result += upperFirst(sub);
		}
		return result;
	}

	/**
	 * 反驼峰化.</br>
	 * 遇到大写的就将它转化为小写,然后前面加个分隔符,首字母只小写,不加分隔符.</br>
	 * 例子： 传入 StringHelper ,split 为 下划线(_),则输出 string_helper</br>
	 * 传入 TSysCfg split 为 (_) 输出 t_sys_cfg.
	 * 
	 * @param str
	 * @param split
	 *            分隔符
	 * @return
	 */
	public static String deCamelBak(String str, String split) {
		StringBuffer sb = new StringBuffer();
		for (int index = 0; index < str.length(); index++) {
			char charAt = str.charAt(index);
			if (isUpperEnglishCharacter(charAt)) {
				char lower = (char) (charAt + 32);
				if (index > 0) {
					sb.append(split);
				}
				sb.append(lower);
			} else {
				sb.append(charAt);
			}
		}
		return sb.toString();
	}

	/**
	 * 反驼峰化.分隔符使用 下划线 (_)
	 * 
	 * @param str
	 * @return
	 */
	public static String deCamelBak(String str) {
		if (isEmpty(str)) {
			return str;
		}
		return deCamelBak(str, UNDERLINE);
	}

	/**
	 * 弃用 过滤字符串中的 Utf8Mb4 字符集中的 特殊字符.
	 * 
	 * @param original
	 *            原始值.
	 * @return
	 */
	public static String ignoreUtf8Mb4Chars(String original) {
		return original;
	}
	
	/**
	 * 过滤字符串中的 Utf8Mb4 字符集中的 特殊字符.
	 * 
	 * @param original
	 *            原始值.
	 * @return
	 */
	public static String doIgnoreUtf8Mb4Chars(String original) {
		if (isEmpty(original)) {
			return original;
		}
		byte[] b_text = original.getBytes();
		List<Byte> buffer = new ArrayList<>();
		for (int i = 0; i < b_text.length; i++) {
			if ((b_text[i] & 0xF8) == 0xF0) {
				i += 3;
			} else {
				buffer.add(b_text[i]);
			}
		}
		Byte[] bytes = buffer.toArray(new Byte[buffer.size()]);
		byte[] byteTarget = new byte[bytes.length];
		for (int i = 0; i < byteTarget.length; i++) {
			byteTarget[i] = bytes[i].byteValue();
		}

		return new String(byteTarget);
	}
	/**
	 * 判断两个字符串不是 equals.
	 * 
	 * @param first
	 * @param second
	 * @param treatNullAsEmptyString
	 *            将null当作空字符串来比较,如果不填,默认为false.
	 * @return
	 */
	public static boolean isEquals(String first, String second, boolean... treatNullAsEmptyString) {
		boolean nullIsEmpty = false;
		if (null != treatNullAsEmptyString) {
			nullIsEmpty = treatNullAsEmptyString[0];// 多余的忽略
		}
		if (first == second) {// 相等包括了两个都为空
			return true;
		}
		boolean firstIsNull = null == first;
		boolean secondIsNull = null == second;
		if (!firstIsNull && !secondIsNull) {// 两个都不为空
			return first.equals(second);
		}
		if (nullIsEmpty) {
			first = firstIsNull ? EMPTY : first;
			second = secondIsNull ? EMPTY : second;
		} else {
			return false;
		}

		return first.equals(second);
	}
	
	/**
	 * 计算字符串真实长度
	 * @param str
	 * @return
	 */
	public static int getStrLength(String str) {
		int len = 0;
		char arr[] = str.toCharArray();
		for (int index = 0; index < arr.length; index++) {
			char c = arr[index];
			// 中文字符(根据Unicode范围判断),中文字符长度为2
			if ((c >= 0x0391 && c <= 0xFFE5)) {
				len = len + 2;
			} else if ((c >= 0x0000 && c <= 0x00FF)){ // 英文字符
				len = len + 1;
			}
		}
		return len;
	}

	/**
	 * @param tpl
	 *            模版,变量名用 ${} 包裹.如 ${var1}
	 * @param obj
	 *            普通的javabean.
	 * @return
	 * @throws IllegalArgumentException
	 *             当传入数组或者集合的时候抛出非法参数异常.
	 */
	@SuppressWarnings("unchecked")
//	public static String formatTpl(String tpl, Object obj) throws IllegalArgumentException {
//		obj = null == obj ? new Object() : obj;
//		Class<? extends Object> objClass = obj.getClass();
//		boolean isCollection = Collection.class.isAssignableFrom(objClass);
//		if (objClass.isArray() || isCollection) {
//			throw new IllegalArgumentException("不接受数据或者集合类型的参数.");
//		}
//		Map<String, Object> describe = new HashMap<String, Object>();
//		if (null != obj) {
//			describe = CglibUtil.describe(obj);
//		}
//		return formatTpl(tpl, describe);
//	}
//
//	public static String formatTpl(String tpl, Map<String, Object> obj) {
//		Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z_0-9\\.]+\\}");
//		Matcher matcher = pattern.matcher(tpl);
//		String result = tpl;
//		while (matcher.find()) {
//			String group = matcher.group();
//			String varName = group.substring(2, group.length() - 1);
//			Object varVal = obj.get(varName);
//			varVal = (null == varVal) ? EMPTY : varVal;// 保证不抛错
//			String replace = null;
//			if (Date.class.isAssignableFrom(varVal.getClass())) {
//				replace = DateHelper.format((Date) varVal, DateHelper.FORMAT_TIME);
//			} else if (String.class.isAssignableFrom(varVal.getClass()) || varVal.getClass().isPrimitive()) {
//				// 数组或者集合
//				replace = varVal.toString();
//			} else if (varVal.getClass().isArray() || Collection.class.isAssignableFrom(varVal.getClass())) {
//				// 数组或者集合
//				replace = JSON.toJSONString(varVal);
//			}
//
//			String regex = "\\$\\{" + varName + "\\}";
//			result = result.replaceAll(regex, replace);
//		}
//		return result;
//	}

	/**
	 * 删除开始和结束包含指定字符串 （多个连续）
	 * 同时删除字符串内部包含的空格（多个连续）
	 * @param input
	 * @param trimStr
	 * @param replacement
	 * @return
	 */
	public final static String trim(String input, final String trimStr, final String replacement) {
		String result = input.replaceAll("^(" + trimStr + ")+|(" + trimStr + ")*$", replacement);
		result = result.replaceAll("\\s+", EMPTY);
		return result;
	}
	
	public final static String trimBlank(String input) {
		String result = input.replaceAll("\\s+", EMPTY);
		return result;
	}
	
	/**
	 * 切分数字字符串，每3位加  “,”
	 * @param input
	 * @return
	 */
	public final static String cutDigit(double digit) {
		//if(!isNumeric(input)) return input;
		String input = String.valueOf(digit);
		int inputLen = input.length();
		if(inputLen <= 3) return input;
		
		String intPart = (input.contains(".")) ? input.split("\\.")[0] : input;	
		inputLen = intPart.length();
		StringBuilder value = new StringBuilder();
		for(int i = inputLen - 1, j = 0; i >= 0; i--,j++) {
			if(j > 0 && j < inputLen && j % 3 == 0) {
			   value.append(",");	
			}
			value.append(intPart.charAt(i));
		}
	    String result = StringUtils.reverse(value.toString());
	    if(input.contains(".")) {
	       result += "." + input.split("\\.")[1];
	    }
		return result;
	}
	
	public final static Long toHash(String value) {
		Long seed = 131L, hash = 0L;
		for(int i = 0; i < value.length(); i++) {
			hash = (hash * seed) + value.charAt(i);
		}
		return hash;
	}

    
    /**
	 * 返回key=value&key=value格式
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String parseURLPair(Object o) throws Exception {
		Class<? extends Object> c = o.getClass();
		Field[] fields = c.getDeclaredFields();
		Map<String, Object> map = new TreeMap<>();
		for (Field field : fields) {
			field.setAccessible(true);
			String name = field.getName();
			Object value = field.get(o);
			if (value != null) {
				map.put(name, value);
			}
		}
		Set<Map.Entry<String, Object>> set = map.entrySet();
		Iterator<Map.Entry<String, Object>> it = set.iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry<String, Object> e = it.next();
			sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

	public static String concatUri(String url, String param) {
		if (url == null || "".equals(url.trim())) {
			return param;
		}
		if (param == null || "".equals(param.trim())) {
			return url;
		}
		url = url.trim();
		url = url.replaceAll("\\\\", "/");
		param = param.trim();
		param = param.replaceAll("\\?", "");
		if (!url.contains("?")) {
			url = url.concat("?");
		}
		if (url.endsWith("?") || url.endsWith("&")) {
			if (param.startsWith("&") ) {
				return url.concat(param.substring(1));
			}
			return url.concat(param);
		}
		if (param.startsWith("&")) {
			return url.concat(param);
		}
		return url.concat("&").concat(param);

	}
	
}