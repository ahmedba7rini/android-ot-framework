package com.oneteam.framework.android.app;

import android.content.Context;

import com.oneteam.framework.android.utils.Logger;

/**
 * 
 * Settings is the responsible for manipulating app settings for all different
 * states
 * 
 * @author islamsamak
 * 
 */
public class BasicSettings extends AbstractSettings {

	protected BasicSettings(Context context) {
		super(context);
	}

	public void init(Context context) {

		Logger.v("Settings.init()");

		loadDefaults();
	}

	public static AbstractSettings getInstance() throws IllegalStateException {

		if (sInstance == null) {
			// throw new IllegalStateException("Settings is uninitialized.");

			Logger.w("Settings is uninitialized");
		}

		return sInstance;
	}

	@Override
	protected void loadDefaults() {
	}

}
