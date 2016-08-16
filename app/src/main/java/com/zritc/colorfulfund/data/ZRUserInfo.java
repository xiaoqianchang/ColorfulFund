package com.zritc.colorfulfund.data;

import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRJsonValidator;
import com.zritc.colorfulfund.utils.ZRUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ZRUserInfo.
 *
 * @author eric
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRUserInfo implements Serializable {

    private static final long serialVersionUID = -1994975156104278036L;

    private String userid = "";
    private String username = "";
    private String usercertname = "";
    private String userdescription = "";
    private String phone = "";
    private String userphotoimg = "";
    private String gender = "";
    private int level = 0;// 0-游客,1-注册,2-认证,3-大v
    private String certstatus = "1";// 1-未申请,2-已申请,3-批准,4-被拒
    private String job = "";
    private String company = "";
    private String region = "";
    private String email = "";
    private String certcomment = "";// 认证结果
    private String certdescription = "";
    private String certphotoimg = "";
    private String idcard = "";
    private String idcardback = "";
    private String bizimg = "";
    private String videocount = "0";
    private String articlecount = "0";
    private String msgcount = "0";
    private String attcount = "0";
    private String fancount = "0";
    private String address = "";
    private boolean status;
    private int vistor = 0;
    private int pageIndex = 0;
    private int totalCount = 0;

    private ArrayList<ZRUserInfo> list = new ArrayList<ZRUserInfo>();

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercertname() {
        return usercertname;
    }

    public void setUsercertname(String usercertname) {
        this.usercertname = usercertname;
    }

    public String getUserdescription() {
        return userdescription;
    }

    public void setUserdescription(String userdescription) {
        this.userdescription = userdescription;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserphotoimg() {
        return userphotoimg;
    }

    public void setUserphotoimg(String userphotoimg) {
        this.userphotoimg = userphotoimg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCertstatus() {
        return certstatus;
    }

    public void setCertstatus(String certstatus) {
        this.certstatus = certstatus;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertcomment() {
        return certcomment;
    }

    public void setCertcomment(String certcomment) {
        this.certcomment = certcomment;
    }

    public String getCertdescription() {
        return certdescription;
    }

    public void setCertdescription(String certdescription) {
        this.certdescription = certdescription;
    }

    public String getCertphotoimg() {
        return certphotoimg;
    }

    public void setCertphotoimg(String certphotoimg) {
        this.certphotoimg = certphotoimg;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardback() {
        return idcardback;
    }

    public void setIdcardback(String idcardback) {
        this.idcardback = idcardback;
    }

    public String getBizimg() {
        return bizimg;
    }

    public void setBizimg(String bizimg) {
        this.bizimg = bizimg;
    }

    public String getVideocount() {
        return ZRUtils.validStringFormat(videocount).isEmpty() ? "0"
                : videocount;
    }

    public void setVideocount(String videocount) {
        this.videocount = videocount;
    }

    public String getArticlecount() {
        return ZRUtils.validStringFormat(articlecount).isEmpty() ? "0"
                : articlecount;
    }

    public void setArticlecount(String articlecount) {
        this.articlecount = articlecount;
    }

    public String getMsgcount() {
        return ZRUtils.validStringFormat(msgcount).isEmpty() ? "0" : msgcount;
    }

    public void setMsgcount(String msgcount) {
        this.msgcount = msgcount;
    }

    public String getAttcount() {
        return ZRUtils.validStringFormat(attcount).isEmpty() ? "0" : attcount;
    }

    public void setAttcount(String attcount) {
        this.attcount = attcount;
    }

    public String getFancount() {
        return ZRUtils.validStringFormat(fancount).isEmpty() ? "0" : fancount;
    }

    public void setFancount(String fancount) {
        this.fancount = fancount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getVistor() {
        return vistor;
    }

    public void setVistor(int vistor) {
        this.vistor = vistor;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<ZRUserInfo> getList() {
        return list;
    }

    public void setList(ArrayList<ZRUserInfo> list) {
        this.list = list;
    }

    public ZRUserInfo initFromUserInfoJson(String str) throws JSONException {
        try {
            JSONObject obj = new JSONObject(str);
            if (null != obj && new ZRJsonValidator().validate(obj.toString())) {
                userid = obj.optString(ZRConstant.KEY_USERID);
                username = obj.optString(ZRConstant.KEY_USERNICKNAME);
                usercertname = obj.optString(ZRConstant.KEY_USERCERTNAME);
                userdescription = obj
                        .optString(ZRConstant.KEY_USER_DESCRIPTION);
                phone = obj.optString(ZRConstant.KEY_USER_PHONE);
                userphotoimg = obj.optString(ZRConstant.KEY_USER_PHOTO);
                gender = obj.optString(ZRConstant.KEY_USER_GENDER);
                level = obj.optInt(ZRConstant.KEY_USER_LEVEL);
                status = obj.optBoolean(ZRConstant.KEY_STATUS);
                certstatus = obj.optString(ZRConstant.KEY_USER_CERTSTATUS, "1");
                job = obj.optString(ZRConstant.KEY_USER_JOB);
                company = obj.optString(ZRConstant.KEY_USER_COMPANY);
                region = obj.optString(ZRConstant.KEY_USER_REGION);
                email = obj.optString(ZRConstant.KEY_USER_EMAIL);
                certcomment = obj.optString(ZRConstant.KEY_USER_CERTCOMMENT);
                certdescription = obj
                        .optString(ZRConstant.KEY_USER_CERTDESCRIPTION);
                certphotoimg = obj.optString(ZRConstant.KEY_USER_CERTPHOTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
        return this;
    }

    /**
     * 解析用户相关的视频帖子等数
     *
     * @param str
     * @return
     * @throws JSONException
     */
    public ZRUserInfo initFromUserInfoCountJson(String str)
            throws JSONException {
        try {
            JSONObject obj = new JSONObject(str);
            videocount = obj.optString(ZRConstant.KEY_VIDEONUMBER);
            articlecount = obj.optString(ZRConstant.KEY_ARTICLECOUNT);
            attcount = obj.optString(ZRConstant.KEY_ATTNUMBER);
            fancount = obj.optString(ZRConstant.KEY_FANSNUMBER);
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
        return this;
    }

    /**
     * 解析在线人数数据
     *
     * @param str
     * @return
     * @throws JSONException
     */
    public ZRUserInfo initFromOnlineJson(String str) throws JSONException {
        try {
            JSONObject obj = new JSONObject(str);
            if (null != obj) {
                String[] key = new String[]{ZRConstant.KEY_CERTED,
                        ZRConstant.KEY_REGISTER, ZRConstant.KEY_VISTOR};
                for (int j = 0; j < key.length; j++) {
                    if (j == 2)
                        vistor = obj.optJSONObject(key[j]).optInt(
                                ZRConstant.KEY_COUNT);
                    else {
                        JSONArray array = obj.optJSONArray(key[j]);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject _obj = array.optJSONObject(i);
                            ZRUserInfo user = new ZRUserInfo()
                                    .initFromUserInfoJson(_obj.toString());
                            list.add(user);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
        return this;
    }

    /**
     * 解析关注和粉丝列表
     *
     * @param result
     * @return
     * @throws JSONException
     */
    public ZRUserInfo initFromAttAndFansJson(String result)
            throws JSONException {
        try {
            JSONObject json = new JSONObject(result);
            if (null != json) {
                pageIndex = json.optInt(ZRConstant.KEY_PAGE_INDEX);
                totalCount = json.optInt(ZRConstant.KEY_TOTAL_COUNT);
                JSONArray array = json.optJSONArray(ZRConstant.KEY_LIST);
                if (null != array) {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject _obj = array.optJSONObject(i);
                        if (null != _obj
                                && new ZRJsonValidator().validate(_obj
                                .toString())) {
                            ZRUserInfo user = new ZRUserInfo()
                                    .initFromUserInfoJson(_obj.toString());
                            list.add(user);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
        return this;
    }

}
