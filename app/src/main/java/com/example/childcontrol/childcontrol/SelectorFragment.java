package com.example.childcontrol.childcontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.heinrichreimersoftware.materialintro.app.SlideFragment;

import de.greenrobot.event.EventBus;

public class SelectorFragment extends SlideFragment {

    protected UserTypeDao userTypeDao;
    UserType userType;
    boolean canGoForward = false;
    private RadioGroup radioGroup;

    public SelectorFragment() {
        // Required empty public constructor
    }

    public static SelectorFragment newInstance() {
        return new SelectorFragment();
    }

    public boolean isCanGoForward() {
        return canGoForward;
    }

    public void setCanGoForward(boolean canGoForward) {
        this.canGoForward = canGoForward;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_selector, container, false);
        root.clearFocus();

        radioGroup = (RadioGroup) root.findViewById(R.id.radioGroup1);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setCanGoForward(true);
                EventBus.getDefault().post(new MessageEvent(checkedId));
            }
        });

        return root;
    }

    @Override
    public boolean canGoForward() {
        boolean canGoForward = super.canGoForward();
        canGoForward = canGoForward && isCanGoForward();
        return canGoForward;
    }
}
