package org.taipei.kp.config;

import org.taipei.kp.R;
import org.taipei.kp.fragment.AlbumListFragment;
import org.taipei.kp.fragment.ArticleListFragment;
import org.taipei.kp.fragment.VideoListFragment;

import android.support.v4.app.Fragment;

public class UIConfig {
	
	
	public static Integer[] drawer_iconId = {
		R.drawable.ic_launcher,
		R.drawable.ic_launcher,
		R.drawable.ic_launcher
	};
	
	public static Fragment getPageFragment(int position) {
		Fragment fragment = null;
		switch (position) {
		case 1:
			fragment = new ArticleListFragment();
			break;
		case 2:
			fragment = new AlbumListFragment();
			break;
		case 3:
			fragment = new VideoListFragment();
			break;

		default:
			fragment = new ArticleListFragment();
			break;
		}
		return fragment;
	}

}
