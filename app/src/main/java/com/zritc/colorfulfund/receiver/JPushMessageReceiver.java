package com.zritc.colorfulfund.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.utils.ZRLog;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class JPushMessageReceiver extends BroadcastReceiver {

	private static final String TAG = "JPush";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		ZRLog.d(TAG, "[JPushMessageReceiver] onReceive - " + intent.getAction()
				+ ", extras: " + printBundle(bundle));
		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
			String regId = bundle
					.getString(JPushInterface.EXTRA_REGISTRATION_ID);
			// ZRSharePreferenceKeeper.keepStringValue(context,
			// ZRConstant.KEY_JPUSHID, regId);
			ZRLog.d(TAG, "[JPushMessageReceiver] 接收Registration Id : " + regId);
			// send the Registration Id to your server...
		} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
				.getAction())) {
			ZRLog.d(TAG,
					"[JPushMessageReceiver] 接收到推送下来的自定义消息: "
							+ bundle.getString(JPushInterface.EXTRA_MESSAGE));
			processCustomMessage(context, bundle);
		} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
				.getAction())) {
			ZRLog.d(TAG, "[JPushMessageReceiver] 接收到推送下来的通知");
			int notifactionId = bundle
					.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
			ZRLog.d(TAG, "[JPushMessageReceiver] 接收到推送下来的通知的ID: "
					+ notifactionId);
		} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
				.getAction())) {
			// 打开自定义的Activity
			try {
				Intent i = new Intent();
				String pushJson = bundle.getString(JPushInterface.EXTRA_EXTRA);
				JSONObject obj = new JSONObject(pushJson);
				i.putExtras(bundle);
				context.startActivity(i);
				((Activity) context).overridePendingTransition(R.anim.fade_in,
						R.anim.anim_still);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
				.getAction())) {
			ZRLog.d(TAG, "[JPushMessageReceiver] 用户收到到RICH PUSH CALLBACK: "
					+ bundle.getString(JPushInterface.EXTRA_EXTRA));
			// 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity，
			// 打开一个网页等..
		} else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent
				.getAction())) {
			boolean connected = intent.getBooleanExtra(
					JPushInterface.EXTRA_CONNECTION_CHANGE, false);
			ZRLog.w(TAG, "[JPushMessageReceiver]" + intent.getAction()
					+ " connected state change to " + connected);
		} else {
			ZRLog.d(TAG,
					"[JPushMessageReceiver] Unhandled intent - "
							+ intent.getAction());
		}
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			} else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}

	// send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
		// if (BBActivityMain.isForeground) {
		// String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
		// String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		// Intent msgIntent = new Intent(
		// BBActivityMain.MESSAGE_RECEIVED_ACTION);
		// msgIntent.putExtra(BBActivityMain.KEY_MESSAGE, message);
		// if (!TextUtils.isEmpty(extras)) {
		// try {
		// JSONObject extraJson = new JSONObject(extras);
		// if (null != extraJson && extraJson.length() > 0) {
		// msgIntent.putExtra(BBActivityMain.KEY_EXTRAS, extras);
		// }
		// } catch (JSONException e) {
		//
		// }
		// }
		// context.sendBroadcast(msgIntent);
		// }
	}
}
