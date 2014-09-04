package org.android.kpsdk.listeners;

import java.util.List;

public abstract class OnActionListener<T> implements OnLoadingListener {
	
	public void onComplete(T object) {
		
	}
	
	public void onAllComplete(List<T> object) {
		
	}
	
	@Override
	public void onLoading() {
	}
	@Override
	public void onFail(String reason) {
	}
	@Override
	public void onException(Throwable throwable) {
	}

}
