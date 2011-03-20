package com.androidside.mapdemoa2;

import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;

public class MapDemoA2 extends MapActivity {
    private MapView mapView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // ���̾ƿ��� �߰��Ǿ� �ִ� MapView�� ��´�.
        mapView = (MapView) findViewById(R.id.map);

        // �Ÿ����� ������ �浵�� ��������Ʈ(GeoPoint)�� �����Ѵ�.
        GeoPoint gp = new GeoPoint((int) (37.484203 * 1000000.0),
                (int) (126.929699 * 1000000.0));

        //������ gp�� ȭ�� �߾ӿ� ���� ��ġ��Ų��.
        mapView.getController().setCenter(gp);
        
        //�� ������ 16���� �����Ѵ�.
        mapView.getController().setZoom(16);
                
        //�� ��Ʈ���� �߰��Ѵ�.
        mapView.setBuiltInZoomControls(true);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // �Ϲ������� ���������� ������ �����ش�.
        if (keyCode == KeyEvent.KEYCODE_M) {
            mapView.setSatellite(!mapView.isSatellite());

        // ������б������� �̵��Ѵ�.
        } else if (keyCode == KeyEvent.KEYCODE_A) {
            mapView.getController().animateTo(
                    new GeoPoint((int) (37.481102 * 1E6),
                            (int) (126.952803 * 1E6)));

        // �ʿ� �������� ǥ���Ѵ�.
        } else if (keyCode == KeyEvent.KEYCODE_I) {
            setIcon();

        // �ʿ� ǥ�õ� ��� �������� �����Ѵ�.
        } else if (keyCode == KeyEvent.KEYCODE_C) {
            mapView.removeAllViews();

        // ���� �̹����� �����Ѵ�.
        } else if (keyCode == KeyEvent.KEYCODE_S) {
            saveBitmap();
        }
        
        return (super.onKeyDown(keyCode, event));
    }

    // �ʿ� �������� ǥ���Ѵ�.
    public void setIcon() {
        final GeoPoint gp = new GeoPoint(
                mapView.getMapCenter().getLatitudeE6(), mapView.getMapCenter()
                        .getLongitudeE6());

        MapView.LayoutParams mapParams = new MapView.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, gp,
                MapView.LayoutParams.CENTER);

        ImageView iv = new ImageView(getApplicationContext());
        iv.setImageResource(R.drawable.pin);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(
                        v.getContext(),
                        "LatitudeE6: " + gp.getLatitudeE6() + "\nLongitudeE6: "
                                + gp.getLongitudeE6(), Toast.LENGTH_LONG)
                        .show();

            }
        });
        mapView.addView(iv, mapParams);
    }

    // ���� �̹����� �����Ѵ�.
    private void saveBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(400, 600, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        mapView.draw(canvas);

        try {
            FileOutputStream fos = this.openFileOutput("map.png",
                    Context.MODE_WORLD_WRITEABLE);
            bitmap.compress(CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}