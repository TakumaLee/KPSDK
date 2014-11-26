package org.android.kpsdk.pojo;

import java.util.Map;

public class Photo {
	
	private long dataSetId;
	private String dataSetTitle;
	
	private long id;
	private Map<String, String> images;
	private double latitude;
	private double longitude;
	private String title;
	private String isPrimary;
	private String link;
	public long getDataSetId() {
		return dataSetId;
	}
	public void setDataSetId(long dataSetId) {
		this.dataSetId = dataSetId;
	}
	public String getDataSetTitle() {
		return dataSetTitle;
	}
	public void setDataSetTitle(String dataSetTitle) {
		this.dataSetTitle = dataSetTitle;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Map<String, String> getImages() {
		return images;
	}
	public void setImages(Map<String, String> images) {
		this.images = images;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	

}
