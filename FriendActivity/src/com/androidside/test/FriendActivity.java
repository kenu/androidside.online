package com.androidside.test;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

public class FriendActivity extends ImageActivity
{

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ArrayList friendNames = new ArrayList();
		friendNames.add("Sergey Brin");
		friendNames.add("Sergey Brin2");

		ArrayList friendUrls = new ArrayList();
		friendUrls.add("http://www.androidside.com/data/file/B40/1062675711_XhLwGA1a_sosi1.png");
		friendUrls.add("http://www.androidside.com/data/file/B40/1062675711_XhLwGA1a_sosi1.png");
			

		ListView friendView = new ListView(this.getBaseContext());
		friendView.setAdapter(new CustomAdapter(friendNames, friendUrls, this));

		setContentView(friendView);
	}

}