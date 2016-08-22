package com.zritc.colorfulfund.http;

import android.text.TextUtils;

import com.zritc.colorfulfund.base.ZRApplication;
import com.zritc.colorfulfund.exception.ServerException;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRErrors;
import com.zritc.colorfulfund.utils.ZRNetUtils;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;

import org.apache.http.HttpStatus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络响应封装
 * <p>
 * Created by Chang.Xiao on 2016/8/16.
 *
 * @version 1.0
 */
public abstract class ResponseCallBack<T> implements Callback<T> {

    private final Class<?> c;
    private String code;
    private String msg;
    private String sid;

    public ResponseCallBack(Class<?> c) {
        this.c = c;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            T body = response.body();
            int statusCode = response.code(); // 服务解析返回后的code
            // 405 right return
            if (HttpStatus.SC_METHOD_NOT_ALLOWED == statusCode) {
                String jsonStr = response.errorBody().string();
                Method parseJson = c.getMethod("parseJson", String.class);
                Object object = parseJson.invoke(c.newInstance(), new String(jsonStr));
                body = (T) object;
            }

            if (null != body) {
                // 获取code、msg
                try {
                    Field code = c.getField("code");
                    Field msg = c.getField("msg");
                    Field sid = c.getField("sid");
                    this.code = (String) code.get(body);
                    this.msg = (String) msg.get(body);
                    this.sid = (String) sid.get(body);
                    
                    ZRSharePreferenceKeeper.keepStringValue(ZRApplication.applicationContext,
                            ZRConstant.KEY_EXTRA_SID, this.sid);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // 根据code判断
                if (ZRErrors.SUCCESS.equals(code)) { // 1000
                    onSuccess(body);
                } else {
                    if (TextUtils.isEmpty(msg)) {
                        String errorMsg = ZRErrors.getLocalErrorMsg(ZRApplication.applicationContext, code);
                        onError(code, errorMsg);
                    } else {
                        onError(code, msg);
                    }
                }
            } else {
                onFailure(new ServerException("response body is null"));
            }
        } catch (Exception e) {
            onError("", e.getMessage());
        }
    }

    /**
     * 1.服务器连上失败
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (!ZRNetUtils.isNetworkConnected(ZRApplication.applicationContext)) {
            onError("", "网络不可用!");
            return;
        }
        onError("", t.getMessage());
    }

    public void onFailure(Throwable e) {
        if (e instanceof ServerException) {
            onError("", e.getMessage());
        } else if (e instanceof Exception) {
            // 更多异常处理
        } else {
            onError("", "请求失败，请稍后重试...");
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onError(String code, String msg);
}
