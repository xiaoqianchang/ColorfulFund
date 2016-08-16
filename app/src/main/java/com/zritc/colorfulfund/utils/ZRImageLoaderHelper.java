package com.zritc.colorfulfund.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer.RoundedDrawable;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 图片加载助手
 *
 * @author Midas
 * @version 1.0
 * @createDate 2016-01-13
 * @lastUpdate 2016-01-13
 */
public class ZRImageLoaderHelper {

    public static final int IMG_LOAD_DELAY = 200;

    private static ZRImageLoaderHelper mImageLoaderHelper;
    private ImageLoader mImageLoader;

    public static void init(Context context) {
        initImageLoader(context);
    }

    private static void initImageLoader(Context context) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(false)
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                context)
                /** memoryCache image max width and height **/
                .memoryCacheExtraOptions(750, 1070)
                /** Sets thread pool size for image display tasks. **/
                .threadPoolSize(3)
                /** Sets the priority for image loading threads **/
                .threadPriority(Thread.NORM_PRIORITY - 2)
                /** allow to cache multiple sizes of one image in memory **/
                .denyCacheImageMultipleSizesInMemory()
                /** Sets memory cache for bitmaps. **/
                .memoryCache(new LruMemoryCache(10 * 1024 * 1024))
                /** Sets memory cache for bitmaps. **/
                .memoryCacheSize(10 * 1024 * 1024)
                /** Sets maximum disk cache size for images **/
                .discCacheSize(50 * 1024 * 1024)
                // .diskCacheSize(50 * 1024 * 1024)
                /** Sets name generator for files cached in disk cache. **/
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                /**
                 * Sets type of queue processing for tasks for loading and
                 * displaying images.
                 **/
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(200)
                /** Sets default display image options for image displaying **/
                .defaultDisplayImageOptions(options)

                .build();
        ImageLoader.getInstance().init(configuration);
    }

    public ZRImageLoaderHelper() {
        mImageLoader = ImageLoader.getInstance();
    }

    public static ZRImageLoaderHelper getInstance() {
        if (null == mImageLoaderHelper)
            mImageLoaderHelper = new ZRImageLoaderHelper();
        return mImageLoaderHelper;
    }

    public void loadImage(final String url, ImageView imageView,
                          int defaultResId) {
        if (!url.startsWith("http"))
            return;
        mImageLoader.displayImage(url.trim(), imageView,
                getDisplayImageOptions(defaultResId),
                new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view,
                                                FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view,
                                                  Bitmap loadedImage) {
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                    }
                });

    }

    public void loadImageBySize(final String url, int imageWidth,
                                int imageHeight, final int cornerRadius, final ImageView imageView) {
        if (!url.startsWith("http"))
            return;
        ImageSize imageSize = null;
        if (imageWidth > 0 && imageHeight > 0) {
            imageSize = new ImageSize(imageWidth, imageHeight);
        }

        mImageLoader.loadImage(url.trim(), imageSize,
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view,
                                                FailReason failReason) {
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view,
                                                  Bitmap loadedImage) {
                        try {
//                            if (imageUri.equals(imageView.getTag())) {
//                                imageView.setImageBitmap(loadedImage);
//                            } else {
                                Drawable drawable;
                                if (cornerRadius > 0)
                                    drawable = new RoundedDrawable(loadedImage, ZRDeviceInfo.dp2px(cornerRadius), 0);
                                else
                                    drawable = new BitmapDrawable(loadedImage);
                                imageView.setImageDrawable(drawable);
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                    }
                });
    }

    public void loadImageBySize(final String url, int imageWidth,
                                int imageHeight, final ImageView imageView) {
        if (url.startsWith("http"))
            loadImageBySize(url, imageWidth,
                    imageHeight, 3, imageView);
    }

    public void loadCircleImage(String url, ImageView imageView,
                                int defaultResId, float rdp) {
        if (url.startsWith("http"))
            mImageLoader.displayImage(url.trim(), imageView,
                    getDisplayHeaderImageOptions(defaultResId, rdp));
    }

    /**
     * 获取图片配置
     *
     * @return
     */
    private DisplayImageOptions getDisplayImageOptions(int imageRes) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(imageRes).showImageOnFail(imageRes)
                .resetViewBeforeLoading(false).cacheOnDisc(true)
                .cacheInMemory(true)
                // 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY).build();
        return options;
    }

    private DisplayImageOptions getDisplayHeaderImageOptions(int imageRes,
                                                             float rdp) {
        DisplayImageOptions headerOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(imageRes).showImageOnFail(imageRes)
                .resetViewBeforeLoading(false)
                .cacheOnDisc(true)
                .cacheInMemory(true)
                // 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(ZRDeviceInfo.dp2px(rdp)))
                .build();
        return headerOptions;
    }

}
