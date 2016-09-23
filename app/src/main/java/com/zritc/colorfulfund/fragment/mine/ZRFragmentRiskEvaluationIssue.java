package com.zritc.colorfulfund.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.mine.ZRActivityRiskEvaluationIssue;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.data.model.mine.RiskEvaluationIssue;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/9/21.
 *
 * @version 1.0
 */
public class ZRFragmentRiskEvaluationIssue extends ZRFragmentBase {

    @Bind(R.id.imgBtn_close)
    ImageButton imgBtnClose;

    @Bind(R.id.tv_index_number)
    TextView tvIndexNumber; // 当前问题的指标

    @Bind(R.id.tv_issue_name)
    TextView tvIssueName; // 问题名

    @Bind(R.id.imgBtn_last_issue)
    ImageButton imgBtnLastIssue; // 上一个问题

    @Bind(R.id.imgBtn_next_issue)
    ImageButton imgBtnNextIssue; // 下一个问题

    @Bind(R.id.lv_answer)
    ZRListView lvAnswer;

    private RiskEvaluationIssueAdapter answerAdapter;
    private RiskEvaluationIssue.Issue issue;
    private String issueIndex;
    private String issueNum;
    private List<RiskEvaluationIssue.Answer> answerList;
    private ZRActivityRiskEvaluationIssue parentActivity; // 所属activity

    @Override
    protected void getExtraArguments() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.view_risk_evaluation_issue;
    }

    @Override
    protected void initPresenter() {
        RxView.clicks(imgBtnClose).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                });
        getExtraData();

        answerList = new ArrayList<>();
        if (null != issue) {
            answerList.addAll(issue.answerList);
            tvIndexNumber.setText(issueIndex + "/" + issueNum);
            tvIssueName.setText(issue.issueName);
        }
        parentActivity = (ZRActivityRiskEvaluationIssue) getActivity();
        answerAdapter = new RiskEvaluationIssueAdapter(mContext, answerList, R.layout.lv_risk_evaluation_issue_item);
        lvAnswer.setAdapter(answerAdapter);
        lvAnswer.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            parentActivity.setAnswerId(answerList.get(position).answerId);
            parentActivity.nextPage();
        });

        // 控制上下反翻页
        if ("1".equals(issueIndex)) {
            imgBtnLastIssue.setVisibility(View.GONE);
            imgBtnNextIssue.setVisibility(View.VISIBLE);
        } else if (issueIndex.equals(issueNum)) {
            imgBtnLastIssue.setVisibility(View.VISIBLE);
            imgBtnNextIssue.setVisibility(View.GONE);
        } else {
            imgBtnLastIssue.setVisibility(View.VISIBLE);
            imgBtnNextIssue.setVisibility(View.VISIBLE);
        }
    }

    private void getExtraData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            issue = (RiskEvaluationIssue.Issue) bundle.getSerializable("issue");
            issueIndex = bundle.getString("index");
            issueNum = bundle.getString("issueNum");
        }
    }

    @OnClick({R.id.imgBtn_last_issue, R.id.imgBtn_next_issue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtn_last_issue:
                parentActivity.lastPage();
                break;
            case R.id.imgBtn_next_issue:
                parentActivity.nextPage();
                break;
        }
    }

    static class RiskEvaluationIssueAdapter extends ZRCommonAdapter<RiskEvaluationIssue.Answer> {

        public RiskEvaluationIssueAdapter(Context context, List<RiskEvaluationIssue.Answer> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, RiskEvaluationIssue.Answer item) {
            helper.setText(R.id.tv_answer, item.answerDesc);
        }
    }
}
