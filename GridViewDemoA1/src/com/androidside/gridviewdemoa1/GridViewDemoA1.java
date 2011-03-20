package com.androidside.gridviewdemoa1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewDemoA1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //GridView ��ü�� main.xml�� ����� �Ӽ��� �������� �����Ѵ�. 
        GridView gridview = (GridView) findViewById(R.id.gridview);
        
        //GridView�� �̹����� �����ֱ� ���� �̹��� ����͸� �����Ѵ�.
        gridview.setAdapter(new ImageAdapter(this));
    }

    //BaseAdapter�� ����Ͽ� GridView�� �̹����� ������ �� �ִ� ����� �����Ѵ�.
    public class ImageAdapter extends BaseAdapter {
        private Context context;
        
        //GridView�� �����ֱ� ���� �̹��� �迭�� �����Ѵ�.
        private Integer[] images = { R.drawable.pic1, R.drawable.pic2,
                R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic1, R.drawable.pic2,
                R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic1, R.drawable.pic2,
                R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic1, R.drawable.pic2,
                R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6 };

        public ImageAdapter(Context c) {
            this.context = c;
        }

        //��ü �̹��� ������ ��ȯ�Ѵ�.
        public int getCount() {
            return images.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        //������ �̹����� �����Ѵ�.
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            //������ �� �ִ� ImageView�� ���ٸ� ImageView ��ü�� ���� �����Ѵ�.
            if (convertView == null) {
                imageView = new ImageView(context);
                //�̹��� ���� ũ��� �̹��� ũ�� �׸��� ������ �����Ѵ�.
                imageView.setLayoutParams(new GridView.LayoutParams(60, 60));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(10, 10, 10, 10);
            
            //������ �� �ִ� ImageView�� �ִٸ� �̸� �����Ѵ�.
            } else {
                imageView = (ImageView) convertView;
            }

            //�̹����� ���ڷ� �Ѿ�� position�� �°� �����Ѵ�.
            imageView.setImageResource(images[position]);

            return imageView;
        }
    }
}
