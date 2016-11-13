package com.example.childcontrol.childcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private UserTypeDao userTypeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        userTypeDao = daoSession.getUserTypeDao();
        List<UserType> userType = userTypeDao.queryBuilder().list();

        TextView textView = new TextView(this);
        textView.setText(userType.get(0).getType());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        LinearLayout insertPoint = (LinearLayout) findViewById(R.id.linearLayout);
        insertPoint.addView(textView);

    }
}
