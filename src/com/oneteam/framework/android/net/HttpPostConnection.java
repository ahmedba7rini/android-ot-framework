/**
 * 
 */
package com.oneteam.framework.android.net;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.oneteam.framework.android.utils.Logger;

/**
 * @author islam
 * 
 */
public class HttpPostConnection extends AbstractHttpConnection {

	protected Map<String, NameValuePair> mParamsMap;

	/**
	 * Establish a HTTP connection to the given url with the specified params
	 * 
	 * @param url
	 *            the connection url
	 */
	public HttpPostConnection(String url) {
		this(url, null);
	}

	/**
	 * Establish a HTTP connection to the given url with the specified params
	 * 
	 * @param url
	 *            the connection url
	 * @param params
	 *            the params for the HTTP connection method
	 */
	public HttpPostConnection(String url, Map<String, NameValuePair> paramsMap) {
		super(url);

		if (paramsMap == null) {
			mParamsMap = new HashMap<String, NameValuePair>(5);
		} else {
			mParamsMap = paramsMap;
		}
	}

	public boolean addParam(String key, String value) {

		if (mParamsMap == null) {
			return false;
		}

		NameValuePair pair = new BasicNameValuePair(key, value);

		mParamsMap.put(key, pair);

		return true;
	}

	public boolean removeParam(String key) {

		if (mParamsMap != null && mParamsMap.containsKey(key)) {

			mParamsMap.remove(key);

			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oneteam.framework.android.net.Connection#connect(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public void connect() {

		HttpPost httpPost = new HttpPost(mUrl);

		if (mParamsMap != null && mParamsMap.size() > 0) {

			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(
					mParamsMap.values());

			try {

				httpPost.setEntity(new UrlEncodedFormEntity(params));

			} catch (UnsupportedEncodingException e) {

				Logger.w("Failure while establishing url connection as unsupported encoding error > "
						+ e.getMessage());

				e.printStackTrace();
			}
		}

		super.connect(httpPost);
	}

}
