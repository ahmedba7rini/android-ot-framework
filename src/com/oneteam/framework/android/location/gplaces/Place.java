/*
 * Copyright (C) 2013 OneTeam (IslamSamak : islamsamak01@gmail.com)
 * Karn Shah (10/3/2013)
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

package com.oneteam.framework.android.location.gplaces;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.os.Parcel;

import com.oneteam.framework.android.model.MapModel;

/**
 * Model class for Places data.
 * 
 * @author Karn Shah
 * @Date 10/3/2013
 * 
 */
public class Place implements MapModel {

	private String id;
	private String icon;
	private String name;
	private String vicinity;
	private Double latitude;
	private Double longitude;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Drawable getIcon() {
		return null;
	}

	public String getIconPath() {
		return icon;
	}

	public void setIconPath(String icon) {
		this.icon = icon;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	static Place jsonToPontoReferencia(JSONObject pontoReferencia) {
		try {
			Place result = new Place();
			JSONObject geometry = (JSONObject) pontoReferencia.get("geometry");
			JSONObject location = (JSONObject) geometry.get("location");
			result.setLatitude((Double) location.get("lat"));
			result.setLongitude((Double) location.get("lng"));
			result.setIconPath(pontoReferencia.getString("icon"));
			result.setName(pontoReferencia.getString("name"));
			result.setVicinity(pontoReferencia.getString("vicinity"));
			result.setId(pontoReferencia.getString("id"));
			return result;
		} catch (JSONException ex) {
			Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public String toString() {
		return "Place{" + "id=" + id + ", icon=" + icon + ", name=" + name
				+ ", latitude=" + latitude + ", longitude=" + longitude + '}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public String getSnippet() {
		return name + "\n" + vicinity;
	}

	@Override
	public String getThumbnail() {
		return null;
	}

	@Override
	public void readFromParcel(Parcel in) {
	}

}