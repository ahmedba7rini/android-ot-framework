/**
 * 
 */
package com.oneteam.framework.android.ui.impl.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.oneteam.framework.android.R;
import com.oneteam.framework.android.net.Connectivity;
import com.oneteam.framework.android.ui.activity.AbstractSherlockActivity;
import com.oneteam.framework.android.utils.Logger;

/**
 * @author islam
 * 
 */
public class DefaultSherlockActivity extends AbstractSherlockActivity {

	public static final String EXTRA_SESSION = "extra_session";

	private BroadcastReceiver mNetworkStateReceiver = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oneteam.framework.android.ui.activity.AbstractSherlockActivity#
	 * onCreateActivity(android.os.Bundle)
	 */
	@Override
	protected void onCreateActivity(Bundle savedInstanceState) {

	}

	@Override
	protected void onStart() {
		super.onStart();

		final Connectivity connectivity = new Connectivity();

		if (!connectivity.isConnected(getApplication())) {

			showNoInternetAccessWarning();

			Logger.v("DefaultSherlockActivity::onStart[]: Connectivity warning dialog is shown successfully");
		}
	}

	protected void setupNetworkStateReceiver() {

		if (mNetworkStateReceiver != null) {
			return;
		}

		mNetworkStateReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {

				if (intent == null || intent.getExtras() == null) {
					Logger.d("Invalid change in network state. No intent data or no extras found.");
					return;
				}

				Bundle extras = intent.getExtras();

				@SuppressWarnings("deprecation")
				NetworkInfo networkInfo = (NetworkInfo) extras
						.getParcelable(ConnectivityManager.EXTRA_NETWORK_INFO);

				if (networkInfo == null) {

					Logger.d("No network state info is found.");

					return;
				}

				// Check the type of the connectivity
				int type = networkInfo.getType();

				if (ConnectivityManager.TYPE_MOBILE == type
						|| ConnectivityManager.TYPE_WIFI == type
						|| ConnectivityManager.TYPE_WIMAX == type) {

					boolean isConnected = networkInfo.isConnectedOrConnecting();

					if (!isConnected) {

						showNoInternetAccessWarning();
					}
				}
			}
		};

		registerReceiver(mNetworkStateReceiver, new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION));
	}

	protected void showNoInternetAccessWarning() {

		final Connectivity connectivity = new Connectivity();

		AlertDialog alertDialog = connectivity.createWarning(
				DefaultSherlockActivity.this,
				android.R.drawable.ic_dialog_alert,
				R.string.title_no_internet_access,
				R.string.msg_no_internet_access);

		String btnName = getString(android.R.string.ok);

		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, btnName,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						DefaultSherlockActivity.this.finish();
					}
				});

		alertDialog.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mNetworkStateReceiver != null) {

			try {

				unregisterReceiver(mNetworkStateReceiver);

			} catch (Exception e) {
			}
		}
	}

}