package com.efan.request.callback;

/**
 * Created by 一帆 on 2016/4/9.
 */
public interface Callback{
    public void onError(Exception e);
    public void onResponse(Object response);
}
