package com.zritc.colorfulfund.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fortunegroup.ZRActivityArticleDetails;
import com.zritc.colorfulfund.activity.fortunegroup.ZRActivityVideoDetails;
import com.zritc.colorfulfund.activity.fund.ZRActivityFundList;
import com.zritc.colorfulfund.activity.fund.ZRActivityGroupRedemption;
import com.zritc.colorfulfund.activity.fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.activity.fund.ZRActivitySingleRedemption;
import com.zritc.colorfulfund.activity.scene.ZRActivityEduScene;
import com.zritc.colorfulfund.activity.wish.ZRActivityWishHomePage;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.circle.GetCommentList4C;
import com.zritc.colorfulfund.data.response.circle.GetPostInfo4C;
import com.zritc.colorfulfund.data.response.circle.GetPostList4C;
import com.zritc.colorfulfund.data.response.circle.CreateCollection;
import com.zritc.colorfulfund.data.response.circle.CreateComment;
import com.zritc.colorfulfund.data.response.circle.CreatePost;
import com.zritc.colorfulfund.data.response.circle.CreateReport;
import com.zritc.colorfulfund.data.response.circle.CreateThumb;
import com.zritc.colorfulfund.data.response.circle.ReadPost;
import com.zritc.colorfulfund.data.response.mine.EvaluateUserRiskLevel;
import com.zritc.colorfulfund.data.response.mine.GetSurveyList4C;
import com.zritc.colorfulfund.data.response.mine.GetUserMainPageInfo4C;
import com.zritc.colorfulfund.data.response.mine.GetUserTradeHistory4C;
import com.zritc.colorfulfund.data.response.trade.BindPayment;
import com.zritc.colorfulfund.data.response.trade.BuyPo;
import com.zritc.colorfulfund.data.response.trade.EstimateBuyFundFee;
import com.zritc.colorfulfund.data.response.trade.GetFundPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.GetFundPoList4C;
import com.zritc.colorfulfund.data.response.trade.GetUserBankCards4C;
import com.zritc.colorfulfund.data.response.trade.GetUserPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;
import com.zritc.colorfulfund.data.response.trade.UnbindPayment;
import com.zritc.colorfulfund.data.response.user.ChangeTransPwd;
import com.zritc.colorfulfund.data.response.user.CreateShareAlbum;
import com.zritc.colorfulfund.data.response.user.GetLoginPwdVcode;
import com.zritc.colorfulfund.data.response.user.GetUserCollectionList4C;
import com.zritc.colorfulfund.data.response.user.GetUserInfo4C;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.Logoff;
import com.zritc.colorfulfund.data.response.user.PrepareChangeTransPwd;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.data.response.user.ResetLoginPwd;
import com.zritc.colorfulfund.data.response.user.SetTransPwd;
import com.zritc.colorfulfund.data.response.user.UpdateUserInfo;
import com.zritc.colorfulfund.data.response.wish.CreateUserWishList4C;
import com.zritc.colorfulfund.data.response.wish.DeleteUserWishList4C;
import com.zritc.colorfulfund.data.response.wish.GetUserWishLists4C;
import com.zritc.colorfulfund.data.response.wish.GetWishListTypes4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.http.ZRRetrofit;
import com.zritc.colorfulfund.ui.ZRCircleImageView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;
import com.zritc.colorfulfund.utils.ZRUtils;
import com.zritc.colorfulfund.widget.CircleImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    @OnClick({R.id.btn_prepareRegisterAcc, R.id.btn_registerAcc, R.id.btn_login, R.id.btn_prepare_bind_payment,
            R.id.btn_bind_payment, R.id.btn_unbind_payment, R.id.btn_setTransPwd, R.id.btn_group_redemption,
            R.id.btn_buy_po, R.id.btn_user_bank_cards4C, R.id.btn_user_po_list4C, R.id.btn_user_po_info4C,
            R.id.btn_fund_po_list4C, R.id.btn_fund_po_info4C, R.id.btn_single_redemption, R.id.btn_estimateBuyFundFee,
            R.id.btn_article_details, R.id.btn_video_details, R.id.btn_call_camera, R.id.btn_record_growth,
            R.id.btn_generate_album, R.id.btn_edu_scene, R.id.btn_wish_home_page,
            R.id.btn_create_post, R.id.btn_create_comment, R.id.btn_create_thumb, R.id.btn_create_collection,
            R.id.btn_create_report, R.id.btn_read_post, R.id.btn_Post_info, R.id.btn_Post_list, R.id.btn_Comment_list,
            R.id.btn_WishLists, R.id.btn_CreateUserWish, R.id.btn_GetWishListTypes, R.id.btn_DeleteUserWish,
            R.id.btn_GetSurveyList, R.id.btn_EvaluateUserRiskLevel,
            R.id.btn_PrepareChangeTransPwd, R.id.btn_ChangeTransPwd, R.id.btn_ResetLoginPwd, R.id.btn_GetLoginPwdVcode,
            R.id.btn_Logoff, R.id.btn_UpdateUserInfo, R.id.btn_GetUserInfo4C, R.id.btn_GetUserCollectionList4C, R.id.btn_GetUserMainPageInfo4C, R.id.btn_GetUserTradeHistory4C,
            R.id.btn_CreateShareAlbum})
    public void onClick(View view) {
        String realName = "张三";
        String identityNo = "110101190001012837"; // 110101190001012837
        String paymentType = "bank:003";
        String paymentNo = "6222023803013297864";
        String phone = "13564228527"; // 18721081671、18512123013、18817618813
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
            case R.id.btn_call_camera: // 获取图片
                openPhotoPicker(this);
                break;
            case R.id.btn_record_growth: // 记录成长
                RecordGrowthDialog recordGrowthDialog = new RecordGrowthDialog(this);
                recordGrowthDialog.show();
                break;
            case R.id.btn_generate_album: // 成长相册
                openImageSelector();
                break;
            case R.id.btn_edu_scene: // 教育场景
                startActivity(new Intent(this, ZRActivityEduScene.class));
                break;
            case R.id.btn_wish_home_page: // 心愿首页
                startActivity(new Intent(this, ZRActivityWishHomePage.class));
                break;
            case R.id.btn_create_post: // 创建帖子
                Call<CreatePost> postCallbackByPost = ZRNetManager.getInstance().createPostCallbackByPost("http://www.cy580.com/file/upload/201307/29/14-03-21-44-88802.png", "business", "SB", "1", "ssssssssssssssssssssssssssss", "bbbbbbbbbbbbbbbbbbbbbbbb", "");
                postCallbackByPost.enqueue(new ResponseCallBack<CreatePost>(CreatePost.class) {
                    @Override
                    public void onSuccess(CreatePost createPost) {
                        showToast(createPost.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_create_comment: // 创建评论
                Call<CreateComment> commentCallbackByPost = ZRNetManager.getInstance().createCommentCallbackByPost("1", "我是一个好人！");
                commentCallbackByPost.enqueue(new ResponseCallBack<CreateComment>(CreateComment.class) {
                    @Override
                    public void onSuccess(CreateComment createComment) {
                        showToast(createComment.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_create_thumb: // 点赞
                Call<CreateThumb> thumbCallbackByPost = ZRNetManager.getInstance().createThumbCallbackByPost("1");
                thumbCallbackByPost.enqueue(new ResponseCallBack<CreateThumb>(CreateThumb.class) {
                    @Override
                    public void onSuccess(CreateThumb createThumb) {
                        showToast(createThumb.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_create_collection: // 收藏
                Call<CreateCollection> collectionCallbackByPost = ZRNetManager.getInstance().createCollectionCallbackByPost("1");
                collectionCallbackByPost.enqueue(new ResponseCallBack<CreateCollection>(CreateCollection.class) {
                    @Override
                    public void onSuccess(CreateCollection createCollection) {
                        showToast(createCollection.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_create_report: // 举报评论
                Call<CreateReport> reportCallbackByPost = ZRNetManager.getInstance().createReportCallbackByPost("2");
                reportCallbackByPost.enqueue(new ResponseCallBack<CreateReport>(CreateReport.class) {
                    @Override
                    public void onSuccess(CreateReport createReport) {
                        showToast(createReport.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_read_post: // 阅读帖子
                Call<ReadPost> readPostCall = ZRNetManager.getInstance().readPostCallbackByPost("1");
                readPostCall.enqueue(new ResponseCallBack<ReadPost>(ReadPost.class) {
                    @Override
                    public void onSuccess(ReadPost readPost) {
                        showToast(readPost.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_Post_info: // 获取帖子详情
                Call<GetPostInfo4C> postInfo4CCallbackByPost = ZRNetManager.getInstance().getPostInfo4CCallbackByPost("38");
                postInfo4CCallbackByPost.enqueue(new ResponseCallBack<GetPostInfo4C>(GetPostInfo4C.class) {
                    @Override
                    public void onSuccess(GetPostInfo4C getPostInfo4C) {
                        showToast(getPostInfo4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_Post_list: // 帖子列表
                Call<GetPostList4C> postList4CCallbackByPost = ZRNetManager.getInstance().getPostList4CCallbackByPost("1", "0");
                postList4CCallbackByPost.enqueue(new ResponseCallBack<GetPostList4C>(GetPostList4C.class) {
                    @Override
                    public void onSuccess(GetPostList4C getPostList4C) {
                        showToast(getPostList4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_Comment_list: // 帖子的评论列表
                Call<GetCommentList4C> commentList4CCallbackByPost = ZRNetManager.getInstance().getCommentList4CCallbackByPost("38");
                commentList4CCallbackByPost.enqueue(new ResponseCallBack<GetCommentList4C>(GetCommentList4C.class) {
                    @Override
                    public void onSuccess(GetCommentList4C getCommentList4C) {
                        showToast(getCommentList4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_WishLists: // 获取心愿列表
                Call<GetUserWishLists4C> userWishLists4CCallbackByPost = ZRNetManager.getInstance().getUserWishLists4CCallbackByPost();
                userWishLists4CCallbackByPost.enqueue(new ResponseCallBack<GetUserWishLists4C>(GetUserWishLists4C.class) {
                    @Override
                    public void onSuccess(GetUserWishLists4C getUserWishLists4C) {
                        showToast(getUserWishLists4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_CreateUserWish: // 创建用户心愿
                Call<CreateUserWishList4C> createUserWishList = ZRNetManager.getInstance().createUserWishList4CCallbackByPost(1, "篮球", "12", String.valueOf(ZRUtils.getCurrentLongTime()));
                createUserWishList.enqueue(new ResponseCallBack<CreateUserWishList4C>(CreateUserWishList4C.class) {
                    @Override
                    public void onSuccess(CreateUserWishList4C createUserWishList4C) {
                        showToast(createUserWishList4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_GetWishListTypes: // 获取心愿类型列表
                Call<GetWishListTypes4C> wishListTypes4CCallbackByPost = ZRNetManager.getInstance().getWishListTypes4CCallbackByPost();
                wishListTypes4CCallbackByPost.enqueue(new ResponseCallBack<GetWishListTypes4C>(GetWishListTypes4C.class) {
                    @Override
                    public void onSuccess(GetWishListTypes4C getWishListTypes4C) {
                        showToast(getWishListTypes4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_DeleteUserWish: // 删除用户心愿
                Call<DeleteUserWishList4C> deleteUserWishList4CCallbackByPost = ZRNetManager.getInstance().deleteUserWishList4CCallbackByPost(1);
                deleteUserWishList4CCallbackByPost.enqueue(new ResponseCallBack<DeleteUserWishList4C>(DeleteUserWishList4C.class) {
                    @Override
                    public void onSuccess(DeleteUserWishList4C deleteUserWishList4C) {
                        showToast(deleteUserWishList4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_GetSurveyList: // 获取风险等级评测信息列表
                Call<GetSurveyList4C> getSurveyList4CCall = ZRNetManager.getInstance().getSurveyList4CCallbackByPost();
                getSurveyList4CCall.enqueue(new ResponseCallBack<GetSurveyList4C>(GetSurveyList4C.class) {
                    @Override
                    public void onSuccess(GetSurveyList4C getSurveyList4C) {
                        showToast(getSurveyList4C.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_EvaluateUserRiskLevel: // 评估用户风险等级
                Call<EvaluateUserRiskLevel> evaluateUserRiskLevelCall = ZRNetManager.getInstance().evaluateUserRiskLevelCallbackByPost("1");
                evaluateUserRiskLevelCall.enqueue(new ResponseCallBack<EvaluateUserRiskLevel>(EvaluateUserRiskLevel.class) {
                    @Override
                    public void onSuccess(EvaluateUserRiskLevel evaluateUserRiskLevel) {
                        showToast(evaluateUserRiskLevel.toString());
                    }

                    @Override
                    public void onError(String code, String msg) {
                        showToast(msg);
                    }
                });
                break;
            case R.id.btn_PrepareChangeTransPwd: // 修改交易密码前，对用户身份进行验证
                Call<PrepareChangeTransPwd> prepareChangeTransPwdCall = ZRNetManager.getInstance().prepareChangeTransPwdCallbackByPost("", identityNo, "", phone);
                prepareChangeTransPwdCall.enqueue(new ResponseCallBack<PrepareChangeTransPwd>(PrepareChangeTransPwd.class) {
                    @Override
                    public void onSuccess(PrepareChangeTransPwd prepareChangeTransPwd) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_ChangeTransPwd: // 修改交易密码
                Call<ChangeTransPwd> changeTransPwdCall = ZRNetManager.getInstance().changeTransPwdCallbackByPost(phone, vCode, password);
                changeTransPwdCall.enqueue(new ResponseCallBack<ChangeTransPwd>(ChangeTransPwd.class) {
                    @Override
                    public void onSuccess(ChangeTransPwd changeTransPwd) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_ResetLoginPwd: // 重置登录密码
                Call<ResetLoginPwd> resetLoginPwdCall = ZRNetManager.getInstance().resetLoginPwdCallbackByPost(phone, vCode, password);
                resetLoginPwdCall.enqueue(new ResponseCallBack<ResetLoginPwd>(ResetLoginPwd.class) {
                    @Override
                    public void onSuccess(ResetLoginPwd resetLoginPwd) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_GetLoginPwdVcode: // 重置登录密码验证码
                Call<GetLoginPwdVcode> getLoginPwdVcodeCall = ZRNetManager.getInstance().getLoginPwdVcodeCallbackByPost(phone);
                getLoginPwdVcodeCall.enqueue(new ResponseCallBack<GetLoginPwdVcode>(GetLoginPwdVcode.class) {
                    @Override
                    public void onSuccess(GetLoginPwdVcode getLoginPwdVcode) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_Logoff: // 用户退出
                Call<Logoff> logoffCall = ZRNetManager.getInstance().logoffCallbackByPost();
                logoffCall.enqueue(new ResponseCallBack<Logoff>(Logoff.class) {
                    @Override
                    public void onSuccess(Logoff logoff) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_UpdateUserInfo: // 更新用户信息
                Call<UpdateUserInfo> updateUserInfoCall = ZRNetManager.getInstance().updateUserInfoCallbackByPost("", "");
                updateUserInfoCall.enqueue(new ResponseCallBack<UpdateUserInfo>(UpdateUserInfo.class) {
                    @Override
                    public void onSuccess(UpdateUserInfo updateUserInfo) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_GetUserInfo4C: // 获取用户信息
                Call<GetUserInfo4C> getUserInfo4CCall = ZRNetManager.getInstance().getUserInfo4CCallbackByPost("");
                getUserInfo4CCall.enqueue(new ResponseCallBack<GetUserInfo4C>(GetUserInfo4C.class) {
                    @Override
                    public void onSuccess(GetUserInfo4C getUserInfo4C) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_GetUserCollectionList4C: // 获取用户收藏列表
                Call<GetUserCollectionList4C> getUserCollectionList4CCall = ZRNetManager.getInstance().getUserCollectionList4CCallbackByPost("1");
                getUserCollectionList4CCall.enqueue(new ResponseCallBack<GetUserCollectionList4C>(GetUserCollectionList4C.class) {
                    @Override
                    public void onSuccess(GetUserCollectionList4C getUserCollectionList4C) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_GetUserMainPageInfo4C: // 获取我的主页信息
                Call<GetUserMainPageInfo4C> getUserMainPageInfo4CCall = ZRNetManager.getInstance().getUserMainPageInfo4CCallbackByPost();
                getUserMainPageInfo4CCall.enqueue(new ResponseCallBack<GetUserMainPageInfo4C>(GetUserMainPageInfo4C.class) {
                    @Override
                    public void onSuccess(GetUserMainPageInfo4C getUserMainPageInfo4C) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_GetUserTradeHistory4C: // 获取用户组合交易历史记录
                Call<GetUserTradeHistory4C> getUserTradeHistory4CCall = ZRNetManager.getInstance().getUserTradeHistory4CCallbackByPost();
                getUserTradeHistory4CCall.enqueue(new ResponseCallBack<GetUserTradeHistory4C>(GetUserTradeHistory4C.class) {
                    @Override
                    public void onSuccess(GetUserTradeHistory4C getUserTradeHistory4C) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
            case R.id.btn_CreateShareAlbum: // 创建分享相册
                Call<CreateShareAlbum> createShareAlbumCall = ZRNetManager.getInstance().createShareAlbumCallbackByPost("");
                createShareAlbumCall.enqueue(new ResponseCallBack<CreateShareAlbum>(CreateShareAlbum.class) {
                    @Override
                    public void onSuccess(CreateShareAlbum createShareAlbum) {

                    }

                    @Override
                    public void onError(String code, String msg) {

                    }
                });
                break;
        }
    }

    /**
     * 从相册获取返回
     *
     * @param path
     */
    @Override
    protected void onGalleryComplete(String path) {
        super.onGalleryComplete(path);
        uploadImage(path);
    }

    /**
     * 上传图片
     *
     * @param path
     */
    private void uploadImage(String path) {
        String descriptionString = "hello, this is description speaking";

        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file); // multipart/form-data // image/*
        Map<String, RequestBody> params = new HashMap<>();
        params.put("sid", toRequestBody(ZRDeviceInfo.getSid()));
        params.put("deviceid", toRequestBody(ZRDeviceInfo.getDeviceID()));
        params.put("rid", toRequestBody(ZRDeviceInfo.getRid()));
        params.put("pathKey", toRequestBody("Upload_File_EDU"));
        params.put("file\"; filename=\"image.png\"" + file.getName(), requestBody); // uploadImages
        /*Call<ResponseBody> call = ZRRetrofit.getFileUploadApiInstance().uploadAvatar(
                "http://172.16.101.52:9007/uploadFile"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , "Upload_File_EDU"
                , file.getName()
                , requestBody);*/
        Call<ResponseBody> call = ZRRetrofit.getFileUploadApiInstance().uploadAvatar(
                "http://172.16.101.201:9006/uploadFile"
                , params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.v("Upload", response.message());
                    Log.i("Upload", response.toString());
                    Log.i("Upload", response.body().string());
                    Log.v("Upload", "success");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload", t.toString());
            }
        });
        /*call.enqueue(new ResponseCallBack<ResponseBody>(ResponseBody.class) {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    Log.v("Upload", responseBody.string());
                    Log.v("Upload", "success");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String code, String msg) {
                Log.e("Upload", msg);
            }
        });*/
        /*call.enqueue(new ResponseCallBack<String>(String.class) {
            @Override
            public void onSuccess(String s) {
                Log.v("Upload", s);
                Log.v("Upload", "success");
            }

            @Override
            public void onError(String code, String msg) {
                Log.e("Upload", msg);
            }
        });*/
        /*call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("Upload", response.message());
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Upload", t.toString());
            }
        });*/
    }

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    static class RecordGrowthDialog extends Dialog {

        @Bind(R.id.img_cancle)
        ImageView imgCancle;

        @Bind(R.id.img_avatar)
        CircleImageView imgAvatar;

        @Bind(R.id.edt_description)
        EditText edtDescription;

        @Bind(R.id.tv_money)
        TextView tvMoney;

        @Bind(R.id.btn_complete)
        Button btnComplete;

        private Context mContext;

        public RecordGrowthDialog(Context context) {
            super(context);
            this.mContext = context;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.view_record_growth);
            ButterKnife.bind(this);

            // 创建一个头像
            ZRCircleImageView avatarmageView = new ZRCircleImageView(mContext);
            avatarmageView.setImageResource(R.mipmap.icon_user);

            Window window = getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.getDecorView().setPadding(0, 0, 0 ,0);
            WindowManager windowManager = window.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams windowparams = window.getAttributes();
            windowparams.width = display.getWidth(); // 设置dialog的宽度为当前手机屏幕的宽度
            window.setWindowAnimations(R.style.animationBottomTranslate);
            window.setBackgroundDrawableResource(R.color.transparent);
            window.setAttributes(windowparams);

            imgAvatar.setImageResource(R.mipmap.ic_img_profile_bg);

            edtDescription.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String description = s.toString();
                    btnComplete.setEnabled(!TextUtils.isEmpty(description));
                }
            });
        }

        @OnClick({R.id.img_cancle, R.id.ll_show_save_money, R.id.btn_complete})
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.img_cancle: // 关闭
                    dismiss();
                    break;
                case R.id.ll_show_save_money: // 走基金申购流程
                    // 世界申购
                    intent.setClass(getContext(), ZRActivityMultiFundApplyPurchase.class);
                    Bundle bundle = new Bundle();
                    GetFundPoList4C.FundPoList pro = new GetFundPoList4C().new FundPoList();
                    pro.poBase = new GetFundPoList4C().new PoBase();
                    bundle.putSerializable("GetFundPoList4C.FundPoList", pro);
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
                    break;
                case R.id.btn_complete: // 保存图片到服务端
                    Toast.makeText(getContext(), "Coding中", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private ArrayList<String> mSelectPath = new ArrayList<String>();
    private void openImageSelector() {
        // 外部图片资源
        ArrayList<String> externalList = new ArrayList<>();
        externalList.add("http://scimg.jb51.net/allimg/160813/103-160Q3143110P5.jpg");
        externalList.add("http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg");
        externalList.add("http://img2.imgtn.bdimg.com/it/u=1509312158,1202655144&fm=21&gp=0.jpg");
        externalList.add("http://pic24.nipic.com/20121029/5056611_120019351000_2.jpg");
        externalList.add("http://www.pptbz.com/pptpic/uploadfiles_6909/201202/2012022917310499.jpg");
        externalList.add("http://pic14.nipic.com/20110610/7181928_110502231129_2.jpg");
        externalList.add("http://img.taopic.com/uploads/allimg/120423/107913-12042323220753.jpg");
        externalList.add("http://pic51.nipic.com/file/20141016/24066_130156779281_2.jpg");
        externalList.add("http://www.xxjxsj.cn/article/uploadpic/2012-4/201241221251481736.jpg");
        externalList.add("http://pic4.nipic.com/20090910/2302530_144753008092_2.jpg");
        externalList.add("http://img102.mypsd.com.cn/20120929/1/Mypsd_13953_201209291653040031B.jpg");
        Intent intent = new Intent();
        int selectedMode = ZRActivityGenerateAlbum.MODE_MULTI;
        int maxNum = externalList.size(); // 最大可选择图片的数量
        intent.setClass(mContext, ZRActivityGenerateAlbum.class);
        // 是否显示拍摄图片
        intent.putExtra(
                ZRActivityGenerateAlbum.EXTRA_SHOW_CAMERA, false);
        // 最大可选择图片数量
        intent.putExtra(
                ZRActivityGenerateAlbum.EXTRA_SELECT_COUNT,
                maxNum);
        // 选择模式
        intent.putExtra(
                ZRActivityGenerateAlbum.EXTRA_SELECT_MODE,
                selectedMode);
        // 默认选择
        if (mSelectPath != null && mSelectPath.size() > 0) {
            intent.putExtra(
                    ZRActivityGenerateAlbum.EXTRA_DEFAULT_SELECTED_LIST,
                    mSelectPath);
        }
//        intent.putExtra(
//                ZRActivityGenerateAlbum.EXTRA_EXTERNAL_LIST,
//                externalList);
        startActivityForResult(intent,
                ZRConstant.ACTIVITY_REQUEST_TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ZRConstant.ACTIVITY_REQUEST_TAKE_PICTURE:
                    mSelectPath = data
                            .getStringArrayListExtra(ZRActivityGenerateAlbum.EXTRA_RESULT);
                    break;
            }
        }
    }
}
