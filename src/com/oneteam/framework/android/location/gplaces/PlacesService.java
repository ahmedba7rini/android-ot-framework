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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Create request for Places API.
 * 
 * @author Karn Shah
 * @Date 10/3/2013
 * 
 */
public class PlacesService {

	private String API_KEY;

	public PlacesService(String apikey) {
		this.API_KEY = apikey;
	}

	public void setApiKey(String apikey) {
		this.API_KEY = apikey;
	}

	public ArrayList<Place> findPlaces(double latitude, double longitude,
			String placeSpacification) throws IllegalStateException {

		String urlString = makeUrl(latitude, longitude, placeSpacification);

		try {
			String json = getJSON(urlString);

			System.out.println(json);

			JSONObject object = new JSONObject(json);

			String status = object.getString("status");

			if (!"ok".equalsIgnoreCase(status)) {
				throw new IllegalStateException(status);
			}

			JSONArray array = object.getJSONArray("results");

			ArrayList<Place> arrayList = new ArrayList<Place>(array.length());
			for (int i = 0; i < array.length(); i++) {
				try {
					Place place = Place
							.jsonToPontoReferencia((JSONObject) array.get(i));
					Log.v("Places Services ", "" + place);
					arrayList.add(place);
				} catch (Exception e) {
				}
			}
			return arrayList;
		} catch (JSONException ex) {
			Logger.getLogger(PlacesService.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

	// https://maps.googleapis.com/maps/api/place/search/json?location=28.632808,77.218276&radius=500&types=atm&sensor=false&key=apikey
	// https://maps.googleapis.com/maps/api/place/search/json?keyword=%22city%22&name=%22city%22&location=30.044541,31.235115&radius=5000&sensor=false&key=apikey
	private String makeUrl(double latitude, double longitude, String place) {

		StringBuilder urlString = new StringBuilder(
				"https://maps.googleapis.com/maps/api/place/search/json?");

		urlString.append("&location=");
		urlString.append(Double.toString(latitude));
		urlString.append(",");
		urlString.append(Double.toString(longitude));
		urlString.append("&radius=5000");
		urlString.append("&keyword=" + place);
		urlString.append("&name=" + place);
		urlString.append("&sensor=false&key=" + API_KEY);

		return urlString.toString();
	}

	protected String getJSON(String url) {
		return getUrlContents(url);
	}

	private String getUrlContents(String theUrl) {
		StringBuilder content = new StringBuilder();

		try {
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream()), 8);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}