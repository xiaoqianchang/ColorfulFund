package com.zritc.colorfulfund.iView;

/**
 * 风险评测结果
 * <p>
 * Created by Chang.Xiao on 2016/9/22.
 *
 * @version 1.0
 */
public interface IRiskEvaluationIssueCompleteView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
