package com.oneteam.framework.android.app;

public class BasicManager extends AbstractManager {

	private static IManager sInstance;

	public static IManager getInstance() {

		if (sInstance == null) {

			sInstance = new BasicManager();
		}

		return sInstance;
	}

}
