package org.android.kpsdk;

import java.util.ArrayList;
import java.util.List;

import org.android.kpsdk.listeners.OnAlbumListener;
import org.android.kpsdk.listeners.OnArticleCategoryListener;
import org.android.kpsdk.listeners.OnArticleListener;
import org.android.kpsdk.listeners.OnPhotoListener;
import org.android.kpsdk.listeners.OnVideoCategoryListener;
import org.android.kpsdk.manager.AlbumManager;
import org.android.kpsdk.manager.ArticleManager;
import org.android.kpsdk.manager.VideoManager;
import org.android.kpsdk.pojo.Album;
import org.android.kpsdk.pojo.Article;
import org.android.kpsdk.pojo.ArticleCategory;
import org.android.kpsdk.pojo.Photo;
import org.android.kpsdk.pojo.VideoCategory;

import android.content.Context;

public class KPAndroid {
	
	Context	context;
	private	static KPAndroid instance = null;
	public	String API_KEY;
	
	public static synchronized KPAndroid initSingleton(Context context, String appKey) {
		ArticleManager.initSingleton(context);
		AlbumManager.initSingleton(context);
		VideoManager.initSingleton(context);
		if (instance == null && context != null) {
			Context appContext = context.getApplicationContext();
			instance = new KPAndroid(appContext, appKey);
		}
		return instance;
	}
	
	public static KPAndroid getInstance() {
		return instance;
	}
	
	private KPAndroid(Context context, String appKey) {
		this.context = context;
		API_KEY = appKey;
	}
	
	public void fetchArticles(final int categoryId, final OnArticleListener onArticleListener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<Article> articleLists = null;
				articleLists = ArticleManager.getInstance().fetchArticleDetails(categoryId);
				if (onArticleListener != null)
					onArticleListener.onComplete(articleLists);
			}
		}).start();
		
	}
	
	public void fetchAllArticles(final List<ArticleCategory> articleCategories, final OnArticleListener onArticleListener) {
		List<List<Article>> articles = new ArrayList<List<Article>>();
		for (int i = 0; i < articleCategories.size(); i++) {
			List<Article> articleLists = null;
			articleLists = ArticleManager.getInstance().fetchArticleDetails(articleCategories.get(i).getId());
			articles.add(articleLists);
		}
		if (onArticleListener != null)
			onArticleListener.onAllComplete(articles);
	}
	
	public void fetchArticleCategory(final OnArticleCategoryListener onArticleCategoryListener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<ArticleCategory> articleCategories = null;
				articleCategories = ArticleManager.getInstance().fetchArticleCategory();
				if (onArticleCategoryListener != null)
					onArticleCategoryListener.onComplete(articleCategories);
			}
		}).start();
		
	}
	
	public void fetchAlbum(final OnAlbumListener onAlbumListener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<Album> albums = null;
				albums = AlbumManager.getInstance().fetchAlbum();
				if (onAlbumListener != null)
					onAlbumListener.onComplete(albums);
			}
		}).start();
	}
	
	public void fetchPhoto(final String albumId, final OnPhotoListener onPhotoListener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<Photo> photos = null;
				photos = AlbumManager.getInstance().fetchPhotos(albumId);
				if (onPhotoListener != null)
					onPhotoListener.onComplete(photos);
			}
		}).start();
	}
	
	public void fetchVideo(final OnVideoCategoryListener onVideoCategoryListener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<VideoCategory> videoCategories = null;
				videoCategories = VideoManager.getInstance().fetchVideoList();
				if (onVideoCategoryListener != null)
					onVideoCategoryListener.onComplete(videoCategories);
			}
		}).start();
	}

}
