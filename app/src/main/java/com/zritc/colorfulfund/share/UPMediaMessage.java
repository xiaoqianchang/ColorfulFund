package com.zritc.colorfulfund.share;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public class UPMediaMessage implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final int SHARE_FROM_INVITE_MEETING = 1;
    public static final int SHARE_FROM_APP = SHARE_FROM_INVITE_MEETING + 1;

    public String mAppName;
    public String mTitle;
    public String mSMSDesc;
    public String mDescription;
    public String mUrl;
    public String mText;
    public String mImageUrl;
    public Bitmap mThumbData;
    public String mSmsNumber = "";
    // extend data
    public String mVideoPk;
    public String mTitle2;//主要区分朋友圈和分享好友
    public int mShareFrom = SHARE_FROM_APP;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSMSDesc() {
        return TextUtils.isEmpty(mSMSDesc) ? mDescription : mSMSDesc;
    }

    public void setSMSDesc(String SMSDesc) {
        this.mSMSDesc = SMSDesc;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getImageUrl() {
            return mImageUrl;
    }

    public Bitmap getThumbData() {
        return mThumbData;
    }

    public String getSmsNumber() {
        return mSmsNumber;
    }

    public String getVideoPk() {
        return mVideoPk;
    }

    public void setVideoPk(String videopk) {
        this.mVideoPk = videopk;
    }

    public String getTitle2() {
        return TextUtils.isEmpty(mTitle2) ? mTitle : mTitle2;
    }

    public void setTitle2(String title2) {
        this.mTitle2 = title2;
    }

    public int getShareFrom() {
        return mShareFrom;
    }

    public void setShareFrom(int shareFrom) {
        this.mShareFrom = shareFrom;
    }
}
