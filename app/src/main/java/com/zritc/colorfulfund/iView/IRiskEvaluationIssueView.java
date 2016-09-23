package com.zritc.colorfulfund.iView;

/**
 * 风险评估问题
 * <p>
 * Created by Chang.Xiao on 2016/9/21.
 *
 * @version 1.0
 */
public interface IRiskEvaluationIssueView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
