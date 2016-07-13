package com.study.an.QiNiu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.study.an.all.R;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2016/7/6.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    UploadManager uploadManager;
    String data;
    String key;
    String token;
    UpCompletionHandler handler;
    UpProgressHandler progressHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiniu_main);
        key = "7DL55B.txt";
        setQiNiuConfiguration();
        findViews();
    }

    private void findViews() {
        findViewById(R.id.simple_upload).setOnClickListener(this);
        findViewById(R.id.btn_downLand).setOnClickListener(this);
    }

    /**
     * 初始化配置
     */
    private void setQiNiuConfiguration() {
//        Configuration config = new Configuration.Builder()
//                .chunkSize(512 * 1024)  //分片上传时，每片的大小。 默认 256K
//                .putThreshhold(1024 * 1024)  // 启用分片上传阀值。默认 512K
//                .connectTimeout(10) // 链接超时。默认 10秒
//                .responseTimeout(60) // 服务器响应超时。默认 60秒
////                .recorder(recorder)  // recorder 分片上传时，已上传片记录器。默认 null
////                .recorder(recorder, keyGen)  // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
//                .zone(Zone.zone0) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。默认 Zone.zone0
//                .build();
        //重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
        uploadManager = new UploadManager();


    }

    private void simpleUpload() {
        ImagePutPolicy putPolicy = new ImagePutPolicy(key);
        putPolicy.setDeadline(3600);
        token = UploadToken.getUploadToken(putPolicy);
        byte[] data = new byte[]{0, 1, 2, 3};

        uploadManager.put(data, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                //  res 包含hash、key等信息，具体字段取决于上传策略的设置。
                Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + response);
            }
        }, new UploadOptions(null, "test-type", true, null, null));

    }

    //记录上传进度
    private void uploadProgress() {

        uploadManager.put(data, key, token, handler,
                new UploadOptions(null, null, false,
                        new UpProgressHandler() {
                            /**
                             * @param key 即uploadManager.put(file, key, ...)方法指定的key
                             * @param percent 进度
                             */
                            public void progress(String key, double percent) {
                                Log.i("qiniu", key + ": " + percent);
                            }
                        }, null));

    }


    private volatile boolean isCancelled = false;

    //取消上传
    private void cancelUpload() {

        // 初始化、执行上传
        uploadManager.put(data, key, token, handler,
                /**
                 * UploadOptions参数说明：
                 *参数	               类型	                说明
                 *params	           Map<String, String>	自定义变量，key必须以 x: 开始。
                 *mimeType	           String	            指定文件的mimeType。
                 *checkCrc	           boolean	            是否验证上传文件。
                 *progressHandler	   UpProgressHandler	上传进度回调。
                 *cancellationSignal   UpCancellationSignal	取消上传，当isCancelled()返回true时，不再执行更多上传。
                 */
                new UploadOptions(null, null, false, progressHandler,
                        new UpCancellationSignal() {
                            public boolean isCancelled() {
                                return isCancelled;
                            }
                        }));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simple_upload:
                simpleUpload();
                break;
            case R.id.btn_downLand:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            getBack();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            default:
                break;
        }
    }
    private String getUrl(String fileName){
        return UploadToken.getDownLoadToken(fileName,3600);
    }

    private void getBack()throws IOException{
        //创建OkHttpClient对象，用于稍后发起请求
        OkHttpClient client = new OkHttpClient();
        //根据请求URL创建一个Request对象
        Request request = new Request.Builder().url(getUrl("7DL55B.txt")).build();

        //根据Request对象发起Get同步Http请求
//        Response response = client.newCall(request).execute();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes=response.body().bytes();
                Log.d(TAG,new String(bytes));
            }
        });
    }

}

