package com.zritc.colorfulfund.http;

import android.graphics.Bitmap;

import com.zritc.colorfulfund.base.ZRApplication;
import com.zritc.colorfulfund.data.model.file.UploadFile;
import com.zritc.colorfulfund.utils.CacheUtils;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRImageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 文件上传Manager
 * <p>
 * Created by Chang.Xiao on 2016/9/14.
 *
 * @version 1.0
 */
public final class FileUploadManager {

    private static FileUploadManager fileUploadManager;
    private static FileUploadApi fileUploadApi;

    private FileUploadManager() {}

    public static FileUploadManager getInstance() {
        if (null == fileUploadManager) {
            fileUploadManager = new FileUploadManager();
        }
        fileUploadApi = ZRRetrofit.getFileUploadApiInstance();

        return fileUploadManager;
    }

    /**
     * 上传图片
     *
     * @return
     */
    public Call<UploadFile> uploadImage(String path) {
        String descriptionString = "hello, this is description speaking";

        Bitmap bitmap = ZRImageUtil.getResizedImage(path, null, 200, true, 0);
        path = saveToSdCard(bitmap);

        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file); // multipart/form-data // image/*
        Map<String, RequestBody> params = new HashMap<>();
        params.put("sid", toRequestBody(ZRDeviceInfo.getSid()));
        params.put("deviceid", toRequestBody(ZRDeviceInfo.getDeviceID()));
        params.put("rid", toRequestBody(ZRDeviceInfo.getRid()));
        params.put("pathKey", toRequestBody("Upload_File_EDU"));
        params.put("file\"; filename=\"image.png\"" + file.getName(), requestBody); // uploadImages
        return fileUploadApi.uploadImage(
                "http://172.16.101.201:9006/uploadFile"
                , params);
    }

    /**
     * 上传图片
     *
     * @return
     */
    public Call<UploadFile> uploadFile(File file) {
        String path = file.getAbsolutePath();

        Bitmap bitmap = ZRImageUtil.getResizedImage(path, null, 200, true, 0);
        path = saveToSdCard(bitmap);
        file = new File(path);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file); // multipart/form-data // image/*
        Map<String, RequestBody> params = new HashMap<>();
        params.put("sid", toRequestBody(ZRDeviceInfo.getSid()));
        params.put("deviceid", toRequestBody(ZRDeviceInfo.getDeviceID()));
        params.put("rid", toRequestBody(ZRDeviceInfo.getRid()));
        params.put("pathKey", toRequestBody("Upload_File_EDU"));
        params.put("file\"; filename=\"image.png\"" + file.getName(), requestBody); // uploadImages
        return fileUploadApi.uploadImage(
                "http://172.16.101.201:9006/uploadFile"
                , params);
    }

    /**
     * This method converts String to RequestBody
     *
     * @param value
     * @return
     */
    private RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    /**
     * 将压缩的图片保存
     *
     * @param bitmap 剪切后图片的图片
     * @return 压缩后图片的图片的路径
     */
    public String saveToSdCard(Bitmap bitmap) {
        Date datel = new Date(System.currentTimeMillis());
        String dateTime = datel.getTime() + "";
        String files = CacheUtils.getCacheDirectory(ZRApplication.applicationContext, true,
                "pic") + dateTime + ".jpg";
        File file = new File(files);
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
