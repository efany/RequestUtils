package com.efan.request.response;

/**
 * Created by 一帆 on 2016/4/10.
 */
public class Response {
    private Headers headers;
    private String body;

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
