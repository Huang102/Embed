package com.kisen.embed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kisen.edview.EdView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        //不适用Application配置的LoadView，单个自定义
        EdView.get()
                .activity(this)
                .loadAnimation(CustomLoadAnim.class);
        */
    }

    public void cancel(View view) {
        EdView.stopLoad(this);
    }

    public void show(View view) {
        EdView.showLoad(this);
    }
}
