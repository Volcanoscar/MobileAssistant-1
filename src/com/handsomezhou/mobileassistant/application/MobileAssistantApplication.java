package com.handsomezhou.mobileassistant.application;

import android.app.Application;
import android.content.Context;

public class MobileAssistantApplication extends Application {
	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
	}

	public static Context getContextObject() {
		return mContext;
	}
}
