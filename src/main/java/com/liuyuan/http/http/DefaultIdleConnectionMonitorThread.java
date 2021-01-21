package com.liuyuan.http.http;

import org.apache.http.conn.HttpClientConnectionManager;

import java.util.concurrent.TimeUnit;
/**
 * @Author: liuyuan
 * @Date: 2021/1/16 3:47 下午
 */
public class DefaultIdleConnectionMonitorThread extends Thread {
	/** 间隔时间 单位MILLISECONDS */
	private int INTERVAL =10 * 1000;
	/** idle阈值 单位MILLISECONDS */
	private int IDLE =  30 * 1000;

	private final HttpClientConnectionManager connMgr;
	
	private volatile boolean shutdown;

	public DefaultIdleConnectionMonitorThread(HttpClientConnectionManager connMgr, int INTERVAL, int IDLE) {
		super();
		this.connMgr = connMgr;
		this.INTERVAL = INTERVAL;
		this.IDLE = IDLE;
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(INTERVAL);
					// Close expired connections
					connMgr.closeExpiredConnections();
					// Optionally, close connections
					// that have been idle longer than 30 sec
					connMgr.closeIdleConnections(IDLE, TimeUnit.MILLISECONDS);
				}
			}
		} catch (InterruptedException ex) {
			// terminate
		}
	}

	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}
}