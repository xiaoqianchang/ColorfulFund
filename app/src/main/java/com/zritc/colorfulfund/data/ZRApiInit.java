package com.zritc.colorfulfund.data;

import java.io.Serializable;

/**
 * Created by gufei on 16/5/3.
 */
public class ZRApiInit implements Serializable {

    /**
     *
     */
    private final long serialVersionUID = 1L;

    private String device_register;

    private String user_register;

    private String user_login;

    private String user_logout;

    private String user_reset_password;

    private String user_get_register_vcode;

    private String user_get_reset_password_vcode;

    private String check_vcode;

    private String user_get_my_info;

    private String user_get_his_info;

    private String user_update_info;

    private String user_apply_cert;

    private String upload_image;

    private String live_verify_video_access_code;

    private String live_create_activity;

    private String user_accept_investor_license;

    private String live_invite_audience;

    private String live_update_white_list;

    private String live_get_white_list;

    private String live_get_history_white_list;

    private String live_should_play;

    private String live_get_invite_vcode;

    private String live_get_my_list;

    private String live_get_my_invited_list;

    private String live_get_his_list;

    private String live_post_comment;

    private String live_update_audience_info;

    private String live_get_user_list;

    private String live_close_or_cancel;

    private String live_get_recommended_list;

    private String live_collect_to_user_collection;

    private String live_get_user_collection;

    private String live_post_question;

    private String search_live;

    private String search_user;

    private String search_article;

    private String forum_create_article;

    private String forum_upload_file;

    private String forum_remove_article;

    private String user_correct;

    private String user_complain;

    private String forum_comment_article;

    private String forum_thumb_article;

    private String user_dynamic_info;

    private String forum_get_my_article_list;

    private String forum_get_article_collected_status;

    private String forum_get_article_thumbed_status;

    private String follow_user;

    private String user_get_follower_list;

    private String user_get_fan_list;

    private String user_get_follow_relation;

    private String forum_update_board_list;

    private String forum_get_selected_board_list;

    private String forum_get_available_board_list;

    private String get_main_page;

    private String get_about_page;

    private String get_license_page;

    private String get_region_list;

    private String get_app_download_page;

    private String video_image_prefix;

    private String article_image_prefix;

    private String user_image_prefix;

    private String short_link_prefix;

    private String banner_image_prefix;

    private String channel_image_prefix;

    private String public_article_list;

    private String private_article_list;

    private String video_status_prefix;

    private String article_info4web_prefix;

    private String video_list_prefix;

    private String video_info_prefix;

    private String video_comment_list_prefix;

    private String article_info_prefix;

    private String article_comment_list_prefix;

    private String share_article_prefix;

    private String im_userinfo;

    private String im_appbind;

    private String im_pushmsg;

    private String im_read;

    private String im_top;

    private String im_mask;

    private String im_remove;

    private String im_msgs;

    private String im_time;

/**
     * 获取更多频道的地址
     */
    private String get_channel_list;

    public String getArticle_comment_list_prefix() {
        return article_comment_list_prefix + "%s/%s.shtml";
    }

    public String getArticle_image_prefix() {
        return article_image_prefix;
    }

    public String getArticle_info4web_prefix() {
        return article_info4web_prefix + "%s.shtml";
    }

    public String getArticle_info_prefix() {
        return article_info_prefix + "%s.shtml";
    }

    public String getBanner_image_prefix() {
        return banner_image_prefix;
    }

    public String getChannel_image_prefix() {
        return channel_image_prefix;
    }

    public String getCheck_vcode() {
        return check_vcode;
    }

    public String getDevice_register() {
        return device_register;
    }

    public String getFollow_user() {
        return follow_user;
    }

    public String getForum_comment_article() {
        return forum_comment_article;
    }

    public String getForum_create_article() {
        return forum_create_article;
    }

    public String getForum_get_article_collected_status() {
        return forum_get_article_collected_status;
    }

    public String getForum_get_article_thumbed_status() {
        return forum_get_article_thumbed_status;
    }

    public String getForum_get_available_board_list() {
        return forum_get_available_board_list;
    }

    public String getForum_get_my_article_list() {
        return forum_get_my_article_list;
    }

    public String getForum_get_selected_board_list() {
        return forum_get_selected_board_list;
    }

    public String getForum_remove_article() {
        return forum_remove_article;
    }

    public String getForum_thumb_article() {
        return forum_thumb_article;
    }

    public String getForum_update_board_list() {
        return forum_update_board_list;
    }

    public String getForum_upload_file() {
        return forum_upload_file;
    }

    public String getGet_about_page() {
        return get_about_page;
    }

    public String getGet_app_download_page() {
        return get_app_download_page;
    }

    public String getGet_license_page() {
        return get_license_page;
    }

    public String getGet_main_page() {
        return get_main_page;
    }

    public String getGet_region_list() {
        return get_region_list;
    }

    public String getLive_close_or_cancel() {
        return live_close_or_cancel;
    }

    public String getLive_collect_to_user_collection() {
        return live_collect_to_user_collection;
    }

    public String getLive_create_activity() {
        return live_create_activity;
    }

    public String getLive_get_his_list() {
        return live_get_his_list;
    }

    public String getLive_get_history_white_list() {
        return live_get_history_white_list;
    }

    public String getLive_get_invite_vcode() {
        return live_get_invite_vcode;
    }

    public String getLive_get_my_invited_list() {
        return live_get_my_invited_list;
    }

    public String getLive_get_my_list() {
        return live_get_my_list;
    }

    public String getLive_get_recommended_list() {
        return live_get_recommended_list;
    }

    public String getLive_get_user_collection() {
        return live_get_user_collection;
    }

    public String getLive_get_user_list() {
        return live_get_user_list;
    }

    public String getLive_get_white_list() {
        return live_get_white_list;
    }

    public String getLive_invite_audience() {
        return live_invite_audience;
    }

    public String getLive_post_comment() {
        return live_post_comment;
    }

    public String getLive_post_question() {
        return live_post_question;
    }

    public String getLive_should_play() {
        return live_should_play;
    }

    public String getLive_update_audience_info() {
        return live_update_audience_info;
    }

    public String getLive_update_white_list() {
        return live_update_white_list;
    }

    public String getLive_verify_video_access_code() {
        return live_verify_video_access_code;
    }

    public String getPrivate_article_list() {
        return private_article_list;
    }

    public String getPublic_article_list() {
        return public_article_list;
    }

    public String getSearch_article() {
        return search_article;
    }

    public String getSearch_live() {
        return search_live;
    }

    public String getSearch_user() {
        return search_user;
    }

    public String getShare_article_prefix() {
        return share_article_prefix + "%s.shtml";
    }

    public String getShort_link_prefix() {
        return short_link_prefix;
    }

    public String getUpload_image() {
        return upload_image;
    }

    public String getUser_accept_investor_license() {
        return user_accept_investor_license;
    }

    public String getUser_apply_cert() {
        return user_apply_cert;
    }

    public String getUser_complain() {
        return user_complain;
    }

    public String getUser_correct() {
        return user_correct;
    }

    public String getUser_dynamic_info() {
        return user_dynamic_info;
    }

    public String getUser_get_fan_list() {
        return user_get_fan_list;
    }

    public String getUser_get_follow_relation() {
        return user_get_follow_relation;
    }

    public String getUser_get_follower_list() {
        return user_get_follower_list;
    }

    public String getUser_get_his_info() {
        return user_get_his_info;
    }

    public String getUser_get_my_info() {
        return user_get_my_info;
    }

    public String getUser_get_register_vcode() {
        return user_get_register_vcode;
    }

    public String getUser_get_reset_password_vcode() {
        return user_get_reset_password_vcode;
    }

    public String getUser_image_prefix() {
        return user_image_prefix;
    }

    public String getUser_login() {
        return user_login;
    }

    public String getUser_logout() {
        return user_logout;
    }

    public String getUser_register() {
        return user_register;
    }

    public String getUser_reset_password() {
        return user_reset_password;
    }

    public String getUser_update_info() {
        return user_update_info;
    }

    public String getVideo_comment_list_prefix() {
        return video_comment_list_prefix + "%s/%s.shtml";
    }

    public String getVideo_image_prefix() {
        return video_image_prefix;
    }

    public String getVideo_info_prefix() {
        return video_info_prefix + "%s.shtml";
    }

    public String getVideo_list_prefix() {
        return video_list_prefix + "%s/%s.shtml";
    }

    public String getVideo_status_prefix() {
        return video_status_prefix + "%s.shtml";
    }

    public String getIm_userinfo() {
        return im_userinfo;
    }

    public String getIm_appbind() {
        return im_appbind;
    }

    public String getIm_pushmsg() {
        return im_pushmsg;
    }

    public String getIm_read() {
        return im_read;
    }

    public String getIm_top() {
        return im_top;
    }

    public String getIm_mask() {
        return im_mask;
    }

    public String getIm_remove() {
        return im_remove;
    }

    public String getIm_msgs() {
        return im_msgs;
    }

    public String getIm_time() {
        return im_time;
    }

public String getGet_channel_list() {
        return get_channel_list;
    }

    public void setGet_channel_list(String get_channel_list) {
        this.get_channel_list = get_channel_list;
    }
}
