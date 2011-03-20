package com.androidside.locationdemob1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class LocationDemoB1 extends Activity {
    String PROXIMITY_ALERT = "com.androidside.intent.action.PROXIMITY_ALERT";
    
    ProximityAlertReceiver alertReceiver; //���� �溸 ���ù�
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //���� �溸 ���
        this.addProximityAlert();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        //���� �溸 ���ù� ���
        alertReceiver = new ProximityAlertReceiver();
        IntentFilter filter = new IntentFilter(PROXIMITY_ALERT);
        registerReceiver(alertReceiver, filter);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
        //���� �溸 ���ù� ����
        unregisterReceiver(alertReceiver);
    }    
    
    //���� ��� ���
    private void addProximityAlert() {
        LocationManager locMgr = 
            (LocationManager) getSystemService(LOCATION_SERVICE);
                
        Intent intent = new Intent(PROXIMITY_ALERT);
        PendingIntent pendingIntent = 
            PendingIntent.getBroadcast(this, -1, intent, 0);
        
        double latitude = 37.484241; //�Ÿ��� ����
        double longitude = 126.929651; //�Ÿ��� �浵
        float radius = 10f; //10����
        long expiration = 60*60*1000; //1�ð�
        
        locMgr.addProximityAlert(latitude, longitude, 
                radius, expiration, pendingIntent);
    }
}

//���� ��� ���ù�
class ProximityAlertReceiver extends BroadcastReceiver {
    String key = LocationManager.KEY_PROXIMITY_ENTERING;

    @Override
    public void onReceive(Context context, Intent intent) {
        //������ �ݰ� �ȿ� ������ true, �׷��� ������ false
        boolean isEnter = intent.getBooleanExtra(key, false);
        
        if (isEnter) {
            Toast.makeText(context, "�ݰ� �ȿ� ���Խ��ϴ�.", Toast.LENGTH_LONG)
                .show();
        } else {
            Toast.makeText(context, "�ݰ��� ������ϴ�.", Toast.LENGTH_LONG)
                .show();
        }
    }
    
}