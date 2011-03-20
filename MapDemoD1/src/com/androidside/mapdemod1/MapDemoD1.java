package com.androidside.mapdemod1;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapDemoD1 extends MapActivity implements View.OnClickListener {
    LocationManager locMgr;
    LocationListener locLnr;

    MapView map;
    Geocoder geocoder;

    Button startButton;
    Button stopButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locLnr = new MyLocationListener();

        map = (MapView) findViewById(R.id.map);
        map.getController().setZoom(map.getMaxZoomLevel());
        map.setBuiltInZoomControls(true);

        geocoder = new Geocoder(this, Locale.KOREA);

        startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(this);

        stopButton = (Button) findViewById(R.id.stop);
        stopButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int millis = 5000; // �и�������
        int distance = 5; // �Ÿ�(����)

        if (v == startButton) {
            // ��ġ ���� ����
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, millis,
                    distance, locLnr);
        } else if (v == stopButton) {
            // ��ġ ���� ����
            locMgr.removeUpdates(locLnr);
        }
    }

    public class MyLocationListener implements LocationListener {
        @Override
        // ��ġ�� ����Ǿ��� �� ȣ��
        public void onLocationChanged(Location loc) {
            GeoPoint gp = new GeoPoint((int) (loc.getLatitude() * 1E6),
                    (int) (loc.getLongitude() * 1E6));

            map.getController().animateTo(gp);

            Toast.makeText(getApplicationContext(), "�ּ� : " + getAddress(loc),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        // ��ġ �����ڰ� ��Ȱ��ȭ�Ǿ��� �� ȣ��
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "GPS �̿� �Ұ�",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        // ��ġ �����ڰ� Ȱ��ȭ�Ǿ��� �� ȣ��
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "GPS �̿� ����",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        // ���°� ����Ǿ��� �� ȣ��
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // �ּҸ� ��ȯ�Ѵ�.
        private String getAddress(Location loc) {
            StringBuffer sb = new StringBuffer();

            try {
                List<Address> addrs = geocoder.getFromLocation(loc
                        .getLatitude(), loc.getLongitude(), 1);

                for (Address addr : addrs) {
                    for (int i = 0; i <= addr.getMaxAddressLineIndex(); i++) {
                        sb.append(addr.getAddressLine(i));
                        sb.append(" ");
                    }
                }
            } catch (Exception e) {
                sb.append("�ּ����� ��ٰ� ������ �߻��߽��ϴ�.");
            }

            return sb.toString();
        }
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}