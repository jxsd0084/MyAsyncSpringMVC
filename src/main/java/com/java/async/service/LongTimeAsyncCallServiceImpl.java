package com.java.async.service;

import com.java.async.callback.LongTermTaskCallback;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jakeChen
 * @Description:
 * @date 26/06/2018
 */
@Service("longTimeAsyncCallService")
public class LongTimeAsyncCallServiceImpl
// implements LongTimeAsyncCallService
{

	private final int CORE_POOL_SIZE = 4;
	private final int NEED_SECONDS = 3;

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CORE_POOL_SIZE);



	public void makeRemoteCallAndUnknownWhenFinish(LongTermTaskCallback callback) {
		System.out.println("完成此任务需要 : " + NEED_SECONDS + " 秒");
		scheduler.schedule(() -> callback.callback("长时间异步调用完成."), NEED_SECONDS, TimeUnit.SECONDS);
	}



	@Async
	// @Override
	public void asyncDoSth() {
	
		try {

			System.out.println("async do sth start");

			Thread.sleep(5000L);

			System.out.println("async do sth stop");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
