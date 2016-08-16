package com.zritc.colorfulfund.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshBase;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshBase.OnRefreshListener;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshWebView;
import com.zritc.colorfulfund.utils.ZRUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * webview
 *
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-27
 * @lastUpdate 2015-11-27
 */
public class ZRActivityPullToRefreshWebView extends ZRActivityBase {

    private WebView mWebView;
    private String mSourceUrl = "https://www.baidu.com/";

    @Bind(R.id.pull_refresh_webview)
    ZRPullToRefreshWebView mPullWebView;
    @Bind(R.id.img_back)
    ImageView imgBack;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initPresenter() {
        initViews();
    }

    @SuppressLint("NewApi")
    private void initViews() {
        mPullWebView.setOnRefreshListener(new OnRefreshListener<WebView>() {

            @Override
            public void onPullDownToRefresh(
                    ZRPullToRefreshBase<WebView> refreshView) {
                mWebView.loadUrl(mSourceUrl);
            }

            @Override
            public void onPullUpToRefresh(
                    ZRPullToRefreshBase<WebView> refreshView) {
            }
        });

        mWebView = mPullWebView.getRefreshableView();
		mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mPullWebView.onPullDownRefreshComplete();
                setLastUpdateTime();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.i("====>html error======", errorCode + "");
            }
		});
        // mWebView.getSettings().setSupportZoom(true);
        // mWebView.getSettings().setBuiltInZoomControls(true);
        // WebSettings settings = mWebView.getSettings();
        // settings.setUseWideViewPort(true);
        // settings.setLoadWithOverviewMode(true);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.requestFocus();
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.loadUrl(mSourceUrl);
//		mWebView.setDownloadListener(new DownloadListener() {  
//            @Override
//            public void onDownloadStart(String url, String userAgent,  
//                    String contentDisposition, String mimetype,long contentLength) {  
//                 System.out.println("=========>开始下载 url =" + url);  
//                 Uri uri = Uri.parse(url);     
//                 Intent intent = new Intent(Intent.ACTION_VIEW, uri);     
//                 startActivity(intent);  
//                 finish();
//            }  
//        });  
        setLastUpdateTime();
    }

    @OnClick(R.id.img_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void setLastUpdateTime() {
        mPullWebView.setLastUpdatedLabel(ZRUtils.getCurrentTime("MM-dd HH:mm"));
    }
}
