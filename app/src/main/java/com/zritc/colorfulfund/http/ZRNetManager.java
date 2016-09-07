package com.zritc.colorfulfund.http;

import com.zritc.colorfulfund.data.response.circle.CreateCollection;
import com.zritc.colorfulfund.data.response.circle.CreateComment;
import com.zritc.colorfulfund.data.response.circle.CreatePost;
import com.zritc.colorfulfund.data.response.circle.CreateReport;
import com.zritc.colorfulfund.data.response.circle.CreateThumb;
import com.zritc.colorfulfund.data.response.circle.ReadPost;
import com.zritc.colorfulfund.data.response.trade.AdjustPo;
import com.zritc.colorfulfund.data.response.trade.BindPayment;
import com.zritc.colorfulfund.data.response.trade.BuyPo;
import com.zritc.colorfulfund.data.response.trade.EstimateBuyFundFee;
import com.zritc.colorfulfund.data.response.trade.GetFundPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.GetFundPoList4C;
import com.zritc.colorfulfund.data.response.trade.GetUserBankCards4C;
import com.zritc.colorfulfund.data.response.trade.GetUserPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.GetUserPoList4C;
import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;
import com.zritc.colorfulfund.data.response.trade.RedeemPo;
import com.zritc.colorfulfund.data.response.trade.UnbindPayment;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.data.response.user.SetTransPwd;
import com.zritc.colorfulfund.data.response.user.ValidateVCode;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Net api
 * <p>
 * Created by Chang.Xiao on 2016/5/30.
 *
 * @version 1.0
 */
public final class ZRNetManager {

    public interface ZRNetApi {

        @FormUrlEncoded
        @POST
        Call<Login> loginCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("loginName") String loginName
                , @Field("password") String password
        );

        @FormUrlEncoded
        @POST
        Call<AdjustPo> adjustPoCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("poCode") String poCode
        );

        @FormUrlEncoded
        @POST
        Call<BindPayment> bindPaymentCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("realName") String realName
                , @Field("identityNo") String identityNo
                , @Field("paymentType") String paymentType
                , @Field("paymentNo") String paymentNo
                , @Field("phone") String phone
                , @Field("verifyCode") String verifyCode
        );

        @FormUrlEncoded
        @POST
        Call<BuyPo> buyPoCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("poCode") String poCode
                , @Field("amount") String amount
                , @Field("paymentId") String paymentId
        );

        @FormUrlEncoded
        @POST
        Call<GetUserBankCards4C> getUserBankCards4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<PrepareBindPayment> prepareBindPaymentCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("realName") String realName
                , @Field("identityNo") String identityNo
                , @Field("paymentType") String paymentType
                , @Field("paymentNo") String paymentNo
                , @Field("phone") String phone
        );

        @FormUrlEncoded
        @POST
        Call<RedeemPo> redeemPoCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("poCode") String poCode
                , @Field("amount") String amount
        );

        @FormUrlEncoded
        @POST
        Call<UnbindPayment> unbindPaymentCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("paymentNo") String paymentNo
        );

        @FormUrlEncoded
        @POST
        Call<PrepareRegisterAcc> prepareRegisterAccCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("loginName") String loginName
        );

        @FormUrlEncoded
        @POST
        Call<RegisterAcc> registerAccCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("loginName") String loginName
                , @Field("password") String password
                , @Field("vCode") String vCode
        );

        @FormUrlEncoded
        @POST
        Call<SetTransPwd> setTransPwdCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("password") String password
        );

        @FormUrlEncoded
        @POST
        Call<ValidateVCode> validateVCodeCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("loginName") String loginName
                , @Field("password") String password
                , @Field("vCode") String vCode
        );

        @FormUrlEncoded
        @POST
        Call<EstimateBuyFundFee> estimateBuyFundFeeCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("tradeType") String tradeType
                , @Field("fundCode") String fundCode
                , @Field("amount") String amount
        );

        @FormUrlEncoded
        @POST
        Call<GetFundPoInfo4C> getFundPoInfo4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("poCode") String poCode
        );

        @FormUrlEncoded
        @POST
        Call<GetFundPoList4C> getFundPoList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<GetUserPoInfo4C> getUserPoInfo4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("poCode") String poCode
        );

        @FormUrlEncoded
        @POST
        Call<GetUserPoList4C> getUserPoList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        /********************************Circle start***********************************************/
        @FormUrlEncoded
        @POST
        Call<CreateCollection> createCollectionCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("postId") String postId
        );

        @FormUrlEncoded
        @POST
        Call<CreateComment> createCommentCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("postId") String postId
                , @Field("comment") String comment
        );

        @FormUrlEncoded
        @POST
        Call<CreatePost> createPostCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("coverImgURL") String coverImgURL
                , @Field("tagList") String tagList
                , @Field("title") String title
                , @Field("postType") String postType
                , @Field("contentHtml") String contentHtml
                , @Field("content") String content
                , @Field("quote") String quote
        );

        @FormUrlEncoded
        @POST
        Call<CreateReport> createReportCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("commentId") String commentId
        );

        @FormUrlEncoded
        @POST
        Call<CreateThumb> createThumbCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("postId") String postId
        );

        @FormUrlEncoded
        @POST
        Call<ReadPost> readPostCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("postId") String postId
        );
        /********************************Circle end*************************************************/
    }

    private static ZRNetManager netManager;
    private static ZRNetApi api;

    private ZRNetManager() {}

    public static ZRNetManager getInstance() {
        if (null == netManager) {
            netManager = new ZRNetManager();
        }
//        if (null == api) {
            api = ZRRetrofit.getNetApiInstance();
//        }

        return netManager;
    }

    public Call<Login> loginCallbackByPost(String loginName, String password) {
        return api.loginCallbackByPost(
                "http://172.16.101.201:9006/user/login"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , loginName
                , password
        );
    }

    public Call<AdjustPo> adjustPoCallbackByPost(String poCode) {
        return api.adjustPoCallbackByPost(
                "http://172.16.101.201:9006/trade/adjustPo"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , poCode
        );
    }

    public Call<BindPayment> bindPaymentCallbackByPost(String realName, String identityNo, String paymentType, String paymentNo, String phone, String verifyCode) {
        return api.bindPaymentCallbackByPost(
                "http://172.16.101.201:9006/trade/bindPayment"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , realName
                , identityNo
                , paymentType
                , paymentNo
                , phone
                , verifyCode
        );
    }

    public Call<BuyPo> buyPoCallbackByPost(String poCode, String amount, String paymentId) {
        return api.buyPoCallbackByPost(
                "http://172.16.101.201:9006/trade/buyPo"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , poCode
                , amount
                , paymentId
        );
    }

    public Call<GetUserBankCards4C> getUserBankCards4CCallbackByPost() {
        return api.getUserBankCards4CCallbackByPost(
                /*"http://172.16.101.201:9006/trade/getUserBCards"*/
                "http://172.16.101.202/play/trade/getUserBankCards4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<PrepareBindPayment> prepareBindPaymentCallbackByPost(String realName, String identityNo, String paymentType, String paymentNo, String phone) {
        return api.prepareBindPaymentCallbackByPost(
                "http://172.16.101.201:9006/trade/prepareBindPayment"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , realName
                , identityNo
                , paymentType
                , paymentNo
                , phone
        );
    }

    public Call<RedeemPo> redeemPoCallbackByPost(String poCode, String amount) {
        return api.redeemPoCallbackByPost(
                "http://172.16.101.201:9006/trade/redeemPo"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , poCode
                , amount
        );
    }

    public Call<UnbindPayment> unbindPaymentCallbackByPost(String paymentNo) {
        return api.unbindPaymentCallbackByPost(
                "http://172.16.101.201:9006/trade/unbindPayment"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , paymentNo
        );
    }

    public Call<PrepareRegisterAcc> prepareRegisterAccCallbackByPost(String loginName) {
        return api.prepareRegisterAccCallbackByPost(
                "http://172.16.101.201:9006/user/prepareRegisterAcc"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , loginName
        );
    }

    public Call<RegisterAcc> registerAccCallbackByPost(String loginName, String password, String vCode) {
        return api.registerAccCallbackByPost(
                "http://172.16.101.201:9006/user/registerAcc"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , loginName
                , password
                , vCode
        );
    }

    public Call<SetTransPwd> setTransPwdCallbackByPost(String password) {
        return api.setTransPwdCallbackByPost(
                "http://172.16.101.201:9006/user/setTransPwd"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , password
        );
    }

    public Call<ValidateVCode> validateVCodeCallbackByPost(String loginName, String password, String vCode) {
        return api.validateVCodeCallbackByPost(
                "http://172.16.101.201:9006/user/validateVCode"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , loginName
                , password
                , vCode
        );
    }

    public Call<EstimateBuyFundFee> estimateBuyFundFeeCallbackByPost(String tradeType, String fundCode, String amount) {
        return api.estimateBuyFundFeeCallbackByPost(
                "http://172.16.101.201:9006/trade/estimateBuyFundFee"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , tradeType
                , fundCode
                , amount
        );
    }

    public Call<GetFundPoInfo4C> getFundPoInfo4CCallbackByPost(String poCode) {
        return api.getFundPoInfo4CCallbackByPost(
                /*"http://172.16.101.201:9006/trade/getFundPoInfo4C"*/
                "http://172.16.101.202/play/trade/getFundPoInfo4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , poCode
        );
    }

    public Call<GetFundPoList4C> getFundPoList4CCallbackByPost() {
        return api.getFundPoList4CCallbackByPost(
                /*"http://172.16.101.201:9006/trade/getFundPoList4C"*/
                "http://172.16.101.202/play/trade/getFundPoList4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<GetUserPoInfo4C> getUserPoInfo4CCallbackByPost(String poCode) {
        return api.getUserPoInfo4CCallbackByPost(
                /*"http://172.16.101.201:9006/trade/getUserPoInfo4C"*/
                "http://172.16.101.202/play/trade/getUserPoInfo4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , poCode
        );
    }

    public Call<GetUserPoList4C> getUserPoList4CCallbackByPost() {
        return api.getUserPoList4CCallbackByPost(
                /*"http://172.16.101.201:9006/trade/getUserPoList4C"*/
                "http://172.16.101.202/play/trade/getUserPoList4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    /********************************Circle start***********************************************/
    public Call<CreateCollection> createCollectionCallbackByPost(String postId) {
        return api.createCollectionCallbackByPost(
                "http://172.16.101.52:9006/circle/createCollection"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
        );
    }

    public Call<CreateComment> createCommentCallbackByPost(String postId, String comment) {
        return api.createCommentCallbackByPost(
                "http://172.16.101.52:9006/circle/createComment"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
                , comment
        );
    }

    public Call<CreatePost> createPostCallbackByPost(String coverImgURL, String tagList, String title, String postType, String contentHtml, String content, String quote) {
        return api.createPostCallbackByPost(
                "http://172.16.101.52:9006/circle/createPost"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , coverImgURL
                , tagList
                , title
                , postType
                , contentHtml
                , content
                , quote
        );
    }

    public Call<CreateReport> createReportCallbackByPost(String commentId) {
        return api.createReportCallbackByPost(
                "http://172.16.101.52:9006/circle/createReport"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , commentId
        );
    }

    public Call<CreateThumb> createThumbCallbackByPost(String postId) {
        return api.createThumbCallbackByPost(
                "http://172.16.101.52:9006/circle/createThumb"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
        );
    }

    public Call<ReadPost> readPostCallbackByPost(String postId) {
        return api.readPostCallbackByPost(
                "http://172.16.101.52:9006/circle/readPost"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
        );
    }
    /********************************Circle end*************************************************/

}
