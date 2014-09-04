package org.android.kpsdk;

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
	
	public void fetchArticles(int categoryId, OnArticleListener onArticleListener) {
		List<Article> articleLists = null;
		if (onArticleListener != null)
			onArticleListener.onComplete(articleLists);
	}
	
	public void fetchArticleCategory(OnArticleCategoryListener onArticleCategoryListener) {
		List<ArticleCategory> articleCategories = null;
		if (onArticleCategoryListener != null)
			onArticleCategoryListener.onComplete(articleCategories);
	}

}
