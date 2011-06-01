package com.songkick.api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.songkick.api.helper.ArtistResultsPage;
import com.songkick.api.helper.LocationResultsPage;
import com.songkick.api.obj.Artist;
import com.songkick.api.obj.Location;

/**
 * $Revision$ $Date$ $Author$
 * 
 * $Log$
 * 
 * 
 * (c) 2008 Future Platforms
 * 
 */

public class Songkick {
	private String apiKey;
	
	public Songkick(String a) {
		this.apiKey = a;
	}

	private BufferedReader getReader(String u) throws IOException {
		String urlStr = u + "&apikey="+apiKey;
		System.err.println("url="+urlStr);
		URL url = new URL(urlStr);
		HttpURLConnection c = (HttpURLConnection) url.openConnection();
		return new BufferedReader(new InputStreamReader(c.getInputStream()));
	}

	private ArtistResultsPage getArtistResultsPage(String name, int page) throws IOException {
		BufferedReader reader = getReader("http://api.songkick.com/api/3.0/search/artists.json?page=" + page + "&query=" + URLEncoder.encode(name, "UTF-8"));
		Gson gson = new Gson();
		return gson.fromJson(reader, ArtistResultsPage.class);
	}
	
	public List<Artist> getArtists(String name, int page) throws IOException {
		return getArtistResultsPage(name, page).getResultsPage().getResults().getArtists();
	}

	public List<Artist> getArtists(String name) throws IOException {
		List<Artist> artists = new ArrayList<Artist>();
		int pageNum = 1;

		List<Artist> page = getArtists(name, pageNum);
		while (page!=null && (page.size()>0)) {
			artists.addAll(page);

			// If there might be more artists to grab, get another set
			if (page.size()==50) page = getArtists(name, ++pageNum); 
			else page = null;
		}
		return artists;
	}
	
	private LocationResultsPage getLocationResultsPage(String name, int page) throws IOException {
		BufferedReader reader = getReader("http://api.songkick.com/api/3.0/search/locations.json?page=" + page + "&query=" + URLEncoder.encode(name, "UTF-8"));
		Gson gson = new Gson();
		return gson.fromJson(reader,LocationResultsPage.class);
	}

	
	public List<Location> getLocationsByName(String name, int page) throws IOException {
		return getLocationResultsPage(name, page).getResultsPage().getResults().getLocations();
	}
	
	public List<Location> getLocationsByName(String name) throws IOException {
		List<Location> locations = new ArrayList<Location>();
		int pageNum = 1;

		List<Location> page = getLocationsByName(name, pageNum);
		while (page!=null && (page.size()>0)) {
			locations.addAll(page);
			// If there might be more locations to grab, get another set
			if (page.size()==50) page = getLocationsByName(name, ++pageNum);
			else page = null;
		}
		return locations;
	}

	private LocationResultsPage getLocationResultsPage(double lat, double lng, int page) throws IOException {
		BufferedReader reader = getReader("http://api.songkick.com/api/3.0/search/locations.json?location=geo:" + lat + "," + lng +"&page="+page);
		Gson gson = new Gson();
		return gson.fromJson(reader,LocationResultsPage.class);
	}

	public List<Location> getLocationsByLatLng(double lat, double lng, int page) throws IOException {
		return getLocationResultsPage(lat, lng, page).getResultsPage().getResults().getLocations();
	}
	
	public List<Location> getLocationsByLatLng(double lat, double lng) throws IOException {
		List<Location> locations = new ArrayList<Location>();
		int pageNum = 1;

		List<Location> page = getLocationsByLatLng(lat, lng, pageNum);
		while (page!=null && (page.size()>0)) {
			locations.addAll(page);
			// If there might be more locations to grab, get another set
			if (page.size()==50) page = getLocationsByLatLng(lat, lng, ++pageNum);
			else page = null;
		}
		return locations;
	}

}
