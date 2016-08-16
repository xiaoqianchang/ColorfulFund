package com.zritc.colorfulfund.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gufei on 16/8/10.
 */
public class ZRCardInfo implements Serializable {

    private static final long serialVersionUID = -6306771797624555411L;

    private int cardId;
    private String cardName;
    private String cardImage;
    private String singleQuota;
    private String dayQuota;
    private List<ZRCardInfo> datas = new ArrayList<>();

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public List<ZRCardInfo> getDatas() {
        return datas;
    }

    public void setDatas(List<ZRCardInfo> datas) {
        this.datas = datas;
    }

    public String getDayQuota() {
        return dayQuota;
    }

    public void setDayQuota(String dayQuota) {
        this.dayQuota = dayQuota;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSingleQuota() {
        return singleQuota;
    }

    public void setSingleQuota(String singleQuota) {
        this.singleQuota = singleQuota;
    }

    @Override
    public String toString() {
        return "ZRCardInfo{" +
                "cardId=" + cardId +
                ", cardName='" + cardName + '\'' +
                ", cardImage='" + cardImage + '\'' +
                ", singleQuota='" + singleQuota + '\'' +
                ", dayQuota='" + dayQuota + '\'' +
                ", datas=" + datas +
                '}';
    }
}
