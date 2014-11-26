package org.taipei.kp;

import org.taipei.kp.adapter.DrawerLeftAdapter;
import org.taipei.kp.config.KPAppConfig;
import org.taipei.kp.uicontroller.KPDrawerItemClickListener;

import takuma.tutorial.activity.TakumaBaseActivity;

import com.androidquery.AQuery;
import com.mikhaellopez.circularimageview.CircularImageView;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class DrawerActivity extends TakumaBaseActivity {
	
	FrameLayout frameLayout;
	
	/**
	 * android drawer list
	 * */
	public DrawerLayout				mDrawerLayout;
    public ListView					mDrawerList;
    public RelativeLayout			mLeftDrawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected ActionBar actionBar;
    protected KPDrawerItemClickListener drawerClickListener;
    private DrawerLeftAdapter adapter;
    
    View view;
    AQuery aQuery;
    RelativeLayout mask;
    ImageView cover;
    CircularImageView head;
    TextView name;
    TextView version;
    
    Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_drawer);
		frameLayout = (FrameLayout) findViewById(R.id.content_frame);
		initActionBar();
		initDrawer(R.drawable.ic_drawer);
		initFacebookView();
        initDrawerList();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		KPApplication.setCurrentActivity(this);
//		refreshHeaderView();
//		refreshDrawerList();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		KPApplication.setCurrentActivity(null);
	}
	
	protected void initActionBar(){
		if (getActionBar() == null)
			return;
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }
	
	protected void initDrawer(int toggleImgRId){
		version = (TextView) findViewById(R.id.textView_DrawerVersion);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);        
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toggleImgRId, R.string.drawer_open, R.string.drawer_close) {
        	@Override
            public void onDrawerOpened(View drawerView) {
        		super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            	super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        version.setText("Version : " + KPAppConfig.getAppVersion());
    }
	
	private void initFacebookView() {
		view = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
		aQuery = new AQuery(this);
		mask = (RelativeLayout) view.findViewById(R.id.relativeLayout_Drawer_Mask);
		cover = (ImageView) view.findViewById(R.id.imageView_Drawer_FacebookCover);
		head = (CircularImageView) view.findViewById(R.id.imageView_Drawer_FacebookHeadShot);
		name = (TextView) view.findViewById(R.id.textView_Drawer_FBName);
//		refreshHeaderView();
	}
	
	private void initDrawerList() {
		mLeftDrawerLayout = (RelativeLayout) findViewById(R.id.left_drawer);
		mDrawerList = (ListView) findViewById(R.id.listView_Drawer);
		drawerClickListener = new KPDrawerItemClickListener(this, mDrawerLayout, mDrawerList, getSupportFragmentManager());
		adapter = new DrawerLeftAdapter(this);
		mDrawerList.setSelector(R.drawable.drawer_background);
		mDrawerList.addHeaderView(view);
        mDrawerList.setOnItemClickListener(drawerClickListener);
        mDrawerList.setAdapter(adapter);
    }
	
	protected void setDrawerActivity(int layoutResID) {
		View view = LayoutInflater.from(this).inflate(layoutResID, null);
		frameLayout.addView(view);
	}
	
	protected void setHighlight(int position) {
		drawerClickListener.currentPosition = position;
		mDrawerList.setItemChecked(position, true);
	}
	
//	public void refreshHeaderView() {
//		if (SimpleFacebook.getInstance(this).isLogin()) {
////			ImageLoader.getInstance().displayImage(CLAccountManager.getInstance().myAccount.getHeadshotUrl(), photo, CLApplication.getUIL2TimesOption());
//			aQuery.id(head).image(CLAccountManager.getInstance().myAccount.getHeadshotUrl(), true, true);
//			aQuery.id(cover).image(CLAccountManager.getInstance().myAccount.getCoverUrl(), true, true);
//			name.setText(CLAccountManager.getInstance().myAccount.getName());
//			cover.setVisibility(View.VISIBLE);
//			if (CLAccountManager.getInstance().myAccount.getCoverUrl() != null)
//				mask.setVisibility(View.VISIBLE);
//		}
//		else {
//			head.setImageResource(R.drawable.fb_circle);
//			head.setBackgroundResource(0);
//			name.setText("");
//			cover.setImageDrawable(null);
//			cover.setVisibility(View.GONE);
//			mask.setVisibility(View.GONE);
//		}
//	}
//	
//	public void refreshDrawerList() {
//		if (adapter != null)
//			adapter.notifyDataSetChanged();
//	}
//	
//	final Runnable mRefresh = new Runnable() {
//		
//		@Override
//		public void run() {
//			refreshHeaderView();
//		}
//	};
//	
//	DelegateManager.DrawerFacebookActionDelegate delegate = new DelegateManager.DrawerFacebookActionDelegate() {
//		
//		public void complete() {
//			mHandler.post(mRefresh);
////			TrackerEvent eventPojo = GoogleAnalyticsTrackerManager.getInstance().getAUTrackerEvent(TrackerEvent.AU_FB_LOGIN_TRACKER);
////			GoogleAnalyticsTrackerManager.getInstance().sendGAMessage(eventPojo);
//		};
//	};
//	
//	DelegateManager.DrawerFacebookControlDelegate controlDelegate = new DelegateManager.DrawerFacebookControlDelegate() {
//		
//		public void doFacebookAction() {
//			if (!SimpleFacebook.getInstance(DrawerActivity.this).isLogin())
//				SimpleFacebook.getInstance(DrawerActivity.this).login(FacebookManager.loginListener);
////			else
////				SimpleFacebook.getInstance(DrawerActivity.this).logout(FacebookManager.logoutListener);
////			mHandler.post(mRefresh);
//		}
//	};
//	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if (data != null)
//			SimpleFacebook.getInstance(this).onActivityResult(this, requestCode, resultCode, data);
//		super.onActivityResult(requestCode, resultCode, data);
//	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	return super.onCreateOptionsMenu(menu);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
