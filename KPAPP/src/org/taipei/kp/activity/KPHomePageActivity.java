package org.taipei.kp.activity;

import org.taipei.kp.AdsActivity;
import org.taipei.kp.R;
import org.taipei.kp.config.KPAllConfig;
import org.taipei.kp.config.UIConfig;
import org.taipei.kp.pojo.HomeInfo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
//import android.widget.TabHost;

public class KPHomePageActivity extends AdsActivity {
	private static String TAG = KPHomePageActivity.class.getSimpleName();
	
	/**
	 * Use Fragment and drawer
	 * */
	private FrameLayout frameLayout;
	private HomeInfo infoItem;
	int displayPosition = 0;
	
	
//	private AdView adView;
//	private LinearLayout linearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseActivity(R.layout.activity_home_page, true);
		initUIView();
//		if (CLAccountManager.getInstance().isNeedUpdate)
//			new CheckVersionAsyncTask(this, CLAppConfig.getAppVersion()).execute(CLAppConfig.GOOGLEPLAY_BROWSER);
//		AppVersionManager.isNeedUpdate(this);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_Home, infoItem.fragment).commit();
			setHighlight(infoItem.position);
			drawerClickListener.oldFragment = infoItem.fragment;			
		}		
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		//CHOCOLABS AD
//		if (hasFocus)
//			new basic().initChoco(AppConfig.Hash_KEY, this, "2");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		System.gc();
		super.onDestroy();
	}
	
	private void initUIView() {
		
		frameLayout = (FrameLayout) findViewById(R.id.frameLayout_Home);
		drawerClickListener.rId = R.id.frameLayout_Home;
		drawerClickListener.mActivity = this;
		
		if (getIntent().getExtras() != null) {
			if (displayPosition == 0)	//displayPosition != 0 >>> is debug mode
				displayPosition = getIntent().getExtras().getInt(KPAllConfig.BUNDLE_HOME);
			infoItem = new HomeInfo(UIConfig.getPageFragment(displayPosition), displayPosition);
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		drawerClickListener.oldFragment.onActivityResult(requestCode, resultCode, data);
	    super.onActivityResult(requestCode, resultCode, data);
	} 
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		drawerClickListener.menu = menu;
//		try {
//			if (menu != null) {
//				menu.removeItem(R.id.action_dramaSort);
//				menu.removeItem(R.id.action_search);
//				menu.removeItem(R.id.action_edit);
//			}
//			if (drawerClickListener.currentPosition == 2 && menu != null) {
//				getMenuInflater().inflate(R.menu.home_page, menu);
////	    		menu.removeItem(R.id.action_search);
//				menu.removeItem(R.id.action_edit);
//			} else if (drawerClickListener.currentPosition == 3 && menu != null) {
//				getMenuInflater().inflate(R.menu.home_page, menu);
//				menu.removeItem(R.id.action_dramaSort);
//			} else {
//				getMenuInflater().inflate(R.menu.home_page, menu);
//	    		menu.removeItem(R.id.action_dramaSort);
//	    		menu.removeItem(R.id.action_edit);
//			}
//		} catch (Exception e) {
//		}
//		
//		if (drawerClickListener.currentPosition == 50)
//			drawerClickListener.secondFragment.onCreateOptionsMenu(menu, getMenuInflater());
//		return true;
//	}
//	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
	        return true;
		}
		if (drawerClickListener != null)
			if (drawerClickListener.oldFragment != null)
				drawerClickListener.oldFragment.onOptionsItemSelected(item);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
				drawerClickListener.currentPosition = infoItem.position;
				drawerClickListener.secondFragment = null;
//				drawerClickListener.oldFragment = infoItem.fragment;
				onCreateOptionsMenu(drawerClickListener.menu);
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
