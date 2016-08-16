package com.zritc.colorfulfund.data.response;

import java.io.Serializable;

/**
 * response model
 * <p>
 * Created by Chang.Xiao on 2016/6/1.
 *
 * @version 1.0
 */
public class ZRRSpBaseData implements Serializable {

    public String rid;
    public String sid;
    public String optype;
    public int code;
    public String msg;

    @Override
    public String toString() {
        return "ZRRSpBaseData{" +
                "rid='" + rid + '\'' +
                ", sid='" + sid + '\'' +
                ", optype='" + optype + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
