package org.android.kpsdk.entity;

import java.util.Map;

public class Album {
	
	private String id;
	private int photos;
	private int videos;
	private String title;
	private String description;
	private long date_create;
	private long date_update;
	private String link;
	private Map<String, String> thumbnails;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPhotos() {
		return photos;
	}
	public void setPhotos(int photos) {
		this.photos = photos;
	}
	public int getVideos() {
		return videos;
	}
	public void setVideos(int videos) {
		this.videos = videos;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getDate_create() {
		return date_create;
	}
	public void setDate_create(long date_create) {
		this.date_create = date_create;
	}
	public long getDate_update() {
		return date_update;
	}
	public void setDate_update(long date_update) {
		this.date_update = date_update;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Map<String, String> getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(Map<String, String> thumbnails) {
		this.thumbnails = thumbnails;
	}
	

}
