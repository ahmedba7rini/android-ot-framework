/**
 * 
 */
package com.oneteam.framework.android.task;

/**
 * @author islam
 * 
 */
public interface TaskCallback<R> {

	public void onRequestFailure(String error);

	public void onRequestSuccess(R results);

}
