package com.zritc.colorfulfund.data;

import com.google.gson.Gson;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRJsonValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * ZRSysinfo.
 *
 * @author eric
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRSysinfo implements Serializable {

    private static final long serialVersionUID = -1994975156104278036L;

    private String sid = "";// sid
    private ZRApiInit apiInit;
    private ZRUpdateInfo updateInfo;
    public ZRApiInit getApiInit() {
        return apiInit;
    }

    public void setApiInit(ZRApiInit apiInit) {
        this.apiInit = apiInit;
    }

    public ZRSysinfo initFromSysConfigJson(String str) throws JSONException {
        try {
            if(new ZRJsonValidator().validate(str)) {
                JSONObject obj = new JSONObject(str);

                JSONObject updateObj = obj
                        .optJSONObject(ZRConstant.KEY_CLIENT_INFO);
                updateInfo = new ZRUpdateInfo().initFromJson(updateObj);

                Gson gson = new Gson();
                apiInit = gson.fromJson(obj.optJSONObject("init_api").toString(), ZRApiInit.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return this;
    }
}
