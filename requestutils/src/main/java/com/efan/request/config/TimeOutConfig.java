package com.efan.request.config;

/**
 * Created by 一帆 on 2016/4/10.
 */
public class TimeOutConfig {
    private long connectTimeOut = 10_000;
    private long writeTimeOut = 10_000;
    private long ReadTimeOut = 10_000;

    public TimeOutConfig() {
    }

    public TimeOutConfig(long connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public TimeOutConfig(long connectTimeOut, long writeTimeOut, long readTimeOut) {
        this.connectTimeOut = connectTimeOut;
        this.writeTimeOut = writeTimeOut;
        ReadTimeOut = readTimeOut;
    }

    public long getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(long connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public long getWriteTimeOut() {
        return writeTimeOut;
    }

    public void setWriteTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
    }

    public long getReadTimeOut() {
        return ReadTimeOut;
    }

    public void setReadTimeOut(long readTimeOut) {
        ReadTimeOut = readTimeOut;
    }
}
