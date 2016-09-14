package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.edu.GrowingRecord;
import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.data.model.file.UploadFile;
import com.zritc.colorfulfund.data.response.edu.GetGrowingRecordList4C;
import com.zritc.colorfulfund.data.response.edu.GetUserPoAssetInfo4C;
import com.zritc.colorfulfund.http.FileUploadManager;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IEduSceneView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * EduScenePresenter 教育场景
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-31
 * @lastUpdate 2016-8-31
 */
public class EduScenePresenter extends BasePresenter<IEduSceneView> {

    public EduScenePresenter(Context context, IEduSceneView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void getUserPoAssetInfo4C(String poCode) {
        Call<GetUserPoAssetInfo4C> getUserPoAssetInfo4CCall = ZRNetManager.getInstance().getUserPoAssetInfo4CCallbackByPost(poCode);
        getUserPoAssetInfo4CCall.enqueue(new ResponseCallBack<GetUserPoAssetInfo4C>(GetUserPoAssetInfo4C.class) {
            @Override
            public void onSuccess(GetUserPoAssetInfo4C getUserPoAssetInfo4C) {
                UserPoAssetInfo userPoAssetInfo = new UserPoAssetInfo();
                userPoAssetInfo.targetAmount = getUserPoAssetInfo4C.userPoInvestInfo.userInitPoAssetInfo.targetAmount;
                userPoAssetInfo.totalAmount = getUserPoAssetInfo4C.userPoInvestInfo.userPoAsset.totalAmount;
                userPoAssetInfo.totalProfit = getUserPoAssetInfo4C.userPoInvestInfo.userPoAsset.totalProfit;
                iView.onSuccess(userPoAssetInfo);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    public void getGrowingRecordList4C() {
        Call<GetGrowingRecordList4C> getGrowingRecordList4CCall = ZRNetManager.getInstance().getGrowingRecordList4CCallbackByPost();
        getGrowingRecordList4CCall.enqueue(new ResponseCallBack<GetGrowingRecordList4C>(GetGrowingRecordList4C.class) {
            @Override
            public void onSuccess(GetGrowingRecordList4C getGrowingRecordList4C) {
                GrowingRecord growingRecord = new GrowingRecord();
                List<List<GrowingRecord>> growingRecordLists = new ArrayList<>();

                List<GrowingRecord> _growingRecordList = new ArrayList<>();
                int j = 1;
                int len = getGrowingRecordList4C.growingRecordList.size();
                for (int i = 0; i < len; i++) {
                    if (i == 4 * j) {
                        j++;
                        _growingRecordList = new ArrayList<>();
                    }
                    GetGrowingRecordList4C.GrowingRecordList growingRecordList = getGrowingRecordList4C.growingRecordList.get(i);
                    GrowingRecord _growingRecord = new GrowingRecord();
                    _growingRecord.growingDesc = growingRecordList.growingDesc;
                    _growingRecord.targetDate = growingRecordList.targetDate;
                    if (null != _growingRecord.photoUrlInfo)
                        _growingRecord.photoUrlInfo.photoUrl = growingRecordList.photoUrlInfo.photoUrl;
                    _growingRecordList.add(_growingRecord);
                    if (i == (4 * j) - 1 || i == len-1) {
                        growingRecordLists.add(_growingRecordList);
                    }
                }
                growingRecord.growingRecordLists = growingRecordLists;
                iView.onSuccess(growingRecord);
            }

            @Override
            public void onError(String code, String msg) {
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
                iView.onError(msg);
            }
        });
    }
}
