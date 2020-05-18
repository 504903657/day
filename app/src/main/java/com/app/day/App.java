package com.app.day;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;


public class App extends Application {

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static Context sContext;
    private static App sApp;

    public App() {
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }

    public static App getInstance() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (sApp == null)
            sApp = this;
    }


    public static DaoMaster getDaoMaster(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(sApp, "day.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        return daoMaster;
    }

    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
