package com.zritc.colorfulfund.iView;

import com.zritc.colorfulfund.data.response.user.Login;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/8/11.
 *
 * @version 1.0
 */
public interface IRegisterView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void sendAuthCodeSucess();
    void sendAuthCodeFail(String msg);
    void registerSuccess();
    void registerFail(String msg);
    void loginSuccess(Login login);
    void loginFail(String message);
}
