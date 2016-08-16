package com.zritc.colorfulfund.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRHttpResourceCache {
	public static final int SIZE_SRC = -1;
	private static final String LAST_MODIFIED_FORMAT = "EEE, d MMM yyyy HH:mm:ss 'GMT'";
	private static final String SIGN_SUFFIX = "_sign";
	private static final String RESOURCE_CACHE_DIR = "resource";
	private static final int CACHE_SIZE = 1024 * 1024 * 20;
	private static Context sContext;
	private static File sDirectory;

	public static void init(Context context) {
		sContext = context;
		sDirectory = new File(sContext.getCacheDir().getAbsolutePath()
				+ File.separator + RESOURCE_CACHE_DIR);
	}

	public static long getLastModified(String url) {
		return ZRSharePreferenceKeeper.getLongValue(sContext,
				ZRUtils.getMD5(url));
	}

	public static void setLastModified(String url, long lastModified) {
		ZRSharePreferenceKeeper.keepLongValue(sContext, ZRUtils.getMD5(url),
				lastModified);
	}

	public static String getFormatLastModified(String url) {
		long time = ZRSharePreferenceKeeper.getLongValue(sContext,
				ZRUtils.getMD5(url));
		return ZRUtils.formatTimeWithOutTimeZoneFixed(time,
				LAST_MODIFIED_FORMAT);
	}

	public static void setFormatLastModified(String url, String lastModified) {
		ZRSharePreferenceKeeper.keepLongValue(sContext, ZRUtils.getMD5(url),
				ZRUtils.getTimeWithOutTimeZoneFixed(lastModified,
						LAST_MODIFIED_FORMAT));
	}

	public static String getResourceUTF8(String url) throws IOException {
		String key = ZRUtils.getMD5(url);
		String data = ZRFileUtils.readFile(new File(sDirectory, key));
		if (null != data) {
			return data;
		}
		throw new IOException();
	}

	public static boolean saveResourceUTF8(String url, String content) {
		if (!checkCacheSize()) {
			return false;
		}
		String key = ZRUtils.getMD5(url);
		return ZRFileUtils.saveFile(content, new File(sDirectory, key));
	}

	public static Bitmap getBitmap(String url) {
		try {
			File file = new File(sDirectory, ZRUtils.getMD5(url));
			if (!file.exists()) {
				return null;
			}
			return BitmapFactory.decodeFile(file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			ZRLog.e("ZRHttpResourceCache", "getBitmap() : " + e.getMessage());
			return null;
		}
	}

	public static Bitmap getBitmap(String url, int reqWidth, int reqHeight) {
		try {
			File file = new File(sDirectory, ZRUtils.getMD5(url));
			if (!file.exists()) {
				return null;
			}
			// First decode with inJustDecodeBounds=true to check dimensions
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(file.getAbsolutePath(), options);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth,
					reqHeight);

			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		} catch (Exception e) {
			e.printStackTrace();
			ZRLog.e("ZRHttpResourceCache", "getBitmap() : " + e.getMessage());
			return null;
		}
	}

	/**
	 * Calculate an inSampleSize for use in a {@link BitmapFactory.Options}
	 * object when decoding bitmaps using the decode* methods from
	 * {@link BitmapFactory}. This implementation calculates the closest
	 * inSampleSize that will result in the final decoded bitmap having a width
	 * and height equal to or larger than the requested width and height. This
	 * implementation does not ensure a power of 2 is returned for inSampleSize
	 * which can be faster when decoding but results in a larger bitmap which
	 * isn't as useful for caching purposes.
	 * 
	 * @param options
	 *            An options object with out* params already populated (run
	 *            through a decode* method with inJustDecodeBounds==true
	 * @param reqWidth
	 *            The requested width of the resulting bitmap
	 * @param reqHeight
	 *            The requested height of the resulting bitmap
	 * @return The value to be used for inSampleSize
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee a final image
			// with both dimensions larger than or equal to the requested height
			// and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

			// This offers some additional logic in case the image has a strange
			// aspect ratio. For example, a panorama may have a much larger
			// width than height. In these cases the total pixels might still
			// end up being too large to fit comfortably in memory, so we should
			// be more aggressive with sample down the image (=larger
			// inSampleSize).

			final float totalPixels = width * height;

			// Anything more than 2x the requested pixels we'll sample down
			// further
			final float totalReqPixelsCap = reqWidth * reqHeight * 2;

			while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
				inSampleSize++;
			}
		}
		return inSampleSize;
	}

	/**
	 * Cache drawable with key. <b>Call this method just when the drawable is
	 * original size in url, and each url can call this method just once.</b>
	 * 
	 * @param url
	 *            The key in cache map.
	 * @param drawable
	 *            The drawable to cache.
	 */
	public static boolean cacheBitmap(String url, Bitmap bmp) {
		if (!sDirectory.exists() && !sDirectory.mkdirs()) {
			return false;
		}
		if (!checkCacheSize()) {
			return false;
		}
		File file = new File(sDirectory, ZRUtils.getMD5(url));
		if (file.exists() && !file.delete()) {
			return false;
		}

		FileOutputStream fos = null;
		try {
			if (!file.createNewFile()) {
				return false;
			}
			fos = new FileOutputStream(file);
			bmp.compress(CompressFormat.PNG, 100, fos);
			fos.flush();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static void clearCacheFile() {
		File directory = sContext.getCacheDir();
		if (directory.exists()) {
			File[] files = directory.listFiles();
			for (File f : files) {
				f.delete();
			}
		}
	}

	private static boolean checkCacheSize() {
		File[] files = sDirectory.listFiles();
		if (null == files)
			return true;
		long size = 0;
		for (File subFile : files) {
			if (subFile.isDirectory()) {
				subFile.delete();
			} else {
				size += subFile.length();
			}
		}

		if (size > CACHE_SIZE) {
			Arrays.sort(files, new Comparator<File>() {

				@Override
				public int compare(File lhs, File rhs) {
					return (int) (lhs.lastModified() - rhs.lastModified());
				}
			});

			int i = 0;
			while (size > CACHE_SIZE) {
				if (i >= files.length) {
					return false;
				}
				File current = files[i++];
				long len = current.length();
				if (current.delete()) {
					size -= len;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}
