package com.app.day;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

    private DbUser dbUser;

    @OnClick(R.id.btn_login)
    public void submit() {
        if (etUsername.getText().length() == 0) {
            T.showShort(this, "Please enter username");
            return;
        }
        if (etPassword.getText().length() == 0) {
            T.showShort(this, "Please enter password");
            return;
        }

        if (etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("111111")) {
            T.showShort(this, "success");
        }else {
            T.showShort(this, "fail");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        dbUser = DbUser.getInstance(this);
        List<User> userList = dbUser.loadAllNote();
        if (userList.size() > 0) {
            etUsername.setText(userList.get(0).getUsername());
        }
    }
}
