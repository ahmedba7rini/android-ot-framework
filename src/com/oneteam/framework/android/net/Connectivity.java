/**
 * 
 */
package com.oneteam.framework.android.net;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.oneteam.framework.android.R;

/**
 * @author islam
 * 
 */
public class Connectivity {

	public boolean isConnected(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {

			NetworkInfo info = connectivity.getActiveNetworkInfo();

			if (info != null) {

				if (info.isConnected() && info.isAvailable()) {

					return true;
				}
			}
		}

		return false;
	}

	public AlertDialog createWarnNoInternetAccess(Context context) {
		return createWarning(context, R.drawable.ic_launcher,
				R.string.title_no_internet_access,
				R.string.msg_no_internet_access);
	}

	public AlertDialog createWarning(Context context, int icon, int title,
			int msg) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		alertDialog.setTitle(title);

		alertDialog.setMessage(context.getString(msg));

		alertDialog.setIcon(icon);

		return alertDialog;
	}

}
