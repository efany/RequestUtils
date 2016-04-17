package com.efan.request.builder;

import com.efan.request.request.Request;

/**
 * Created by 一帆 on 2016/4/9.
 */
public class DeleteBuilder extends Builder {

    public DeleteBuilder() {
        super();
        request.setMode(Request.Mode.PATCH);
    }

    public DeleteBuilder(Request request) {
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
