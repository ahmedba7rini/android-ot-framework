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

package com.oneteam.framework.android.ui.activity;

import android.content.Intent;

import com.oneteam.framework.android.service.IListener;
import com.oneteam.framework.android.service.IService;

/**
 * @author islam
 * 
 */
public interface IActivity {

	public static final String ACTION_UPDATE_SCREEN = "com.parfield.prayers.action_UPDATE_SCREEN";

	public static final String EXTRA_REQUIRES_UPDATE_VIEW = "extra_requires_update_view";

	public boolean onUpdateView(Intent intent);

	public void addListener(IListener listener);

	public void registerService(IService service);

	public void unregisterService(IService service);

}
