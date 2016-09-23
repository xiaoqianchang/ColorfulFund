package com.zritc.colorfulfund.data.model.fund;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FundManager 基金动态
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-09-22
 * @lastUpdate 2016-09-22
 */
public class FundManager implements Serializable {

    private long date;
    private String categoryIconUrl;
    private String categoryName;
    private int category = 0;

    // 调仓变化
    private String adjustTitle;//调整标题
    private String fundName;//基金名称
    private String adjustUpdate;//调整比例

    // 投资简报
    private String currentMonthProft;//本月收益
    private String currentMonthInvestment;//本月投资
    private String currentTrend;//趋势

    // 专家意见
    private String expertOpinionTitle;
    private String getExpertOpinionContent;

    // 教育基金
    private String eduTitle;
    private long eduDate;

    // 心愿完成
    private String wishTitle;
    private long wishDate;

    // 定投提醒
    private String fundReminderTitle;
    private String fundReminderPer;
    private long fundReminderDate;

    private List<FundManager> datas = new ArrayList<>();

    public String getAdjustTitle() {
        return adjustTitle;
    }

    public void setAdjustTitle(String adjustTitle) {
        this.adjustTitle = adjustTitle;
    }

    public String getAdjustUpdate() {
        return adjustUpdate;
    }

    public void setAdjustUpdate(String adjustUpdate) {
        this.adjustUpdate = adjustUpdate;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCategoryIconUrl() {
        return categoryIconUrl;
    }

    public void setCategoryIconUrl(String categoryIconUrl) {
        this.categoryIconUrl = categoryIconUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCurrentMonthInvestment() {
        return currentMonthInvestment;
    }

    public void setCurrentMonthInvestment(String currentMonthInvestment) {
        this.currentMonthInvestment = currentMonthInvestment;
    }

    public String getCurrentMonthProft() {
        return currentMonthProft;
    }

    public void setCurrentMonthProft(String currentMonthProft) {
        this.currentMonthProft = currentMonthProft;
    }

    public String getCurrentTrend() {
        return currentTrend;
    }

    public void setCurrentTrend(String currentTrend) {
        this.currentTrend = currentTrend;
    }

    public List<FundManager> getDatas() {
        return datas;
    }

    public void setDatas(List<FundManager> datas) {
        this.datas = datas;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getEduDate() {
        return eduDate;
    }

    public void setEduDate(long eduDate) {
        this.eduDate = eduDate;
    }

    public String getEduTitle() {
        return eduTitle;
    }

    public void setEduTitle(String eduTitle) {
        this.eduTitle = eduTitle;
    }

    public String getExpertOpinionTitle() {
        return expertOpinionTitle;
    }

    public void setExpertOpinionTitle(String expertOpinionTitle) {
        this.expertOpinionTitle = expertOpinionTitle;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public long getFundReminderDate() {
        return fundReminderDate;
    }

    public void setFundReminderDate(long fundReminderDate) {
        this.fundReminderDate = fundReminderDate;
    }

    public String getFundReminderTitle() {
        return fundReminderTitle;
    }

    public void setFundReminderTitle(String fundReminderTitle) {
        this.fundReminderTitle = fundReminderTitle;
    }

    public String getFundReminderPer() {
        return fundReminderPer;
    }

    public void setFundReminderPer(String fundReminderPer) {
        this.fundReminderPer = fundReminderPer;
    }

    public String getGetExpertOpinionContent() {
        return getExpertOpinionContent;
    }

    public void setGetExpertOpinionContent(String getExpertOpinionContent) {
        this.getExpertOpinionContent = getExpertOpinionContent;
    }

    public long getWishDate() {
        return wishDate;
    }

    public void setWishDate(long wishDate) {
        this.wishDate = wishDate;
    }

    public String getWishTitle() {
        return wishTitle;
    }

    public void setWishTitle(String wishTitle) {
        this.wishTitle = wishTitle;
    }
}
