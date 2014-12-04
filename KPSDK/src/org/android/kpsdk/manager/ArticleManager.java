package org.android.kpsdk.manager;

import java.util.ArrayList;
import java.util.List;

import org.android.kpsdk.entity.Article;
import org.android.kpsdk.entity.ArticleCategory;
import org.android.kpsdk.http.HttpGetOOMAsyncTask;
import org.android.kpsdk.utils.KPApiFormateUrl;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class ArticleManager {
	
	Context context;
	private static ArticleManager instance;
	
	private ArticleManager(Context context) {
		this.context = context;
	}
	
	public static synchronized ArticleManager initSingleton(Context context) {
		if (instance == null && context != null) {
			Context appContext = context.getApplicationContext();
			instance = new ArticleManager(appContext);
		}
		return instance;
	}
	
	public static synchronized ArticleManager getInstance() {
		return instance;
	}
	
	public List<ArticleCategory> fetchArticleCategory() {
		List<ArticleCategory> articleCategories = new ArrayList<ArticleCategory>();
		try {
			JSONObject object = new JSONObject(new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getArticleCategory()).get());
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject arrayObject = array.getJSONObject(i);
				ArticleCategory articleCategory = new ArticleCategory();
				articleCategory.setId(arrayObject.getInt("id"));
				articleCategory.setName(arrayObject.getString("name"));
				articleCategories.add(articleCategory);
			}
		} catch (Exception e) {
		}
		return articleCategories;
	}
	
	public List<Article> fetchArticleDetails(int id) {
		List<Article> articles = new ArrayList<Article>();
		try {
			JSONObject object = new JSONObject(new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getArticleDetails(String.valueOf(id))).get());
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject arrayObject = array.getJSONObject(i);
				Article article = new Article();
				article.setId(arrayObject.getInt("id"));
				article.setTitle(arrayObject.getString("title"));
				article.setUrl(arrayObject.getString("url"));
				articles.add(article);
			}
		} catch (Exception e) {
		}
		return articles;
	}

}
