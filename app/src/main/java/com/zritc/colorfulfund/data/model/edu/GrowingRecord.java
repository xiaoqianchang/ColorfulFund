package com.zritc.colorfulfund.data.model.edu;

import java.io.Serializable;
import java.util.List;

/**
 * 成长记录
 * Created by gufei on 16/9/13.
 */
public class GrowingRecord implements Serializable {

    /**
     * 投资金额
     */
    public String investmentAmount = "";

    /**
     * 目标完成预期时间
     */
    public long targetDate;

    /**
     * 成长描述
     */
    public String growingDesc = "";

    public PhotoUrlInfo photoUrlInfo;

    public List<GrowingRecord> growingRecordList;

    public List<List<GrowingRecord>> growingRecordLists;

    /**
     * photoUrlInfo
     */
    public class PhotoUrlInfo implements Serializable {

        /**
         * 图片文件路径
         */
        public String photoUrl = "";

        @Override
        public String toString() {
            return "PhotoUrlInfo{" +
                    "photoUrl='" + photoUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GrowingRecord{" +
                "growingDesc='" + growingDesc + '\'' +
                ", periodicalAmount='" + investmentAmount + '\'' +
                ", targetDate=" + targetDate +
                ", photoUrlInfo=" + photoUrlInfo +
                ", growingRecordList=" + growingRecordList +
                ", growingRecordLists=" + growingRecordLists +
                '}';
    }
}
