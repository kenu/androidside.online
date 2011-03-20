package com.androidside.demoteimagedemoa1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class RemoteImageDemoA1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //���ݿ��� ������ �� �̹����� ������ ���̾ƿ��� ImageView�� ������ �´�.
        ImageView imageView = (ImageView) findViewById(R.id.image1);
        
        //���ݿ��� ������ �� �̹����� URL �������� �����Ѵ�.
        URL url = null;
        try {
            url = new URL("http://www.androidside.com/banner/banner.jpg");
        } catch (MalformedURLException e) {            
            e.printStackTrace();
        }
                
        //getRemoteImage() �޼ҵ带 ȣ���ؼ� Bitmap �̹����� ������ �ͼ� ImageView�� �����Ѵ�.
        imageView.setImageBitmap(getRemoteImage(url));

    }

    //���ݿ� �ִ� �̹����� Bitmap �������� ��ȯ�Ѵ�.
    private static Bitmap getRemoteImage(final URL url) {
        Bitmap bitmap = null;
        
        try {
            //�־��� url�� �����Ѵ�.
            URLConnection conn = url.openConnection();
            conn.connect();
            
            //�̹����� ������ �ͼ� decodeStream() �޼ҵ�� Bitmap �̹����� �����.
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return bitmap;
    }
}
