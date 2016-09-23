package com.zritc.colorfulfund.activity.mine;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;

import butterknife.Bind;

/**
 * 帮助界面
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public class ZRActivityHelp extends ZRActivityToolBar {

    @Bind(R.id.browser_wv)
    WebView mBrowserWv;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_help;
    }

    @Override
    protected void initPresenter() {
        setTitleText("帮助");
        WebSettings webSettings = mBrowserWv.getSettings();

        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAppCachePath(this.getCacheDir().getAbsolutePath());
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        // 允许执行javascript语句
        webSettings.setJavaScriptEnabled(true);
        // html页面大小自适应
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setDomStorageEnabled(true);

        mBrowserWv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebView.HitTestResult hit = view.getHitTestResult();
                // 视频点击：midasapp://goVideoDetail?m?videoPK
                // 图片放大：midasapp://showImageNative?m?ImgTag
                // PDF阅读：midasapp://loadPDFDocument?m?pdfURL
                if (hit != null) {
                    return true;
                } else {
                    return true;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
        mBrowserWv.loadUrl("https://www.baidu.com/");
    }
}
