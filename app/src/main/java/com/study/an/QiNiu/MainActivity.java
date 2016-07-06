package com.study.an.QiNiu;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

/**
 * Created by admin on 2016/7/6.
 */
public class MainActivity extends AppCompatActivity {
    UploadManager uploadManager;
    String data;
    String key;
    String token;
    UpCompletionHandler handler;
    UpProgressHandler progressHandler;

    /**
     * 初始化配置
     */
    private void setQiNiuConfiguration() {
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                .connectTimeout(10) // 链接超时。默认 10秒
                .responseTimeout(60) // 服务器响应超时。默认 60秒
//                .recorder(recorder)  // recorder 分片上传时，已上传片记录器。默认 null
//                .recorder(recorder, keyGen)  // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(Zone.zone0) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。默认 Zone.zone0
                .build();
// 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
        uploadManager = new UploadManager(config);

    }

    private void simpleUpload() {
        // 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
        UploadManager uploadManager = new UploadManager();
        data = "<File对象、或 文件路径、或 字节数组>";
        key = "<指定七牛服务上的文件名,或 null>";
        token = "<从服务端SDK获取>";
        /**
         * UpCompletionHandler
         *@key 即uploadManager.put(file, key, ...)方法指定的key。
         *@info http请求的状态信息等，可记入日志。isOK()返回 true表示上传成功。
         *@response 七牛反馈的信息。可从中解析保存在七牛服务的key等信息，具体字段取决于上传策略的设置。
         */
        handler = new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                //  res 包含hash、key等信息，具体字段取决于上传策略的设置。
                Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + response);
            }
        };
        /**
         * UploadManager.put参数说明：
         *
         *@data	                  byte[]/String/File  	数据，可以是byte数组，文件路径，文件。
         *@key	                  String	            保存在服务器上的资源唯一标识。请参考键值对。
         *@token	                  String	            服务器分配的token。
         *@completionHandler	      UpCompletionHandler	上传回调函数，必填。
         *@options	              UploadOptions	        如果需要进度通知、crc校验、中途取消、指定mimeType则需要填写相应字段，详见UploadOptions参数说明。
         */
        uploadManager.put(data, key, token, handler, null);

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
}
