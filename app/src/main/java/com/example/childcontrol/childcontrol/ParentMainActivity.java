package com.example.childcontrol.childcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParentMainActivity extends AbstractParentActivity {

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";
    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    @BindView(R.id.message)
    protected TextView mTextMessage;
    protected int CURRENT_STATUS;

    protected FirebaseAuth mAuth;
    protected DatabaseReference myRef;
    protected FirebaseUser user;
    protected FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_parent_main);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        myRef = database.getReference("parentChild/" + user.getUid());

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String token = FirebaseInstanceId.getInstance().getToken();
                Map<String, String> parenChildMap = new HashMap<String, String>();
                if (dataSnapshot.getValue() != null) {
                    GenericTypeIndicator<Map<String, Map<String, String>>> genericTypeIndicator = new GenericTypeIndicator<Map<String, Map<String, String>>>() {
                    };
                    Map<String, Map<String, String>> parenChildCurrentMap = dataSnapshot.getValue(genericTypeIndicator);
                    String parent = null;
                    String child = null;
                    for (Map.Entry<String, Map<String, String>> entry : parenChildCurrentMap.entrySet()) {
                        parent = entry.getValue().get("parent");
                        child = entry.getValue().get("child");
                    }
                    if (parent == null && child == null) {
                        parenChildMap.put("parent", token);
                        parenChildMap.put("child", "");
                        myRef.push().setValue(parenChildMap);
                    } else if (child != null && parent != null && !parent.equals(token)) {
                        parenChildMap.put("parent", token);
                        parenChildMap.put("child", child);
                        myRef.push().setValue(parenChildMap);
                    }
                } else {
                    parenChildMap.put("parent", token);
                    parenChildMap.put("child", "");
                    myRef.push().setValue(parenChildMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mTextMessage.setText("Запустить слежение");
    }

    @OnClick(R.id.watch)
    public void watch(View view) {
        Log.d("myLogs", "CURRENT STATUS = " + CURRENT_STATUS);
        if (CURRENT_STATUS <= 0 || CURRENT_STATUS == STATUS_FINISH) {
            CURRENT_STATUS = STATUS_START;
            mTextMessage.setText("Остановить слежение");
            startService(new Intent(ParentMainActivity.this, WatchService.class));
        } else {
            CURRENT_STATUS = STATUS_FINISH;
            mTextMessage.setText("Запустить слежение");
            stopService(new Intent(ParentMainActivity.this, WatchService.class));
        }
    }
}
