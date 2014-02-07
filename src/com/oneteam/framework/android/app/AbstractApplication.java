/*
 * Copyright (C) 2013 OneTeam (IslamSamak : islamsamak01@gmail.com)
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