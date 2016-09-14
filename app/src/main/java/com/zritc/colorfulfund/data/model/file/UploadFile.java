package com.zritc.colorfulfund.data.model.file;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 图片上传model
 * <p>
 * Created by Chang.Xiao on 2016/9/14.
 *
 * @version 1.0
 */
public class UploadFile {

    /**
     * session id
     */
    public String sid = "";

    /**
     * 请求 id
     */
    public String rid = "";

    /**
     * 返回代码
     */
    public String code = "";

    /**
     * 返回信息
     */
    public String msg = "";

    /**
     * 接口类型
     */
    public String optype = "";

    /**
     * 文件路径
     */
    public String filePath = "";

    /**
     * parse json
     */
    public synchronized UploadFile parseJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        if (jsonObject.isNull("sid")) {
            Log.d("CreateCollection", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.sid = jsonObject.optString("sid");
        if (jsonObject.isNull("rid")) {
            Log.d("CreateCollection", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.rid = jsonObject.optString("rid");
        if (jsonObject.isNull("code")) {
            Log.d("CreateCollection", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.code = jsonObject.optString("code");
        if (jsonObject.isNull("msg")) {
            Log.d("CreateCollection", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.msg = jsonObject.optString("msg");
        if (jsonObject.isNull("optype")) {
            Log.d("CreateCollection", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.optype = jsonObject.optString("optype");
        if (jsonObject.isNull("filePath")) {
            Log.d("filePath", "has no mapping for key " + "filePath" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.filePath = jsonObject.optString("filePath");

        return this;
    }
}
