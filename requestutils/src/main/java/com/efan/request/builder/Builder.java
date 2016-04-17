package com.efan.request.builder;

import com.efan.request.RequestUtils;
import com.efan.request.callback.Callback;
import com.efan.request.request.Params;
import com.efan.request.request.Request;
import com.efan.request.response.Headers;

/**
 * Created by 一帆 on 2016/4/9.
 */
public abstract class Builder {
    protected Request request;
    protected Headers.Builder headersBuilder = new Headers.Builder();
    protected Params.Builder paramsBuilder = new Params.Builder();

    public Builder() {
        request = new Request();
    }

    public Builder(Request request) {
        this.request = request;
    }

    public Builder url(String url) {
        request.setUrl(url);
        return this;
    }

    public Builder params(Params params) {
        request.setParams(params);
        return this;
    }

    public Builder addParams(String key, String value) {
        paramsBuilder.add(key, value);
        return this;
    }

    public Builder headers(Headers headers) {
        request.setHeaders(headers);
        return this;
    }

    public Builder addHeader(String key, String value) {
        headersBuilder.add(key, value);
        return this;
    }

    public Builder addCallback(Callback callback) {
        request.setCallback(callback);
        return this;
    }

    public Request generate(){
        return request;
    }

    public abstract Builder build();

    public void execute(){
        request.setHeaders(headersBuilder.build());
        request.setParams(paramsBuilder.build());
        RequestUtils.getInstance().execute(request);
    }

    public void execute(Callback callback){
        request.setHeaders(headersBuilder.build());
        request.setParams(paramsBuilder.build());
        request.setCallback(callback);
        RequestUtils.execute(request);
    }
}
