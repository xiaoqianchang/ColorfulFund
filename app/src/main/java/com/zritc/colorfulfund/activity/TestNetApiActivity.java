package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.Fund.ZRActivityGroupRedemption;
import com.zritc.colorfulfund.activity.Fund.ZRActivitySingleRedemption;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.trade.BindPayment;
import com.zritc.colorfulfund.data.response.trade.EstimateBuyFundFee;
import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;
import com.zritc.colorfulfund.data.response.trade.UnbindPayment;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.data.response.user.SetTransPwd;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.utils.ZRNetUtils;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;

public class TestNetApiActivity extends ZRActivityBase {

    @Bind(R.id.btn_prepareRegisterAcc)
    Button btnPrepareRegisterAcc; // 预注册(获取验证码)
    @Bind(R.id.btn_registerAcc)
    Button btnRegisterAcc; // 注册
    @Bind(R.id.btn_login)
    Button btnLogin; // 登录
    @Bind(R.id.btn_prepare_bind_payment)
    Button btnPrepareBindPayment; // 预绑卡
    @Bind(R.id.btn_bind_payment)
    Button bindPayment; // 绑卡
    @Bind(R.id.btn_unbind_payment)
    Button unbindPayment; // 解绑
    @Bind(R.id.btn_setTransPwd)
    Button btnSetTransPwd; // 设置交易密码
    @Bind(R.id.btn_estimateBuyFundFee)
    Button btnEstimateBuyFundFee;

    @Bind(R.id.btn_group_redemption)
    Button btnGroupRedemption;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test_nei_api;
    }

    @Override
    protected void initPresenter() {
    }

    @OnClick({R.id.btn_prepareRegisterAcc, R.id.btn_registerAcc, R.id.btn_login, R.id.btn_prepare_bind_payment, R.id.btn_bind_payment, R.id.btn_unbind_payment, R.id.btn_setTransPwd, R.id.btn_group_redemption, R.id.btn_single_redemption, R.id.btn_estimateBuyFundFee})
    public void onClick(View view) {
        String realName = "肖昌";
        String identityNo = "110101190001012837";
        String paymentType = "bank:007";
        String paymentNo = "6217003762218235621";
        String phone = "13564228527";
        String password = "123456";
        String vCode = "1234";
        switch (view.getId()) {
            case R.id.btn_prepareRegisterAcc:
                Call<PrepareRegisterAcc> prepareRegisterAccCall = ZRNetManager.getInstance().prepareRegisterAccCallbackByPost(phone);
                prepareRegisterAccCall.enqueue(new ResponseCallBack<PrepareRegisterAcc>(PrepareRegisterAcc.class) {
                    @Override
                    public void onSuccess(PrepareRegisterAcc prepareRegisterAcc) {
                        showToast(prepareRegisterAcc.msg);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_registerAcc:
                Call<RegisterAcc> registerAccCall = ZRNetManager.getInstance().registerAccCallbackByPost(phone, password, vCode);
                registerAccCall.enqueue(new ResponseCallBack<RegisterAcc>(RegisterAcc.class) {
                    @Override
                    public void onSuccess(RegisterAcc registerAcc) {
                        showToast(registerAcc.msg);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_login:
                Call<Login> loginCall = ZRNetManager.getInstance().loginCallbackByPost(phone, password, ZRNetUtils.getLocalIpAddress());
                loginCall.enqueue(new ResponseCallBack<Login>(Login.class) {
                    @Override
                    public void onSuccess(Login login) {
                        Intent intent = new Intent(mContext, ZRActivityMain.class);
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_prepare_bind_payment:
                Call<PrepareBindPayment> prepareBindPaymentCall = ZRNetManager.getInstance().prepareBindPaymentCallbackByPost(realName, identityNo, paymentType, paymentNo, phone);
                prepareBindPaymentCall.enqueue(new ResponseCallBack<PrepareBindPayment>(PrepareBindPayment.class) {
                    @Override
                    public void onSuccess(PrepareBindPayment prepareBindPayment) {
                        showToast(prepareBindPayment.msg);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_bind_payment:
                Call<BindPayment> bindPaymentCall = ZRNetManager.getInstance().bindPaymentCallbackByPost(realName, identityNo, paymentType, paymentNo, phone, vCode);
                bindPaymentCall.enqueue(new ResponseCallBack<BindPayment>(BindPayment.class) {
                    @Override
                    public void onSuccess(BindPayment bindPayment) {
                        showToast(bindPayment.msg);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_unbind_payment:
                Call<UnbindPayment> unbindPaymentCall = ZRNetManager.getInstance().unbindPaymentCallbackByPost(paymentNo);
                unbindPaymentCall.enqueue(new ResponseCallBack<UnbindPayment>(UnbindPayment.class) {
                    @Override
                    public void onSuccess(UnbindPayment unbindPayment) {
                        showToast(unbindPayment.msg);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_setTransPwd:
                Call<SetTransPwd> setTransPwdCall = ZRNetManager.getInstance().setTransPwdCallbackByPost(password);
                setTransPwdCall.enqueue(new ResponseCallBack<SetTransPwd>(SetTransPwd.class) {
                    @Override
                    public void onSuccess(SetTransPwd setTransPwd) {
                        showToast(setTransPwd.msg);
                            }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                        }
                });
                break;
            case R.id.btn_estimateBuyFundFee: // 估算申购费用
                Call<EstimateBuyFundFee> estimateBuyFundFeeCall = ZRNetManager.getInstance().estimateBuyFundFeeCallbackByPost("2", "ZH000484", 25.00);
                estimateBuyFundFeeCall.enqueue(new ResponseCallBack<EstimateBuyFundFee>(EstimateBuyFundFee.class) {
                    @Override
                    public void onSuccess(EstimateBuyFundFee estimateBuyFundFee) {
                        showToast(estimateBuyFundFee.msg);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_single_redemption: // 单个赎回
                startActivity(new Intent(this, ZRActivitySingleRedemption.class));
                break;
            case R.id.btn_group_redemption: // 组合赎回
                Intent intent = new Intent(mContext, ZRActivityGroupRedemption.class);
                startActivity(intent);
                break;
        }
    }
}
