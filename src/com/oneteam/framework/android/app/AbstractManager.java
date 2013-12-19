package com.oneteam.framework.android.app;

import com.oneteam.framework.android.app.config.Configuration;
import com.oneteam.framework.android.utils.Logger;

public abstract class AbstractManager implements IManager {

	protected boolean mInitialized;

	protected AbstractManager() {
	}

	public void init(Configuration configs) {

		if (configs.isMonitored) {

			Logger.init(AbstractApplication.getApplication(),
					AbstractApplication.getApplication().getAppTag());
		}

		Logger.d("Initializing ImagesApp ... !!");

		initDataProvider();

		BasicSettings settings = (BasicSettings) BasicSettings.getInstance();

		settings.init(AbstractApplication.getApplication());

		mInitialized = true;

	}

	public boolean isInitialized() {
		return mInitialized;
	}

	@Override
	public void initDataProvider() {
	}

	@Override
	public void clearMemoryCache() {
	}

	@Override
	public void clearStorageCache() {
	}

	@Override
	public void destory() {
	}

}
