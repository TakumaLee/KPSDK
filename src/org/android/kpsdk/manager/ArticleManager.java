package org.android.kpsdk.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.taipei.kp.pojo.Article;
import org.taipei.kp.pojo.ArticleCategory;
import org.taipei.kp.utils.KPApiFormateUrl;

import takuma.tutorial.http.HttpGetOOMAsyncTask;
import android.content.Context;

public class ArticleManager {
	
	Context context;
	private static ArticleManager instance;
	
	public List<ArticleCategory> articleCategories;
	public List<List<Article>> articleList;
	
	private ArticleManager(Context context) {
		this.context = context;
		articleCategories = new ArrayList<ArticleCategory>();
		articleList = new ArrayList<List<Article>>();
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
	
	public void downloadArticleCategory() {
		if (!articleCategories.isEmpty())
			articleCategories = new ArrayList<ArticleCategory>();
		if (!articleList.isEmpty())
			articleList = new ArrayList<List<Article>>();
		try {
			JSONObject object = new JSONObject(new HttpGetOOMAsyncTask(context).execute(KPApiFormateUrl.getArticleCategory()).get());
			JSONArray array = object.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				JSONObject arrayObject = array.getJSONObject(i);
				ArticleCategory articleCategory = new ArticleCategory();
				articleCategory.setId(arrayObject.getInt("id"));
				articleCategory.setName(arrayObject.getString("name"));
				articleCategories.add(articleCategory);
				articleList.add(downloadArticleDetails(articleCategory.getId()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List<Article> downloadArticleDetails(int id) {
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
			// TODO: handle exception
		}
		return articles;
	}

}
