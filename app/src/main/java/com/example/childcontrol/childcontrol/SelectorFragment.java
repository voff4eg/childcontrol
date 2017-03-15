package com.example.childcontrol.childcontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.heinrichreimersoftware.materialintro.app.SlideFragment;

import de.greenrobot.event.EventBus;

public class SelectorFragment extends SlideFragment {

    public SelectorFragment() {
        // Required empty public constructor
    }

    public static SelectorFragment newInstance() {
        return new SelectorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_selector, container, false);
        root.clearFocus();

        RadioGroup radioGroup = (RadioGroup) root.findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                EventBus.getDefault().post(new MessageEvent(checkedId));
            }
        });

        RadioButton parentRB = (RadioButton) root.findViewById(R.id.parent);
        parentRB.setText(R.string.parent);
        RadioButton childRB = (RadioButton) root.findViewById(R.id.child);
        childRB.setText(R.string.child);

        return root;
    }
}
