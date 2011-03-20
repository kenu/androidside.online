package com.androidside.test;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter
{

	// example data providing this custom adapter with a list of friend          
	// names and their respective image urls
	private List friendNames;
	private List friendUrls;

	// the image renderer callback
	private ImageReceivedCallback imageRenderCallback;

	public CustomAdapter(List friendNames, List friendUrls, 
			ImageReceivedCallback imageRenderCallback)

	{
		this.friendNames = friendNames;
		this.friendUrls = friendUrls;
		this.imageRenderCallback = imageRenderCallback;
	}


	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout layout = new LinearLayout(parent.getContext());
		layout.setOrientation(LinearLayout.HORIZONTAL);

		String friendName = (String) friendNames.get(position);
		String friendImageUrl = (String) friendUrls.get(position);

		ImageView imageView = new ImageView(layout.getContext());

		// will start downloading/rendering the image
		new ImageReceiver(friendImageUrl, imageRenderCallback,  imageView);

		TextView friendLabel = new TextView(layout.getContext());
		friendLabel.setText(friendName);

		layout.addView(friendLabel);
		layout.addView(imageView);

		return layout;
	}

	public int getCount() 
	{
		return friendNames.size();
	}

	public Object getItem(int position) 
	{
		return friendNames.get(position);
	}


	public long getItemId(int position) 
	{
		return 0;
	}

}
