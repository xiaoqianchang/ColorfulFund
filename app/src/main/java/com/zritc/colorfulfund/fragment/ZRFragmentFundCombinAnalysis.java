package com.zritc.colorfulfund.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fund.ZRActivityGroupRedemption;
import com.zritc.colorfulfund.activity.fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.iView.IFundCombinAnalysis;
import com.zritc.colorfulfund.presenter.FundCombinAnalysisPresenter;

import butterknife.Bind;

/**
 * ZRFragmentFundCombinAnalysis 基金组合分析
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-09-22
 * @lastUpdate 2016-09-22
 */
public class ZRFragmentFundCombinAnalysis extends ZRFragmentBase<FundCombinAnalysisPresenter> implements IFundCombinAnalysis {
    private static final String ARG_PARAM = "param";

    @Bind(R.id.webview)
    WebView webView;

    private FundCombinAnalysisPresenter fundCombinAnalysisPresenter;

    public static ZRFragmentFundCombinAnalysis newInstance(String param) {
        ZRFragmentFundCombinAnalysis fragment = new ZRFragmentFundCombinAnalysis();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getExtraArguments() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_fund_combin_analysis;
    }

    @Override
    protected void initPresenter() {
        fundCombinAnalysisPresenter = new FundCombinAnalysisPresenter(getActivity(), this);
        fundCombinAnalysisPresenter.init();
    }

    @Override
    public void initView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebView.HitTestResult hit = view.getHitTestResult();
                // 申购：midasapp://multiFundApplyPurchase?m?poCode=ZH000484
                // 赎回：midasapp://groupRedemption?m?poCode=ZH000484
                if (hit != null) {
                    Intent intent = new Intent();
                    String[] strArray = url.split("[?]");
                    if (strArray[0].contains("MultiFundApplyPurchase")) {
                        intent.setClass(getActivity(), ZRActivityMultiFundApplyPurchase.class);
                        intent.putExtra("poCode", "ZH000484");
                        intent.putExtra("money", "10000");
                        startMenuActivity(intent);
                    } else if (strArray[0].contains("GroupRedemption")) {
                        intent.setClass(getActivity(), ZRActivityGroupRedemption.class);
                        intent.putExtra("poCode", "ZH000484");
                        startMenuActivity(intent);
                    }
                    return true;
                } else {
                    return true;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");

        // 用JavaScript调用Android函数：
        // 先建立桥梁类，将要调用的Android代码写入桥梁类的public函数
        // 绑定桥梁类和WebView中运行的JavaScript代码
        // 将一个对象起一个别名传入，在JS代码中用这个别名代替这个对象，可以调用这个对象的一些方法
        webView.addJavascriptInterface(new WebAppInterface(getActivity()),
                "AndroidInterface");
        webView.loadUrl("https://www.baidu.com/");

    }

    /**
     * 自定义的Android代码和JavaScript代码之间的桥梁类
     *
     */
    public class WebAppInterface {
        Context context;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            context = c;
        }

        /**
         * Show a toast from the web page
         */
        // 如果target 大于等于API 17，则需要加上如下注解
        @JavascriptInterface
        public void returnFundInfo2App(String array) {

        }
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(String msg) {

    }
}
