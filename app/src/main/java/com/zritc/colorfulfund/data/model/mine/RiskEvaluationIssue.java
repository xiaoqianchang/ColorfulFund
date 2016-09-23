package com.zritc.colorfulfund.data.model.mine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 风险评测问题模型
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class RiskEvaluationIssue implements Serializable {

    public List<Issue> issueList = new ArrayList<>(); // 风险评估问题列表
    public String issueNum; // 问题数量

    /**
     * 单个问题对象
     *
     * Created by Chang.Xiao on 2016/9/20.
     *
     * @version 1.0
     */
    public class Issue implements Serializable {
        public String issueId; // 问题Id
        public String issueName; // 问题名字
        public List<Answer> answerList = new ArrayList<>(); // 当前问题的可供答案
    }

    /**
     * 问题答案
     *
     * Created by Chang.Xiao on 2016/9/20.
     *
     * @version 1.0
     */
    public class Answer implements Serializable {
        public String answerId; // 答案Id
        public String answerDesc; // 答案描述
    }
}
