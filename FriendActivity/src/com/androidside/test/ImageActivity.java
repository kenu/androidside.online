package com.androidside.test;

import android.app.Activity;

public class ImageActivity extends Activity implements ImageReceivedCallback 
{

	public void onImageReceived(ImageDisplayer displayer) 
	{
		// run the ImageDisplayer on the UI thread
		this.runOnUiThread(displayer);
	}

}