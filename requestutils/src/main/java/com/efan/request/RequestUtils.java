package com.efan.request;

import com.efan.request.builder.DeleteBuilder;
import com.efan.request.builder.GetBuilder;
import com.efan.request.builder.PatchBuilder;
import com.efan.request.builder.PostBuilder;
import com.efan.request.client.RequestClient;
import com.efan.request.client.RequestClientDefault;
import com.efan.request.config.TimeOutConfig;
import com.efan.request.request.Request;

/**
 * Created by 一帆 on 2016/4/9.
 */
public class RequestUtils {
    private static RequestClient requestClient;

    /**
     * 单例模式
     */
    private RequestUtils(){}

    public static RequestUtils getInstance(){
        return RequestUtilHolder.mInstance;
    }

    /**
     * 设置请求方式 get
     * @return GetBuilder
     */
    public static GetBuilder get(){
        return new GetBuilder();
    }

    /**
     * 设置请求方式 post
     * @return PostBuilder
     */
    public static PostBuilder post(){
        return new PostBuilder();
    }

    /**
     * 设置请求方式 get
     * @return GetBuilder
     */
    public static PatchBuilder patch(){
        return new PatchBuilder();
    }

    /**
     * 设置请求方式 post
     * @return PostBuilder
     */
    public static DeleteBuilder delete(){
        return new DeleteBuilder();
    }

    /**
     * 执行网络请求
     * @param request
     */
    public static void execute(final Request request){
        if(requestClient == null){
            requestClient = new RequestClientDefault();
        }
        switch (request.getMode()){
            case GET:
                requestClient.get(request);
                break;
            case POST:
                requestClient.post(request);
                break;
            case PATCH:
                requestClient.patch(request);
                break;
            case DELETE:
                requestClient.delete(request);
                break;
        }
    }

    /**
     * 取消网络请求
     * @param request
     */
    public static void cancel(final Request request){
        requestClient.cancel(request);
    }

    /**
     * 取消所有网络请求
     */
    public static void cancelAll(){
        requestClient.cancelAll();
    }

    /**
     * 设置网络请求Client
     * @param requestClient
     */
    public void setRequestClient(RequestClient requestClient) {
        this.requestClient = requestClient;
    }

    /**
     * 设置超时策略
     * @param timeOutConfig
     */
    public void setTimeOutConfig(TimeOutConfig timeOutConfig) {
        if (timeOutConfig == null){
            timeOutConfig = new TimeOutConfig();
        }
        requestClient.setTimeOutConfig(timeOutConfig);
    }

    private static class RequestUtilHolder{
        private static final RequestUtils mInstance = new RequestUtils();
    }
}
