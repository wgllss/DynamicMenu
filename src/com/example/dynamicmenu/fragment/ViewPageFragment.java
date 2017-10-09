/*
 * Copyright (C) 2012 yueyueniao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.dynamicmenu.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.dynamicmenu.MainActivity;
import com.example.dynamicmenu.R;
import com.example.dynamicmenu.adapter.MovieAdapter;
import com.example.dynamicmenu.widget.MovieLayout;
import com.example.dynamicmenu.widget.SlidingView;
import com.example.dynamicmenu.widget.SlidingView.OnScrollCloseListener;
import com.example.dynamicmenu.widget.SlidingView.OnScrollOpenListener;

import android.adapter.FragmentPagerAdapter;
import android.annotation.SuppressLint;
import android.common.DeviceManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.tween.CommonTraslate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class ViewPageFragment extends Fragment implements OnScrollOpenListener, OnScrollCloseListener, OnPageChangeListener, OnClickListener {

	private Button showLeft;
	private Button showRight;
	private FragmentPagerAdapter mAdapter;
	private Button station_m;
	private MovieLayout mMovieLayout;
	public ViewPager mViewPager;
	private ImageView mImageView;
	private MovieAdapter mMovieAdapter;
	private List<String> listTitle = new ArrayList<String>();
	public List<Fragment> mFragmentList = new ArrayList<Fragment>();;
	public FragmentPagerAdapter mFragmentPagerAdapter;
	private HorizontalScrollView mHorizontalScrollView;
	private int startLeft = 0;
	private Button btnAdd;
	private ViewPageChangeListener myPageChangeListener;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.view_pager, null);
		showLeft = (Button) mView.findViewById(R.id.showLeft);
		showRight = (Button) mView.findViewById(R.id.showRight);
		station_m = (Button) mView.findViewById(R.id.station_m);
		mMovieLayout = (MovieLayout) mView.findViewById(R.id.movie_layout);
		mViewPager = (ViewPager) mView.findViewById(R.id.pager);
		mImageView = (ImageView) mView.findViewById(R.id.img___0);
		mHorizontalScrollView = (HorizontalScrollView) mView.findViewById(R.id.horizon_srcoll);
		btnAdd = (Button) mView.findViewById(R.id.btn_add);
		// btnAdd.setVisibility(View.GONE);
		for (int i = 0; i < 10; i++) {
			Fragment activityfragment = MyFragment.newInstance(i);
			mFragmentList.add(activityfragment);
			listTitle.add("标题" + i);
		}
		mFragmentPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager(), mFragmentList);
		mViewPager.setAdapter(mFragmentPagerAdapter);
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.setOnPageChangeListener(this);
		mMovieAdapter = new MovieAdapter(listTitle, inflater.getContext());
		mMovieAdapter.currentPosition = 0;
		mMovieLayout.setAdapter(mMovieAdapter);
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		((MainActivity) getActivity()).setOnScrollOpenListener(this);
		((MainActivity) getActivity()).setOnScrollCloseListener(this);
		btnAdd.setOnClickListener(this);
		station_m.setOnClickListener(this);
		showLeft.setOnClickListener(this);
		showRight.setOnClickListener(this);
	}

	public boolean isFirst() {
		if (mViewPager.getCurrentItem() == 0)
			return true;
		else
			return false;
	}

	public boolean isEnd() {
		if (mViewPager.getCurrentItem() == mFragmentList.size() - 1)
			return true;
		else
			return false;
	}

	public void setViewPageChangeListener(ViewPageChangeListener l) {
		this.myPageChangeListener = l;
	}

	public interface ViewPageChangeListener {
		public void onPageSelected(int position);
	}

	@Override
	public void onScrollClose(SlidingView slidingView) {
		station_m.setVisibility(View.GONE);
	}

	@Override
	public void onScrollOpen(SlidingView slidingView) {
		station_m.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:
			listTitle.add("标题" + mFragmentList.size());
			Fragment activityfragment = MyFragment.newInstance(mFragmentList.size());
			mFragmentList.add(activityfragment);
			mFragmentPagerAdapter.notifyDataSetChanged();
			mMovieAdapter = new MovieAdapter(listTitle, getActivity());
			mMovieAdapter.currentPosition = 0;
			mMovieLayout.removeAllViews();
			mMovieLayout.setAdapter(mMovieAdapter);
			break;
		case R.id.showLeft:
			((MainActivity) getActivity()).showLeft();
			station_m.setVisibility(View.VISIBLE);
			break;
		case R.id.showRight:
			((MainActivity) getActivity()).showRight();
			station_m.setVisibility(View.VISIBLE);
			break;
		case R.id.station_m:
			((MainActivity) getActivity()).showCenter();
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@SuppressLint("NewApi")
	@Override
	public void onPageSelected(int arg0) {
		int traslate = arg0 * mImageView.getWidth() - 3 * (DeviceManager.getScreenWidth(getActivity())) / listTitle.size();
		CommonTraslate.TranslateAnimation(mImageView, startLeft, arg0 * mImageView.getWidth(), 0, 0, 400);
		startLeft = arg0 * mImageView.getWidth();
		mHorizontalScrollView.smoothScrollTo(traslate, 0);
		mMovieAdapter.currentPosition = arg0;
		mMovieLayout.removeAllViews();
		mMovieLayout.setAdapter(mMovieAdapter);
		if (myPageChangeListener != null) {
			myPageChangeListener.onPageSelected(arg0);
		}
	}
}
