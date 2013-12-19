/**
 * 
 */
package com.oneteam.framework.android.ui.impl.viewmodel;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author islam
 * 
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {

	private Context mContext;

	private List<Class<?>> mClassesList;

	private List<Bundle> mExtrasList;

	private List<String> mTitlesList;

	public SectionPagerAdapter(Context context, FragmentManager fm,
			List<Class<?>> classes) {

		super(fm);

		mContext = context;

		mClassesList = classes;
	}

	public void setFragmentExtras(List<Bundle> extras) {
		mExtrasList = extras;
	}

	public void setFragmentTitles(List<String> titles) {
		mTitlesList = titles;
	}

	@Override
	public Fragment getItem(int position) {

		if (mClassesList == null) {
			return null;
		}

		Class<?> clss = mClassesList.get(position);

		Bundle args = null;

		if (mExtrasList != null) {
			args = mExtrasList.get(position);
		}

		Fragment fragment = Fragment
				.instantiate(mContext, clss.getName(), args);

		return fragment;
	}

	@Override
	public int getCount() {

		if (mClassesList != null) {

			return mClassesList.size();
		}

		return 0;
	}

	@Override
	public CharSequence getPageTitle(int position) {

		if (mTitlesList != null) {
			return mTitlesList.get(position);
		}

		return super.getPageTitle(position);
	}

}
