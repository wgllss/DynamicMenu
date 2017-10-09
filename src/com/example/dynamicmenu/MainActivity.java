package com.example.dynamicmenu;

import android.activity.CommonActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import com.example.dynamicmenu.fragment.LeftFragment;
import com.example.dynamicmenu.fragment.RightFragment;
import com.example.dynamicmenu.fragment.ViewPageFragment;
import com.example.dynamicmenu.fragment.ViewPageFragment.ViewPageChangeListener;
import com.example.dynamicmenu.widget.SlidingMenu;
import com.example.dynamicmenu.widget.SlidingView.OnScrollCloseListener;
import com.example.dynamicmenu.widget.SlidingView.OnScrollOpenListener;

public class MainActivity extends CommonActivity implements
		ViewPageChangeListener {

	public SlidingMenu mSlidingMenu;
	private LeftFragment leftFragment;
	private RightFragment rightFragment;
	private ViewPageFragment viewPageFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setSlidingMenu();
	}

	private void setSlidingMenu() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		mSlidingMenu.setAlignLeftScreenWidth((dm.widthPixels / 5) * 3);
		mSlidingMenu.setAlignRightScreenWidth((dm.widthPixels / 5) * 2);
		mSlidingMenu.setLeftView(getLayoutInflater().inflate(
				R.layout.left_frame, null));
		mSlidingMenu.setRightView(getLayoutInflater().inflate(
				R.layout.right_frame, null));
		mSlidingMenu.setCenterView(getLayoutInflater().inflate(
				R.layout.center_frame, null));
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		leftFragment = new LeftFragment();
		t.replace(R.id.left_frame, leftFragment);
		rightFragment = new RightFragment();
		t.replace(R.id.right_frame, rightFragment);
		viewPageFragment = new ViewPageFragment();
		t.replace(R.id.center_frame, viewPageFragment);
		t.commit();
		viewPageFragment.setViewPageChangeListener(this);
	}

	public void setCurrentItem(int position) {
		viewPageFragment.mViewPager.setCurrentItem(position);
	}

	public int getCurrentItem() {
		return viewPageFragment.mViewPager.getCurrentItem();
	}

	public void showLeft() {
		mSlidingMenu.showLeftView();
	}

	public void showRight() {
		mSlidingMenu.showRightView();
	}

	public void showCenter() {
		mSlidingMenu.showCenterView();
	}

	public void setOnScrollOpenListener(OnScrollOpenListener onScrollEndListener) {
		mSlidingMenu.setOnScrollOpenListener(onScrollEndListener);
	}

	public void setOnScrollCloseListener(
			OnScrollCloseListener mOnScrollCloseListener) {
		mSlidingMenu.setOnScrollCloseListener(mOnScrollCloseListener);
	}

	@Override
	public void onPageSelected(int position) {
		if (viewPageFragment.isFirst()) {
			mSlidingMenu.setCanSliding(true, false);
		} else if (viewPageFragment.isEnd()) {
			mSlidingMenu.setCanSliding(false, true);
		} else {
			mSlidingMenu.setCanSliding(false, false);
		}
	}
}
