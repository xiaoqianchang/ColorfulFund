package com.zritc.colorfulfund.activity.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.model.file.UploadFile;
import com.zritc.colorfulfund.data.model.mine.PersonalInfo;
import com.zritc.colorfulfund.data.response.user.Logoff;
import com.zritc.colorfulfund.data.response.user.UpdateUserInfo;
import com.zritc.colorfulfund.iView.ISettingView;
import com.zritc.colorfulfund.presenter.SettingPresenter;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 设置界面
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public class ZRActivitySetting extends ZRActivityToolBar<SettingPresenter> implements ISettingView {

    @Bind(R.id.img_head_icon)
    ImageView imgHeadIcon;

    @Bind(R.id.tv_nickname)
    TextView tvNickname;

    private SettingPresenter presenter;
    private PersonalInfo personalInfo; // 个人信息
    private String userId; // 用户id
    private String password;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initPresenter() {
        presenter = new SettingPresenter(this, this);
        presenter.init();
        // 获取数据
        presenter.doGetUserInfo(userId);
    }

    @Override
    public void initView() {
        setTitleText("个人资料");
        getExtraData();
        refreshView();
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            PersonalInfo personalInfo = (PersonalInfo) bundle.getSerializable("personalInfo");
            if (null != personalInfo) {
                userId = personalInfo.userId;
            }
        }
    }

    @OnClick({R.id.img_head_icon, R.id.rl_modify_password, R.id.rl_reset_trade_password, R.id.rl_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_head_icon: // 头像
                openPhotoPicker(this);
                break;
            case R.id.rl_modify_password: // 修改登录密码
                presenter.doModifyPassword(password);
                break;
            case R.id.rl_reset_trade_password: // 重置交易密码
                presenter.doResetTradePassword(password);
                break;
            case R.id.rl_logout: // 退出登录
                // 创建退出对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // 设置对话框标题
                builder.setTitle("退出登录");
                // 设置对话框消息
                builder.setMessage("退出登录后将无法收到APP推送通知，推人退出吗？");
                builder.setCancelable(true);
                // 添加选择按钮并注册监听
                builder.setPositiveButton("确定", (DialogInterface dialog, int which) -> presenter.doLogout());
                builder.setNegativeButton("取消", (DialogInterface dialog, int which) -> {});
                // 显示对话框
                builder.show();
                break;
        }
    }

    private void refreshView() {
        if (null != personalInfo) {
            if (!TextUtils.isEmpty(personalInfo.avatar)) {
                Picasso.with(mContext).load("http://172.16.101.202/" + personalInfo.avatar).placeholder(R.mipmap.icon_header).into(imgHeadIcon);
            } else {
                Picasso.with(mContext).load(R.mipmap.icon_header).into(imgHeadIcon);
            }
            tvNickname.setText(personalInfo.nickName);
        }
    }

    @Override
    public void showProgress(CharSequence message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof PersonalInfo) { // 获取用户信息
            personalInfo = (PersonalInfo) object;
            refreshView();
        } else if (object instanceof UploadFile) { // 上传头像
            personalInfo.avatar = ((UploadFile) object).filePath;
            // 更新用户信息
            presenter.doUpdateUserInfo(personalInfo.nickName, personalInfo.avatar);
        } else if (object instanceof UpdateUserInfo) { // 更新用户信息
            refreshView();
        } else if (object instanceof Logoff) { // 退出
            Logoff logoff = (Logoff) object;
            showToast(logoff.msg);
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    /**
     * 从相册获取返回
     *
     * @param path
     */
    @Override
    protected void onGalleryComplete(String path) {
        super.onGalleryComplete(path);
        if (!TextUtils.isEmpty(path))
            presenter.uploadImage(path);
    }

    /**
     * 从拍照获取返回
     *
     * @param captureFile
     */
    @Override
    protected void onCaptureComplete(File captureFile) {
        super.onCaptureComplete(captureFile);
        if (null != captureFile)
            presenter.uploadFile(captureFile);
    }
}
