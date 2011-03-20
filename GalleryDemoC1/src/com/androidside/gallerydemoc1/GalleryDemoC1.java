package com.androidside.gallerydemoc1;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class GalleryDemoC1 extends Activity {
    private Cursor cursor;
    private int columnIndex;
    private Gallery gallery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //managedQuery �޼ҵ忡�� ��ȸ�� �÷� �̸��� �����Ѵ�.
        //�̹����� ����� ���̵� �����Ѵ�.
        String[] proj = { MediaStore.Images.Thumbnails._ID };
        
        //�ܺ� �޸𸮿� �ִ� �̹��� ������ ��ȸ�Ѵ�.        
        cursor = managedQuery(
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, //�ܺ� �޸� 
                proj, //��ȸ�� �÷�
                null, //WHERE��, ���� ����
                null, //WHERE��, ���� ���� ����
                null); //Order by��, ���� ���� ����
        startManagingCursor(cursor);

        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
        
        // /res/layout/main.xml�� ������ gallery1�̶�� �̸��� ���� Gallery�� �ҷ��´�
        gallery = (Gallery) findViewById(R.id.gallery1);
        
        //���������� ������ �̹����� ó���ϴ� ����͸� �����Ѵ�.
        //ImageAdapter�� BaseAdapter�� Ȯ���ؼ� ������ �Ѵ�.
        gallery.setAdapter(new ImageAdapter(this));

        //�������� �̹����� Ŭ������ �� �ش� �̹����� ũ�� �����ֱ� ���� ������ �����Ѵ�.
        //OnItemClickListener ��ü�� �������� �����ϸ� Ŭ�� �̺�Ʈ�� �߻����� �� ���ϴ� ó���� �� �� �ִ�.
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position,
                    long id) {
                //managedQuery �޼ҵ忡�� ��ȸ�� �÷� �̸��� �����Ѵ�.
                //�̹��� �����͸� �����Ѵ�.
                String[] proj = { MediaStore.Images.Media.DATA };
                
                //�ܺ� �޸𸮿� �ִ� �̹��� ������ ��ȸ�Ѵ�.        
                Cursor dataCursor = managedQuery(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, //�ܺ� �޸� 
                        proj, //��ȸ�� �÷�
                        null, //WHERE��, ���� ����
                        null, //WHERE��, ���� ���� ����
                        null); //Order by��, ���� ���� ����
                
                //�����Ϳ� �ش��ϴ� �÷� �ε����� ��´�.
                int columnIndex = dataCursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        
                //���������� ����ڰ� ������ �����ۿ� �ش��ϴ� ��ġ�� Ŀ���� �̵��Ѵ�.
                dataCursor.moveToPosition((int) gallery.getSelectedItemId());
        
                //������ �ε����� �ش��ϴ� �̹��� ���� �̸��� ��´�. 
                String filename = dataCursor.getString(columnIndex);
        
                
                //���� �̸����� ��Ʈ���� ��´�. 
                Bitmap bitmap = BitmapFactory.decodeFile(filename);
        
                //��Ʈ�� �̹����� �̹����信 �����Ѵ�.
                ImageView imageView = (ImageView) findViewById(R.id.image1);
                imageView.setImageBitmap(bitmap);
                
                dataCursor.close();
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;

        public ImageAdapter(Context c) {
            context = c;

            // �������� ǥ���ϱ� ���� ��� ��Ÿ���� ��´�.
            // R.styleable.Gallery1�� �������� ���� ����� �����ϱ� ���� ��Ÿ�� �̸��̸�
            // �� ���������� /res/values/styles.xml�� �ۼ��ߴ�.
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
            itemBackground = a.getResourceId(
                    R.styleable.Gallery1_android_galleryItemBackground, 0);

            // ������ ��� ��Ÿ���� ��� ���� ����ߴ� �ڿ��� �����Ѵ�.
            a.recycle();
        }

        public int getCount() {
            return cursor.getCount();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            
            if (convertView == null) {
                imageView = new ImageView(context);    
                
                //�̹����� �Ʒ����� ������ ���̾ƿ� ũ���� �߾ӿ� ��ġ�Ѵ�.
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                
                //�������� �������� �̹��� ũ�⸦ �����Ѵ�. 100*80
                imageView.setLayoutParams(new Gallery.LayoutParams(100, 80));
                
                //ImageView ��� ��Ÿ�Ͽ� Gallery�� ��� ��Ÿ���� �����Ѵ�. 
                imageView.setBackgroundResource(itemBackground);
            } else {
                imageView = (ImageView) convertView;
            }
            
            cursor.moveToPosition(position);
                
            int id = cursor.getInt(columnIndex);
            
            //�̹��� ��θ� �����Ѵ�.
            imageView.setImageURI(Uri.withAppendedPath(
                    MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + id));        
            
            return imageView;
        }
    }
}