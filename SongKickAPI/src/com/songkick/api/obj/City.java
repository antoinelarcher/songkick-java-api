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

public class City {
	private String displayName;
	private String city;
	private NamedItem country;
	private float lng;
	private float lat;
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public NamedItem getCountry() {
		return country;
	}
	public void setCountry(NamedItem country) {
		this.country = country;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
