package com.study.an.QiNiu;

import android.util.Base64;

import com.google.gson.Gson;
import com.qiniu.android.utils.UrlSafeBase64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by admin on 2016/7/12.
 */
public class UploadToken {

    public static String getUploadToken(Object object) {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(object);
        String encodedPutPolicy = UrlSafeBase64.encodeToString(jsonObject);
        try {
            String encodedSign = hmacSha1(encodedPutPolicy);
            String uploadToken = QiNiuConstant.ACCESSKEY + ":" + encodedSign + ":" + encodedPutPolicy;
            return uploadToken;
        } catch (Exception e) {
            return null;
        }
    }

    public static String hmacSha1(final String base)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String type = "HmacSHA1";
        try {
            SecretKeySpec secret = new SecretKeySpec(QiNiuConstant.SECRETKEY.getBytes(), type);
            Mac mac = Mac.getInstance(type);
            mac.init(secret);
            byte[] digest = mac.doFinal(base.getBytes());
            return Base64.encodeToString(digest, Base64.URL_SAFE).replaceAll("\n", "");

        } catch (Exception e) {
            return null;
        }
    }
}