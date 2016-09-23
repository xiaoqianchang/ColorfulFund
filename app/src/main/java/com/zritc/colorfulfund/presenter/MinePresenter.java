package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.zritc.colorfulfund.data.model.mine.MyProperty;
import com.zritc.colorfulfund.data.model.mine.PersonalInfo;
import com.zritc.colorfulfund.data.response.mine.GetUserMainPageInfo4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IMineView;
import com.zritc.colorfulfund.utils.ZRUtils;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;

/**
 * 我的界面
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class MinePresenter extends BasePresenter<IMineView> {

    public MinePresenter(Context context, IMineView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取数据
     */
    public void doGetUserMainPageInfo() {
        Call<GetUserMainPageInfo4C> getUserMainPageInfo4CCall = ZRNetManager.getInstance().getUserMainPageInfo4CCallbackByPost();
        getUserMainPageInfo4CCall.enqueue(new ResponseCallBack<GetUserMainPageInfo4C>(GetUserMainPageInfo4C.class) {
            @Override
            public void onSuccess(GetUserMainPageInfo4C getUserMainPageInfo4C) {
                PersonalInfo personalInfo = dataConverter(getUserMainPageInfo4C);
                iView.onSuccess(personalInfo);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    /**
     * 网络模型数据转换器
     *
     * @param getUserMainPageInfo4C
     * @return
     */
    private PersonalInfo dataConverter(GetUserMainPageInfo4C getUserMainPageInfo4C) {
        /*PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.avatar = "http://imgsrc.baidu.com/forum/w%3D580/sign=ccacdb64d3160924dc25a213e407359b/842b2834349b033b21e440e816ce36d3d539bd91.jpg";
        personalInfo.nickName = "一个好人";
        personalInfo.myPropertyAmount = "20000";
        personalInfo.bankCardNum = "2";
        personalInfo.riskEvaluationType = "未评估";*/

        double totalIncome = 0;
        double propertyTotalAmount = 0;
        PersonalInfo personalInfo = new PersonalInfo();
        MyProperty myProperty = new MyProperty(); // 我的资产
        GetUserMainPageInfo4C.UserMainPageInfo userMainPageInfo = getUserMainPageInfo4C.userMainPageInfo;
        if (null != userMainPageInfo) {
            GetUserMainPageInfo4C.UserInfo userInfo = userMainPageInfo.userInfo;
            if (null != userInfo) {
                personalInfo.userId =userInfo.userId;
                personalInfo.phone =userInfo.phone;
                personalInfo.avatar =userInfo.photoURL;
                personalInfo.nickName =userInfo.nickName;
            }
            List<GetUserMainPageInfo4C.UserAsset> userAssets = userMainPageInfo.userAsset;
            if (null != userAssets) {
                for (GetUserMainPageInfo4C.UserAsset userAsset : userAssets) {
                    MyProperty.Property property = new MyProperty().new Property();
                    property.poCode = userAsset.poBase.poCode;
                    property.poName = userAsset.poBase.poName;
                    property.expectedYearlyRoe = userAsset.poBase.expectedYearlyRoe;
                    property.riskLevel = userAsset.poBase.riskLevel;

                    property.balance = ZRUtils.getFormatDecimal(userAsset.userPoAsset.totalAmount);
                    property.income = ZRUtils.getFormatDecimal(userAsset.userPoAsset.totalProfit);
                    myProperty.propertyList.add(property);

                    // 计算总收益、总资产
                    if (TextUtils.isEmpty(userAsset.userPoAsset.totalProfit)) {
                        userAsset.userPoAsset.totalProfit = "0";
                    }
                    totalIncome += Double.parseDouble(userAsset.userPoAsset.totalProfit);
                    if (TextUtils.isEmpty(userAsset.userPoAsset.totalAmount)) {
                        userAsset.userPoAsset.totalAmount = "0";
                    }
                    propertyTotalAmount += Double.parseDouble(userAsset.userPoAsset.totalAmount);

                }
            }
            myProperty.totalIncome = ZRUtils.getDecimalFormat(totalIncome);
            myProperty.propertyTotalAmount = ZRUtils.getDecimalFormat(propertyTotalAmount);

            personalInfo.myProperty = myProperty;

            personalInfo.myPropertyAmount =ZRUtils.getDecimalFormat(propertyTotalAmount);
            personalInfo.bankCardNum = String.valueOf(userMainPageInfo.userBankCardList.size());
        }

        return personalInfo;
    }
}
