package com.zritc.colorfulfund.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * PhotoPicker class used for Application.Help to get photo from local uri.
 * 
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-03
 * @lastUpdate 2016-01-09
 */
public class ZRPhotoPicker {

	private static final String IMAGE_TYPE = "image/*";

	/**
	 * 打开照相机
	 * 
	 * @param activity
	 *            当前的activity
	 * @param requestCode
	 *            拍照成功时activity forResult 的时候的requestCode
	 * @param photoFile
	 *            拍照完毕时,图片保存的位置
	 */
	public static void launchCamera(Activity activity, int requestCode,
			File photoFile) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
		activity.startActivityForResult(intent, requestCode);
	}

	/**
	 * 本地照片调用
	 * 
	 * @param activity
	 * @param requestCode
	 */
	public static void launchGallery(Activity activity, int requestCode) {
		if (launchSys(activity, requestCode)
				&& launch3partyBroswer(activity, requestCode)
				&& launchFinally(activity))
			;
	}

	/**
	 * PopupMenu打开本地相册.
	 */
	private static boolean launchSys(Activity activity, int actResultCode) {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				IMAGE_TYPE);
		try {
			activity.startActivityForResult(intent, actResultCode);
		} catch (android.content.ActivityNotFoundException e) {
			return true;
		}
		// try {
		// Intent intent = new Intent(Intent.ACTION_GET_CONTENT);//
		// ACTION_OPEN_DOCUMENT
		// intent.addCategory(Intent.CATEGORY_OPENABLE);
		// intent.setType("image/*");
		// if (android.os.Build.VERSION.SDK_INT >=
		// android.os.Build.VERSION_CODES.KITKAT) {
		// activity.startActivityForResult(intent, actResultCode);//
		// SELECT_PIC_KITKAT
		// } else {
		// activity.startActivityForResult(intent, actResultCode);// SELECT_PIC
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// return true;
		// }
		return false;
	}

	/**
	 * 打开其他的一文件浏览器,如果没有本地相册的话
	 */
	private static boolean launch3partyBroswer(Activity activity,
			int requestCode) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType(IMAGE_TYPE);
		Intent wrapperIntent = Intent.createChooser(intent, null);
		try {
			activity.startActivityForResult(wrapperIntent, requestCode);
		} catch (android.content.ActivityNotFoundException e1) {
			return true;
		}
		return false;
	}

	/**
	 * 这个是找不到相关的图片浏览器,或者相册
	 */
	private static boolean launchFinally(Activity activity) {
		Toast.makeText(activity, "您的系统没有文件浏览器或相册,请安装！", Toast.LENGTH_LONG)
				.show();
		return false;
	}

	/**
	 * 获取从本地图库返回来的时候的URI解析出来的文件路径
	 * 
	 * @return
	 */
	public static String getPhotoPathByLocalUri(Context context, Intent data) {
		try {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = context.getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			return picturePath;

			// Uri selectedImage = data.getData();
			// ZRLog.e("ZRFileUtils", selectedImage.toString());
			// if (selectedImage != null) {
			// String uriStr = selectedImage.toString();
			// String path = uriStr.substring(10, uriStr.length());
			// if (path.startsWith("com.sec.android.gallery3d")) {
			// return null;
			// }
			// }
			// String[] filePathColumn = { MediaStore.Images.Media.DATA };
			// Cursor cursor = context.getContentResolver().query(selectedImage,
			// filePathColumn, null, null, null);
			// if (null != cursor) {
			// cursor.moveToFirst();
			// int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			// String picturePath = cursor.getString(columnIndex);
			// cursor.close();
			// return picturePath;
			// }
			// return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getPath(final Context context, final Uri uri) {

		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {

			// Return the remote address
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri,
			String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}

	/**
	 * 剪切图片 在 onActivityResult的时候 从 返回的 Intent 里面获取 data 就是bitmap >>>> Bitmap
	 * bitmap = data.getParcelableExtra("data");
	 * 
	 * @param activity
	 * @param imagePath
	 *            文件路径
	 * @param requestCode
	 *            返回码
	 */
	public static void startCrop(Activity activity, String imagePath,
			int requestCode, boolean isLarge) {
		File f = new File(imagePath);
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(Uri.fromFile(f), "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1); // 裁剪框比例 4 : 3
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", isLarge ? 400 : 200);
		intent.putExtra("outputY", isLarge ? 300 : 200);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", true); // 设置为true 的时候才能有返回
		// intent.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		activity.startActivityForResult(intent, requestCode);
	}

}