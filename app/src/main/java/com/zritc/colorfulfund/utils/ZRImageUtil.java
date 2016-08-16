package com.zritc.colorfulfund.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.squareup.picasso.Transformation;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图像处理的工具
 */
public class ZRImageUtil {

    /**
     * Utility method for downsampling images.
     *
     * @param path    the file path
     * @param data    if file path is null, provide the image data directly
     * @param target  the target dimension
     * @param isWidth use width as target, otherwise use the higher value of height
     *                or width
     * @param round   corner radius
     * @return the resized image
     */
    @SuppressWarnings("deprecation")
    public static Bitmap getResizedImage(String path, byte[] data, int target,
                                         boolean isWidth, int round) {

        Options options = null;

        if (target > 0) {

            Options info = new Options();
            info.inJustDecodeBounds = true;
            // 设置这两个属性可以减少内存损耗
            info.inInputShareable = true;
            info.inPurgeable = true;

            decode(path, data, info);

            int dim = info.outWidth;
            if (!isWidth)
                dim = Math.max(dim, info.outHeight);
            int ssize = sampleSize(dim, target);

            options = new Options();
            options.inSampleSize = ssize;

        }

        Bitmap bm = null;
        try {
            bm = decode(path, data, options);
        } catch (OutOfMemoryError e) {
            L.red(e.toString());
            e.printStackTrace();
        }
        /**
         * 把图片旋转为正的方向
         */
        int degree = ZRImageUtil.readPictureDegree(path);
        Bitmap newbitmap = ZRImageUtil.rotaingImageView(degree, bm);
        if (round > 0) {
            newbitmap = getRoundedCornerBitmap(newbitmap, round);
        }
        return newbitmap;
    }

    private static Bitmap decode(String path, byte[] data,
                                 Options options) {

        Bitmap result = null;

        if (path != null) {

            result = decodeFile(path, options);

        } else if (data != null) {

            result = BitmapFactory.decodeByteArray(data, 0, data.length,
                    options);

        }

        if (result == null && options != null && !options.inJustDecodeBounds) {
            L.red("decode image failed" + path);
        }

        return result;
    }

    @SuppressWarnings("deprecation")
    private static Bitmap decodeFile(String path, Options options) {

        Bitmap result = null;

        if (options == null) {
            options = new Options();
        }

        options.inInputShareable = true;
        options.inPurgeable = true;

        FileInputStream fis = null;

        try {

            fis = new FileInputStream(path);

            FileDescriptor fd = fis.getFD();

            result = BitmapFactory.decodeFileDescriptor(fd, null, options);

        } catch (IOException e) {
            L.red(e.toString());
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    private static int sampleSize(int width, int target) {

        int result = 1;

        for (int i = 0; i < 10; i++) {

            if (width < target * 2) {
                break;
            }

            width = width / 2;
            result = result * 2;

        }

        return result;
    }

    /**
     * 获取圆角的bitmap
     *
     * @param bitmap
     * @param pixels
     * @return
     */
    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * auto fix the imageOrientation
     *
     * @param bm   source
     * @param iv   imageView if set invloke it's setImageBitmap() otherwise do
     *             nothing
     * @param uri  image Uri if null user path
     * @param path image path if null use uri
     */
    public static Bitmap autoFixOrientation(Bitmap bm, ImageView iv, Uri uri,
                                            String path) {
        int deg = 0;
        try {
            ExifInterface exif = null;
            if (uri == null) {
                exif = new ExifInterface(path);
            } else if (path == null) {
                exif = new ExifInterface(uri.getPath());
            }

            if (exif == null) {
                L.red("exif is null check your uri or path");
                return bm;
            }

            String rotate = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
            int rotateValue = Integer.parseInt(rotate);
            System.out.println("orientetion : " + rotateValue);
            switch (rotateValue) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    deg = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    deg = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    deg = 270;
                    break;
                default:
                    deg = 0;
                    break;
            }
        } catch (Exception ee) {
            Log.d("catch img error", "return");
            if (iv != null)
                iv.setImageBitmap(bm);
            return bm;
        }
        Matrix m = new Matrix();
        m.preRotate(deg);
        bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m,
                true);

        // bm = Compress(bm, 75);
        if (iv != null)
            iv.setImageBitmap(bm);
        return bm;
    }

    /**
     * @param bmp
     * @return
     */
    public static Bitmap blurImageAmeliorate(Bitmap bmp) {
        long start = System.currentTimeMillis();
        // 高斯矩阵
        int[] gauss = new int[]{1, 2, 1, 2, 4, 2, 1, 2, 1};

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 13; // 值越小图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * gauss[idx]);
                        newG = newG + (int) (pixG * gauss[idx]);
                        newB = newB + (int) (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        long end = System.currentTimeMillis();
        Log.d("may", "used time=" + (end - start));
        return bitmap;
    }

    public static class L {

        public static void red(Object o) {
            Log.e("ImageUtil", o.toString());
        }

    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /*
     * 旋转图片
     *
     * @param angle
     *
     * @param bitmap
     *
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    public static class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                                   int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null)
                return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size,
                        Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    public static class GlideRoundTransform extends BitmapTransformation {

        private static float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                                   int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null)
                return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(),
                    Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(),
                        source.getHeight(), Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(),
                    source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }

    public static class PicassoCircleTransform implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap
                    .createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

    public static class PicassoRoundTransform implements Transformation {

        private static float radius = 0f;

        public PicassoRoundTransform(Context context) {
            this(context, 4);
        }

        public PicassoRoundTransform(Context context, int dp) {
            radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        public String key() {
            return "circle";
        }

        @Override
        public Bitmap transform(Bitmap toTransform) {
            return getRoundedCornerBitmap(toTransform, radius);
        }
    }

    /**
     * 根据屏幕尺寸处理图片的URL
     *
     * @return
     */
    public static String managerImageUrlByScreenSize(String url,
                                                     boolean isList, int screenType) {
        try {
            String[] tempList = url.split("/");
            String fileName = "";
            if (tempList.length >= 2
                    && TextUtils.equals(tempList[tempList.length - 2],
                    tempList[tempList.length - 1])) {
                if (isList) {
                    fileName = "t150";
                } else {
                    if (screenType == ZRDeviceInfo.IMAGE_TYPE_640) {
                        fileName = "t640";
                    } else if (screenType == ZRDeviceInfo.IMAGE_TYPE_1080) {
                        fileName = "t1080";
                    } else {
                        fileName = tempList[tempList.length - 1];
                    }
                }
            } else {
                fileName = tempList[tempList.length - 1];
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < tempList.length; i++) {
                if (i != tempList.length - 1) {
                    result.append(tempList[i]);
                    result.append("/");
                } else {
                    result.append(fileName);
                }
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return url;
        }

    }

    public static BitmapDrawable getBitmap(Context context, int resImageId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        opt.inPurgeable = true;

        opt.inInputShareable = true;

        InputStream is = context.getResources().openRawResource(

                resImageId);

        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);

        BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);

        return bd;
    }


}