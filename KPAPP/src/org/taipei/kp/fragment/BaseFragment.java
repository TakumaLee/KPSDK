package org.taipei.kp.fragment;

import org.taipei.kp.DrawerActivity;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	
	@Override
	public void onResume() {
		((DrawerActivity) getActivity()).mDrawerLayout.closeDrawer(((DrawerActivity) getActivity()).mLeftDrawerLayout);
		super.onResume();
	}

}
