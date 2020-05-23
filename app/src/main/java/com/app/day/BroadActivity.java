package com.app.day;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BroadActivity extends Activity implements View.OnClickListener {

    private Intent intent = null;
    private Button btn_start_service;
    private Button btn_stop_service;
    private Button btn_bind_service;
    private Button btn_unbind_service;
    private Button btn_sync_data;
    private EditText et_data;
    private TextView tv_out;
    MyServiceConn myServiceConn;
    MyService.MyBinder binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        intent = new Intent(this, MyService.class);
        myServiceConn = new MyServiceConn();
        setOnClick();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                intent.putExtra("data", et_data.getText().toString());
                startService(intent);
                break;
            case R.id.btn_stop_service:
                stopService(intent);
                break;
            case R.id.btn_bind_service:
                bindService(intent, myServiceConn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                if (binder != null) {
                    unbindService(myServiceConn);
                }
                break;
            case R.id.btn_sync_data:
                if (binder != null) {
                    binder.setData(et_data.getText().toString());
                }
                break;
            default:
                break;
        }

    }

    class MyServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.MyBinder) service;
            binder.getService().setDataCallback(new MyService.DataCallback() {
                @Override
                public void dataChanged(String str) {
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("str", str);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            });

        }

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                tv_out.setText(msg.getData().getString("str"));
            };
        };

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }

    }

    private void loadUI() {
        btn_start_service = (Button) findViewById(R.id.btn_start_service);
        btn_stop_service = (Button) findViewById(R.id.btn_stop_service);
        btn_bind_service = (Button) findViewById(R.id.btn_bind_service);
        btn_unbind_service = (Button) findViewById(R.id.btn_unbind_service);
        btn_sync_data = (Button) findViewById(R.id.btn_sync_data);
        et_data = (EditText) findViewById(R.id.et_data);
        tv_out = (TextView) findViewById(R.id.tv_out);

    }

    private void setOnClick() {
        loadUI();
        btn_start_service.setOnClickListener(this);
        btn_stop_service.setOnClickListener(this);
        btn_bind_service.setOnClickListener(this);
        btn_unbind_service.setOnClickListener(this);
        btn_sync_data.setOnClickListener(this);

    }

}
