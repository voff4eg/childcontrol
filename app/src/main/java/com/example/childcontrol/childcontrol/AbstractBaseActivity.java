package com.example.childcontrol.childcontrol;


import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public abstract class AbstractBaseActivity extends AppCompatActivity {

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    protected FirebaseAuth mAuth;

    protected FirebaseAuth.AuthStateListener mAuthListener;

    public void onStart() {
        super.onStart();
        // Connect the client.
        mGoogleApiClient.connect();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onStop() {
        // only stop if it's connected, otherwise we crash
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
