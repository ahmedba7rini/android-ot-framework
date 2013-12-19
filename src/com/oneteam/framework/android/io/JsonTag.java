package com.oneteam.framework.android.io;

public class JsonTag {

	public final String mName;

	public JsonTag() {
		this("");
	}

	protected JsonTag(String name) {
		mName = name;
	}

	@Override
	public String toString() {
		return mName;
	}
}
