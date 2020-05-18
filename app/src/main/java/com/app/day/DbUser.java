package com.app.day;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;


public class DbUser {
    private static final String TAG = DbUser.class.getSimpleName();
    private static DbUser instance;
    private static Context appContext;
    private DaoSession mDaoSession;
    private UserDao userDao;

    public DbUser() {
        super();
    }

    public static DbUser getInstance(Context context) {
        if (instance == null) {
            instance = new DbUser();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = App.getDaoSession(context);
            instance.userDao = instance.mDaoSession.getUserDao();
        }
        instance.mDaoSession.clear();
        System.out.println("return instance");
        return instance;
    }

    public User loadNote(long id) {
        if (!TextUtils.isEmpty(id + "")) {
            return userDao.load(id);
        }
        return null;
    }

    public List<User> loadAllNote() {
        return userDao.loadAll();
    }

    public long saveNote(User user) {
        return userDao.insertOrReplace(user);
    }

    public void deleteAllNote() {
        userDao.deleteAll();
    }

    public void deleteNote(long id) {
        userDao.deleteByKey(id);
        Log.i(TAG, "delete");
    }

    public User getUserByUsername(String username) {
        instance.mDaoSession.clear();
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Username.eq(username)).list();
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public User getUserByUserId(String userId) {
        instance.mDaoSession.clear();
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Id.eq(userId)).list();
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}