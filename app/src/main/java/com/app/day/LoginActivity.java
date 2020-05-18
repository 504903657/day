package com.app.day;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @OnClick(R.id.btn_login)
    public void login(){
        T.showShort(this,"hello");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
