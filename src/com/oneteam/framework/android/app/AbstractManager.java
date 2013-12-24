/*
 * Copyright (C) 2013 OneTeam (IslamSamak)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
