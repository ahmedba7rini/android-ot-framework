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

package com.oneteam.framework.android.net;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import com.oneteam.framework.android.utils.Logger;

/**
 * @author islam
 * 
 */
public abstract class AbstractHttpConnection implements Connection {

	protected String mUrl;

	protected StatusLine mStatusLine;

	protected HttpResponse mHttpResponse;

	/**
	 * Establish a HTTP connection to the given url with the specified params
	 * 
	 * @param url
	 *            the connection url
	 */
	public AbstractHttpConnection(String url) {

		mUrl = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oneteam.framework.android.net.Connection#hasResponse()
	 */
	@Override
	public boolean hasResponse() {

		if (!hasConnection()) {
			return false;
		}

		StatusLine statusLine = mHttpResponse.getStatusLine();

		if (statusLine == null
				|| HttpStatus.SC_OK != statusLine.getStatusCode()) {

			Logger.d("Invalid response for this url ' " + mUrl + " '");

			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oneteam.framework.android.net.Connection#getStatus()
	 */
	@Override
	public StatusLine getStatus() {

		if (!hasConnection()) {
			return null;
		}

		StatusLine statusLine = mHttpResponse.getStatusLine();

		return statusLine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oneteam.framework.android.net.Connection#getContent()
	 */
	@Override
	public InputStream getContent() {

		if (!hasConnection()) {
			return null;
		}

		HttpEntity httpEntity = mHttpResponse.getEntity();

		try {

			InputStream in = httpEntity.getContent();

			return in;

		} catch (IllegalStateException e) {

			Logger.w("No stream content as it has already been obtained, Error > "
					+ e.getMessage());

			e.printStackTrace();

			return null;
		} catch (IOException e) {

			Logger.w("No stream content as stream could not be created, Error > "
					+ e.getMessage());

			e.printStackTrace();

			return null;
		}
	}

	protected boolean hasConnection() {

		if (mHttpResponse == null) {
			Logger.w("No connection is established for this url ' " + mUrl
					+ " '");

			return false;
		}

		return true;
	}

	protected void connect(HttpRequestBase httpMethod) {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {

			mHttpResponse = httpClient.execute(httpMethod);

		} catch (IllegalStateException e) {

			Logger.w("Invalid url, Error > " + e.getMessage());

			e.printStackTrace();

		} catch (ClientProtocolException e) {

			Logger.w("HTTP Protocol Error > " + e.getMessage());

			e.printStackTrace();

		} catch (IOException e) {

			Logger.w("IO Error > " + e.getMessage());

			e.printStackTrace();
		}
	}

}
