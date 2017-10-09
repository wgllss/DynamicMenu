package com.example.dynamicmenu.app;

import android.app.Application;
import android.common.CommonApplication;

public class MyApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		CommonApplication.setApplication(this);
	}
}
