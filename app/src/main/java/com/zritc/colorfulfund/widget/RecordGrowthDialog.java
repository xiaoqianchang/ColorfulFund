package com.zritc.colorfulfund.widget;

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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.activity.wish.ZRActivityWishHomePage;
import com.zritc.colorfulfund.data.response.edu.CreateGrowingRecord;
import com.zritc.colorfulfund.data.response.trade.GetFundPoList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.ui.ZRCircleImageView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 记录成长
 * <p>
 * Created by Chang.Xiao on 2016/9/10.
 *
 * @version 1.0
 */
public class RecordGrowthDialog extends Dialog {

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
    private String avatar;
    private String poCode;
    private String amount;
    private String sceneId; // 场景ID

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

        imgAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        amount = "2000";
        tvMoney.setText(String.format("%s元", amount));
        if (!TextUtils.isEmpty(avatar)) {
            Picasso.with(mContext).load(avatar).placeholder(R.mipmap.icon_header).into(imgAvatar);
        } else {
            Picasso.with(mContext).load(R.mipmap.icon_header).into(imgAvatar);
        }

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
//                GetFundPoList4C.FundPoList pro = new GetFundPoList4C().new FundPoList();
//                pro.poBase = new GetFundPoList4C().new PoBase();
//                bundle.putSerializable("GetFundPoList4C.FundPoList", pro);
                bundle.putString(ZRConstant.INTENT_FROM_WHERE, RecordGrowthDialog.class.getName());
                bundle.putString("poCode", "ZH000487"); // poCode
                bundle.putString("money", amount);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
                break;
            case R.id.btn_complete: // 保存图片到服务端
                // 创建成长记录
                doCreateGrowingRecord("26", avatar, amount, edtDescription.getText().toString());
                break;
        }
    }

    private void doCreateGrowingRecord(String planId, String photo, String investAmount, String description) {
        Call<CreateGrowingRecord> createGrowingRecordCall = ZRNetManager.getInstance().createGrowingRecordCallbackByPost(planId, photo, investAmount, description);
        createGrowingRecordCall.enqueue(new ResponseCallBack<CreateGrowingRecord>(CreateGrowingRecord.class) {
            @Override
            public void onSuccess(CreateGrowingRecord createGrowingRecord) {
                dismiss();
            }

            @Override
            public void onError(String code, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setImgAvatar(String imgUrl) { // 172.16.101.201:9006/
        if (!ZRUtils.isUrl(imgUrl)) { // http://172.16.101.202/
            imgUrl = "http://172.16.101.202/" + imgUrl;
        }
        this.avatar = imgUrl;
    }

    public void setPoCode(String poCode) {
        this.poCode = poCode;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }
}
