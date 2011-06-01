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
import com.songkick.api.obj.Artist;

public class ArtistResults {
	private List<Artist> artist;

	public List<Artist> getArtists() {
		return artist;
	}

	public void setArtist(List<Artist> artist) {
		this.artist = artist;
	}
}
