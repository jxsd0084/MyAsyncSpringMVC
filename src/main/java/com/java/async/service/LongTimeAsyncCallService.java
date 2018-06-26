package com.java.async.service;

import com.java.async.support.LongTermTaskCallback;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jakeChen
 * @Description:
 * @date 26/06/2018
 */
public class LongTimeAsyncCallService {

	private final int CorePoolSize = 4;
	private final int NeedSeconds = 3;

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CorePoolSize);



	public void makeRemoteCallAndUnknownWhenFinish(LongTermTaskCallback callback) {
		System.out.println("完成此任务需要 : " + NeedSeconds + " 秒");
		scheduler.schedule(new Runnable() {
			@Override
			public void run() {
				callback.callback("长时间异步调用完成.");
			}
		}, 0L, TimeUnit.SECONDS);
	}

	

}
