package org.taipei.kp;

import org.taipei.kp.config.KPAppConfig;
import org.taipei.kp.manager.ArticleManager;

import com.androidquery.callback.BitmapAjaxCallback;

import takuma.tutorial.context.ApplicationContextSingleton;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.ActivityManager.MemoryInfo;
import android.util.Log;

public class KPApplication extends Application {
private static String TAG = KPApplication.class.getSimpleName();
	
	private AlarmManager alarmMgr;
	private PendingIntent alarmIntent;
	
//	DAOFactory daoFactory;
//	SQLite sqlite;
    
    public ApplicationContextSingleton 	appContext;
    private static Activity 			activity;
    
    public void onCreate() {
        appContext = ApplicationContextSingleton.initialize(getApplicationContext());
        KPAppConfig.initConfig(getApplicationContext(), getString(R.string.app_name));
        ApplicationContextSingleton.initialize(getApplicationContext());
        ArticleManager.initSingleton(getApplicationContext());
//        SimpleFacebook
//        SimpleFacebook.setConfiguration(facebookConfiguration());
//        daoFactory = DAOFactory.initSingleton(getApplicationContext());
    }
    
//    public Permission[] getFacebookPermission() {
//    	Permission[] permissions = new Permission[] {
//    			Permission.USER_PHOTOS,
//    			Permission.USER_BIRTHDAY,
//    			Permission.EMAIL,
//    			Permission.PUBLISH_ACTION
//    		};
//    	return permissions;
//    }
    
//    private SimpleFacebookConfiguration facebookConfiguration() {
//    	SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
//        .setAppId(getResources().getString(R.string.app_id))
//        .setNamespace(getResources().getString(R.string.app_name))
//        .setPermissions(getFacebookPermission())
//        .build();
//    	
//    	return configuration;
//    }
    
    public static void setCurrentActivity(Activity currentActivity) {
        activity = currentActivity;
    }

    public static Activity currentActivity() {
        return activity;
    }

    @Override
    public void onLowMemory() {
        Log.e(TAG, "On Low Memory!!!!");
        MemoryInfo mi = new MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        int usableMemory = activityManager.getMemoryClass();
        Log.v(TAG, String.valueOf(usableMemory));
        long availableMegs = mi.availMem / 1048576L;
        long thresholdMegs = mi.threshold / 1048576L;
        Log.e(TAG, "availableMegs:"+availableMegs+" thresholdMegs:"+thresholdMegs);
        BitmapAjaxCallback.clearCache();
        super.onLowMemory();
    }

}
