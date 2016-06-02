package com.study.an.Volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.study.an.Utils.WebSetUtils;
import com.study.an.all.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/5/31.
 */
public class SimpleHttpRequestActivity extends AppCompatActivity {
//    WebView mWebView;
    RequestQueue mQueue;
    ImageLoader imageLoader;
    NetworkImageView mNetworkImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_request);
//        mWebView=(WebView)findViewById(R.id.webView);
//        WebSetUtils.getWebSetting(this,mWebView);
        mQueue= Volley.newRequestQueue(this);
        mQueue.add(mRequest);
        ImageView imageView=(ImageView) findViewById(R.id.imageView);
        imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.drawable.photo, R.drawable.photo);
        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
                listener, 200, 200);
        mNetworkImageView=(NetworkImageView)findViewById(R.id.netWorkImageView);
        mNetworkImageView.setDefaultImageResId(R.drawable.photo);
        mNetworkImageView.setErrorImageResId(R.drawable.photo);
        mNetworkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",imageLoader);
    }

    StringRequest mRequest = new StringRequest(Request.Method.POST,"http://www.baidu.com",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Log.d("TAG", s);
//                    mWebView.loadDataWithBaseURL(null,s,"text/html", "utf-8",null);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();
            map.put("params1", "value1");
            map.put("params2", "value2");
            return map;
        }
    };

    public class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }
}
