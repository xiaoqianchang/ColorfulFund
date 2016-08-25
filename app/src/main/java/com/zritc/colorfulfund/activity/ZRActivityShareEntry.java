package com.zritc.colorfulfund.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.share.IUPShareRequestCallback;
import com.zritc.colorfulfund.share.UPMediaMessage;
import com.zritc.colorfulfund.share.UPShareErrors;
import com.zritc.colorfulfund.share.UPShareManager;
import com.zritc.colorfulfund.ui.ZRTextView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRLog;
import com.zritc.colorfulfund.utils.ZRStrings;
import com.zritc.colorfulfund.utils.ZRToastFactory;

/**
 * 分享入口
 *
 * @author Midas.
 */
public class ZRActivityShareEntry extends Activity {

    private Context mContext;
    private static final int SHARE_STATE_SHOW = 1;
    private static final int SHARE_STATE_HIDE = 2;
    private static final int SHARE_STATE_SNAPPING = 3;

    private static final int DURATION_SHARE_SHOW = 300;

    private View mShareContainer;
    private View mShareBackground;
    private View mShareContent;

    private AlphaAnimation mShareBackShowAnimation;
    private AlphaAnimation mShareBackHideAnimation;

    private TranslateAnimation mShareShowAnimation;
    private TranslateAnimation mShareHideAnimation;

    private int mShareState = SHARE_STATE_HIDE;

    private UPShareManager mShareManager = UPShareManager.getInstance(this);
    private UPMediaMessage mExtraMessage;

    private IUPShareRequestCallback mShareCallback = new IUPShareRequestCallback() {

        @Override
        public void onShareSucceed(int platform) {
            ZRToastFactory.getLongToast(ZRActivityShareEntry.this, R.string.share_back);
        }

        @Override
        public void onShareFailed(int platform, int errorCode, String errorMsg) {
            String platformName = "";
            switch (platform) {
                case UPShareManager.SHARE_WEIBO:
                    platformName = ZRStrings.get(mContext, "lable_sina");
                    break;
                case UPShareManager.SHARE_WEIXIN_GROUP:
                case UPShareManager.SHARE_WEIXIN_SINGLE:
                    platformName = ZRStrings.get(mContext, "lable_weixin");
                    break;
                case UPShareManager.SHARE_QQ:
                    platformName = ZRStrings.get(mContext, "lable_qq");
                    break;
                case UPShareManager.SHARE_SMS:
                    platformName = ZRStrings.get(mContext, "lable_sms");
                    break;
                case UPShareManager.SHARE_EMAIL:
                    platformName = ZRStrings.get(mContext, "lable_mail");
                    break;
            }
            if (UPShareErrors.ERROR_UNINSTALLED == errorCode) {
                ZRToastFactory.getLongToast(ZRActivityShareEntry.this, String.format(ZRStrings.get(mContext, "error_share_not_installed"), platformName));
            } else if (UPShareErrors.ERROR_UNSUPPORT == errorCode) {
                ZRToastFactory.getLongToast(ZRActivityShareEntry.this, String.format(ZRStrings.get(mContext, "error_share_not_support"), platformName));
            } else if (UPShareErrors.ERROR_UNKNOW == errorCode) {
                ZRToastFactory.getLongToast(ZRActivityShareEntry.this, String.format(ZRStrings.get(mContext, "error_share_unknown"), platformName));
            } else {
                ZRLog.d("share failed:errorCode=" + errorCode + ",errorMsg="
                        + errorMsg + ",platform=" + platform);
                ZRToastFactory.getLongToast(ZRActivityShareEntry.this, R.string.share_failed);
            }
        }

        @Override
        public void onShareCancelled(int platform) {
            ZRToastFactory.getLongToast(ZRActivityShareEntry.this, R.string.share_failed);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_share);
        mExtraMessage = (UPMediaMessage) getIntent().getSerializableExtra(
                ZRConstant.KEY_EXTRA_INTRO);
        mShareManager.initPlatform(UPShareManager.SHARE_WEIXIN_SINGLE,
                UPShareManager.SHARE_WEIXIN_ID);
        mShareManager.initPlatform(UPShareManager.SHARE_WEIBO,
                UPShareManager.SHARE_SINA_ID);
        mShareManager.initPlatform(UPShareManager.SHARE_QQ,
                UPShareManager.SHARE_QQ_ID);
        initShareView();
    }

    @Override
    public void onResume() {
        super.onResume();
        showShareView();
    }

    private void initShareView() {
        mShareContainer = findViewById(R.id.view_share_container);
        mShareBackground = findViewById(R.id.view_share_background);
        mShareBackground.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                hideShareView();
            }

        });
        mShareContent = findViewById(R.id.view_share);

        mShareBackShowAnimation = new AlphaAnimation(0f, 0.5f);
        mShareBackShowAnimation.setDuration(DURATION_SHARE_SHOW);
        mShareBackShowAnimation.setFillAfter(true);
        mShareBackShowAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mShareBackground.setVisibility(View.VISIBLE);
                mShareContent.setVisibility(View.VISIBLE);
                mShareState = SHARE_STATE_SHOW;
            }
        });

        mShareBackHideAnimation = new AlphaAnimation(0.5f, 0f);
        mShareBackHideAnimation.setDuration(DURATION_SHARE_SHOW);
        mShareBackHideAnimation.setFillAfter(true);
        mShareBackHideAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mShareContainer.setVisibility(View.GONE);
                mShareBackground.setVisibility(View.GONE);
                mShareContent.setVisibility(View.GONE);
                mShareState = SHARE_STATE_HIDE;
            }
        });

        mShareShowAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0);
        mShareShowAnimation.setDuration(DURATION_SHARE_SHOW);
        mShareShowAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mShareBackground.setVisibility(View.VISIBLE);
                mShareContent.setVisibility(View.VISIBLE);
                mShareState = SHARE_STATE_SHOW;
            }
        });

        mShareHideAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
        mShareHideAnimation.setDuration(DURATION_SHARE_SHOW);
        mShareHideAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mShareContainer.setVisibility(View.GONE);
                mShareBackground.setVisibility(View.GONE);
                mShareContent.setVisibility(View.GONE);
                mShareState = SHARE_STATE_HIDE;
            }
        });

        ZRTextView shareTitle = (ZRTextView) findViewById(R.id.text_share_title);
        shareTitle
                .setText(ZRStrings.get(
                        mContext, "lable_shareto"));

        findViewById(R.id.btn_share_cancel).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        hideShareView();
                    }
                });
        findViewById(R.id.btn_share_sms).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("smsto:"
                                + mExtraMessage.getSmsNumber());
                        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                        String sms = "";
                        try {
                            sms = mExtraMessage.getSMSDesc()
                                    .replaceAll("[$]", "&")
                                    .replaceAll("#", "=");
                        } catch (Exception e) {
                            e.printStackTrace();
                            sms = mExtraMessage.getSMSDesc();
                        }
                        intent.putExtra("sms_body", sms);
                        startActivity(intent);
                        setResult(RESULT_OK);
                        hideShareView();
                    }
                });
        findViewById(R.id.btn_share_weixin).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        UPMediaMessage msg = new UPMediaMessage();
                        msg.mTitle = mExtraMessage.getTitle();
                        msg.mDescription = mExtraMessage.getDescription();
                        msg.mUrl = mExtraMessage.getUrl();// 必须外网url，否则返回-4
                        msg.mImageUrl = mExtraMessage.getImageUrl();
                        msg.mThumbData = BitmapFactory.decodeResource(
                                getResources(), R.mipmap.icon_share_logo);//
                        // icon大小有限制，否则也不通过
                        mShareManager.share(UPShareManager.SHARE_WEIXIN_SINGLE,
                                mShareCallback, msg);
                        hideShareView();
                    }
                });
        findViewById(R.id.btn_share_pengyouquan).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        UPMediaMessage msg = new UPMediaMessage();
                        msg.mTitle = mExtraMessage.getTitle2();
                        msg.mDescription = mExtraMessage.getDescription();
                        msg.mUrl = mExtraMessage.getUrl();
                        msg.mImageUrl = mExtraMessage.getImageUrl();
                        msg.mThumbData = BitmapFactory.decodeResource(
                                getResources(), R.mipmap.icon_share_logo);
                        mShareManager.share(UPShareManager.SHARE_WEIXIN_GROUP,
                                mShareCallback, msg);
                        hideShareView();
                    }
                });
        findViewById(R.id.btn_share_weibo).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        UPMediaMessage msg = new UPMediaMessage();
                        msg.mTitle = mExtraMessage.getTitle();
                        msg.mDescription = mExtraMessage.getDescription();
                        msg.mUrl = mExtraMessage.getUrl();
                        msg.mImageUrl = mExtraMessage.getImageUrl();
                        msg.mThumbData = BitmapFactory.decodeResource(
                                getResources(), R.mipmap.icon_share_logo);
                        mShareManager.share(UPShareManager.SHARE_WEIBO,
                                mShareCallback, msg);
                        hideShareView();
                    }
                });
        findViewById(R.id.btn_share_qq).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        UPMediaMessage msg = new UPMediaMessage();
                        msg.mTitle = mExtraMessage.getTitle();
                        msg.mDescription = mExtraMessage.getDescription();
                        msg.mUrl = mExtraMessage.getUrl();
                        msg.mImageUrl = "http://www.midasjr.com/html/general/midas.png";//mExtraMessage.getImageUrl();
                        mShareManager.share(UPShareManager.SHARE_QQ,
                                mShareCallback, msg);
                        hideShareView();
                    }
                });
        findViewById(R.id.btn_share_email).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mShareManager.share(UPShareManager.SHARE_EMAIL,
                                mShareCallback, mExtraMessage.mSMSDesc);
                        hideShareView();
                    }
                });
    }

    private void showShareView() {
        if (SHARE_STATE_HIDE == mShareState) {
            mShareState = SHARE_STATE_SNAPPING;
            mShareContainer.setVisibility(View.VISIBLE);
            mShareBackground.setVisibility(View.INVISIBLE);
            mShareContent.setVisibility(View.INVISIBLE);
            mShareBackground.startAnimation(mShareBackShowAnimation);
            mShareContent.startAnimation(mShareShowAnimation);
        }
    }

    private void hideShareView() {
        if (SHARE_STATE_SHOW == mShareState) {
            mShareState = SHARE_STATE_SNAPPING;
            mShareBackground.setVisibility(View.INVISIBLE);
            mShareContent.setVisibility(View.INVISIBLE);
            mShareBackground.startAnimation(mShareBackHideAnimation);
            mShareContent.startAnimation(mShareHideAnimation);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        hideShareView();
    }

}
