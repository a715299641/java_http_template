package com.liuyuan.http.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class DefaultServiceUnavailableRetryStrategy implements ServiceUnavailableRetryStrategy {
	private final Logger logger = LoggerFactory.getLogger(DefaultServiceUnavailableRetryStrategy.class);

	/**
	 * 间隔时间
	 */
	private int interval;
	private int executionCount;

	public DefaultServiceUnavailableRetryStrategy() {
		this(0, 0);
	}

	public DefaultServiceUnavailableRetryStrategy(int executionCount, int interval) {
		this.executionCount = executionCount;
		if (this.executionCount <= 0) {
			this.executionCount = 0;
		}
		this.interval = interval;
		if (this.interval <= 0) {
			this.interval = 0;
		}
	}

	@Override
	public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
		if (executionCount >= this.executionCount) {
			// 超过设置的最大次数则不再重试
			if (logger.isDebugEnabled()) {
				logger.debug("it has been three time.");
			}
			return false;
		}
		
		return false;
	}

	/**
	 * 间隔时间
	 */
	@Override
	public long getRetryInterval() {
		if (interval >= 0) {
			return interval;
		}
		return 50;
	}

}
