package com.zritc.colorfulfund.data.response;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/6/1.
 *
 * @version 1.0
 */
public class ZRRspDefaultData extends ZRRSpBaseData {

    public DefaultResult result;

    public class DefaultResult {
        public String cid;
        public String msgid;
        public String fmsgid;

        @Override
        public String toString() {
            return "DefaultResult{" +
                    "cid='" + cid + '\'' +
                    ", msgid='" + msgid + '\'' +
                    ", fmsgid='" + fmsgid + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ZRRspDefaultData{" +
                "rid='" + rid + '\'' +
                ", sid='" + sid + '\'' +
                ", optype='" + optype + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result.toString() +
                '}';
    }
}
