package org.taipei.kp;

import org.taipei.kp.config.KPAppConfig;

import com.google.ads.mediation.customevent.CustomEventBannerListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class AdsActivity extends DrawerActivity implements CustomEventBannerListener, BannerAdListener {
	private static String TAG = AdsActivity.class.getSimpleName();
	
//	private AdView adView;
//	private AdRequest adRequest;
	private MoPubView moPubView;
	private Fragment fragment;
	LinearLayout	linearLayout;
	FrameLayout		frameLayout;
//	private  Tracker mTrackers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		GoogleAnalytics.getInstance(this).newTracker(R.string.ga_trackingId);
//		sendGoogleAnalyticsScreen(this);
		setDrawerActivity(R.layout.activity_ads);
		frameLayout = (FrameLayout) findViewById(R.id.frameLayout_Chocolabs);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout_Chocolabs);
	}
	
	private void initAdView() {
		
		//admob add
		try {
			
//			InMobiAdapterExtras inMobiExtras = new InMobiAdapterExtras();
//		    inMobiExtras.setIncome(65000);
		    
		    moPubView = (MoPubView) findViewById(R.id.adview);
		    moPubView.setAdUnitId(KPAppConfig.MOPUB_BANNER);
		    moPubView.setBannerAdListener(this);
		    moPubView.loadAd();
			
//			adView = new AdView(this);
//			adView.setAdSize(AdSize.BANNER);
//			adView.setAdUnitId(CLAppConfig.AdMob_KEY);
			
//			linearLayout.addView(moPubView);
			
//			adRequest = new AdRequest.Builder()
//			.addNetworkExtras(inMobiExtras)
//			.build();
//			adView.loadAd(adRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	public AdView getAdView() {
//		return adView;
//	}
	
//	public void refreshAdview(String admobId) {
//		try {
//			adView.destroy();
//			linearLayout.removeAllViews();
//			adView = null;
//			adView = new AdView(this);
//			adView.setAdSize(AdSize.BANNER);
//			adView.setAdUnitId(admobId);
//			linearLayout.addView(adView);
//			adView.loadAd(adRequest);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	public void setBaseActivity(int layoutResID, boolean isNeedInitAd) {
		View view = LayoutInflater.from(this).inflate(layoutResID, null);
		frameLayout.addView(view);
		if (isNeedInitAd)
			initAdView();
		else
			linearLayout.setVisibility(View.GONE);
	}
	
	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}
	
	public LinearLayout getAdsLinearLayout() {
		return linearLayout;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (moPubView != null)
			moPubView.destroy();
	}

	@Override
	public void onDismissScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailedToReceiveAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeaveApplication() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPresentScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceivedAd(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBannerClicked(MoPubView arg0) {
//		Toast.makeText(getApplicationContext(), "onBannerClicked.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBannerCollapsed(MoPubView arg0) {
//		Toast.makeText(getApplicationContext(), "onBannerCollapsed.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBannerExpanded(MoPubView arg0) {
//		Toast.makeText(getApplicationContext(), "onBannerExpanded.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBannerFailed(MoPubView arg0, MoPubErrorCode arg1) {
//		Toast.makeText(getApplicationContext(), "onBannerFailed.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBannerLoaded(MoPubView arg0) {
//		Toast.makeText(getApplicationContext(), "Banner successfully loaded.", Toast.LENGTH_SHORT).show();
	}

}
