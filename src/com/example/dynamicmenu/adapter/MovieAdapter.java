package com.example.dynamicmenu.adapter;

import java.util.List;

import android.adapter.CommonAdapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dynamicmenu.MainActivity;
import com.example.dynamicmenu.R;

public class MovieAdapter extends CommonAdapter<String> implements
		OnTouchListener {

	public int currentPosition = -1;
	private Context mContext;

	public MovieAdapter(List<?> list) {
		super(list);
	}

	public MovieAdapter(List<?> list, Context mContext) {
		super(list);
		this.mContext = mContext;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		convertView = LayoutInflater.from(mContext).inflate(R.layout.movie,
				null);
		TextView txt = (TextView) convertView.findViewById(R.id.movie_text_0);
		txt.setText(list.get(position));
		if (currentPosition == -1) {
			txt.setTextColor(Color.BLACK);
		} else if (currentPosition == position) {
			txt.setTextColor(Color.RED);
		} else {
			txt.setTextColor(Color.BLACK);
		}

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				((MainActivity) mContext).setCurrentItem(position);
			}
		});
		convertView.setOnTouchListener(this);
		return convertView;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			((MainActivity) mContext).mSlidingMenu.setCanSliding(false, false);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_OUTSIDE:
		case MotionEvent.ACTION_UP:
			if (((MainActivity) mContext).getCurrentItem() == 0) {
				((MainActivity) mContext).mSlidingMenu.setCanSliding(true,
						false);
			} else if (((MainActivity) mContext).getCurrentItem() == (getCount() - 1)) {
				((MainActivity) mContext).mSlidingMenu.setCanSliding(false,
						true);
			} else {
				((MainActivity) mContext).mSlidingMenu.setCanSliding(false,
						false);
			}
			break;
		default:
			break;
		}
		return false;
	}
}
