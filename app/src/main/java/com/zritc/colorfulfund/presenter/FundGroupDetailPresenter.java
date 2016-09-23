package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.zritc.colorfulfund.data.model.edu.PoChangeHistory;
import com.zritc.colorfulfund.data.model.edu.PoFund;
import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.data.response.edu.GetPoChangeHistory4C;
import com.zritc.colorfulfund.data.response.edu.GetUserPoAssetInfo4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IFundGroupDetailView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * FundGroupDetailPresenter 基金组合详情
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-30
 * @lastUpdate 2016-8-30
 */
public class FundGroupDetailPresenter extends BasePresenter<IFundGroupDetailView> {

    public FundGroupDetailPresenter(Context context, IFundGroupDetailView iView) {
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
                userPoAssetInfo.totalAmount = getUserPoAssetInfo4C.userPoInvestInfo.userPoAsset.totalAmount;
                userPoAssetInfo.targetAmount = getUserPoAssetInfo4C.userPoInvestInfo.userInitPoAssetInfo.targetAmount;
                userPoAssetInfo.initialtAmount = getUserPoAssetInfo4C.userPoInvestInfo.userInitPoAssetInfo.initialtAmount;
                userPoAssetInfo.profitbythismonth = getUserPoAssetInfo4C.userPoInvestInfo.userInitPoAssetInfo.profitbythismonth;
                userPoAssetInfo.expectedYearlyRoe = getUserPoAssetInfo4C.userPoInvestInfo.fundPoInfo.poBase.expectedYearlyRoe;
                userPoAssetInfo.targetDate = String.valueOf(getUserPoAssetInfo4C.userPoInvestInfo.userInitPoAssetInfo.targetDate);
                userPoAssetInfo.riskLevel = getUserPoAssetInfo4C.userPoInvestInfo.fundPoInfo.poBase.riskLevel;

                userPoAssetInfo.poFundList = new ArrayList<>();
                List<PoFund> _poFundList = new ArrayList<>();
                for (GetUserPoAssetInfo4C.PoFundList x : getUserPoAssetInfo4C.userPoInvestInfo.fundPoInfo.poFundList) {
                    String fundCode = x.fundCode;
                    String fundName = x.fundName;
                    if (TextUtils.isEmpty(fundCode) || TextUtils.isEmpty(fundName))
                        continue;
                    PoFund poFund = new PoFund();
                    poFund.fundCode = x.fundCode;
                    poFund.fundName = x.fundName;
                    poFund.poPercentage = x.poPercentage;
                    _poFundList.add(poFund);
                }
                userPoAssetInfo.poFundList.addAll(_poFundList);
                iView.onSuccess(userPoAssetInfo);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    public void getPoChangeHistory4C(String poCode) {
        Call<GetPoChangeHistory4C> getPoChangeHistory4CCall = ZRNetManager.getInstance().getPoChangeHistory4CCallbackByPost(poCode);
        getPoChangeHistory4CCall.enqueue(new ResponseCallBack<GetPoChangeHistory4C>(GetPoChangeHistory4C.class) {
            @Override
            public void onSuccess(GetPoChangeHistory4C getPoChangeHistory4C) {
                PoChangeHistory poChangeHistory = new PoChangeHistory();
                for (GetPoChangeHistory4C.PoChangeHistory x : getPoChangeHistory4C.poChangeHistory) {
                    PoChangeHistory y = new PoChangeHistory();
                    List<PoFund> _poFundList = new ArrayList<>();
                    for (GetPoChangeHistory4C.PoFundList z : x.poFundList) {
                        String fundCode = z.fundCode;
                        String fundName = z.fundName;
                        if (TextUtils.isEmpty(fundCode) || TextUtils.isEmpty(fundName))
                            continue;
                        PoFund poFund = new PoFund();
                        poFund.fundCode = fundCode;
                        poFund.fundName = fundName;
                        poFund.poPercentage = z.poPercentage;
                        _poFundList.add(poFund);

                        y.poFundList.addAll(_poFundList);
                    }
                    poChangeHistory.poChangeHistory.add(y);
                }
                iView.onSuccess(poChangeHistory);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

}
