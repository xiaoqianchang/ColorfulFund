package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.mine.TradeBillList;
import com.zritc.colorfulfund.data.response.mine.GetUserTradeHistory4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ITradeBillView;
import com.zritc.colorfulfund.utils.ZRUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 交易账单
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public class TradeBillPresenter extends BasePresenter<ITradeBillView> {

    public TradeBillPresenter(Context context, ITradeBillView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取数据
     */
    public void doGetUserTradeHistory() {
        Call<GetUserTradeHistory4C> getUserTradeHistory4CCall = ZRNetManager.getInstance().getUserTradeHistory4CCallbackByPost();
        getUserTradeHistory4CCall.enqueue(new ResponseCallBack<GetUserTradeHistory4C>(GetUserTradeHistory4C.class) {
            @Override
            public void onSuccess(GetUserTradeHistory4C getUserTradeHistory4C) {
                TradeBillList tradeBillList = dataConverter(getUserTradeHistory4C);
                iView.onSuccess(tradeBillList);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    /**
     * 网络模型数据转换器
     */
    private TradeBillList dataConverter(GetUserTradeHistory4C getUserTradeHistory4C) {
        /*TradeBillList tradeBillList = new TradeBillList();
        for (int i = 0; i < 5; i++) {
            TradeBillList.TradeBill tradeBill = new TradeBillList().new TradeBill();
            tradeBill.fundName = "长城宽带" + i;
            tradeBill.fundAmount = i + "100";
            for (int j = 0; j < 5; j++) {
                TradeBillList.TradeBillDetail tradeBillDetail = new TradeBillList().new TradeBillDetail();
                tradeBillDetail.actionType = "申购费";
                tradeBillDetail.status = "已扣除";
                tradeBillDetail.createDate = "09-05";
                tradeBillDetail.tradeMoney = "30";
                tradeBill.tradeBillDetailList.add(tradeBillDetail);
            }
            tradeBillList.tradeBillList.add(tradeBill);
        }*/

        TradeBillList tradeBillList = new TradeBillList();
        List<GetUserTradeHistory4C.UserTradeHistory> userTradeHistorys = getUserTradeHistory4C.userTradeHistory;
        if (null != userTradeHistorys) {
            for (GetUserTradeHistory4C.UserTradeHistory userTradeHistory : userTradeHistorys) {
                TradeBillList.TradeBill tradeBill = new TradeBillList().new TradeBill();
                tradeBill.poCode = userTradeHistory.poBase.poCode;
                tradeBill.fundName = userTradeHistory.poBase.poName;
                tradeBill.fundProfit = userTradeHistory.userPoAsset.totalProfit;
                tradeBill.fundAmount = userTradeHistory.userPoAsset.totalAmount;
                List<GetUserTradeHistory4C.TradeHistory> tradeHistorys = userTradeHistory.tradeHistory;
                if (null != tradeHistorys) {
                    for (GetUserTradeHistory4C.TradeHistory tradeHistory : tradeHistorys) {
                        TradeBillList.TradeBillDetail tradeBillDetail = new TradeBillList().new TradeBillDetail();
                        tradeBillDetail.actionType = tradeHistory.actionType;
                        tradeBillDetail.status = tradeHistory.status;
                        tradeBillDetail.createDate = ZRUtils.formatTime(tradeHistory.createDateOn, ZRUtils.TIME_FORMAT7);
                        tradeBillDetail.tradeMoney = tradeHistory.tradeAmount;
                        tradeBill.tradeBillDetailList.add(tradeBillDetail);
                    }
                    tradeBillList.tradeBillList.add(tradeBill);
                }
            }
        }

        return tradeBillList;
    }
}
