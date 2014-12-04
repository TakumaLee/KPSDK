package org.android.kpsdk.manager;

import java.util.ArrayList;
import java.util.List;

import org.android.kpsdk.entity.Music;
import org.android.kpsdk.entity.MusicList;
import org.android.kpsdk.http.HttpGetOOMAsyncTask;
import org.android.kpsdk.utils.KPApiFormateUrl;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class MusicManager {
	
	Context context;
	private static MusicManager instance;
	
	private MusicManager(Context context) {
		this.context = context;
	}
	
	public static synchronized MusicManager initSingleton(Context context) {
		if (instance == null && context != null) {
			Context appContext = context.getApplicationContext();
			instance = new MusicManager(appContext);
		}
		return instance;
	}
	
	public static synchronized MusicManager getInstance() {
		return instance;
	}
	
	public List<MusicList> fetchMusicList() {
		List<MusicList> musicLists = new ArrayList<MusicList>();
		try {
			JSONObject object = new JSONObject(new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getMusicsList()).get());
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject arrayObject = array.getJSONObject(i);
				MusicList musicList = new MusicList();
				musicList.setId(arrayObject.getString("id"));
				musicList.setName(arrayObject.getString("name"));
				musicList.setCount(arrayObject.getInt("count"));
				musicLists.add(musicList);
			}
		} catch (Exception e) {
		}
		return musicLists;
	}
	
	public List<Music> fetchVideoDetails(String videoId) {
		List<Music> musics = new ArrayList<Music>();
		try {
			JSONObject object = new JSONObject(new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getMusicDetails(videoId)).get());
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject arrayObject = array.getJSONObject(i);
				Music music = new Music();
				music.setMusicID(arrayObject.getInt("id"));
				music.setSoundcloudID(arrayObject.getString("soundcloudID"));
				music.setVotes(arrayObject.getString("votes"));
				music.setSong_name(arrayObject.getString("song_name"));
				music.setGroupname(arrayObject.getString("groupname"));
				music.setLink(arrayObject.getString("link"));
				music.setCreative(arrayObject.getString("creative"));
				music.setTags(arrayObject.getString("tags"));
				music.setTrack_id(arrayObject.getLong("track_id"));
				music.setWaveform_url(arrayObject.getString("waveform_url"));
				music.setStream_url(arrayObject.getString("stream_url"));
				music.setPlay_count(arrayObject.getInt("play_count"));
				music.setFavoritings_count(arrayObject.getInt("favoritings_count"));
				music.setSoundcloud_url(arrayObject.getString("soundcloud_url"));
				musics.add(music);
			}
		} catch (Exception e) {
		}
		return musics;
	}

}
