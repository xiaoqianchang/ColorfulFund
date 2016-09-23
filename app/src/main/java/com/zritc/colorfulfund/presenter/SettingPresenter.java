package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.file.UploadFile;
import com.zritc.colorfulfund.data.model.mine.PersonalInfo;
import com.zritc.colorfulfund.data.response.user.GetUserInfo4C;
import com.zritc.colorfulfund.data.response.user.Logoff;
import com.zritc.colorfulfund.data.response.user.UpdateUserInfo;
import com.zritc.colorfulfund.http.FileUploadManager;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ISettingView;

import java.io.File;

import retrofit2.Call;

/**
 * Setting presenter
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class SettingPresenter extends BasePresenter<ISettingView> {

    public SettingPresenter(Context context, ISettingView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取用户信息
     *
     * @param userId
     */
    public void doGetUserInfo(String userId) {
        Call<GetUserInfo4C> getUserInfo4CCall = ZRNetManager.getInstance().getUserInfo4CCallbackByPost(userId);
        getUserInfo4CCall.enqueue(new ResponseCallBack<GetUserInfo4C>(GetUserInfo4C.class) {
            @Override
            public void onSuccess(GetUserInfo4C getUserInfo4C) {
                PersonalInfo personalInfo = userInfoConverter(getUserInfo4C);
                iView.onSuccess(personalInfo);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    private PersonalInfo userInfoConverter(GetUserInfo4C getUserInfo4C) {
        PersonalInfo personalInfo = new PersonalInfo();
        GetUserInfo4C.UserInfo userInfo = getUserInfo4C.userInfo;
        if (null != userInfo) {
            personalInfo.phone = userInfo.phone;
            personalInfo.nickName = userInfo.nickname;
            personalInfo.avatar = userInfo.photoUrl;
        }
        return personalInfo;
    }

    /**
     * 修改密码
     *
     * @param password
     */
    public void doModifyPassword(String password) {
        iView.showProgress("修改中...");
        iView.hideProgress();
    }

    /**
     * 重置交易面
     *
     * @param password
     */
    public void doResetTradePassword(String password) {
        iView.showProgress("修改中...");
        iView.hideProgress();
    }

    /**
     * 更新用户信息
     *
     * @param nickname
     * @param photoUrl
     */
    public void doUpdateUserInfo(String nickname, String photoUrl) {
        iView.showProgress("更新中...");
        Call<UpdateUserInfo> updateUserInfoCall = ZRNetManager.getInstance().updateUserInfoCallbackByPost(nickname, photoUrl);
        updateUserInfoCall.enqueue(new ResponseCallBack<UpdateUserInfo>(UpdateUserInfo.class) {
            @Override
            public void onSuccess(UpdateUserInfo updateUserInfo) {
                iView.hideProgress();
                iView.onSuccess(updateUserInfo);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }

    /**
     * 退出
     */
    public void doLogout() {
        iView.showProgress("退出中...");
        Call<Logoff> logoffCall = ZRNetManager.getInstance().logoffCallbackByPost();
        logoffCall.enqueue(new ResponseCallBack<Logoff>(Logoff.class) {
            @Override
            public void onSuccess(Logoff logoff) {
                iView.hideProgress();
                iView.onSuccess(logoff);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }

    /**
     * 上传图片
     *
     * @param path
     */
    public void uploadImage(String path) {
        iView.showProgress("上传中...");
        Call<UploadFile> uploadImageCall = FileUploadManager.getInstance().uploadImage(path);
        uploadImageCall.enqueue(new ResponseCallBack<UploadFile>(UploadFile.class) {
            @Override
            public void onSuccess(UploadFile uploadFile) {
                iView.hideProgress();
                iView.onSuccess(uploadFile);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }

    /**
     * 上传图片
     *
     * @param captureFile
     */
    public void uploadFile(File captureFile) {
        iView.showProgress("上传中...");
        Call<UploadFile> uploadImageCall = FileUploadManager.getInstance().uploadFile(captureFile);
        uploadImageCall.enqueue(new ResponseCallBack<UploadFile>(UploadFile.class) {
            @Override
            public void onSuccess(UploadFile uploadFile) {
                iView.hideProgress();
                iView.onSuccess(uploadFile);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }
}
