package org.taipei.kp.adapter;

import java.util.Collections;
import java.util.List;

import org.taipei.kp.R;
import org.taipei.kp.pojo.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ArticleListAdapter extends BaseAdapter {
	
	Context context;
	LayoutInflater inflater;
	public List<Article> articles;
	
	public ArticleListAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		articles = Collections.emptyList();
	}

	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		return articles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ViewHolder {
		TextView title;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.adapter_article_list_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.textView_Article_ListItem);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(articles.get(position).getTitle());
		convertView.setTag(holder);
		return convertView;
	}

}
