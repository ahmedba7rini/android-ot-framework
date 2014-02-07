/**
 * 
 */
package com.oneteam.framework.android.service;

import android.net.NetworkInfo;

/**
 * @author islam
 * 
 */
public interface OnNetworkStateChangedListener {

	public void onChange(int type, NetworkInfo networkInfo);

}
