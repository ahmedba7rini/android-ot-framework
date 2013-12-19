/**
 * 
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
