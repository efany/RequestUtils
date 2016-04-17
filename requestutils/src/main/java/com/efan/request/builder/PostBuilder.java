package com.efan.request.builder;

import com.efan.request.request.Request;

/**
 * Created by 一帆 on 2016/4/9.
 */
public class PostBuilder extends Builder {

    public PostBuilder() {
        super();
        request.setMode(Request.Mode.POST);
    }

    public PostBuilder(Request request) {
        super(request);
    }

    @Override
    public Request generate() {
        return request;
    }

    @Override
    public Builder build() {
        return this;
    }
}
