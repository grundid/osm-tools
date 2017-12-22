package org.osmtools.taginfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeysValues {

	private double fraction;
	private String value;
	private int count;
	private String description;
	@JsonProperty("in_wiki")
	private Boolean inWiki;

	public Boolean getInWiki() {
		return inWiki;
	}

	public void setInWiki(Boolean inWiki) {
		this.inWiki = inWiki;
	}

	public double getFraction() {
		return fraction;
	}

	public void setFraction(double fraction) {
		this.fraction = fraction;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
