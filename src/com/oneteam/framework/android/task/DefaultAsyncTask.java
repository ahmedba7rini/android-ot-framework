/**
 * 
 */
package com.oneteam.framework.android.task;

import android.os.AsyncTask;

/**
 * @author islam
 * 
 */
public class DefaultAsyncTask<P, G, R> extends AsyncTask<P, G, R> {

	protected TaskCallback<R> mCallback;

	public void setTaskCallback(TaskCallback<R> callback) {
		mCallback = callback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(G... values) {
		super.onProgressUpdate(values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected R doInBackground(P... params) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(R result) {
		super.onPostExecute(result);

		if (mCallback != null) {
			mCallback.onRequestSuccess(result);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onCancelled(java.lang.Object)
	 */
	@Override
	protected void onCancelled(R result) {
		super.onCancelled(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onCancelled()
	 */
	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

}
