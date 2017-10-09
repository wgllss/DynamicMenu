package com.example.dynamicmenu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class MovieLayout extends LinearLayout {

	private BaseAdapter adapter;
	private Context context;

	public MovieLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public void setAdapter(final BaseAdapter adapter) {
		this.adapter = adapter;
		for (int i = 0; i < adapter.getCount(); i++) {
			View view = adapter.getView(i, null, null);
			//view.setPadding(10, 0, 10, 0);
			this.setOrientation(HORIZONTAL);
			this.addView(view, new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
}
