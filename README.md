KPSDK
=====

The SDK use the ["柯文哲 野生官網 Unlimited" api](http://unlimited.kptaipei.tw/)

#Entity

1. Album
2. Article
3. ArticleCategory
4. Photo
5. VideoCategory
6. Video


#Listener

1. OnAlbumListener
2. OnArticleCategoryListener
3. OnArticleListener
4. OnPhotoListener
5. OnVideoCategoryListener
6. OnVideoListener

#How to Use

In your Application, you need to initial instance by using KPAndroid

```Java
KPAndroid.initSingleton(getApplicationContext(), <YOUR_KP_API_KEY>);
```

and next, you can call function to get any object kp entity from api
like album, article, article category...etc

EX

```java
KPAndroid.getInstance().fetchArticleCategory(new OnArticleCategoryListener() {
	@Override
	public void onComplete(List<ArticleCategory> object) {
		//do your things
	}
});		
```

#資料授權條款

##說明文章授權條款

- 除 <a name="fenced-code-block">/musics</a> 資料以外 採用 [Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/) 條款，即為：
	1. 要有來源標示（柯文哲野生官網）
	2. 可以自由利用（無論營利與否），具體細節請參考上述條款。
- /musics 音樂內容授權：
不可重製
	1. 僅可在非商業用途前提下播放音樂
	2. 要有來源(柯文哲野生官網)、平台(soundcloud)與音樂作者標示