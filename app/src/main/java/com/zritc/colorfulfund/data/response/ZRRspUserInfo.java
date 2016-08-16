package com.zritc.colorfulfund.data.response;

import java.util.List;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/6/3.
 *
 * @version 1.0
 */
public class ZRRspUserInfo extends ZRRSpBaseData {

    public UserInfoResult result;

    public class UserInfoResult {

        public List<UserInfo> userinfo;

        public class UserInfo {
            public String userid;
            public String usernickname;
            public String userphoto;
            public String userlevel;
            public String usergender;
            public String userdescription;
            public String usercertdescription;

            @Override
            public String toString() {
                return "UserInfo{" +
                        "userid='" + userid + '\'' +
                        ", usernickname='" + usernickname + '\'' +
                        ", userphoto='" + userphoto + '\'' +
                        ", userlevel='" + userlevel + '\'' +
                        ", usergender='" + usergender + '\'' +
                        ", userdescription='" + userdescription + '\'' +
                        ", usercertdescription='" + usercertdescription + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "UserInfoResult{" +
                    "userinfo=" + userinfo.toString() +
                    '}';
        }
    }
}
