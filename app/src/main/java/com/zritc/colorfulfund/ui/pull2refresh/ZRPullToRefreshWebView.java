package com.zritc.colorfulfund.ui.pull2refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 封装了WebView的下拉刷新
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRPullToRefreshWebView extends ZRPullToRefreshBase<WebView> {
    /**
     * 构造方法
     * 
     * @param context context
     */
    public ZRPullToRefreshWebView(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     */
    public ZRPullToRefreshWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     * @param defStyle defStyle
     */
    public ZRPullToRefreshWebView(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @see com.zritc.colorfulfund.ui.pull2refresh#createRefreshableView(Context,
     *      AttributeSet)
     */
    @Override
    protected WebView createRefreshableView(Context context, AttributeSet attrs) {
        WebView webView = new WebView(context);
        return webView;
    }

    /**
     * @see com.zritc.colorfulfund.ui.pull2refresh#isReadyForPullDown()
     */
    @Override
    protected boolean isReadyForPullDown() {
        return mRefreshableView.getScrollY() == 0;
    }

    /**
     * @see com.zritc.colorfulfund.ui.pull2refresh#isReadyForPullUp()
     */
    @Override
    protected boolean isReadyForPullUp() {
        float exactContentHeight = (float)Math.floor(mRefreshableView
                .getContentHeight() * mRefreshableView.getScale());
        return mRefreshableView.getScrollY() >= (exactContentHeight - mRefreshableView
                .getHeight());
    }
}
