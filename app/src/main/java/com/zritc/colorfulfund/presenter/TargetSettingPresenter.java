package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.data.response.edu.CreateUserInvestmentPlan4Edu;
import com.zritc.colorfulfund.data.response.edu.GetUserPoAssetInfo4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ITargetSettingView;

import retrofit2.Call;

/**
 * TargetSettingPresenter 目标设定
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-31
 * @lastUpdate 2016-8-31
 */
public class TargetSettingPresenter extends BasePresenter<ITargetSettingView> {

    public TargetSettingPresenter(Context context, ITargetSettingView iView) {
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
                userPoAssetInfo.expectedYearlyRoe = getUserPoAssetInfo4C.userPoInvestInfo.fundPoInfo.poBase.expectedYearlyRoe;
                iView.onSuccess(userPoAssetInfo);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    public void createUserInvestmentPlan4Edu(String poCode, String targetDate, String targetAmount, String initialAmount) {
        iView.showProgress("处理中...");
        Call<CreateUserInvestmentPlan4Edu> createUserInvestmentPlan4EduCall = ZRNetManager.getInstance().createUserInvestmentPlan4EduCallbackByPost(poCode, targetDate, targetAmount, initialAmount);
        createUserInvestmentPlan4EduCall.enqueue(new ResponseCallBack<CreateUserInvestmentPlan4Edu>(CreateUserInvestmentPlan4Edu.class) {
            @Override
            public void onSuccess(CreateUserInvestmentPlan4Edu createUserInvestmentPlan4Edu) {
                iView.hideProgress();
                iView.onSuccess(createUserInvestmentPlan4Edu);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }
}
