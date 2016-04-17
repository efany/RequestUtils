package com.efan.request.request;

import com.efan.request.callback.Callback;
import com.efan.request.response.Headers;

public class Request {
    protected Mode mode;
    protected String url;
    protected Object tag;
    protected Params params;
    protected Headers headers;
    protected Callback callback;
    public static enum Mode{POST, GET, PATCH, DELETE}

    public Request() {}

    public Request(Mode mode) {
        this.mode = mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Params getParams() {
        return params;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
    public Callback getCallback() {
        return callback;
    }


    @Override
    public String toString() {
        return "BaseRequest{" +
                "url='" + url + '\'' +
                ", params=" + params +
                ", headers=" + headers +
                '}';
    }
}
