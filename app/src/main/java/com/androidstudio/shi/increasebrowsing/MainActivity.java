package com.androidstudio.shi.increasebrowsing;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private TextView btn_test;
    private String[] city;
    private int i = 0;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            OkHttpUtils
                    .get()
                    .url(city[i])
                    .build()
                    .connTimeOut(8000)
                    .execute(new StringCallback() {

                        @Override
                        public void onError(Call call, Exception e) {
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e("返回结果", response);
                        }
                    });
            i++;
            if (i < city.length) {
                handler.sendEmptyMessageDelayed(0, 1000);
                return true;
            }else{
                return false;
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
            }
        });
        city = getResources().getStringArray(R.array.url);
    }
}
