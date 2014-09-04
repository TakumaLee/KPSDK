package org.android.kpsdk;

import java.util.ArrayList;
import java.util.List;

import org.android.kpsdk.listeners.OnArticleCategoryListener;
import org.android.kpsdk.listeners.OnArticleListener;
import org.android.kpsdk.manager.ArticleManager;
import org.android.kpsdk.pojo.Article;
import org.android.kpsdk.pojo.ArticleCategory;

import android.content.Context;

public class KPManager {
	
	Context	context;
	private	static KPManager instance = null;
	public	String API_KEY;
	
	public static synchronized KPManager initSingleton(Context context, String appKey) {
		ArticleManager.initSingleton(context);
		if (instance == null && context != null) {
			Context appContext = context.getApplicationContext();
			instance = new KPManager(appContext, appKey);
		}
		return instance;
	}
	
	public static KPManager getInstance() {
		return instance;
	}
	
	private KPManager(Context context, String appKey) {
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

}
