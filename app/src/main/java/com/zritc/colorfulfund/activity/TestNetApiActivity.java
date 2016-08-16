package com.zritc.colorfulfund.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.CardManage.ZRActivityCardManage;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.trade.BindPayment;
import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;
import com.zritc.colorfulfund.data.response.trade.UnbindPayment;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.data.response.user.SetTransPwd;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.utils.ZRNetUtils;
import com.zritc.colorfulfund.utils.ZRSystemUtils;
import com.zritc.colorfulfund.utils.ZRToastFactory;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Bind(R.id.btn_group_redemption)
    Button btnGroupRedemption;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test_nei_api;
    }

    @Override
    protected void initPresenter() {
    }

    @OnClick({R.id.btn_prepareRegisterAcc, R.id.btn_registerAcc, R.id.btn_login, R.id.btn_prepare_bind_payment, R.id.btn_bind_payment, R.id.btn_unbind_payment, R.id.btn_setTransPwd, R.id.btn_group_redemption})
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
                prepareRegisterAccCall.enqueue(new Callback<PrepareRegisterAcc>() {
                    @Override
                    public void onResponse(Call<PrepareRegisterAcc> call, Response<PrepareRegisterAcc> response) {
                        try {
                            PrepareRegisterAcc body = response.body();
                            if (null != body) {
                                Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PrepareRegisterAcc> call, Throwable t) {
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_registerAcc:
                Call<RegisterAcc> registerAccCall = ZRNetManager.getInstance().registerAccCallbackByPost(phone, password, vCode);
                registerAccCall.enqueue(new Callback<RegisterAcc>() {
                    @Override
                    public void onResponse(Call<RegisterAcc> call, Response<RegisterAcc> response) {
                        try {
                            RegisterAcc body = response.body();
                            if (null != body) {
                                Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterAcc> call, Throwable t) {
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_login:
                Call<Login> loginCall = ZRNetManager.getInstance().loginCallbackByPost(phone, password, ZRNetUtils.getLocalIpAddress());
                loginCall.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        try {
                            Login body = response.body();
                            if (null != body) {
                                Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_prepare_bind_payment:
                Call<PrepareBindPayment> prepareBindPaymentCall = ZRNetManager.getInstance().prepareBindPaymentCallbackByPost(realName, identityNo, paymentType, paymentNo, phone);
                prepareBindPaymentCall.enqueue(new Callback<PrepareBindPayment>() {
                    @Override
                    public void onResponse(Call<PrepareBindPayment> call, Response<PrepareBindPayment> response) {
                        try {
                            PrepareBindPayment body = response.body();
                            if (null != body) {
                                Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PrepareBindPayment> call, Throwable t) {
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_bind_payment:
                Call<BindPayment> bindPaymentCall = ZRNetManager.getInstance().bindPaymentCallbackByPost(realName, identityNo, paymentType, paymentNo, phone, vCode);
                bindPaymentCall.enqueue(new Callback<BindPayment>() {
                    @Override
                    public void onResponse(Call<BindPayment> call, Response<BindPayment> response) {
                        try {
                            BindPayment body = response.body();
                            if (null != body) {
                                Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<BindPayment> call, Throwable t) {
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_unbind_payment:
                Call<UnbindPayment> unbindPaymentCall = ZRNetManager.getInstance().unbindPaymentCallbackByPost(paymentNo);
                unbindPaymentCall.enqueue(new Callback<UnbindPayment>() {
                    @Override
                    public void onResponse(Call<UnbindPayment> call, Response<UnbindPayment> response) {
                        try {
                            UnbindPayment body = response.body();
                            if (null != body) {
                                Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UnbindPayment> call, Throwable t) {
                        ZRToastFactory.getToast(mContext, t.getMessage()).show();
                    }
                });
                break;
            case R.id.btn_setTransPwd:
                Call<SetTransPwd> setTransPwdCall = ZRNetManager.getInstance().setTransPwdCallbackByPost(password);
                setTransPwdCall.enqueue(new Callback<SetTransPwd>() {
                    @Override
                    public void onResponse(Call<SetTransPwd> call, Response<SetTransPwd> response) {
                        try {
                            SetTransPwd body = response.body();
                            if (null != body) {
                                Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<SetTransPwd> call, Throwable t) {
                        ZRToastFactory.getToast(mContext, t.getMessage()).show();
                    }
                });
                break;
            case R.id.btn_group_redemption: // 组合赎回
                Intent intent = new Intent(mContext, ZRActivityGroupRedemption.class);
                startActivity(intent);
                break;
        }
    }
}
