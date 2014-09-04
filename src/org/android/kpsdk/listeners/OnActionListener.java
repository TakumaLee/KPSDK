package org.android.kpsdk.listeners;

public abstract class OnActionListener<T> implements OnLoadingListener {
	
	public void onComplete(T object) {
		
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
