package com.androidside.prefdemob1;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefEdit extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.prefs);
    }
}