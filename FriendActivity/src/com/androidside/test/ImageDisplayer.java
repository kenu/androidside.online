package com.androidside.test;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class ImageDisplayer implements Runnable
{
	public ImageView view;
	public Bitmap bmp;


	public ImageDisplayer(ImageView imageView, Bitmap bmp)
	{
		this.view = imageView;
		this.bmp = bmp;
	}

	public void run()
	{
		view.setImageBitmap(bmp);
	}

}