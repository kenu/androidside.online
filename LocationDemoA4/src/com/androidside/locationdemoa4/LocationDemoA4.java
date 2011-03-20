package com.androidside.locationdemoa4;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class LocationDemoA4 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //��ġ ������ ���ϱ�
        LocationManager locMgr = 
            (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String result = "";
        
        result = "��ġ ������ ����\n" + getProviders(locMgr);
        result += "\n";
        result += "������ ��ġ ������\n" + getBestProvider(locMgr);
        
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(result);
    }
    
    //����� �� �ִ� ��� ��ġ ������ ���ϱ�
    private String getProviders(LocationManager locMgr) {
        List<String> providers = locMgr.getProviders(false);
        
        String str = "";
        
        for(String provider : providers) {
            str += provider+"\n";
        }
        
        return str;
    }
    
    //����(criteria)�� �´� ������ ��ġ ������ ���ϱ�
    private String getBestProvider(LocationManager locMgr) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        
        String provider = locMgr.getBestProvider(criteria, true);
        
        return provider;
    }
}