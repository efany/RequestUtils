package com.efan.request_sample;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.efan.request.callback.Callback;
import com.efan.request.client.RequestClient;
import com.efan.request.config.TimeOutConfig;
import com.efan.request.request.Params;
import com.efan.request.request.Request;
import com.efan.request_sample.cookie.SimpleCookieJar;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 一帆 on 2016/4/9.
 */
public class OkhttpClient extends RequestClient {

    private OkHttpClient client;
    private Handler handler;
    private OkHttpClient.Builder okHttpClientBuilder;
    public OkhttpClient() {
        handler = new Handler(Looper.getMainLooper());
        okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        okHttpClientBuilder
                .connectTimeout(timeOutConfig.getConnectTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(timeOutConfig.getWriteTimeOut(), TimeUnit.SECONDS)
                .readTimeout(timeOutConfig.getReadTimeOut(), TimeUnit.SECONDS);
        client = okHttpClientBuilder.build();
    }

    @Override
    public void get(final Request request) {
        okhttp3.Request req = new okhttp3.Request.Builder()
                .url(request.getUrl())
                .build();
        client.newCall(req).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailResultCallback(e, request.getCallback());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                try {
                    String s = response.body().string();
                    sendSuccessResultCallback(s, request.getCallback());
                } catch (Exception e) {
                    sendFailResultCallback(e,request.getCallback());
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void post(final Request request) {
        okhttp3.Request req = new okhttp3.Request.Builder()
                .url(request.getUrl())
                .post(generateRequestBody(request))
                .build();

        client.newCall(req).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("haha", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                try {
                    String s = response.body().string();
                    sendSuccessResultCallback(s, request.getCallback());
                } catch (Exception e) {
                    sendFailResultCallback(e,request.getCallback());
                    e.printStackTrace();
                }
//                Headers responseHeaders = response.headers();
//                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                    Log.d("haha",responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }
            }
        });
    }

    @Override
    public void patch(final Request request) {
        okhttp3.Request req = new okhttp3.Request.Builder()
                .url(request.getUrl())
                .patch(generateRequestBody(request))
                .build();

        client.newCall(req).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("haha", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                try {
                    String s = response.body().string();
                    sendSuccessResultCallback(s, request.getCallback());
                } catch (Exception e) {
                    sendFailResultCallback(e,request.getCallback());
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void delete(final Request request) {
        okhttp3.Request req = new okhttp3.Request.Builder()
                .url(request.getUrl())
                .delete(generateRequestBody(request))
                .build();

        client.newCall(req).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("haha", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                try {
                    String s = response.body().string();
                    sendSuccessResultCallback(s, request.getCallback());
                } catch (Exception e) {
                    sendFailResultCallback(e,request.getCallback());
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void cancel(Request request) {
//        if(request.getTag() != null){
//            Call call = (Call) request.getTag();
//            call.cancel();
//        }
    }

    @Override
    public void cancelAll() {

    }

    @Override
    public void setTimeOutConfig(TimeOutConfig timeOutConfig) {
        super.setTimeOutConfig(timeOutConfig);
        okHttpClientBuilder
                .connectTimeout(timeOutConfig.getConnectTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(timeOutConfig.getWriteTimeOut(), TimeUnit.SECONDS)
                .readTimeout(timeOutConfig.getReadTimeOut(), TimeUnit.SECONDS);
        client = okHttpClientBuilder.build();
    }

    private void sendFailResultCallback(final Exception e, final Callback callback)
    {
        if (callback == null) return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(e);
            }
        });
    }

    private void sendSuccessResultCallback(final Object response, final Callback callback) throws Exception
    {
        if (callback == null) return;
        handler.post(new Runnable() {
            @Override
            public void run() {
            callback.onResponse(response);
            }
        });
    }

    private RequestBody generateRequestBody(Request request){
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        Params params = request.getParams();
        for (int i = 0; i<params.size(); i++){
            builder.addFormDataPart(params.name(i),params.value(i));
        }
        return builder.build();
    }
}
