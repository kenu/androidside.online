package com.androidside.surfaceviewdemoa2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewDemoA2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }
}

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,
        Runnable {
    private SurfaceHolder holder; //���ǽ�Ȧ��

    private Paint paint; //����Ʈ
    private Canvas canvas; //ĵ����

    private Thread thread; //������
    private boolean isRun; //������ ���� ���θ� ��Ÿ���� �÷���

    MyIcon myIcon;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            myIcon.isHit(touchX, touchY);
        }

        return true;
    }

    @Override
    public void run() {
        myIcon = new MyIcon();

        isRun = true;

        while (isRun) {
            canvas = holder.lockCanvas();

            //����� ���� ������ ä���
            fillBackground(Color.BLACK);

            //�������� �ڵ����� �̵���Ų��.
            myIcon.moveAuto(canvas);

            holder.unlockCanvasAndPost(canvas);

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //����� ������ ������ ĥ�Ѵ�.
    private void fillBackground(int bg) {
        paint.setColor(bg);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);
    }

    class MyIcon {
        //���� ���
        private static final int LEFT = 0;
        private static final int UP = 1;
        private static final int RIGHT = 2;
        private static final int DOWN = 3;

        private Bitmap icon;
        private Bitmap icon2;
        private int androidWidth; //������ �ʺ�
        private int androidHeight; //������ ����

        int x = 0; //�������� ���� ����� X ��ǥ
        int y = 0; //�������� ���� ����� Y

        int maxX; //�������� �̵��� �� �ִ� �ִ� X ��ǥ
        int maxY; //�������� �̵��� �� �ִ� �ִ� Y ��ǥ

        int speed = 80; //������ �̵� �ʺ�

        boolean isHit = false; //������ ��ġ ����

        //�� ���� �����ܰ� �������� �ʺ�� ���̸� ���Ѵ�. �׸��� ȭ�� ũ�⸦ ���Ѵ�.
        MyIcon() {
            //�⺻ �ȵ���̵� ���
            icon = BitmapFactory
                    .decodeResource(getResources(), R.drawable.img1);
            //��ġ�� ����� �ȵ���̵� ���
            icon2 = BitmapFactory.decodeResource(getResources(),
                    R.drawable.img2);

            androidWidth = icon.getWidth(); //������ �ʺ�
            androidHeight = icon.getHeight(); //������ ����

            maxX = getWidth() - androidWidth; //�������� �̵��� �� �ִ� �ִ� X ��ǥ
            maxY = getHeight() - androidHeight; //�������� �̵��� �� �ִ� �ִ� Y ��ǥ
        }

        //������ �̵� �ʺ�(���ǵ�)�� ������ �� �ִ� ������
        MyIcon(int speed) {
            this();
            this.speed = speed;
        }

        //��ġ�� ���� �������� ���� ����� isHit ������ true�� �����Ѵ�. 
        public void isHit(int touchX, int touchY) {
            if (touchX >= x && touchX <= x + androidWidth 
                    && touchY >= y && touchY <= y + androidHeight) {
                isHit = true;
            }
        }

        //�������� ������ �������� �̵���Ų��. �̶� ȭ���� �Ѿ ���� ȭ�� ������ �̵���Ų��.
        public void setNextPosition() {
            int direction = (int) (Math.random() * 4);

            if (direction == LEFT) {
                x -= speed;
            }

            if (direction == RIGHT) {
                x += speed;
            }

            if (direction == UP) {
                y -= speed;
            }
            if (direction == DOWN) {
                y += speed;
            }

            //X ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (x < 0) {
                x = 0;
            }

            //X ��ǥ�� ������ ȭ���� ���� ���ٸ�
            if (x > maxX) {
                x = maxX;
            }

            //Y ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (y < 0) {
                y = 0;
            }

            //Y ��ǥ�� �Ʒ��� ȭ���� ���� ���ٸ�
            if (y > maxY) {
                y = maxY;
            }
        }

        //�������� �ڵ����� �̵���Ų��.
        public void moveAuto(Canvas canvas) {
            setNextPosition();

            //�����̴ٰ� ����ڿ� ���� ��ġ�Ǿ��ٸ� icon2 �̹����� �����ְ� 
            //�׷��� ������ icon �̹����� �����ش�.
            if (isHit) {
                canvas.drawBitmap(icon2, x, y, paint);
                isHit = false;
            } else {
                canvas.drawBitmap(icon, x, y, paint);
            }
        }
    }
}