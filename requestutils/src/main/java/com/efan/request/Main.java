package com.efan.request;

import com.efan.request.callback.Callback;

/**
 * Created by 一帆 on 2016/4/9.
 */
public class Main {
    public static void main(String[] arg){
        RequestUtils.get().url("http://rger")
                .addHeader("fdg","dgdg")
                .addParams("ewg","erb")
                .addParams("fbd","erb")
                .build()
                .execute(new Callback() {
                    @Override
                    public void onError(Exception e) {
                        System.out.print(e);
                    }

                    @Override
                    public void onResponse(Object response) {
                        System.out.print(response);
                    }
                });

//        String[] a = {"aa","bb","cc","dd"};
//        Headers headers = new Headers.Builder()
//                .add("ee","ff")
//                .add("gg","hh")
//                .build();
//        System.out.print(headers.size());
//        System.out.print(headers.get("ee"));
//        System.out.print(headers.get("gg"));
//        System.out.print(headers.name(0));
//        System.out.print(headers.name(1));
//        System.out.print(headers.value(0));
//        System.out.print(headers.value(1));
    }
}
