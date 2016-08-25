package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fund.ZRActivityFundList;
import com.zritc.colorfulfund.activity.fund.ZRActivityGroupRedemption;
import com.zritc.colorfulfund.activity.fund.ZRActivitySingleRedemption;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.trade.BindPayment;
import com.zritc.colorfulfund.data.response.trade.BuyPo;
import com.zritc.colorfulfund.data.response.trade.EstimateBuyFundFee;
import com.zritc.colorfulfund.data.response.trade.GetFundPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.GetFundPoList4C;
import com.zritc.colorfulfund.data.response.trade.GetUserBankCards4C;
import com.zritc.colorfulfund.data.response.trade.GetUserPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;
import com.zritc.colorfulfund.data.response.trade.UnbindPayment;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.data.response.user.SetTransPwd;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;

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

    @OnClick({R.id.btn_prepareRegisterAcc, R.id.btn_registerAcc, R.id.btn_login, R.id.btn_prepare_bind_payment, R.id.btn_bind_payment, R.id.btn_unbind_payment, R.id.btn_setTransPwd, R.id.btn_group_redemption, R.id.btn_buy_po, R.id.btn_user_bank_cards4C, R.id.btn_user_po_list4C, R.id.btn_user_po_info4C, R.id.btn_fund_po_list4C, R.id.btn_fund_po_info4C, R.id.btn_single_redemption, R.id.btn_estimateBuyFundFee, R.id.btn_article_details, R.id.btn_video_details})
    public void onClick(View view) {
        String realName = "张三";
        String identityNo = "110101190001012837"; // 110101190001012837
        String paymentType = "bank:003";
        String paymentNo = "6222023803013297864";
        String phone = "18512123013"; // 18721081671
        String password = "123456";
        String vCode = "0453";
        String poCode = "ZH000484";
        String userPaymentId = "6";
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
                Call<Login> loginCall = ZRNetManager.getInstance().loginCallbackByPost(phone, password);
                loginCall.enqueue(new ResponseCallBack<Login>(Login.class) {
                    @Override
                    public void onSuccess(Login login) {
                        // 登录成功，保存状态
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_PHONE, phone);
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_PASSWORD, password);
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_SID, login.sid);
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_RID, login.rid);
                        showToast(login.msg);
//                        Intent intent = new Intent(mContext, ZRActivityMain.class);
//                        mContext.startActivity(intent);
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
                Call<EstimateBuyFundFee> estimateBuyFundFeeCall = ZRNetManager.getInstance().estimateBuyFundFeeCallbackByPost("2", "ZH000484", "25.00");
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
            case R.id.btn_buy_po:
                Call<BuyPo> buyPoCall = ZRNetManager.getInstance().buyPoCallbackByPost("ZH000484", "2000.00", userPaymentId);
                buyPoCall.enqueue(new ResponseCallBack<BuyPo>(BuyPo.class) {
                    @Override
                    public void onSuccess(BuyPo buyPo) {
                        showToast(buyPo.msg);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_user_bank_cards4C: // 获取用户已绑定的银行卡列表
                String sid = ZRDeviceInfo.getSid();
                String serverDeviceId = ZRDeviceInfo.getServerDeviceId();
                String rid = ZRDeviceInfo.getRid();
                Call<GetUserBankCards4C> userBankCards4CCallbackByPost = ZRNetManager.getInstance().getUserBankCards4CCallbackByPost();
                userBankCards4CCallbackByPost.enqueue(new ResponseCallBack<GetUserBankCards4C>(GetUserBankCards4C.class) {
                    @Override
                    public void onSuccess(GetUserBankCards4C getUserBankCards4C) {
                        showToast(getUserBankCards4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_user_po_list4C: // 获取我的基金列表
                /*Call<GetUserPoList4C> userPoList4CCallbackByPost = ZRNetManager.getInstance().getUserPoList4CCallbackByPost(userPaymentId);
                userPoList4CCallbackByPost.enqueue(new ResponseCallBack<GetUserPoList4C>(GetUserPoList4C.class) {
                    @Override
                    public void onSuccess(GetUserPoList4C getUserPoList4C) {
                        showToast(getUserPoList4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });*/
                startActivity(new Intent(this, ZRActivityFundList.class));
                break;
            case R.id.btn_user_po_info4C: // 获取我的基金信息
                Call<GetUserPoInfo4C> userPoInfo4CCallbackByPost = ZRNetManager.getInstance().getUserPoInfo4CCallbackByPost(poCode);
                userPoInfo4CCallbackByPost.enqueue(new ResponseCallBack<GetUserPoInfo4C>(GetUserPoInfo4C.class) {
                    @Override
                    public void onSuccess(GetUserPoInfo4C getUserPoInfo4C) {
                        showToast(getUserPoInfo4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_fund_po_list4C: // 获取基金列表
                Call<GetFundPoList4C> fundPoList4CCallbackByPost = ZRNetManager.getInstance().getFundPoList4CCallbackByPost();
                fundPoList4CCallbackByPost.enqueue(new ResponseCallBack<GetFundPoList4C>(GetFundPoList4C.class) {
                    @Override
                    public void onSuccess(GetFundPoList4C getFundPoList4C) {
                        showToast(getFundPoList4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_fund_po_info4C: // 获取基金信息
                Call<GetFundPoInfo4C> fundPoInfo4CCallbackByPost = ZRNetManager.getInstance().getFundPoInfo4CCallbackByPost(poCode);
                fundPoInfo4CCallbackByPost.enqueue(new ResponseCallBack<GetFundPoInfo4C>(GetFundPoInfo4C.class) {
                    @Override
                    public void onSuccess(GetFundPoInfo4C getFundPoInfo4C) {
                        showToast(getFundPoInfo4C.toString());
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
            case R.id.btn_article_details: // 文章详情
                startActivity(new Intent(this, ZRActivityArticleDetails.class));
                break;
            case R.id.btn_video_details: // 视频详情
                startActivity(new Intent(this, ZRActivityVideoDetails.class));
                break;
        }
    }
}
