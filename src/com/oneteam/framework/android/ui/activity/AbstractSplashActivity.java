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

package com.oneteam.framework.android.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author islam
 * 
 */
public abstract class AbstractSplashActivity extends AbstractSherlockActivity {

	protected Class<?> mTargetClass;

	protected Bundle mTargetExtras;

	protected BackgroundTask mBackgroundTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mBackgroundTask = new BackgroundTask();

		mBackgroundTask.execute();
	}

	protected void onCreateActivity(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	}

	protected class BackgroundTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			AbstractSplashActivity.this.onPreExecute();

		}

		@Override
		protected Void doInBackground(Void... params) {

			synchronized (this) {
				AbstractSplashActivity.this.doInBackground();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			super.onPostExecute(result);

			startTarget();
		}
	}

	/**
	 * 
	 */
	protected void startTarget() {

		if (mTargetClass == null) {
			throw new IllegalStateException("No target activity is specified");
		}

		// start a new activity
		Intent intent = new Intent(AbstractSplashActivity.this, mTargetClass);

		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

		startActivity(intent);

		finish();
	}

	public void setTargetClass(Class<?> targetClass) {
		mTargetClass = targetClass;
	}

	public void setTargetExtras(Bundle targetExtras) {
		mTargetExtras = targetExtras;
	}

	abstract public void onPreExecute();

	abstract public void doInBackground();

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		mBackgroundTask.cancel(true);

		this.finish();
	}

}
