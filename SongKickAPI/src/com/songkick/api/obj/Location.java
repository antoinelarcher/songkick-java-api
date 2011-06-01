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

public class Location {
	private City city;
	private MetroArea metroArea;
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public MetroArea getMetroArea() {
		return metroArea;
	}
	public void setMetroArea(MetroArea metroArea) {
		this.metroArea = metroArea;
	}
}
