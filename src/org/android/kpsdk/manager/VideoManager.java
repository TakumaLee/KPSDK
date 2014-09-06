package org.android.kpsdk.manager;

import java.util.ArrayList;
import java.util.List;

import org.android.kpsdk.http.HttpGetOOMAsyncTask;
import org.android.kpsdk.pojo.Video;
import org.android.kpsdk.pojo.VideoCategory;
import org.android.kpsdk.utils.KPApiFormateUrl;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class VideoManager {
	
	Context context;
	private static VideoManager instance;
	
	private VideoManager(Context context) {
		this.context = context;
	}
	
	public static synchronized VideoManager initSingleton(Context context) {
		if (instance == null && context != null) {
			Context appContext = context.getApplicationContext();
			instance = new VideoManager(appContext);
		}
		return instance;
	}
	
	public static synchronized VideoManager getInstance() {
		return instance;
	}
	
	public List<VideoCategory> fetchVideoList() {
		List<VideoCategory> videoCategories = new ArrayList<VideoCategory>();
		try {
			JSONObject object = new JSONObject(new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getVideosList()).get());
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject arrayObject = array.getJSONObject(i);
				VideoCategory video = new VideoCategory();
				video.setId(arrayObject.getString("id"));
				video.setTitle(arrayObject.getString("title"));
				video.setDescription("description");
				video.setLink(arrayObject.getString("link"));
				videoCategories.add(video);
			}
		} catch (Exception e) {
		}
		return videoCategories;
	}
	
	public List<Video> fetchVideoDetails(String videoId) {
		List<Video> videos = new ArrayList<Video>();
		try {
			JSONObject object = new JSONObject(new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getVideoDetails(videoId)).get());
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject arrayObject = array.getJSONObject(i);
				Video video = new Video();
				video.setId(arrayObject.getString("id"));
				video.setTitle(arrayObject.getString("title"));
				video.setDescription("description");
				video.setLink(arrayObject.getString("link"));
				videos.add(video);
			}
		} catch (Exception e) {
		}
		return videos;
	}

}
