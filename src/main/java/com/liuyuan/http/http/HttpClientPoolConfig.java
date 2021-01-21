package com.liuyuan.http.http;

//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyStore;

/**
 * 默认http client配置
 * 
 * @author liuyuan
 *
 */
public class HttpClientPoolConfig /*extends GenericObjectPoolConfig*/ {

	final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 连接池最大连接
	 */
	private int maxTotal = 200;

	/**
	 * 连接池每个路由最大连接;分配给同一个route(路由)最大的并发连接数。 route：运行环境机器 到 目标机器的一条线路。
	 * 举例来说，我们使用HttpClient的实现来分别请求 www.baidu.com 的资源和 www.bing.com
	 * 的资源那么他就会产生两个route。
	 */
	private int defaultMaxPerRoute = 100;

	/**
	 * #启用/禁用带有指定超时值的 SO_TIMEOUT，以毫秒为单位。将此选项设为非零的超时值时，在与此 Socket 关联的 InputStream 上调用 read() 将只阻塞此时间长度。
	 * 如果超过超时值，将引发 java.net.#SocketTimeoutException，虽然 Socket 仍旧有效。
	 * 选项 必须在进入阻塞操作前被启用才能生效。超时值必须是 > 0 的数。超时值为 0 被解释为无穷大超时值
	 */
	private int soTimeout = 5 * 1000;
	/**
	 * 从连接池中获取连接的超时时间
	 * 单位毫秒
	 */
	private int connectionRequestTimeout = 5 * 1000;
	/**
	 * 
	 * 链接建立的时间：默认与服务器连接超时时间；套接字连接到服务器，并指定一个超时值。超时值零被解释为无限超时。在建立连接或者发生错误之前，连接一直处于阻塞状态，单位毫秒；
	 * 设置可防止防止阻塞，不设置会导致建立tcp链接时，阻塞，假死；
	 */
	private int connectTimeout = 5 * 1000;
	
	/**
	 * 默认socket读数据超时时间，等待数据的时间或者两个包之间的间隔时间，单位毫秒；
	 * 设置可防止防止阻塞，不设置会导致建立tcp链接时，阻塞，假死
	 */
	private int socketTimeout = 5 * 1000;
	
	/**
	 * pool连接存活时间，单位秒unit:second
	 */
	private int connTimeToLive = 300;

	/**
	 * 连接重试次数
	 */
	private int retry = 3;
	
	/**
	 * 业务调用重试次数
	 */
	private int busiRetry = 3;
	
	/**
	 * 服务器 keep-alive值
	 */
	private int keepalive = 30 * 1000;
	/**
	 * 重试间隔时间，单位毫秒
	 */
	private int interval = 10 * 1000;
	/**
	 * idle阈值，单位毫秒
	 */
	private int idle = 30 * 1000;

	/**
	 * 读取字节大小
	 */
	private int byteSize = 4096;

	private ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy = new DefaultServiceUnavailableRetryStrategy();

	/**
	 * 服务器 keep-alive值
	 */
	private DefaultConnectionKeepAliveStrategy connectionKeepAliveStrategy = new DefaultConnectionKeepAliveStrategy() {

		@Override
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			/*
			 * long keepAlive = super.getKeepAliveDuration(response, context); if (keepAlive
			 * == -1) { // 如果keep-alive值没有由服务器明确设置，那么保持连接持续5秒。 keepAlive = 5000; } return
			 * keepAlive;
			 */

			// Honor 'keep-alive' header
			HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
			while (it.hasNext()) {
				HeaderElement he = it.nextElement();
				String param = he.getName();
				String value = he.getValue();
				if (value != null && param.equalsIgnoreCase("timeout")) {
					try {
						return Long.parseLong(value) * 1000;
					} catch (NumberFormatException ignore) {
						return keepalive;
					}
				}
			}
			return keepalive;
		}

	};

	private HttpRequestRetryHandler httpRequestRetryHandler = new DefaultHttpRequestRetryHandler() {

		@Override
		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
			if (executionCount > getRetry()) {
				// 超过设置的最大次数则不再重试
				logger.debug("it has been {} time.", retry);
				return false;
			}
			// 服务端断掉客户端的连接异常
			if (exception instanceof NoHttpResponseException) {
				if (logger.isDebugEnabled()) {
					logger.debug("NoHttpResponseException");
				}
				return true;
			}
			if (exception instanceof InterruptedIOException) {
				// Timeout
				if (logger.isDebugEnabled()) {
					logger.debug("Timeout");
				}
				return true;
			}
			if (exception instanceof UnknownHostException) {
				// Unknown host
				if (logger.isDebugEnabled()) {
					logger.debug("Unknown host");
				}
				return false;
			}
			if (exception instanceof ConnectTimeoutException) {
				// Connection refused
				if (logger.isDebugEnabled()) {
					logger.debug("Connection refused");
				}
				return false;
			}
			if (exception instanceof SSLException) {
				// SSL handshake exception
				if (logger.isDebugEnabled()) {
					logger.debug("SSL handshake exception");
				}
				return false;
			}
			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
			if (idempotent) {
				// Retry if the request is considered idempotent
				if (logger.isDebugEnabled()) {
					logger.debug("considered idempotent");
				}
				return true;
			}
			return false;
		}

	};

	private DefaultTrustStrategy defaultTrustStrategy = new DefaultTrustStrategy();

	private KeyStore truststore;

	private TrustStrategy trustStrategy;

	public ServiceUnavailableRetryStrategy getServiceUnavailableRetryStrategy() {
		return serviceUnavailableRetryStrategy;
	}

	public void setServiceUnavailableRetryStrategy(ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy) {
		this.serviceUnavailableRetryStrategy = serviceUnavailableRetryStrategy;
	}

	public DefaultTrustStrategy getDefaultTrustStrategy() {
		return defaultTrustStrategy;
	}

	public void setDefaultTrustStrategy(DefaultTrustStrategy defaultTrustStrategy) {
		this.defaultTrustStrategy = defaultTrustStrategy;
	}

	public HttpRequestRetryHandler getHttpRequestRetryHandler() {
		return httpRequestRetryHandler;
	}

	public void setHttpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
		this.httpRequestRetryHandler = httpRequestRetryHandler;
	}

	public HttpClientPoolConfig() {
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}

	public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	public KeyStore getTruststore() {
		return truststore;
	}

	public void setTruststore(KeyStore truststore) {
		this.truststore = truststore;
	}

	public TrustStrategy getTrustStrategy() {
		return trustStrategy;
	}

	public void setTrustStrategy(TrustStrategy trustStrategy) {
		this.trustStrategy = trustStrategy;
	}

	public int getConnTimeToLive() {
		return connTimeToLive;
	}

	public void setConnTimeToLive(int connTimeToLive) {
		this.connTimeToLive = connTimeToLive;
	}

	public DefaultConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
		return connectionKeepAliveStrategy;
	}

	public void setConnectionKeepAliveStrategy(DefaultConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
		this.connectionKeepAliveStrategy = connectionKeepAliveStrategy;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public int getKeepalive() {
		return keepalive;
	}

	public void setKeepalive(int keepalive) {
		this.keepalive = keepalive;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getIdle() {
		return idle;
	}

	public void setIdle(int idle) {
		this.idle = idle;
	}

	public int getByteSize() {
		return byteSize;
	}

	public void setByteSize(int byteSize) {
		this.byteSize = byteSize;
	}

	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getBusiRetry() {
		return busiRetry;
	}

	public void setBusiRetry(int busiRetry) {
		this.busiRetry = busiRetry;
	}
}
