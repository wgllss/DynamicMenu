package com.example.dynamicmenu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.example.dynamicmenu.GridViewActivity;
import com.example.dynamicmenu.R;
import com.example.dynamicmenu.widget.PullListView;
import com.example.dynamicmenu.widget.PullListView.PuLLListener;

public class MyFragment extends Fragment implements PuLLListener, OnItemClickListener {
	private static final String TAG = "TestFragment";

	private int hello;// = "hello android";
	private int defaultHello = 3;
	final String arr[] = { "AAAAAAAAAAAAAAAAAA", "bbbbbbbbbbbbbbbbbbbb", "ccccccccccccccc", "ddddddddddddddd", "eeeeeeeeeeeeeee", "fff", "AAA", "bbbb", "ccc", "ddd", "eee", "fff", "AAA", "bbbb",
			"ccc", "ddd", "eee", "fff", "AAA", "bbbb", "ccc", "ddd", "eee", "fff", "AAA", "bbbb", "ccc", "ddd", "eee", "fff", "AAA", "bbbb", "ccc", "ddd", "eee", "fff" };
	private PullListView list;

	public static MyFragment newInstance(int i) {
		MyFragment newFragment = new MyFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("hello", i);
		newFragment.setArguments(bundle);
		return newFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "TestFragment-----onCreate");
		Bundle args = getArguments();
		hello = args != null ? args.getInt("hello") : defaultHello;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "TestFragment-----onCreateView");
		View view = inflater.inflate(R.layout.lay1, container, false);
		list = (PullListView) view.findViewById(R.id.list);
		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item, arr);
		list.setAdapter(adapter);
		list.setPuLLListener(MyFragment.this);
		list.setPullLoadEnable(true);
		list.setOnItemClickListener(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onRefresh() {
		Handler handle = new Handler();
		handle.postDelayed(new Runnable() {

			@Override
			public void run() {
				list.stopRefresh();
			}
		}, 1000L);
	}

	@Override
	public void onLoadMore() {
		Handler handle = new Handler();
		handle.postDelayed(new Runnable() {

			@Override
			public void run() {
				list.stopLoadMore();
			}
		}, 1000L);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		getActivity().startActivity(new Intent(getActivity(), GridViewActivity.class));
	}

}
