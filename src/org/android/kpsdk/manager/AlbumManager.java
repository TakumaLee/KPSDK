package org.android.kpsdk.manager;

import java.util.ArrayList;
import java.util.List;

import org.android.kpsdk.http.HttpGetOOMAsyncTask;
import org.android.kpsdk.pojo.Album;
import org.android.kpsdk.pojo.ArticleCategory;
import org.android.kpsdk.pojo.Photo;
import org.android.kpsdk.utils.KPApiFormateUrl;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class AlbumManager {
	
	Context context;
	private static AlbumManager instance;
	
	private AlbumManager(Context context) {
		this.context = context;
	}
	
	public static synchronized AlbumManager initSingleton(Context context) {
		if (instance == null && context != null) {
			Context appContext = context.getApplicationContext();
			instance = new AlbumManager(appContext);
		}
		return instance;
	}
	
	public static synchronized AlbumManager getInstance() {
		return instance;
	}
	
	public List<Album> fetchAlbum() {
		List<Album> albums = new ArrayList<Album>();
		try {
			JSONObject object = new JSONObject(new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getAlbumsList()).get());
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject arrayObject = array.getJSONObject(i);
				Album album = new Album();
				album.setId(arrayObject.getString("id"));
				album.setPhotos(arrayObject.getInt("photos"));
				album.setVideos(arrayObject.getInt("videos"));
				album.setTitle(arrayObject.getString("title"));
				album.setDescription("description");
				album.setDate_create(arrayObject.getLong("date_create"));
				album.setDate_update(arrayObject.getLong("date_create"));
				album.setLink(arrayObject.getString("link"));
				albums.add(album);
			}
		} catch (Exception e) {
		}
		return albums;
	}
	
	public List<Photo> fetchPhotos(String albumId) {
		List<Photo> photos = new ArrayList<Photo>();
		try {
			String string = new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getAlbumsAllPhotos(albumId)).get();
			JSONObject object = new JSONObject(string);
			JSONObject dataObject = object.getJSONObject("data");
			JSONArray photoJsonArray = dataObject.getJSONArray("photos");
			for (int i = 0; i < photoJsonArray.length(); i++) {
//				JSONObject arrayObject = dataObject.optJSONObject(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photos;
	}

}
