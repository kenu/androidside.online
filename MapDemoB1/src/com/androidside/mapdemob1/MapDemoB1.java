package com.androidside.mapdemob1;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class MapDemoB1 extends MapActivity {
    private TextView latitudeText, longitudeText;

    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mapView = (MapView) findViewById(R.id.mapview);

        mapView.getController().setZoom(16);
        mapView.setBuiltInZoomControls(true);

        latitudeText = (TextView) findViewById(R.id.latitude);
        longitudeText = (TextView) findViewById(R.id.longitude);

        // ������ ����� ��, ȭ�� �߾ӿ� ������ ǥ���Ѵ�.
        GeoPoint centerGeoPoint = mapView.getMapCenter();
        
        // ��Ŀ�� �̵��Ѵ�.
        moveMarker(centerGeoPoint);
    }
    
    // ȭ�� �߾ӿ� ������ ���� ����Ʈ�� ǥ�õ� �� �ֵ��� �ϸ�
    // addMarker �޼ҵ带 ȣ���ؼ� ������ ǥ���Ѵ�.
    private void moveMarker(GeoPoint centerGeoPoint) {
        mapView.getController().animateTo(centerGeoPoint);

        // ������ �浵�� ȭ�� ��ܿ� ǥ���Ѵ�.
        latitudeText.setText("Longitude: "
                + ((float) centerGeoPoint.getLongitudeE6() / 1000000));
        longitudeText.setText("Latitude: "
                + ((float) centerGeoPoint.getLatitudeE6() / 1000000));

        addMarker(centerGeoPoint.getLatitudeE6(), centerGeoPoint
                .getLongitudeE6());
    };

    // ������ ȭ�鿡 ǥ���Ѵ�.
    private void addMarker(int markerLatitude, int markerLongitude) {
        // ���� �̹����� ��� �´�.
        Drawable marker = getResources().getDrawable(R.drawable.pin);
        marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker
                .getIntrinsicHeight());

        mapView.getOverlays().add(
                new MyLocations(marker, markerLatitude, markerLongitude));
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }



    // ȭ�� ǥ�ù��� ǥ���ϰ� �����ϴ� ItemizedOverlay Ŭ������ �ۼ��Ѵ�.
    class MyLocations extends ItemizedOverlay<OverlayItem> {
        // ȭ�� ǥ�ù�(������)�� �����ϴ� ArrayList Ŭ����
        private List<OverlayItem> locations = new ArrayList<OverlayItem>();
        private Drawable marker;
        private OverlayItem myOverlayItem;

        boolean isMove;

        // �־��� ����, �浵�� OverlayItem���� �����ؼ� locations�� �߰��Ѵ�.
        public MyLocations(Drawable marker, int latitudeE6,
                int longitudeE6) {
            super(marker);
            this.marker = marker;

            GeoPoint myPlace = new GeoPoint(latitudeE6, longitudeE6);
            
            myOverlayItem = new OverlayItem(myPlace, "��ġ", "���� ��ġ�Դϴ�");
            locations.add(myOverlayItem);            

            // createItem �޼ҵ��� �۾� ���� �ݵ�� ȣ��Ǿ�� �ϸ�
            // ItemizedOverlay�� ����ؼ� �������� ó���� �� ��� �۾��� ���ش�.
            populate();
        }

        @Override
        // �־��� i�� locations�� ����� OverlayItem�� ��ȯ�Ѵ�.
        protected OverlayItem createItem(int i) {
            return locations.get(i);
        }

        @Override
        // locations�� ��ü ũ�⸦ ��ȯ�Ѵ�.
        public int size() {
            return locations.size();
        }
        
        @Override
        protected boolean onTap(int index) {            
            return super.onTap(index);
            
        }

        @Override
        // ����(marker)�� ȭ�鿡 �׸���. �̶� �̹����� �ϴ� �߾��� �������� �׸���.
        public void draw(Canvas canvas, MapView mapView, boolean shadow) {
            super.draw(canvas, mapView, shadow);
            
            boundCenterBottom(marker);
        }

        @Override
        // ����� �̺�Ʈ�� ����ؼ� ȭ�鿡 ����(marker)�� �׸���.
        public boolean onTouchEvent(MotionEvent event, MapView map) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP) {
                if (!isMove) {
                    // ����ڰ� ������ ���� ��������Ʈ�� ���Ѵ�.
                    Projection proj = mapView.getProjection();
                    GeoPoint loc = proj.fromPixels((int) event.getX(),
                            (int) event.getY());

                    // ���� ������ �����Ѵ�.
                    mapView.getOverlays().remove(0);

                    // ����ڰ� ������ ��ġ�� ������ ���� ��ġ��Ų��.
                    moveMarker(loc);
                }
            } else if (action == MotionEvent.ACTION_DOWN) {
                isMove = false;

            } else if (action == MotionEvent.ACTION_MOVE) {
                isMove = true;
            }

            return super.onTouchEvent(event, map);
        }
    }
}