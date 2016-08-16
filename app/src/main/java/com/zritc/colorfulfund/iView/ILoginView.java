package com.zritc.colorfulfund.iView;

import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;

/**
 * 登录界面view操作
 * <p>
 * Created by Chang.Xiao on 2016/8/11.
 *
 * @version 1.0
 */
public interface ILoginView extends IBaseView {

    void loginSuccess(PrepareBindPayment prepareBindPayment);
    void loginFail(String message);
    void showProgress(CharSequence message);
    void hideProgress();
}
