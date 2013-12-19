/**
 * 
 */
package com.oneteam.framework.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.oneteam.framework.android.io.IJsonNode;

/**
 * @author islam
 * 
 */
public class Session implements IJsonNode, Model {

	private String mAccessToken;

	public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>() {
		public Session createFromParcel(Parcel in) {
			return new Session(in);
		}

		public Session[] newArray(int size) {
			return new Session[size];
		}
	};

	public Session(String accessToken) {
		mAccessToken = accessToken;
	}

	public Session(Parcel in) {
		readFromParcel(in);
	}

	public String getAccessToken() {
		return mAccessToken;
	}

	public void setAccessToken(String accessToken) {
		mAccessToken = accessToken;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mAccessToken);
	}

	public void readFromParcel(Parcel in) {
		mAccessToken = in.readString();
	}

}
