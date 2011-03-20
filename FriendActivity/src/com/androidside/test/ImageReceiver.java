package com.androidside.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageReceiver extends Thread
{
	String url;
	ImageReceivedCallback callback;
	ImageView view;

	public ImageReceiver(String url, ImageReceivedCallback callback, ImageView view)
	{
		this.url = url;
		this.callback = callback;
		this.view = view;
		start();
	}

	public void run()
	{
		try
		{
			HttpURLConnection conn = (HttpURLConnection)(new URL(url)).openConnection();
			conn.connect();
			ImageDisplayer displayer = new ImageDisplayer(view, BitmapFactory.decodeStream(conn.getInputStream()));
			callback.onImageReceived(displayer);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}