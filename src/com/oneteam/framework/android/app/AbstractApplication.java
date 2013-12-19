package com.oneteam.framework.android.app;

import android.app.Application;
import android.content.res.Configuration;

import com.oneteam.framework.android.utils.Logger;

public abstract class AbstractApplication extends Application {

	private static AbstractApplication sInstance;

	public static AbstractApplication getApplication() {
		return sInstance;
	}

	@Override
	public void onCreate() {

		super.onCreate();

		String tag = getAppTag();

		onCreateApplication(tag);
	}

	protected abstract String getAppTag();

	protected void onCreateApplication(String tag) {

		sInstance = this;

		Logger.init(this, tag);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}
}