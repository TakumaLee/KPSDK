package org.taipei.kp.uicontroller;

import org.taipei.kp.R;
import org.taipei.kp.activity.KPHomePageActivity;
import org.taipei.kp.config.KPAllConfig;
import org.taipei.kp.config.UIConfig;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class KPDrawerItemClickListener implements ListView.OnItemClickListener {
	
	private Context context;
	public CharSequence mTitle;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	public int currentPosition;
	public int rId;
	public Menu menu;
	public MenuInflater menuInflater;
	public FragmentActivity mActivity;
	public Fragment oldFragment;
	public Fragment secondFragment;
	public Fragment newFragment;
	FragmentManager fm;
//	DelegateManager.DrawerFacebookControlDelegate delegate;
	
	public KPDrawerItemClickListener(Context context, DrawerLayout mDrawerLayout, ListView mDrawerList, FragmentManager fm) {
		this.context = context;
		this.mDrawerLayout = mDrawerLayout;
		this.mDrawerList = mDrawerList;
		this.fm = fm;
//		this.delegate = delegate;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (currentPosition == 100 && position != 0) {
			Intent intent = new Intent(context, KPHomePageActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(KPAllConfig.BUNDLE_HOME, position);
			context.startActivity(intent);
		} else {
			if (currentPosition == 50) {
				fm.popBackStack();
				if (secondFragment != null) {
					fm.beginTransaction().remove(secondFragment).commitAllowingStateLoss();
					secondFragment = null;
				}
			}
			if (position != 0)
				selectItem(position);
			if (position == 0) {
//				if (delegate != null)
//					delegate.doFacebookAction();
				mDrawerList.setItemChecked(currentPosition, true);
			}
		}
		System.gc();
	}
	
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    if (((Activity) context).getActionBar() != null)
	    	((Activity) context).getActionBar().setTitle(mTitle);
	}
	
	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		FragmentTransaction ft = fm.beginTransaction();
		newFragment = UIConfig.getPageFragment(position);
		if (newFragment != null && oldFragment != newFragment && position != currentPosition) {
			if (oldFragment != null)
				ft.remove(oldFragment);
			if (newFragment != null && position != 0) {
				newFragment = Fragment.instantiate(context, newFragment.getClass().getName());
				if (newFragment == null)
					ft.remove(oldFragment);
				else {
					fm.popBackStack();
					if (oldFragment != null)
						ft.remove(oldFragment);
				}
				ft.add(rId, newFragment);
			}
//			ft.replace(rId, newFragment);
		    // Highlight the selected item, update the title, and close the drawer
		    mDrawerList.setItemChecked(position, true);
		    if (position != 0)
		    	setTitle(context.getResources().getStringArray(R.array.tabs_name)[position - 1]);
		    else
		    	setTitle(context.getResources().getStringArray(R.array.tabs_name)[1]);
		    currentPosition = position;
//		    newFragment.onCreateOptionsMenu(menu, menuInflater);
		    if (mActivity != null)
		    	mActivity.onCreateOptionsMenu(menu);
		}
		ft.commitAllowingStateLoss();
		oldFragment = newFragment;
		newFragment = null;
//		mDrawerLayout.closeDrawer(mDrawerList);
	}
	
}
