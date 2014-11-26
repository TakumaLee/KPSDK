package org.taipei.kp.activity;

import org.taipei.kp.AdsActivity;
import org.taipei.kp.R;
import org.taipei.kp.widget.WebLoadingView;

import com.androidquery.util.Progress;

import takuma.tutorial.activity.TakumaBaseActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class WebViewActivity extends AdsActivity {
	
	Intent intent;
	String url;
	String title
;	WebView webView;
	WebSettings websetting;
	FrameLayout frameLayout;
	ProgressBar progressBar;
	public static String EXTRA_VIEW_URL = "URL";
	public static String EXTRA_TITLE = "TITLE";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseActivity(R.layout.activity_home_page, true);
		initView();
		intent = getIntent();
		url = intent.getExtras().getString(EXTRA_VIEW_URL, url);
		title = intent.getExtras().getString(EXTRA_TITLE);
		websetting = webView.getSettings();
//		websetting.setSupportZoom(true);
		websetting.setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebLoadingView(progressBar));
		webView.loadUrl(url);
		setTitle(title);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	private void initView(){
		frameLayout = (FrameLayout) findViewById(R.id.frameLayout_Home);
		webView = new WebView(this);
		progressBar = new ProgressBar(this);
		frameLayout.addView(webView);
		frameLayout.addView(progressBar);
	}

}
