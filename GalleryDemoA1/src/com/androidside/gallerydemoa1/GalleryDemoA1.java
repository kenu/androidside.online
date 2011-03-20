package com.androidside.gallerydemoa1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryDemoA1 extends Activity {
    //Gallery���� ������ �̹����� �����Ѵ�.
    //�̹������� /res/drawable-hdpi �Ʒ��� pic1.jpg, pic2.jpg �� ���� �̸����� �����Ѵ�.
    //�̹��� �̸��� ���ڷ� �����ؼ��� �ȵǸ� ������, ����, _ ���ڷ� �����ؾ� �Ѵ�.
    Integer[] images = { R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                         R.drawable.pic4, R.drawable.pic5, R.drawable.pic6 };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // /res/layout/gallerydemo1.xml�� ������ gallery1��� �̸��� ���� Gallery�� �ҷ��´�.
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);

        //���������� ������ �̹����� ó���ϴ� ����͸� �����Ѵ�.
        //ImageAdapter�� BaseAdapter�� Ȯ���ؼ� ������ �Ѵ�.
        gallery.setAdapter(new ImageAdapter(this));
    }

    //�������� ������ �̹����� ���� ������ �Ѵ�.
    public class ImageAdapter extends BaseAdapter {
        private Context context;
    
        public ImageAdapter(Context c) {
            context = c;
        }
    
        //�̹��� ��ü ������ ��ȯ�Ѵ�.
        public int getCount() {
            return images.length;
        }
    
        //�־��� position�� ���� �̹��� ���ҽ� ���̵� ��ȯ�Ѵ�.
        public Integer getItem(int position) {
            return images[position];
        }
    
        //�־��� position�� ���� �̹��� ���ҽ� ���̵� ��ȯ�Ѵ�.        
        public long getItemId(int position) {
            return position;
        }
    
        //�־��� position �� ���� ImageView�� ��ȯ�Ѵ�.
        public View getView(int position, View convertView, ViewGroup parent) {
            //ImageView�� �����Ѵ�.
            ImageView imageView = new ImageView(context);
            
            //�־��� position�� �ش��ϴ� �̹��� ���ҽ� ���̵带 �����Ѵ�.
            imageView.setImageResource(getItem(position));
            //imageView.setAdjustViewBounds(true);
            
            //�������� �������� �̹��� ũ�⸦ �����Ѵ�. 100*80
            imageView.setLayoutParams(new Gallery.LayoutParams(100, 80));
                        
            //ImageView�� ��ȯ�Ѵ�.
            return imageView;
        }
    } 
}