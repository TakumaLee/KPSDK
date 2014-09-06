KPSDK
=====

The SDK use the "柯文哲 野生官網 Unlimited" api （http://unlimited.kptaipei.tw/）

#Entity

1. Album
2. Article
3. ArticleCategory
4. Photo


#Listener

1. OnAlbumListener
2. OnArticleCategoryListener
3. OnArticleListener
4. OnPhotoListener

#How to Use

In your Application, you need to initial instance by using KPAndroid

KPAndroid.initSingleton(getApplicationContext);

and next, you can call function to get any object kp entity from api
like album, article, article category...etc

EX

		KPAndroid.getInstance().fetchArticleCategory(new OnArticleCategoryListener() {
				@Override
				public void onComplete(List<ArticleCategory> object) {
					//do your things
				}
			});		