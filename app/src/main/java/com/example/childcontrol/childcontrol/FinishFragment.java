package com.example.childcontrol.childcontrol;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.heinrichreimersoftware.materialintro.app.SlideFragment;

public class FinishFragment extends SlideFragment {

    TextView textView;
    Button button;

    public static FinishFragment newInstance() {
        return new FinishFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_finish, container, false);
        root.clearFocus();

        textView = (TextView) root.findViewById(R.id.textView);
        button = (Button) root.findViewById(R.id.button);
        button.setText(R.string.action_finish);

        textView.setText(R.string.description_finish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        return root;
    }
}
