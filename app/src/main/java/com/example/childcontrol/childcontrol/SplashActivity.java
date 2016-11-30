package com.example.childcontrol.childcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;


public class SplashActivity extends AppCompatActivity {

    public static final String PREF_KEY_FIRST_START = "com.heinrichreimersoftware.materialintro.demo.PREF_KEY_FIRST_START";
    public static final int REQUEST_CODE_INTRO = 1;
    boolean firstStart = true;
    private UserTypeDao userTypeDao;
    private UserType userTypeNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        userTypeDao = daoSession.getUserTypeDao();
        List<UserType> userType = userTypeDao.queryBuilder().list();

        userTypeDao.deleteAll();

        /*userTypeNew = userType.get(0);
        userTypeNew.setType(UserType.PARENT_USER_TYPE);

        //Reset usertype
        userTypeDao.insertOrReplace(userTypeNew);*/

        if (userType != null && userType.size() > 0) {
            firstStart = false;
        }

        if (firstStart) {
            Intent intent = new Intent(this, SplashIntroActivity.class);
            startActivityForResult(intent, REQUEST_CODE_INTRO);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, REQUEST_CODE_INTRO);
        }

        finish();
    }
}
