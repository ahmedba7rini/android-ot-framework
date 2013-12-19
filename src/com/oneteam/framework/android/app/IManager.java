package com.oneteam.framework.android.app;

import com.oneteam.framework.android.app.config.Configuration;

public interface IManager {

	public void init(Configuration config);

	public void initDataProvider(/* SqlDataBase database */);

	public void clearMemoryCache();

	public void clearStorageCache();

	public void destory();

}
