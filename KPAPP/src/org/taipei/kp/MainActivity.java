package org.taipei.kp;

import org.taipei.kp.activity.KPHomePageActivity;
import org.taipei.kp.config.KPAllConfig;
import org.taipei.kp.manager.ArticleManager;

import takuma.tutorial.activity.TakumaBaseActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends TakumaBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		Crashlytics.start(this);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		Handler mHandler = new Handler();

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			ArticleManager.getInstance().downloadArticleCategory();
			wait3SecondsToNext.start();
			return rootView;
		}
		
		Thread wait3SecondsToNext = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					mHandler.post(next);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		final Runnable next = new Runnable() {
			
			@Override
			public void run() {
				toNext();
			}
		};
		
		private void toNext() {
			Intent intent = new Intent(getActivity(), KPHomePageActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(KPAllConfig.BUNDLE_HOME, 0);
			startActivity(intent);
			getActivity().finish();
		}
	}
}
