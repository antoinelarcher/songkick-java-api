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
package com.songkick.api.helper;
import java.util.List;

import com.songkick.api.obj.Location;

public class LocationResults {
	private List<Location> location;

	public List<Location> getLocations() {
		return location;
	}

	public void setArtist(List<Location> location) {
		this.location = location;
	}
}
