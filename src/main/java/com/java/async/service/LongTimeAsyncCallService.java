package com.java.async.service;

import com.java.async.callback.LongTermTaskCallback;

/**
 * @author jakeChen
 * @Description:
 * @date 27/06/2018
 */
public interface LongTimeAsyncCallService {


    void callback(LongTermTaskCallback callback);

    void asyncMethod();

}
