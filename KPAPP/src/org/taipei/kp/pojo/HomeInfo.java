package org.taipei.kp.pojo;

import android.support.v4.app.Fragment;

public class HomeInfo {
	
	public int position;
    public Fragment fragment;
    
    public HomeInfo(Fragment fragment, int position) {
    	this.fragment = fragment;
    	if (position >= 1 && position <= 4)
    		this.position = position;
    	else 
    		this.position = 1;
	}

}
