/**
 * 
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

	abstract public void onPreExecute();

	abstract public void doInBackground();

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		mBackgroundTask.cancel(true);

		this.finish();
	}

}
