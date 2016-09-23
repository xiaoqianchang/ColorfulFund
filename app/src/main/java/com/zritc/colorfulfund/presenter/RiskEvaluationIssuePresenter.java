package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.mine.RiskEvaluationIssue;
import com.zritc.colorfulfund.data.response.mine.GetSurveyList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IRiskEvaluationIssueView;

import java.util.List;

import retrofit2.Call;

/**
 * 风险评估问题
 * <p>
 * Created by Chang.Xiao on 2016/9/21.
 *
 * @version 1.0
 */
public class RiskEvaluationIssuePresenter extends BasePresenter<IRiskEvaluationIssueView> {

    private RiskEvaluationIssue riskEvaluationIssue;

    public RiskEvaluationIssuePresenter(Context context, IRiskEvaluationIssueView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取数据
     */
    public void getSurveyList() {
        Call<GetSurveyList4C> getSurveyList4CCall = ZRNetManager.getInstance().getSurveyList4CCallbackByPost();
        getSurveyList4CCall.enqueue(new ResponseCallBack<GetSurveyList4C>(GetSurveyList4C.class) {
            @Override
            public void onSuccess(GetSurveyList4C getSurveyList4C) {
                dataConverter(getSurveyList4C);
                iView.onSuccess(riskEvaluationIssue);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    /**
     * 网络模型数据转换器
     */
    private void dataConverter(GetSurveyList4C getSurveyList4C) {
        /*riskEvaluationIssue = new RiskEvaluationIssue();

        for (int i = 0; i < 6; i++) {
            RiskEvaluationIssue.Issue issue = new RiskEvaluationIssue().new Issue();
            issue.issueName = "您目前的人生阶段" + i;
            for (int j = 0; j < 5; j++) {
                issue.answerList.add("单身期" + j);
            }
            riskEvaluationIssue.issueList.add(issue);
        }
        riskEvaluationIssue.issueNum = String.valueOf(riskEvaluationIssue.issueList.size());*/

        riskEvaluationIssue = new RiskEvaluationIssue();
        List<GetSurveyList4C.SurveyQuestionList> surveyQuestionList = getSurveyList4C.surveyQuestionList;
        if (null != surveyQuestionList && surveyQuestionList.size() > 0) {
            for (GetSurveyList4C.SurveyQuestionList surveyQuestion : surveyQuestionList) {
                RiskEvaluationIssue.Issue issue = new RiskEvaluationIssue().new Issue();
                issue.issueName = surveyQuestion.questionDesc;
                issue.issueId = String.valueOf(surveyQuestion.questionId);
                for (GetSurveyList4C.Answers answers : surveyQuestion.answers) {
                    RiskEvaluationIssue.Answer answer = new RiskEvaluationIssue().new Answer();
                    answer.answerId = answers.answerId;
                    answer.answerDesc = answers.answerDesc;
                    issue.answerList.add(answer);
                }
                riskEvaluationIssue.issueList.add(issue);
            }
        }
        riskEvaluationIssue.issueNum = String.valueOf(riskEvaluationIssue.issueList.size());
    }
}
