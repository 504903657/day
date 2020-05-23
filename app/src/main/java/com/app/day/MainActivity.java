package com.app.day;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @OnClick({R.id.location,
            R.id.web,
            R.id.broadcast,
            R.id.facebook,
            R.id.twitter,
            R.id.content,
            R.id.sql
    })
    public void onclick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.location:
                intent.setClass(MainActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.web:
                intent.setClass(MainActivity.this, WebActivity.class);
                startActivity(intent);
                break;
            case R.id.broadcast:
                intent.setClass(MainActivity.this, BroadActivity.class);
                startActivity(intent);
                break;
            case R.id.facebook:
                intent.setClass(MainActivity.this, FacebookActivity.class);
                startActivity(intent);
                break;
            case R.id.twitter:
                intent.setClass(MainActivity.this, TwitterActivity.class);
                startActivity(intent);
                break;
            case R.id.content:
                intent.setClass(MainActivity.this, ContentActivity.class);
                startActivity(intent);
                break;
            case R.id.sql:
                intent.setClass(MainActivity.this, SqlActivity.class);
                startActivity(intent);
                break;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


}