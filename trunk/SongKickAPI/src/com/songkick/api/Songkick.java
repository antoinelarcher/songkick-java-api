package com.songkick.api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.songkick.api.helper.ArtistResultsPage;
import com.songkick.api.helper.EventResultsPage;
import com.songkick.api.helper.LocationResultsPage;
import com.songkick.api.obj.Artist;
import com.songkick.api.obj.Event;
import com.songkick.api.obj.EventFilter;
import com.songkick.api.obj.Location;

/**
 * Java API for Songkick
 * 
 * Released under the BSD license
 * 
 */

public class Songkick {
	private String apiKey;
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private static final int PAGE_SIZE = 50;
	private static Logger logger = Logger.getLogger(Songkick.class);
	
	public Songkick(String a) {
		this.apiKey = a;
	}
	
	/**
	 * Helper method; gets a Reader for a specific URL, silently appending our API key
	 * 
	 * @param u
	 * @return
	 * @throws IOException
	 */
	
	private BufferedReader getReader(String u) throws IOException {
		String urlStr = u;
		if (urlStr.contains("?")) {
			if (!urlStr.endsWith("?")) urlStr += "&";
		} else 
			urlStr += "?";
		urlStr += "apikey="+apiKey;
		logger.debug("connecting to " + urlStr);
		URL url = new URL(urlStr);
		HttpURLConnection c = (HttpURLConnection) url.openConnection();
		return new BufferedReader(new InputStreamReader(c.getInputStream()));
	}

	/**
	 * Helper method; pulls in a page of Artists matching a given name and returns it 
	 * 
	 * @param name
	 * @param page
	 * @return
	 * @throws IOException
	 */
	private ArtistResultsPage getArtistResultsPage(String name, int page) throws IOException {
		BufferedReader reader = getReader("http://api.songkick.com/api/3.0/search/artists.json?page=" + page + "&query=" + URLEncoder.encode(name, "UTF-8"));
		Gson gson = new Gson();
		return gson.fromJson(reader, ArtistResultsPage.class);
	}
	
	/**
	 * Return a page of all Artists matching a given name
	 * 
	 * @param name
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public List<Artist> getArtists(String name, int page) throws IOException {
		logger.info("getArtists() name="+name+",page="+page);
		return getArtistResultsPage(name, page).getResultsPage().getResults().getArtists();
	}

	/**
	 * Return a list of all Artists matching a given name
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	
	public List<Artist> getArtists(String name) throws IOException {
		logger.info("getArtists() name="+name);
		List<Artist> artists = new ArrayList<Artist>();
		int pageNum = 1;

		List<Artist> page = getArtists(name, pageNum);
		while (page!=null && (page.size()>0)) {
			artists.addAll(page);

			// If there might be more artists to grab, get another set
			if (page.size()==PAGE_SIZE) page = getArtists(name, ++pageNum); 
			else page = null;
		}
		return artists;
	}
	
	/**
	 * Helper method; pulls in a page of Location results matching a given name and returns it 
	 * 
	 * @param name
	 * @param page
	 * @return
	 * @throws IOException
	 */
	private LocationResultsPage getLocationResultsPage(String name, int page) throws IOException {
		logger.info("getLocationResultsPage() name="+name+",page="+page);
		BufferedReader reader = getReader("http://api.songkick.com/api/3.0/search/locations.json?page=" + page + "&query=" + URLEncoder.encode(name, "UTF-8"));
		Gson gson = new Gson();
		return gson.fromJson(reader,LocationResultsPage.class);
	}

	/**
	 * Return a specific page of Locations matching a given name
	 * 
	 * @param name
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public List<Location> getLocationsByName(String name, int page) throws IOException {
		logger.info("getLocationsByName() name="+name+",page="+page);
		return getLocationResultsPage(name, page).getResultsPage().getResults().getLocations();
	}
	
	/**
	 * Return all locations matching a given name
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	
	public List<Location> getLocationsByName(String name) throws IOException {
		logger.info("getLocationsByName() name="+name);
		List<Location> locations = new ArrayList<Location>();
		int pageNum = 1;

		List<Location> page = getLocationsByName(name, pageNum);
		while (page!=null && (page.size()>0)) {
			locations.addAll(page);
			// If there might be more locations to grab, get another set
			if (page.size()==PAGE_SIZE) page = getLocationsByName(name, ++pageNum);
			else page = null;
		}
		return locations;
	}
	
	/**
	 * Helper method; pulls in a page of location results specified by lat and lng and returns it 
	 * 
	 * @param lat
	 * @param lng
	 * @param page
	 * @return
	 * @throws IOException
	 */

	private LocationResultsPage getLocationResultsPage(double lat, double lng, int page) throws IOException {
		logger.info("getLocationResultsPage() lat="+lat+",lng="+lng+",page="+page);
		BufferedReader reader = getReader("http://api.songkick.com/api/3.0/search/locations.json?location=geo:" + lat + "," + lng +"&page="+page);
		Gson gson = new Gson();
		return gson.fromJson(reader,LocationResultsPage.class);
	}

	/**
	 * Return a specific page of Locations, given a lat and lng
	 * 
	 * @param lat
	 * @param lng
	 * @param page
	 * @return
	 * @throws IOException
	 */
	
	public List<Location> getLocationsByLatLng(double lat, double lng, int page) throws IOException {
		logger.info("getLocationsByLatLng() lat="+lat+",lng="+lng+",page="+page);
		return getLocationResultsPage(lat, lng, page).getResultsPage().getResults().getLocations();
	}
	
	/**
	 * Return a list of all Locations matching a given lat and lng
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 * @throws IOException
	 */
	
	public List<Location> getLocationsByLatLng(double lat, double lng) throws IOException {
		logger.info("getLocationsByLatLng() lat="+lat+",lng="+lng);
		List<Location> locations = new ArrayList<Location>();
		int pageNum = 1;

		List<Location> page = getLocationsByLatLng(lat, lng, pageNum);
		while (page!=null && (page.size()>0)) {
			locations.addAll(page);
			// If there might be more Locations to grab, get another set
			if (page.size()==PAGE_SIZE) page = getLocationsByLatLng(lat, lng, ++pageNum);
			else page = null;
		}
		return locations;
	}
	
	/**
	 * Return all events matching an EventFilter
	 * 
	 * @param ef
	 * @return
	 * @throws IOException
	 */

	public List<Event> getEvents(EventFilter ef) throws IOException {
		logger.info("getEvents() ef="+ef);
		List<Event> events = new ArrayList<Event>();
		int pageNum = 1;

		List<Event> page = getEvents(ef, pageNum);
		while (page!=null && (page.size()>0)) {
			events.addAll(page);
			// If there might be more Locations to grab, get another set
			if (page.size()==PAGE_SIZE) page = getEvents(ef, ++pageNum);
			else page = null;
		}
		return events;
	}
	
	/**
	 * Return a specific page of events matching a particular EventFilter
	 * 
	 * @param ef
	 * @param page
	 * @return
	 * @throws IOException
	 */
	
	
	public List<Event> getEvents(EventFilter ef, int page) throws IOException {
		logger.info("getEvents() ef="+ef+",page="+page);
		
		String url = "http://api.songkick.com/api/3.0/events.json?";
		if (ef.getArtistName()!=null) {
			url = url + "&artist_name=" + URLEncoder.encode(ef.getArtistName(), "UTF-8");
		}

		if (ef.getMaxDate()!=null) {
			url = url + "&max_date=" + dateFormatter.format(ef.getMaxDate());
		}

		if (ef.getMinDate()!=null) {
			url = url + "&min_date=" + dateFormatter.format(ef.getMinDate());
		}
		
		if (ef.getLocation()!=null) {
			if (ef.getLocation().isClientIp()) url = url + "&location=clientip";
			else if (ef.getLocation().getIp()!=null) url = url + "&location=ip:" + ef.getLocation().getIp();
			else if (ef.getLocation().getMetroId()!=null) url = url + "&location=sk:" + ef.getLocation().getMetroId();
			else if (ef.getLocation().getLat()!=Double.MAX_VALUE && ef.getLocation().getLng()!=Double.MAX_VALUE) url = url + "&location=geo:" + ef.getLocation().getLat() + "," + ef.getLocation().getLng();
		}

		url = url + "&page=" + page;
		
		BufferedReader reader = getReader(url);
		Gson gson = new Gson();
		return gson.fromJson(reader,EventResultsPage.class).getResultsPage().getResults().getEvent();
	}
	
	/**
	 * Get the calendar for a particular artist
	 * 
	 * @param artistId
	 * @return
	 * @throws IOException
	 */

	public List<Event> getArtistCalendar(String artistId) throws IOException {
		logger.info("getArtistCalendar() artistId="+artistId);
		List<Event> events = new ArrayList<Event>();
		int pageNum = 1;

		List<Event> page = getArtistCalendar(artistId, pageNum);
		while (page!=null && (page.size()>0)) {
			events.addAll(page);
			// If there might be more Locations to grab, get another set
			if (page.size()==PAGE_SIZE) page = getArtistCalendar(artistId, ++pageNum);
			else page = null;
		}
		return events;
	}

	/**
	 * Get the specific page of an artists calendar
	 * 
	 * @param artistId
	 * @param page
	 * @return
	 * @throws IOException
	 */
	
	public List<Event> getArtistCalendar(String artistId, int page) throws IOException {
		logger.info("getArtistCalendar() artistId="+artistId+",page="+page);
		BufferedReader reader = getReader("http://api.songkick.com/api/3.0/artists/" + artistId + "/calendar.json?page="+page);
		Gson gson = new Gson();
		return gson.fromJson(reader,EventResultsPage.class).getResultsPage().getResults().getEvent();
	}
	
	/**
	 * Get the calendar for a particular metro area
	 * 
	 * @param artistId
	 * @return
	 * @throws IOException
	 */

	//FIXME: so much repeated code around this pagination pattern. Sort it out.
	
	public List<Event> getMetroAreaCalendar(String areaId) throws IOException {
		logger.info("getMetroAreaCalendar() areaId="+areaId);
		List<Event> events = new ArrayList<Event>();
		int pageNum = 1;

		List<Event> page = getMetroAreaCalendar(areaId, pageNum);
		while (page!=null && (page.size()>0)) {
			events.addAll(page);
			// If there might be more Locations to grab, get another set
			if (page.size()==PAGE_SIZE) page = getMetroAreaCalendar(areaId, ++pageNum);
			else page = null;
		}
		return events;
	}
	
	public List<Event> getMetroAreaCalendar(String areaId, int page) throws IOException {
		logger.info("getMetroAreaCalendar() areaId="+areaId+",page="+page);
		BufferedReader reader = getReader("http://api.songkick.com/api/3.0/metro_areas/" + areaId + "/calendar.json?page="+page);
		Gson gson = new Gson();
		return gson.fromJson(reader,EventResultsPage.class).getResultsPage().getResults().getEvent();
	}
	
}
