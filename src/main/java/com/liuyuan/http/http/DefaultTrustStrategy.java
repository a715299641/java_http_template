package com.liuyuan.http.http;

import org.apache.http.conn.ssl.TrustStrategy;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class DefaultTrustStrategy implements TrustStrategy {

	@Override
	public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		return true;
	}

}
