package com.efan.request.client;

import com.efan.request.config.TimeOutConfig;
import com.efan.request.request.Request;

/**
 * Created by 一帆 on 2016/4/9.
 */
public abstract class RequestClient {
    public TimeOutConfig timeOutConfig = new TimeOutConfig();

    public TimeOutConfig getTimeOutConfig() {
        return timeOutConfig;
    }

    public void setTimeOutConfig(TimeOutConfig timeOutConfig) {
        this.timeOutConfig = timeOutConfig;
    }

    public void cancel(Request request){

    }
    public void cancelAll(){

    }
    public abstract void get(Request request);
    public abstract void post(Request request);
    public abstract void patch(Request request);
    public abstract void delete(Request request);
}
