package org.taipei.kp.adapter;

import java.util.List;

import org.taipei.kp.activity.WebViewActivity;
import org.taipei.kp.manager.ArticleManager;
import org.taipei.kp.pojo.ArticleCategory;

import com.markupartist.android.widget.PullToRefreshListView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;

public class ArticlePagerAdapter extends PagerAdapter {
	
	Context context;
	List<ArticleCategory> articleCategories;
	private ListView listView;
	
	public ArticlePagerAdapter(Context context, List<ArticleCategory> articleCategories) {
		this.context = context;
		this.articleCategories = articleCategories;
	}

	@Override
	public int getCount() {
		return articleCategories.size();
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		listView = new ListView(context);
		final ArticleListAdapter adapter = new ArticleListAdapter(context);
		adapter.articles = ArticleManager.getInstance().articleList.get(position);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, WebViewActivity.class);
				intent.putExtra(WebViewActivity.EXTRA_VIEW_URL, adapter.articles.get(position).getUrl());
				intent.putExtra(WebViewActivity.EXTRA_TITLE, adapter.articles.get(position).getTitle());
				context.startActivity(intent);
				
			}
			
		});
		((ViewPager) container).addView(listView, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		return listView;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return articleCategories.get(position).getName();
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

}
