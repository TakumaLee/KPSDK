package org.taipei.kp.fragment;

import org.taipei.kp.R;
import org.taipei.kp.adapter.ArticlePagerAdapter;
import org.taipei.kp.manager.ArticleManager;

import com.astuetz.PagerSlidingTabStrip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ArticleListFragment extends BaseFragment {
	
	View rootView;
	
	LinearLayout linearLayout;
	private PagerSlidingTabStrip	tabs;
	private ViewPager				pager;
	private ArticlePagerAdapter		adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_article_list_page, null);
		initView();
		return rootView;
	}
	
	private void initView() {
		linearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout_ArticleList);
		tabs = new PagerSlidingTabStrip(getActivity());
		pager = new ViewPager(getActivity());
		adapter = new ArticlePagerAdapter(getActivity(), ArticleManager.getInstance().articleCategories);
		LinearLayout.LayoutParams pagerParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, Math.round(48 * getActivity().getApplicationContext().getResources().getDisplayMetrics().density));
		pagerParams.setMargins(0, 3, 0, 0);
		pager.setLayoutParams(pagerParams);
		pager.setAdapter(adapter);

		pager.setCurrentItem(0);
		tabs.setViewPager(pager);
		tabs.setTextColorResource(R.color.actionBarTabTextUnfocus);
		tabs.setUnderlineColorResource(R.color.actionBarTabText);
		tabs.setIndicatorColorResource(R.color.actionBarTabText);
		tabs.setLayoutParams(tabParams);
		linearLayout.addView(tabs);
		linearLayout.addView(pager);
		System.gc();
	}

}
