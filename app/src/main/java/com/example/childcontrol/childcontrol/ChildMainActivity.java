package com.example.childcontrol.childcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

public class ChildMainActivity extends AppCompatActivity {

    protected FirebaseAuth mAuth;
    protected DatabaseReference myRef;
    protected FirebaseUser user;
    protected FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_main);

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
                        parenChildMap.put("parent", "");
                        parenChildMap.put("child", token);
                        myRef.push().setValue(parenChildMap);
                    } else if (parent != null && child != null && !child.equals(token)) {
                        parenChildMap.put("parent", parent);
                        parenChildMap.put("child", token);
                        myRef.push().setValue(parenChildMap);
                    }
                } else {
                    parenChildMap.put("parent", "");
                    parenChildMap.put("child", token);
                    myRef.push().setValue(parenChildMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
