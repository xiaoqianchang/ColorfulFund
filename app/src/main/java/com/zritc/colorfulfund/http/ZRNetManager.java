package com.zritc.colorfulfund.http;

import com.zritc.colorfulfund.data.response.circle.CreateCollection;
import com.zritc.colorfulfund.data.response.circle.CreateComment;
import com.zritc.colorfulfund.data.response.circle.CreatePost;
import com.zritc.colorfulfund.data.response.circle.CreateReport;
import com.zritc.colorfulfund.data.response.circle.CreateThumb;
import com.zritc.colorfulfund.data.response.circle.GetCommentList4C;
import com.zritc.colorfulfund.data.response.circle.GetPostInfo4C;
import com.zritc.colorfulfund.data.response.circle.GetPostList4C;
import com.zritc.colorfulfund.data.response.circle.ReadPost;
import com.zritc.colorfulfund.data.response.edu.CreateGrowingRecord;
import com.zritc.colorfulfund.data.response.edu.CreateUserInvestmentPlan4Edu;
import com.zritc.colorfulfund.data.response.edu.GetGrowingPicList4C;
import com.zritc.colorfulfund.data.response.edu.GetGrowingRecordList4C;
import com.zritc.colorfulfund.data.response.edu.GetPoChangeHistory4C;
import com.zritc.colorfulfund.data.response.edu.GetUserPoAssetInfo4C;
import com.zritc.colorfulfund.data.response.mine.EvaluateUserRiskLevel;
import com.zritc.colorfulfund.data.response.mine.GetSurveyList4C;
import com.zritc.colorfulfund.data.response.mine.GetUserMainPageInfo4C;
import com.zritc.colorfulfund.data.response.mine.GetUserTradeHistory4C;
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
import com.zritc.colorfulfund.data.response.user.ChangeTransPwd;
import com.zritc.colorfulfund.data.response.user.CreateShareAlbum;
import com.zritc.colorfulfund.data.response.user.GetLoginPwdVcode;
import com.zritc.colorfulfund.data.response.user.GetUserCollectionList4C;
import com.zritc.colorfulfund.data.response.user.GetUserInfo4C;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.Logoff;
import com.zritc.colorfulfund.data.response.user.PrepareChangeTransPwd;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.data.response.user.ResetLoginPwd;
import com.zritc.colorfulfund.data.response.user.SetTransPwd;
import com.zritc.colorfulfund.data.response.user.UpdateUserInfo;
import com.zritc.colorfulfund.data.response.user.ValidateVCode;
import com.zritc.colorfulfund.data.response.wish.CreateUserWishList4C;
import com.zritc.colorfulfund.data.response.wish.DeleteUserWishList4C;
import com.zritc.colorfulfund.data.response.wish.GetUserWishLists4C;
import com.zritc.colorfulfund.data.response.wish.GetWishListTypes4C;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

//import com.zritc.colorfulfund.data.response.edu.GetUserPoAssetInfo4C;

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

        @FormUrlEncoded
        @POST
        Call<GetPostInfo4C> getPostInfo4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("postId") String postId
        );

        @FormUrlEncoded
        @POST
        Call<GetPostList4C> getPostList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("boardId") String boardId
                , @Field("pageNumber") String pageNumber
        );

        @FormUrlEncoded
        @POST
        Call<GetCommentList4C> getCommentList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("postId") String postId
        );
        /********************************Circle end*************************************************/

        /********************************Edu start**************************************************/
        @FormUrlEncoded
        @POST
        Call<CreateGrowingRecord> createGrowingRecordCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("planId") String planId
                , @Field("photo") String photo
                , @Field("investAmount") String investAmount
                , @Field("description") String description
        );

        @FormUrlEncoded
        @POST
        Call<CreateUserInvestmentPlan4Edu> createUserInvestmentPlan4EduCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("poCode") String poCode
                , @Field("targetDate") String targetDate
                , @Field("targetAmount") String targetAmount
                , @Field("initialAmount") String initialAmount
        );

        @FormUrlEncoded
        @POST
        Call<GetGrowingPicList4C> getGrowingPicList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<GetGrowingRecordList4C> getGrowingRecordList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<GetPoChangeHistory4C> getPoChangeHistory4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("poCode") String poCode
        );

        @FormUrlEncoded
        @POST
        Call<GetUserPoAssetInfo4C> getUserPoAssetInfo4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("poCode") String poCode
        );

        /********************************Edu end**************************************************/

        /********************************wish start***********************************************/
        @FormUrlEncoded
        @POST
        Call<CreateUserWishList4C> createUserWishList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("typeId") long typeId
                , @Field("name") String name
                , @Field("targetAmount") String targetAmount
                , @Field("targetDate") String targetDate
        );

        @FormUrlEncoded
        @POST
        Call<DeleteUserWishList4C> deleteUserWishList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("wishListId") long wishListId
        );

        @FormUrlEncoded
        @POST
        Call<GetUserWishLists4C> getUserWishLists4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<GetWishListTypes4C> getWishListTypes4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );
        /********************************wish end*************************************************/

        /********************************mine start***********************************************/
        @FormUrlEncoded
        @POST
        Call<GetSurveyList4C> getSurveyList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<EvaluateUserRiskLevel> evaluateUserRiskLevelCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("answerId") String answerId
        );

        @FormUrlEncoded
        @POST
        Call<ChangeTransPwd> changeTransPwdCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("phone") String phone
                , @Field("vCode") String vCode
                , @Field("password") String password
        );

        @FormUrlEncoded
        @POST
        Call<GetUserMainPageInfo4C> getUserMainPageInfo4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<GetUserTradeHistory4C> getUserTradeHistory4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<CreateShareAlbum> createShareAlbumCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("photoUrls") String photoUrls
        );

        @FormUrlEncoded
        @POST
        Call<GetLoginPwdVcode> getLoginPwdVcodeCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("phone") String phone
        );

        @FormUrlEncoded
        @POST
        Call<GetUserCollectionList4C> getUserCollectionList4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("fileName") String fileName
        );

        @FormUrlEncoded
        @POST
        Call<GetUserInfo4C> getUserInfo4CCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("userId") String userId
        );

        @FormUrlEncoded
        @POST
        Call<Logoff> logoffCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
        );

        @FormUrlEncoded
        @POST
        Call<PrepareChangeTransPwd> prepareChangeTransPwdCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("bankCardNo") String bankCardNo
                , @Field("identityNo") String identityNo
                , @Field("name") String name
                , @Field("phone") String phone
        );

        @FormUrlEncoded
        @POST
        Call<ResetLoginPwd> resetLoginPwdCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("phone") String phone
                , @Field("vCode") String vCode
                , @Field("password") String password
        );

        @FormUrlEncoded
        @POST
        Call<UpdateUserInfo> updateUserInfoCallbackByPost(
                @Url String url
                , @Field("sid") String sid
                , @Field("deviceid") String deviceid
                , @Field("rid") String rid
                , @Field("nickname") String nickname
                , @Field("photoUrl") String photoUrl
        );
        /********************************mine end*************************************************/
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
//                , "31343773334E37383513132F38343945F35039636C56330A353021D3964864392BD34362333423D3936637622DD3434438386746137E38323533131F2B77695623105F386C43039A3137313392D862306B1332D23436634322D638323D1382D465653746633E61303513636F3534"
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
                "http://172.16.101.201:9006/circle/createCollection"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
        );
    }

    public Call<CreateComment> createCommentCallbackByPost(String postId, String comment) {
        return api.createCommentCallbackByPost(
                "http://172.16.101.201:9006/circle/createComment"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
                , comment
        );
    }

    public Call<CreatePost> createPostCallbackByPost(String coverImgURL, String tagList, String title, String postType, String contentHtml, String content, String quote) {
        return api.createPostCallbackByPost(
                "http://172.16.101.201:9006/circle/createPost"
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
                "http://172.16.101.201:9006/circle/createReport"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , commentId
        );
    }

    public Call<CreateThumb> createThumbCallbackByPost(String postId) {
        return api.createThumbCallbackByPost(
                "http://172.16.101.201:9006/circle/createThumb"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
        );
    }

    public Call<ReadPost> readPostCallbackByPost(String postId) {
        return api.readPostCallbackByPost(
                "http://172.16.101.201:9006/circle/readPost"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
        );
    }

    public Call<GetPostInfo4C> getPostInfo4CCallbackByPost(String postId) {
        return api.getPostInfo4CCallbackByPost(
                "http://172.16.101.202/play/circle/getPostInfo4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
        );
    }

    public Call<GetPostList4C> getPostList4CCallbackByPost(String boardId, String pageNumber) {
        return api.getPostList4CCallbackByPost(
                "http://172.16.101.202/play/circle/getPostList4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , boardId
                , pageNumber
        );
    }

    public Call<GetCommentList4C> getCommentList4CCallbackByPost(String postId) {
        return api.getCommentList4CCallbackByPost(
                "http://172.16.101.202/play/circle/getCommentList4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , postId
        );
    }
    /********************************Circle end*************************************************/

    /********************************Edu start**************************************************/
    public Call<CreateGrowingRecord> createGrowingRecordCallbackByPost(String planId, String photo, String investAmount, String description) {
        return api.createGrowingRecordCallbackByPost(
                "http://172.16.101.201:9006/tradeedu/createGrowingRecord"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , planId
                , photo
                , investAmount
                , description
        );
    }

    public Call<CreateUserInvestmentPlan4Edu> createUserInvestmentPlan4EduCallbackByPost(String poCode, String targetDate, String targetAmount, String initialAmount) {
        return api.createUserInvestmentPlan4EduCallbackByPost(
                "http://172.16.101.201:9007/tradeedu/createUserInvestmentPlan"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , poCode
                , targetDate
                , targetAmount
                , initialAmount
        );
    }

    public Call<GetGrowingPicList4C> getGrowingPicList4CCallbackByPost() {
        return api.getGrowingPicList4CCallbackByPost(
                "http://172.16.101.202/play/tradeedu/getGrowingPicList4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<GetGrowingRecordList4C> getGrowingRecordList4CCallbackByPost() {
        return api.getGrowingRecordList4CCallbackByPost(
                "http://172.16.101.202/play/tradeedu/getGrowingRecordList4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<GetPoChangeHistory4C> getPoChangeHistory4CCallbackByPost(String poCode) {
        return api.getPoChangeHistory4CCallbackByPost(
                "http://172.16.101.202/play/trade/getPoChangeHistory4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , poCode
        );
    }

    public Call<GetUserPoAssetInfo4C> getUserPoAssetInfo4CCallbackByPost(String poCode) {
        return api.getUserPoAssetInfo4CCallbackByPost(
                "http://172.16.101.202/play/trade/getUserPoAssetInfo4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , poCode
        );
    }

    /********************************Edu end****************************************************/

    /********************************wish start***********************************************/
    public Call<CreateUserWishList4C> createUserWishList4CCallbackByPost(long typeId, String name, String targetAmount, String targetDate) {
        return api.createUserWishList4CCallbackByPost(
                "http://172.16.101.201:9008/tradewish/createUserWishList"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , typeId
                , name
                , targetAmount
                , targetDate
        );
    }

    public Call<DeleteUserWishList4C> deleteUserWishList4CCallbackByPost(long wishListId) {
        return api.deleteUserWishList4CCallbackByPost(
                "http://172.16.101.201:9008/tradewish/deleteUserWishList"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , wishListId
        );
    }

    public Call<GetUserWishLists4C> getUserWishLists4CCallbackByPost() {
        return api.getUserWishLists4CCallbackByPost(
                "http://172.16.101.202/play/tradewish/GetUserWishLists4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<GetWishListTypes4C> getWishListTypes4CCallbackByPost() {
        return api.getWishListTypes4CCallbackByPost(
                "http://172.16.101.202/play/tradewish/GetWishListTypes4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }
    /********************************wish end*************************************************/

    /********************************mine start***********************************************/
    public Call<GetSurveyList4C> getSurveyList4CCallbackByPost() {
        return api.getSurveyList4CCallbackByPost(
                "http://172.16.101.202/play/trade/GetSurveyList4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<EvaluateUserRiskLevel> evaluateUserRiskLevelCallbackByPost(String answerId) {
        return api.evaluateUserRiskLevelCallbackByPost(
                "http://172.16.101.201:9006/trade/evaluateUserRiskLevel"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , answerId
        );
    }

    public Call<ChangeTransPwd> changeTransPwdCallbackByPost(String phone, String vCode, String password) {
        return api.changeTransPwdCallbackByPost(
                "http://172.16.101.201:9006/user/changeTransPwd"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , phone
                , vCode
                , password
        );
    }

    public Call<GetUserMainPageInfo4C> getUserMainPageInfo4CCallbackByPost() {
        return api.getUserMainPageInfo4CCallbackByPost(
                "http://172.16.101.202/play/trade/GetUserMainPageInfo4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<GetUserTradeHistory4C> getUserTradeHistory4CCallbackByPost() {
        return api.getUserTradeHistory4CCallbackByPost(
                "http://172.16.101.202/play/trade/GetUserTradeHistory4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<CreateShareAlbum> createShareAlbumCallbackByPost(String photoUrls) {
        return api.createShareAlbumCallbackByPost(
                "http://172.16.101.201:9006/user/createShareAlbum"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , photoUrls
        );
    }

    public Call<GetLoginPwdVcode> getLoginPwdVcodeCallbackByPost(String phone) {
        return api.getLoginPwdVcodeCallbackByPost(
                "http://172.16.101.201:9006/user/getLoginPwdVcode"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , phone
        );
    }

    public Call<GetUserCollectionList4C> getUserCollectionList4CCallbackByPost(String fileName) {
        return api.getUserCollectionList4CCallbackByPost(
                "http://172.16.101.202/play/user/getUserCollectionList4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , fileName
        );
    }

    public Call<GetUserInfo4C> getUserInfo4CCallbackByPost(String userId) {
        return api.getUserInfo4CCallbackByPost(
                "http://172.16.101.202/play/user/getUserInfo4C"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , userId
        );
    }

    public Call<Logoff> logoffCallbackByPost() {
        return api.logoffCallbackByPost(
                "http://172.16.101.201:9006/user/logoff"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
        );
    }

    public Call<PrepareChangeTransPwd> prepareChangeTransPwdCallbackByPost(String bankCardNo, String identityNo, String name, String phone) {
        return api.prepareChangeTransPwdCallbackByPost(
                "http://172.16.101.201:9006/user/prepareChangeTransPwd"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , bankCardNo
                , identityNo
                , name
                , phone
        );
    }

    public Call<ResetLoginPwd> resetLoginPwdCallbackByPost(String phone, String vCode, String password) {
        return api.resetLoginPwdCallbackByPost(
                "http://172.16.101.201:9006/user/resetLoginPwd"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , phone
                , vCode
                , password
        );
    }

    public Call<UpdateUserInfo> updateUserInfoCallbackByPost(String nickname, String photoUrl) {
        return api.updateUserInfoCallbackByPost(
                "http://172.16.101.201:9006/user/updateUserInfo"
                , ZRDeviceInfo.getSid()
                , ZRDeviceInfo.getServerDeviceId()
                , ZRDeviceInfo.getRid()
                , nickname
                , photoUrl
        );
    }
    /********************************mine end*************************************************/

}
