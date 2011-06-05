package com.songkick.api.obj;

import java.util.List;

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

public class Artist {
	private String uri;
	private String displayName;
	private String id;
	private String onTourUntil;
	private List<Identifier> identifier;

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOnTourUntil() {
		return onTourUntil;
	}
	public void setOnTourUntil(String onTourUntil) {
		this.onTourUntil = onTourUntil;
	}
	public List<Identifier> getIdentifier() {
		return identifier;
	}
	public void setIdentifier(List<Identifier> identifier) {
		this.identifier = identifier;
	}
	
}
