package com.liuyuan.http.util;


import com.liuyuan.http.http.HttpClientConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class JAXBUtils {
	private final static Logger logger = LoggerFactory.getLogger(JAXBUtils.class);
	private final static int BYTESIZE_4096 = 4096;
	private static ConcurrentMap<String, JAXBContext> jaxbContextMap = new ConcurrentHashMap<String, JAXBContext>();

	/**
	 * 
	 * @Title: 获取JAXBContext实例。
	 * @author: luoyintong
	 * @date: 2018年5月2日
	 * @Description: 获取JAXBContext实例。
	 * @param c
	 * @return
	 *
	 */
	private static final JAXBContext getJaxbContext(Class<?> c) {
		if (c == null) {
			return null;
		}
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = jaxbContextMap.get(c.getName());
			if (jaxbContext == null) {
				jaxbContext = JAXBContext.newInstance(c);
				jaxbContextMap.put(c.getName(), jaxbContext);
			}
		} catch (JAXBException e) {
			logger.error("JAXBContext fail.", e);
		}
		return jaxbContext;
	}

	/**
	 * 
	 * @Title: 将实体类转序列化为对应String类型xml节点
	 * @author: luoyintong
	 * @date: 2018年5月2日
	 * @Description: 将实体类转序列化为对应String类型xml节点
	 * @param obj
	 * @return
	 *
	 */
	public static final String modelToXml(Object obj) {
		if (obj == null) {
			return null;
		}
		JAXBContext jaxbContext = getJaxbContext(obj.getClass());
		if (jaxbContext == null) {
			return null;
		}
		// Marshaller和Unmarshaller对象是线程不安全的，切勿进行重用
		Marshaller marshaller = null;
		try {
			// 得到序列化实例Marshaller
			if (marshaller == null) {
				marshaller = jaxbContext.createMarshaller();
				// 设置序列化的编码格式
				marshaller.setProperty(Marshaller.JAXB_ENCODING, HttpClientConstant.UTF_8);
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // 是否省略xm头声明信息
				// 设置格式化输出
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			}
		} catch (JAXBException e) {
			logger.error("getUnmarshaller fail.", e);
		}
		if (marshaller == null) {
			return null;
		}

		StringWriter writer = new StringWriter();
		try {
			marshaller.marshal(obj, writer);
		} catch (Exception e) {
			logger.error("modelToXml fail.", e);
			return null;
		}

		return writer.toString();

	}

	/**
	 * 
	 * @Title: 将xml节点转序列化为对应类型实体类
	 * @author: luoyintong
	 * @date: 2018年5月2日
	 * @Description: 将xml节点转序列化为对应类型实体类
	 * @param xml
	 * @param clz
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T xmlToModel(String xml, Class<T> clz) {
		if (xml == null || clz == null) {
			return null;
		}
		JAXBContext jaxbContext = getJaxbContext(clz);
		if (jaxbContext == null) {
			return null;
		}
		// Marshaller和Unmarshaller对象是线程不安全的，切勿进行重用
		Unmarshaller unmarshaller = null;
		try {
			// 得到反序列化实例Unmarshaller
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			logger.error("getUnmarshaller fail.", e);
		}
		if (unmarshaller == null) {
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("xmlToModel startTime:" + System.currentTimeMillis());
		}

		T ret = null;
		try {
			ret = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			logger.error("xmlToModel fail.", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("xmlToModel endTime:" + System.currentTimeMillis());
		}

		return ret;

	}

	/**
	 * 
	 * @Title: 将流转序列化为对应类型实体类
	 * @author: luoyintong
	 * @date: 2018年5月2日
	 * @Description: 将流转序列化为对应类型实体类
	 * @param in
	 * @param clz
	 * @return
	 *
	 */
	public static <T> T inputStreamToModel(InputStream in, Class<T> clz) {
		if (in == null || clz == null) {
			return null;
		}
		JAXBContext jaxbContext = getJaxbContext(clz);
		if (jaxbContext == null) {
			return null;
		}
		// Marshaller和Unmarshaller对象是线程不安全的，切勿进行重用
		Unmarshaller unmarshaller = null;
		try {
			// 得到反序列化实例Unmarshaller
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			logger.error("getUnmarshaller fail.", e);
		}
		if (unmarshaller == null) {
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("inputStreamToModel startTime:" + System.currentTimeMillis());
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[BYTESIZE_4096];
		T ret = null;
		try {
			int n = 0;
			while ((n = in.read(buffer)) != -1) {
				out.write(buffer, 0, n);
			}

			ret = bytesToModel(out.toByteArray(), clz);

		} catch (Exception e) {
			logger.error("inputStreamToModel fail.", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("inputStreamToModel endTime:" + System.currentTimeMillis());
		}

		return ret;
	}

	/**
	 * 
	 * @Title: 将二进制转序列化为对应类型实体类
	 * @author: luoyintong
	 * @date: 2018年5月2日
	 * @Description: 将二进制转序列化为对应类型实体类
	 * @param data
	 * @param clz
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T bytesToModel(byte[] data, Class<T> clz) {
		if (data == null || clz == null) {
			return null;
		}
		JAXBContext jaxbContext = getJaxbContext(clz);
		if (jaxbContext == null) {
			return null;
		}
		// Marshaller和Unmarshaller对象是线程不安全的，切勿进行重用
		Unmarshaller unmarshaller = null;
		try {
			// 得到反序列化实例Unmarshaller
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			logger.error("getUnmarshaller fail.", e);
		}
		if (unmarshaller == null) {
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("bytesToModel startTime:" + System.currentTimeMillis());
		}
		T ret = null;
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
			ret = (T) unmarshaller.unmarshal(inputStream);

		} catch (Exception e) {
			logger.error("bytesToModel fail.", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("bytesToModel endTime:" + System.currentTimeMillis());
		}

		return ret;
	}

	/**
	 * 
	 * @Title: 将报文节点反序列化为实体类
	 * @author: luoyintong
	 * @date: 2018年5月2日
	 * @Description: 将报文节点反序列化为实体类
	 * @param obj
	 * @param node
	 * @return
	 *
	 */
	public static final Object documentToModel(Class<?> obj, Node node) {
		if (obj == null || node == null) {
			return null;
		}
		JAXBContext jaxbContext = getJaxbContext(obj);
		if (jaxbContext == null) {
			return null;
		}
		// Marshaller和Unmarshaller对象是线程不安全的，切勿进行重用
		Unmarshaller unmarshaller = null;
		try {
			// 得到反序列化实例Unmarshaller
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			logger.error("getUnmarshaller fail.", e);
		}
		if (unmarshaller == null) {
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("documentToModel startTime:" + System.currentTimeMillis());
		}

		Object ret = null;

		try {
			ret = unmarshaller.unmarshal(node);
		} catch (JAXBException e) {
			logger.error("documentToModel fail.", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("documentToModel endTime:" + System.currentTimeMillis());
		}

		return ret;
	}

	/**
	 * 将实体类转序列化为对应node节点
	 * 
	 * @param obj
	 *            实体类
	 * @param node
	 *            创建的新节点
	 * @return
	 */
	public static final Node modelToNode(Class<?> obj, Node node) {
		if (obj == null || node == null) {
			return null;
		}
		JAXBContext jaxbContext = getJaxbContext(obj);
		if (jaxbContext == null) {
			return null;
		}
		// Marshaller和Unmarshaller对象是线程不安全的，切勿进行重用
		Marshaller marshaller = null;
		try {
			// 得到序列化实例Marshaller
			if (marshaller == null) {
				marshaller = jaxbContext.createMarshaller();
				// 设置序列化的编码格式
				marshaller.setProperty(Marshaller.JAXB_ENCODING, HttpClientConstant.UTF_8);
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // 是否省略xm头声明信息
				// 设置格式化输出
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			}
		} catch (JAXBException e) {
			logger.error("getUnmarshaller fail.", e);
		}
		if (marshaller == null) {
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("modelToNode startTime:" + System.currentTimeMillis());
		}

		try {
			marshaller.marshal(obj, node);
		} catch (JAXBException e) {
			logger.error("modelToNode fail.", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("modelToNode startTime:" + System.currentTimeMillis());
		}

		return node;

	}
}