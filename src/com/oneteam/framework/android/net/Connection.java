/**
 * 
 */
package com.oneteam.framework.android.net;

import java.io.InputStream;

import org.apache.http.StatusLine;

/**
 * @author islam
 * 
 */
public interface Connection {

	/**
	 * Establish a HTTP connection
	 */
	public void connect();

	/**
	 * Check if the connection is established and has a response, and the
	 * response is valid "200"
	 * 
	 * @return true if the connection response is valid, else returns false
	 */
	public boolean hasResponse();

	/**
	 * If there is a established connection, it will return the status of it
	 * 
	 * @return null if no connection is done, or the status
	 */
	public StatusLine getStatus();

	/**
	 * Returns the content of the url is the there is a valid response
	 * 
	 * @return Inputstream with the content, or null
	 */
	public InputStream getContent();

}
