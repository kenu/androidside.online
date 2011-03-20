package com.androidside.tabhostdemob2;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabHostDemoB2 extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        TabSpec tabSpec1 = tabHost.newTabSpec("Tab1").setIndicator("ù ��° ��");
        tabSpec1.setContent(R.id.tab1);
        tabHost.addTab(tabSpec1);
        
        TabSpec tabSpec2 = tabHost.newTabSpec("Tab2").setIndicator("�� ��° ��");
        tabSpec2.setContent(R.id.tab2);
        tabHost.addTab(tabSpec2);
        
        TabSpec tabSpec3 = tabHost.newTabSpec("Tab3").setIndicator("�� ��° ��");
        tabSpec3.setContent(R.id.tab3);
        tabHost.addTab(tabSpec3);
        
        tabHost.setCurrentTab(0);
    }
}