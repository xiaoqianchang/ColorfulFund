package com.zritc.colorfulfund.data.model.wish;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/9/13.
 *
 * @version 1.0
 */
public class Wish {

    /**
     * 心愿model
     */
    public String wishId;
    public String wishStatus;
    public String wishName;
    public String targetMoney;
    public int currentProgress; // 当前进度 55

    public Wish() {
    }

    public Wish(String wishStatus, String wishName, String targetMoney, int currentProgress) {
        this.wishStatus = wishStatus;
        this.wishName = wishName;
        this.targetMoney = targetMoney;
        this.currentProgress = currentProgress;
    }
}
