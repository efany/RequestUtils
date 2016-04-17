package com.efan.request.builder;

import com.efan.request.request.Params;
import com.efan.request.request.Request;

/**
 * Created by 一帆 on 2016/4/9.
 */
public class GetBuilder extends Builder {

    public GetBuilder() {
        super();
        request.setMode(Request.Mode.GET);
    }

    public GetBuilder(Request request) {
        super(request);
    }

    @Override
    public Request generate() {
        request.setUrl(appendParams(request.getUrl(), request.getParams()));
        return request;
    }

    @Override
    public Builder build() {
        request.setUrl(appendParams(request.getUrl(), request.getParams()));
        return this;
    }

    /**
     * 将get请求的params添加到url
     * @param url
     * @param params
     * @return
     */
    private String appendParams(String url, Params params)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && params.size()!=0)
        {
            for (int i = 0; i<params.size(); i++)
            {
                sb.append(params.name(i)).append("=").append(params.value(i)).append("&");
            }
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
