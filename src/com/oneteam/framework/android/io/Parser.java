/**
 * 
 */
package com.oneteam.framework.android.io;

import java.util.List;

import android.util.JsonReader;

import com.oneteam.framework.android.pattern.Command;

/**
 * @author islam
 * 
 */
public interface Parser<T> extends Command {

	public List<T> execute(JsonReader reader);

}
