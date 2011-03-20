package com.androidside.mapdemoa4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapDemoA4 extends MapActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        MapView mapView = (MapView) findViewById(R.id.map);

        mapView.getController().setZoom(16);
        mapView.setBuiltInZoomControls(true);

        // �Ÿ��� ��ó�� ���� ����Ʈ�� �����Ѵ�.
        GeoPoint geoPoint1 = new GeoPoint((int) (37.484203 * 1E6),
                (int) (126.929699 * 1E6));
        GeoPoint geoPoint2 = new GeoPoint((int) (37.485203 * 1E6),
                (int) (126.928699 * 1E6));
        GeoPoint geoPoint3 = new GeoPoint((int) (37.486203 * 1E6),
                (int) (126.927699 * 1E6));

        // ���� ����Ʈ�� �������̸� �����Ѵ�.
        MyOverlay myOverlay1 = new MyOverlay(geoPoint1);
        MyOverlay myOverlay2 = new MyOverlay(geoPoint2);
        MyOverlay myOverlay3 = new MyOverlay(geoPoint3);

        // ������ �������̸� ��� �߰��Ѵ�.
        mapView.getOverlays().add(myOverlay1);
        mapView.getOverlays().add(myOverlay2);
        mapView.getOverlays().add(myOverlay3);

        // �߰��� �������̸� ȭ�鿡 ǥ���Ѵ�.
        mapView.invalidate();

        // �Ÿ���(geoPoint1)���� ���� �� ȭ���� �̵��Ѵ�.
        mapView.getController().animateTo(geoPoint1);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}

class MyOverlay extends Overlay {
    GeoPoint geoPoint;

    int radius = 10;

    MyOverlay(GeoPoint geoPoint) {
        super();

        this.geoPoint = geoPoint;
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        super.draw(canvas, mapView, shadow);

        if (shadow == false) {
            Projection projection = mapView.getProjection();

            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(4);

Point point = new Point();
projection.toPixels(geoPoint, point);

            // �������� radius�� �Ͽ��� ���� �׸�
            canvas.drawCircle(point.x, point.y, radius, paint);
        }
    }

    // ����ڰ� ��(Ŭ��)�Ͽ��� �� ȣ��Ǵ� �޼ҵ�
    @Override
    public boolean onTap(GeoPoint p, MapView mapView) {
        Projection projection = mapView.getProjection();

        // ���� ���� �׷��� ����Ʈ ��ǥ ���ϱ�
        Point prePoint = new Point();
        projection.toPixels(geoPoint, prePoint);

        // ����ڰ� ��ġ�� ����Ʈ ��ǥ ���ϱ�
        Point newPoint = new Point();
        projection.toPixels(p, newPoint);

        // ����ڰ� ��ġ�� ���� ���� ���� �׷��� ������ Ȯ���Ͽ� �佺Ʈ�� ������ �浵�� ������
        if (newPoint.x <= prePoint.x + radius
                && newPoint.x >= prePoint.x - radius
                && newPoint.y <= prePoint.y + radius
                && newPoint.y >= prePoint.y - radius) {
            String str = "LatitudeE6: " + p.getLatitudeE6() + "\n"
                    + "LongitudeE6: " + p.getLongitudeE6();

            Toast.makeText(mapView.getContext(), str, Toast.LENGTH_SHORT)
                    .show();
        }

        // false�� �����Ͽ� �ٸ� �������̿����� �� �̺�Ʈ�� ó���� �� �ֵ��� ��.
        // true�� ������ ��� �ֻ���� �������̿����� �� �̺�Ʈ�� ó����
        return false;
    }
}