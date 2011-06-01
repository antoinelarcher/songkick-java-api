/**
 * $Revision$
 * $Date$
 * $Author$
 * 
 * $Log$
 *
 *
 * (c) 2008 Future Platforms
 * 
 */ 
package com.songkick.api.obj;

import java.util.Date;
import java.util.List;

public class Event {
	private String displayName;
	private String type;
	private String uri;
	private Venue venue;
	private City location;
	private SkTime start;
	private List<Performance> performance;
	private String id;
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public City getLocation() {
		return location;
	}
	public void setLocation(City location) {
		this.location = location;
	}
	public SkTime getStart() {
		return start;
	}
	public void setStart(SkTime start) {
		this.start = start;
	}
	public List<Performance> getPerformance() {
		return performance;
	}
	public void setPerformance(List<Performance> performance) {
		this.performance = performance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
