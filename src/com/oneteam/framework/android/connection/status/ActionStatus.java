/**
 * 
 */
package com.oneteam.framework.android.connection.status;

import android.os.Parcel;

import com.oneteam.framework.android.io.IJsonNode;
import com.oneteam.framework.android.model.Model;

/**
 * @author islam
 * 
 */
public class ActionStatus<R, M> implements IJsonNode, Model {

	protected static final String UNAUTHORIZED_ACCESS = "unauthorized_user";

	private boolean mStatus;

	private R mResults;

	private M mMessage;

	public ActionStatus() {
	}

	/**
	 * @return the mStatus
	 */
	public boolean getStatus() {
		return mStatus;
	}

	/**
	 * @param status
	 *            the mStatus to set
	 */
	public void setStatus(boolean status) {
		mStatus = status;
	}

	/**
	 * @return the mResults
	 */
	public R getResults() {
		return mResults;
	}

	/**
	 * @param results
	 *            the mResults to set
	 */
	public void setResults(R results) {
		mResults = results;
	}

	/**
	 * @return the mMessage
	 */
	public M getMessage() {
		return mMessage;
	}

	/**
	 * @param message
	 *            the mMessage to set
	 */
	public void setMessage(M message) {
		mMessage = message;
	}

	/**
	 * Check the authority of the given access token
	 * 
	 * @return
	 */
	public boolean validate() {

		if (mMessage instanceof String) {

			if (!mStatus
					&& UNAUTHORIZED_ACCESS.equalsIgnoreCase((String) mMessage)) {
				return false;
			}

			return true;
		}

		return mStatus;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}

}
