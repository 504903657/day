package com.app.day;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SqlActivity extends Activity {
    private DbUser dbUser;

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        ButterKnife.bind(this);
        dbUser = DbUser.getInstance(this);
        List<User> userList = dbUser.loadAllNote();
        if (userList.size() > 0) {
            username.setText(userList.get(0).getUsername());
            password.setText(userList.get(0).getPassword());
        }
    }
}
