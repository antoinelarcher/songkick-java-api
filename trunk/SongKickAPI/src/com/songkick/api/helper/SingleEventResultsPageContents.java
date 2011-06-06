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

public class SingleEventResultsPageContents implements ResultsPageContents {

	private SingleEventResults results;
	private String status;
	
	public void setResults(SingleEventResults results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Results getResults() {
		return results;
	}
	
	
}
