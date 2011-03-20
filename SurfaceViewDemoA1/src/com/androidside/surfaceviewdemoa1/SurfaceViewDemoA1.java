package com.androidside.surfaceviewdemoa1;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewDemoA1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }
}

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder; //���ǽ�Ȧ��

    private Paint paint; //����Ʈ
    private Canvas canvas; //ĵ����

    private Thread thread; //������
    private boolean isRun; //������ ���� ���θ� ��Ÿ���� �÷���

    public MySurfaceView(Context context) {
        super(context);

        holder = getHolder();
        holder.addCallback(this);

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

    @Override
    public void run() {
        isRun = true;

        while (isRun) {
            canvas = holder.lockCanvas();

            //����� ���� ������ ä���
            fillBackground(Color.BLACK);

            //�ε��� ���ڸ� ��� �����ʿ� ������� ����ϱ�
            printStr(getTime(), Color.WHITE);

            holder.unlockCanvasAndPost(canvas);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //����� ������ ������ ĥ�ϴ� �޼ҵ�
    private void fillBackground(int bg) {
        paint.setColor(bg);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);
    }

    //ȭ�鿡 ���ڿ��� ����ϴ� �޼ҵ�
    private void printStr(String str, int color) {
        paint.setColor(color);
        paint.setTextSize(20);
        canvas.drawText(str, getWidth() - paint.measureText(str), 20, paint);
    }

    //���� �ð�/��/�� ��ȯ�ϴ� �޼ҵ�
    private String getTime() {
        Calendar now = Calendar.getInstance();
        int h = now.get(Calendar.HOUR_OF_DAY);

        int m = now.get(Calendar.MINUTE);
        int s = now.get(Calendar.SECOND);

        return h + ":" + m + ":" + s;
    }

}