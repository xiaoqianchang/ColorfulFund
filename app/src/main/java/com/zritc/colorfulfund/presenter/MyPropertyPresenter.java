package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.mine.MyProperty;
import com.zritc.colorfulfund.iView.IMyPropertyView;

/**
 * 我的资产presenter
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class MyPropertyPresenter extends BasePresenter<IMyPropertyView> {

    private MyProperty myProperty;

    public MyPropertyPresenter(Context context, IMyPropertyView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取数据
     */
    public void doGetData() {
        dataConverter();
        iView.onSuccess(myProperty);
    }

    /**
     * 网络模型数据转换器
     */
    private void dataConverter() {
        /*myProperty = new MyProperty();
        myProperty.totalIncome = "5000.00";
        myProperty.propertyTotalAmount = "10000.00";
        for (int i = 0; i < 10; i++) {
            MyProperty.Property property = new MyProperty().new Property();
            property.poName = "长城宽带" + i;
            property.balance = i + "000";
            property.income = i + "100";
            myProperty.propertyList.add(property);
        }*/
    }
}
