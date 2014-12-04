package org.android.kpsdk.utils;

import org.android.kpsdk.KPAndroid;
import org.android.kpsdk.config.Configuration;

import android.util.Log;

public class KPApiFormateUrl {
	private static final String TAG = KPApiFormateUrl.class.getSimpleName();
	
	private static String CATEGORY	= "category/";
	private static String ALBUM		= "albums/";
	private static String VIDEOS	= "videos/";
	private static String MUSIC		= "musics/";
	private static String ACCESS_TOKEN = "?accessToken=" + KPAndroid.getInstance().API_KEY;
	
	public static String getArticleCategory() {
		Log.v(TAG, "getArticleCategory()");
		return Configuration.KP_API_SERVER + CATEGORY + ACCESS_TOKEN;
	}
	
	public static String getArticleDetails(String categoryId) {
		Log.v(TAG, "getArticleDetails()");
		return Configuration.KP_API_SERVER + CATEGORY + categoryId + ACCESS_TOKEN;
	}
	
	public static String getAlbumsList() {
		Log.v(TAG, "getAlbums()");
		return Configuration.KP_API_SERVER + ALBUM + ACCESS_TOKEN;
	}
	
	public static String getAlbumsAllPhotos(String albumId) {
		Log.v(TAG, "getAlbumsAllPhotos()");
		return Configuration.KP_API_SERVER + ALBUM + albumId + ACCESS_TOKEN;
	}
	
	public static String getVideosList() {
		Log.v(TAG, "getVideos()");
		return Configuration.KP_API_SERVER + VIDEOS + ACCESS_TOKEN;
	}
	
	public static String getVideoDetails(String videoId) {
		Log.v(TAG, "getAlbumsAllPhotos()");
		return Configuration.KP_API_SERVER + VIDEOS + videoId + ACCESS_TOKEN;
	}
	
	public static String getMusicsList() {
		Log.v(TAG, "getVideos()");
		return Configuration.KP_API_SERVER + VIDEOS + ACCESS_TOKEN;
	}
	
	public static String getMusicDetails(String musicId) {
		Log.v(TAG, "getAlbumsAllPhotos()");
		return Configuration.KP_API_SERVER + VIDEOS + musicId + ACCESS_TOKEN;
	}

}
