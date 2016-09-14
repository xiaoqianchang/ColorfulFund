package com.zritc.colorfulfund.widget;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.ImageButton;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.presenter.FortuneGroupCommentListPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 投资圈评论弹出框口
 */
public class FortuneGroupCommentDialog extends Dialog {

    @Bind(R.id.edt_comment)
    EditText editComment;

    @Bind(R.id.btn_back)
    ImageButton btnCancel;

    @Bind(R.id.btn_ok)
    Button btnOk;

    private Context mContext;
    private FortuneGroupCommentListPresenter fortuneGroupCommentListPresenter;
    private String postId;

    public FortuneGroupCommentDialog(Context context, FortuneGroupCommentListPresenter fortuneGroupCommentListPresenter, String postId) {
        super(context);
        this.mContext = context;
        this.fortuneGroupCommentListPresenter = fortuneGroupCommentListPresenter;
        this.postId = postId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_sendcomment_pop);
        ButterKnife.bind(this);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.width = display.getWidth(); // 设置dialog的宽度为当前手机屏幕的宽度
        window.setWindowAnimations(R.style.animationBottomTranslate);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.setAttributes(windowparams);

        editComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String comment = s.toString();
                btnOk.setEnabled(!TextUtils.isEmpty(comment));
            }
        });
    }

    @OnClick({R.id.btn_back, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back: // 关闭
                dismiss();
                break;
            case R.id.btn_ok:
                fortuneGroupCommentListPresenter.sendComment(postId, editComment.getText().toString());
                break;
        }
    }
}
