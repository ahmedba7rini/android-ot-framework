package com.oneteam.framework.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.oneteam.framework.android.BuildConfig;

public class Logger {

	public static String TAG;

	private static int TAG_PREFIX_LENGTH;

	private static int MAX_TAG_LENGTH;

	private Logger() {
		;
	}

	public static void init(Context context, String prefix) {

		TAG = getTag(context, prefix);

		TAG_PREFIX_LENGTH = TAG.length();

		MAX_TAG_LENGTH = 35;
	}

	private static String getTag(Context context, String prefix) {

		StringBuilder tag = new StringBuilder(prefix);

		String pkgName = context.getPackageName();

		PackageManager pkgMgr = context.getPackageManager();

		PackageInfo pkgInfo;

		try {

			pkgInfo = pkgMgr.getPackageInfo(pkgName, 0);

		} catch (NameNotFoundException e) {

			Log.e(tag.toString(),
					"ERROR: Failed to get version no. due to the following error : "
							+ e.getMessage());

			e.printStackTrace();

			return tag.toString();
		}

		tag.append("-").append(pkgInfo.versionCode).append(":");

		return tag.toString();
	}

	public static String makeTag(String str) {

		if (str.length() > MAX_TAG_LENGTH - TAG_PREFIX_LENGTH) {

			return TAG
					+ str.substring(0, MAX_TAG_LENGTH - TAG_PREFIX_LENGTH - 1);
		}

		return TAG + str;
	}

	@SuppressWarnings("rawtypes")
	public static String makeTag(Class cls) {

		return makeTag(cls.getSimpleName());
	}

	/**
	 * Log as verbose
	 * 
	 * @param sTag
	 * @param sLog
	 */
	public static void v(String msg) {

		v("", msg);
	}

	public static void v(String tag, String msg) {

		if ((BuildConfig.DEBUG && Log.isLoggable(TAG, Log.VERBOSE))) {

			Log.v(TAG + tag, msg);
		}
	}

	/**
	 * Log as debug
	 * 
	 * @param sTag
	 * @param sLog
	 */
	public static void d(String msg) {

		d("", msg);
	}

	public static void d(String tag, String msg) {

		if (BuildConfig.DEBUG && Log.isLoggable(TAG, Log.DEBUG)) {

			Log.d(TAG + tag, msg);
		}
	}

	/**
	 * Log as information
	 * 
	 * @param sTag
	 * @param sLog
	 */
	public static void i(String msg) {

		i("", msg);
	}

	public static void i(String tag, String msg) {

		Log.i(TAG + tag, msg);
	}

	/**
	 * Log as warning
	 * 
	 * @param sTag
	 * @param sLog
	 */
	public static void w(String msg) {

		w("", msg);
	}

	public static void w(String tag, String msg) {

		Log.w(TAG + tag, msg);
	}

	/**
	 * Log as error
	 * 
	 * @param sTag
	 * @param sLog
	 */
	public static void e(String msg) {

		e("", msg);
	}

	public static void e(String tag, String msg) {

		Log.e(TAG + tag, msg);
	}

}