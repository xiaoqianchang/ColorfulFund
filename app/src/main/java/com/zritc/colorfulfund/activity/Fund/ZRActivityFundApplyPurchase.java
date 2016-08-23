package com.zritc.colorfulfund.activity.fund;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.iView.IFundApplyPurchaseView;
import com.zritc.colorfulfund.presenter.FundApplyPurchasePresenter;

/**
 * ZRActivityFundApplyPurchase 单只基金申购
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public class ZRActivityFundApplyPurchase extends ZRActivityToolBar<FundApplyPurchasePresenter> implements IFundApplyPurchaseView {

    private FundApplyPurchasePresenter fundApplyPurchasePresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fund_apply_purchase;
    }

    @Override
    protected void initPresenter() {
        fundApplyPurchasePresenter = new FundApplyPurchasePresenter(this, this);
        fundApplyPurchasePresenter.init();
    }

    @Override
    public void initView() {
        setTitleText("基金申购");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showNoMoreData() {

    }

}
