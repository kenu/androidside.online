package com.androidside.locationdemoa2;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 
public class LocationDemoA2 extends Activity implements View.OnClickListener {
    LocationManager locMgr;
    LocationListener locLnr;
    
    TextView text;
    Button startButton;
    Button stopButton;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locLnr = new MyLocationListener();
        
        text = (TextView) findViewById(R.id.text);
        startButton = (Button) findViewById(R.id.start);
        stopButton = (Button) findViewById(R.id.stop);
        
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        int millis = 5000; //�и�������
        int distance = 5; //�Ÿ�
                
        if (v == startButton) { //��ġ ������ �����Ѵ�.
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
                    millis, distance, locLnr);
            text.setText("��ġ ������ �����մϴ�.");
        } else if (v == stopButton) { //��ġ ������ �����Ѵ�.
            locMgr.removeUpdates(locLnr);
            text.setText("��ġ ������ �����մϴ�.");
        }
    }
    
    //��ġ ������ �����ϴ� ������
    public class MyLocationListener implements LocationListener {
        
        //��ġ ������ ����Ǿ��� �� ȣ��ȴ�.
        @Override
        public void onLocationChanged(Location loc) {
            String text = "���� ��ġ : \n" + "���� : " + loc.getLatitude() + "\n"
                    + "�浵 : " + loc.getLongitude();

            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
                    .show();
        }

        //��ġ �����ڰ� ����Ǿ��� �� ȣ��ȴ�.
        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "GPS �̿� �Ұ�",
                    Toast.LENGTH_SHORT).show();
        }

        //��ġ �����ڰ� Ȱ��ȭ�Ǿ��� �� ȣ��ȴ�.
        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "GPS �̿� ����",
                    Toast.LENGTH_SHORT).show();
        }

        //��ġ �������� ���°� ����Ǿ��� �� ȣ��ȴ�.
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }
}