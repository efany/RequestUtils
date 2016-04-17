package com.efan.request_sample;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.efan.request.RequestUtils;
import com.efan.request.builder.GetBuilder;
import com.efan.request.callback.Callback;
import com.efan.request.config.TimeOutConfig;
import com.efan.request.request.Request;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        RequestUtils.getInstance().setRequestClient(new OkhttpClient());
        RequestUtils.getInstance().setTimeOutConfig(new TimeOutConfig(5));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
//                Intent i = new Intent(
//                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(i, 12);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.d("haha",picturePath);
        }
    }
    private void get(){}

    private void getWay1(String url){
        RequestUtils.get()
                .url(url)
                .build()
                .execute(new Callback() {
                    @Override
                    public void onError(Exception e) {
                        Log.d("haha","onError");
                    }

                    @Override
                    public void onResponse(Object response){
                        String s = response.toString();
                        Log.d("haha",s);
                    }
                });
    }

    private void getWay2(){
        RequestUtils.get()
                .url("http://news-at.zhihu.com/api/4/start-image/1080*1776")
                .addCallback(new Callback() {
                    @Override
                    public void onError(Exception e) {
                        Log.d("haha","onError");
                    }

                    @Override
                    public void onResponse(Object response){
                        String s = response.toString();
                        Log.d("haha",s);
                        textView.setText(s);
                    }
                })
                .build()
                .execute();
    }

    private void getSplit(){
        Request request = new GetBuilder()
                .url("http://news-at.zhihu.com/api/4/start-image/1080*1776")
                .build()
                .addCallback(new Callback() {
                    @Override
                    public void onError(Exception e) {
                        Log.d("haha","onError");
                    }

                    @Override
                    public void onResponse(Object response) {
                        String s = response.toString();
                        Log.d("haha",s);
                        textView.setText(s);
                    }
                })
                .generate();
        RequestUtils.execute(request);
    }

    private void post(){
        Log.d("haha","post");
        RequestUtils.post()
                .url("http://192.168.1.147:3000/login")
                .addParams("username","12345678")
                .addParams("password","12345678")
                .build()
                .execute(new Callback() {
                    @Override
                    public void onError(Exception e) {
                        Log.d("haha","onError");
                    }

                    @Override
                    public void onResponse(Object response) {
                        String s = response.toString();
                        Log.d("haha",s);

                        getWay1("http://192.168.1.147:3000/show_my_information");
                    }
                });
    }

    private void patch(){
        RequestUtils.patch()
                .url("http://192.168.1.104:1337/alter")
                .addParams("name","yf")
                .build()
                .execute(new Callback() {
                    @Override
                    public void onError(Exception e) {
                        Log.d("haha","onError");
                    }

                    @Override
                    public void onResponse(Object response) {
                        String s = response.toString();
                        Log.d("haha",s);
                    }
                });
    }
}
