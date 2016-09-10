package com.zritc.colorfulfund.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fund.ZRActivityFundList;
import com.zritc.colorfulfund.activity.fund.ZRActivityGroupRedemption;
import com.zritc.colorfulfund.activity.fund.ZRActivitySingleRedemption;
import com.zritc.colorfulfund.activity.wish.ZRWishHomePage;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.circle.GetCommentList4C;
import com.zritc.colorfulfund.data.response.circle.GetPostInfo4C;
import com.zritc.colorfulfund.data.response.circle.GetPostList4C;
import com.zritc.colorfulfund.data.response.circle.CreateCollection;
import com.zritc.colorfulfund.data.response.circle.CreateComment;
import com.zritc.colorfulfund.data.response.circle.CreatePost;
import com.zritc.colorfulfund.data.response.circle.CreateReport;
import com.zritc.colorfulfund.data.response.circle.CreateThumb;
import com.zritc.colorfulfund.data.response.circle.GetCommentList4C;
import com.zritc.colorfulfund.data.response.circle.GetPostList4C;
import com.zritc.colorfulfund.data.response.circle.ReadPost;
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
import com.zritc.colorfulfund.ui.ZRCircleImageView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;
import com.zritc.colorfulfund.widget.CircleImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
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

    @OnClick({R.id.btn_prepareRegisterAcc, R.id.btn_registerAcc, R.id.btn_login, R.id.btn_prepare_bind_payment,
            R.id.btn_bind_payment, R.id.btn_unbind_payment, R.id.btn_setTransPwd, R.id.btn_group_redemption,
            R.id.btn_buy_po, R.id.btn_user_bank_cards4C, R.id.btn_user_po_list4C, R.id.btn_user_po_info4C,
            R.id.btn_fund_po_list4C, R.id.btn_fund_po_info4C, R.id.btn_single_redemption, R.id.btn_estimateBuyFundFee,
            R.id.btn_article_details, R.id.btn_video_details, R.id.btn_call_camera, R.id.btn_record_growth,
            R.id.btn_generate_album, R.id.btn_wish_home_page,
            R.id.btn_create_post, R.id.btn_create_comment, R.id.btn_create_thumb, R.id.btn_create_collection,
            R.id.btn_create_report, R.id.btn_read_post, R.id.btn_Post_info, R.id.btn_Post_list, R.id.btn_Comment_list})
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
            case R.id.btn_wish_home_page: // 心愿首页
                startActivity(new Intent(this, ZRWishHomePage.class));
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
        }
    }

    static class RecordGrowthDialog extends Dialog {

        @Bind(R.id.img_cancle)
        ImageView imgCancle;

        @Bind(R.id.img_avatar)
        CircleImageView imgAvatar;

        @Bind(R.id.edt_description)
        EditText edtDescription;

        @Bind(R.id.ll_save_money)
        LinearLayout llShowSaveMoney;

        @Bind(R.id.edt_money)
        EditText edtMoney;

        @Bind(R.id.btn_save_money)
        Button btnsavemoney;

        @Bind(R.id.btn_complete)
        Button btnComplete;

        private Context mContext;
        private boolean isShowSaveMoney = false;

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

            edtMoney.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String money = s.toString();
                    btnsavemoney.setEnabled(!TextUtils.isEmpty(money));
                }
            });
        }

        @OnClick({R.id.img_cancle, R.id.ll_show_save_money, R.id.btn_save_money, R.id.btn_complete})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_cancle: // 关闭
                    dismiss();
                    break;
                case R.id.ll_show_save_money:
                    if (isShowSaveMoney) {
                        llShowSaveMoney.setVisibility(View.GONE);
                        isShowSaveMoney = false;
                    } else {
                        llShowSaveMoney.setVisibility(View.VISIBLE);
                        isShowSaveMoney = true;
                    }
                    break;
                case R.id.btn_save_money: // 走基金申购流程
                    break;
                case R.id.btn_complete: // 保存图片到服务端
                    break;
            }
        }
    }

    private ArrayList<String> mSelectPath = new ArrayList<String>();
    private void openImageSelector() {
        Intent intent = new Intent();
        int selectedMode = ZRActivityGenerateAlbum.MODE_MULTI;
        int maxNum = 10; // 最大可选择图片的数量
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
        intent.putExtra(
                ZRActivityGenerateAlbum.EXTRA_EXTERNAL_LIST,
                externalList);
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
