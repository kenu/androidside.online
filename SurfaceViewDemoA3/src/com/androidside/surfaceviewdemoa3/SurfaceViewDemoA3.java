package com.androidside.surfaceviewdemoa3;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewDemoA3 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }
}

//ȭ�鿡 �׷��� ���� ���� ������ ������ �ִ� Ŭ����
class Circle {    
    float x;
    float y;
    int radius;
    
    Paint paint;
    
    Circle(float x, float y, int radius, int a, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        
        paint = new Paint();
        paint.setARGB(a, r, g, b); //���� ����  
        paint.setStyle(Paint.Style.STROKE); //�׵θ��� �׸���.
        paint.setStrokeWidth(10); //�׵θ� �ʺ�
    }
    
    //ȭ�鿡 ���� �׸���.
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder; //���ǽ�Ȧ��

    private ArrayList<Circle> circleList = new ArrayList<Circle>();
    
    private Paint paint; //����Ʈ
    private Canvas canvas; //ĵ����

    private Thread thread; //������
    private boolean isRun; //������ ���� ���θ� ��Ÿ���� �÷���

    public MySurfaceView(Context context) {
        super(context);

        //���ǽ��並 �����ϰ� �����ϴ� ���ǽ�Ȧ�� ��ü�� ��´�.
        holder = getHolder();
        holder.addCallback(this);

        //����Ʈ ��ü�� ��´�.
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        isRun = false;
    }

    //��ġ�� �ǰų� ��ġ�� �հ����� ������ ������ ��ġ ������ �߰��Ѵ�.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            addCircle(x, y);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            addCircle(x, y);
        }
        

        return true;
    }
    
    //���� �׸� ��ġ�� �����Ѵ�.
    private void addCircle(float x, float y) {        
        int radius = (int) (Math.random() * 30); //���� ������
        
        int a = (int) (Math.random() * 255); //����
        int r = (int) (Math.random() * 255); //����
        int g = (int) (Math.random() * 255); //���
        int b = (int) (Math.random() * 255); //û��
        
        
        Circle circle = new Circle(x, y, radius, a, r, g, b);
        circleList.add(circle);
    }
    
    //�߰��� ��� ���� ȭ�鿡 �׸���.
    private void drawAllCircle(Canvas canvas) {
        if (circleList == null) return;
        
        for (int i = 0; i < circleList.size(); i++) {
            circleList.get(i).draw(canvas);
        }
    }

    @Override
    public void run() {
        isRun = true;

        while (isRun) {
            canvas = holder.lockCanvas();

            //����� ���� ������ ä���
            fillBackground(Color.BLACK);

            //�߰��� ��� ���� �׸���.
            drawAllCircle(canvas);
            
            holder.unlockCanvasAndPost(canvas);
        }
    }

    //����� ������ ������ ĥ�Ѵ�.
    private void fillBackground(int bg) {
        paint.setColor(bg);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);
    }
}