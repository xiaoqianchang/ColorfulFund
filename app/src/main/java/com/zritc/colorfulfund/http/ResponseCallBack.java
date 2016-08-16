package com.zritc.colorfulfund.http;

import android.text.TextUtils;

import com.zritc.colorfulfund.base.ZRApplication;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.exception.ServerException;
import com.zritc.colorfulfund.utils.ZRErrors;
import com.zritc.colorfulfund.utils.ZRNetUtils;

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

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            T body = response.body();
            if (null != body) {
                // 根据code判断
                if (ZRErrors.SUCCESS.equals(((Login) body).code)) { // 1000
                    onSuccess(body);
                } else {
                    if (TextUtils.isEmpty(((Login) body).msg)) {
                        String errorMsg = ZRErrors.getLocalErrorMsg(ZRApplication.applicationContext, ((Login) body).code);
                        onError(((Login) body).code, errorMsg);
                    } else {
                        onError(((Login) body).code, ((Login) body).msg);
                    }
                }
            } else {
                onFailure(new ServerException("response body is null"));
            }
        } catch (Exception e) {
            onError("", e.getMessage());
        }
    }

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
