package com.zritc.colorfulfund.http;

import com.zritc.colorfulfund.data.model.file.UploadFile;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * $desc$
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
}
