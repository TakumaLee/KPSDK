KPSDK
=====

The SDK use the ["柯文哲 野生官網 Unlimited" api](http://unlimited.kptaipei.tw/)

# API 選單

1. [文章類別](http://unlimited.kptaipei.tw/docs/#api-ch1)
2. [類別內容](http://unlimited.kptaipei.tw/docs/#api-ch2)
3. [相簿清單](http://unlimited.kptaipei.tw/docs/#api-ch3)
4. [相簿照片](http://unlimited.kptaipei.tw/docs/#api-ch4)
5. [影片播放清單](http://unlimited.kptaipei.tw/docs/#api-ch5)
6. [清單內影片](http://unlimited.kptaipei.tw/docs/#api-ch6)
7. [台北調音樂清單](http://unlimited.kptaipei.tw/docs/#api-ch7)
8. [台北調播放清單內歌曲](http://unlimited.kptaipei.tw/docs/#api-ch8)
9. [競選經費查詢](http://unlimited.kptaipei.tw/docs/#api-ch9)

# Entity

1. <a name="fenced-code-block">Album
2. <a name="fenced-code-block">Article
3. <a name="fenced-code-block">ArticleCategory
4. <a name="fenced-code-block">Photo
5. <a name="fenced-code-block">VideoCategory
6. <a name="fenced-code-block">Video


# Listener

1. <a name="fenced-code-block">OnAlbumListener
2. <a name="fenced-code-block">OnArticleCategoryListener
3. <a name="fenced-code-block">OnArticleListener
4. <a name="fenced-code-block">OnPhotoListener
5. <a name="fenced-code-block">OnVideoCategoryListener
6. <a name="fenced-code-block">OnVideoListener</a>

# How to Use

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

# 資料授權條款

## 說明文章授權條款

- 除 <a name="fenced-code-block">/musics</a> 資料以外 採用 [Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/) 條款，即為：
 1. 要有來源標示（柯文哲野生官網）
 2. 可以自由利用（無論營利與否），具體細節請參考上述條款。
- /musics 音樂內容授權：
 1. 不可重製
 2. 僅可在非商業用途前提下播放音樂
 3. 要有來源(柯文哲野生官網)、平台(soundcloud)與音樂作者標示