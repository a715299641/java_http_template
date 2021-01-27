package com.liuyuan.http.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class HttpClientFactoryBean extends HttpClientPoolConfig
		implements FactoryBean<HttpClientFactoryBean>, InitializingBean, DisposableBean {

	final Logger logger = LoggerFactory.getLogger(getClass());

	private PoolingHttpClientConnectionManager pool;

	public static DefaultIdleConnectionMonitorThread idleConnectionMonitorThread;

	@Override
	public HttpClientFactoryBean getObject() throws Exception {
		return this;
	}

	@Override
	public Class<?> getObjectType() {
		return HttpClientFactoryBean.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	@Override
	public void destroy() throws Exception {
		if (pool != null) {
			pool.shutdown();
		}
		idleConnectionMonitorThread.shutdown();
	}

	@Override
	public void afterPropertiesSet() throws Exception {

//		if (logger.isDebugEnabled()) {
			StringBuffer buffer = new StringBuffer("http client config Info: ");
			buffer.append("[connectionRequestTimeout: " + this.getConnectionRequestTimeout());
			buffer.append(", connectTimeout: " + this.getConnectTimeout());
			buffer.append(", socketTimeout: " + this.getSocketTimeout());
			buffer.append(", retry: " + this.getRetry());
			buffer.append(", busiRetry: " + this.getBusiRetry());
			buffer.append(", maxTotal: " + this.getMaxTotal());
			buffer.append(", defaultMaxPerRoute: " + this.getDefaultMaxPerRoute());
			buffer.append(", soket config soTimeout: " + this.getSoTimeout());
			buffer.append(", connTimeToLive: " + this.getConnTimeToLive());
			buffer.append("]");

			logger.debug(buffer.toString());

			this.initPool();
//		}
	}

	/**
	 * 初始化pool
	 * 
	 * @throws Exception
	 */
	private void initPool() throws Exception {
		SSLContext sslcontext = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//			sslcontext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, this.getDefaultTrustStrategy())
//					.build();
			sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore,this.getDefaultTrustStrategy()).build();
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (KeyStoreException e) {
			throw new RuntimeException(e);
		}
//		sslcontext.init(null, new TrustManager[] { new X509TrustManager() {
//			@Override
//			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//			}
//
//			@Override
//			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//			}
//
//			@Override
//			public X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//		} }, null);
//		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);// 4.3.5

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
		pool = new PoolingHttpClientConnectionManager(r, null, null, null, this.getConnTimeToLive(), TimeUnit.SECONDS);
		pool.setMaxTotal(this.getMaxTotal());
		pool.setDefaultMaxPerRoute(this.getDefaultMaxPerRoute());
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(this.getSoTimeout()).build();
		pool.setDefaultSocketConfig(socketConfig);

		idleConnectionMonitorThread = new DefaultIdleConnectionMonitorThread(pool, this.getInterval(), this.getIdle());

		idleConnectionMonitorThread.start();
	}

	/**
	 * 获取httpclient实例，可自定义http client参数
	 * 如果需要 hc 可改为返回HttpClientBuilder
	 * @return
	 * @throws Exception
	 */
	public CloseableHttpClient getHttpClient(RequestConfig cfg) throws Throwable {
		return this.getCloseableHttpClient(cfg == null ? getRequestConfig() : cfg);
	}
	/**
	 * 获取默认 Request Config ，可以重写该方法自定义http client参数；
	 * @return
	 * @throws Exception
	 */
	public RequestConfig getRequestConfig() throws Exception{
		return RequestConfig.custom()
				.setConnectionRequestTimeout(getConnectionRequestTimeout())
				.setConnectTimeout(getConnectTimeout()).setSocketTimeout(getSocketTimeout())
				.setRedirectsEnabled(true).build();
	}
	
	/**
	 * get connection
	 * 
	 * @param requestConfig
	 * @param connManager
	 * @param retryHandler
	 * @param serviceUnavailStrategy
	 * @param keepAliveStrategy
	 * @return
	 * @throws Exception
	 */
	private CloseableHttpClient getCloseableHttpClient(RequestConfig requestConfig) throws Exception {
		System.out.println(pool);
		return HttpClients.custom().setConnectionManager(pool).setRetryHandler(this.getHttpRequestRetryHandler())
				.setServiceUnavailableRetryStrategy(this.getServiceUnavailableRetryStrategy()).setKeepAliveStrategy(this.getConnectionKeepAliveStrategy())
				.setDefaultRequestConfig(requestConfig).build();
	}

}
