/*
 * Copyright (C) 2013 OneTeam (IslamSamak : islamsamak01@gmail.com)
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

package com.oneteam.framework.android.ui.activity;

import java.util.Hashtable;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.oneteam.framework.android.R;
import com.oneteam.framework.android.service.IListener;
import com.oneteam.framework.android.service.IService;

public abstract class AbstractActivity extends Activity implements IActivity {

	protected boolean mRequiresUpdateView = true;

	protected Hashtable<String, IListener> mListenerMap;

	protected Hashtable<String, IService> mServiceMap;

	protected BroadcastReceiver mUpdateScreenReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent != null) {

				Bundle extras = intent.getExtras();

				if (extras != null && !extras.isEmpty()) {

					mRequiresUpdateView = extras.getBoolean(
							IActivity.EXTRA_REQUIRES_UPDATE_VIEW, false);
				}
			}

			boolean updated = onUpdateView(intent);

			if (updated) {
				mRequiresUpdateView = false;
			}
		}
	};

	public AbstractActivity() {

		mListenerMap = new Hashtable<String, IListener>();

		mServiceMap = new Hashtable<String, IService>();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_basic);

		IntentFilter filter = new IntentFilter(ACTION_UPDATE_SCREEN);

		registerReceiver(mUpdateScreenReceiver, filter);

	}

	@Override
	public void addListener(IListener listener) {

	}

	@Override
	public void registerService(IService service) {

	}

	@Override
	public void unregisterService(IService service) {

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();

		if (mUpdateScreenReceiver != null) {
			unregisterReceiver(mUpdateScreenReceiver);
		}
	}

}
