package com.zritc.colorfulfund.data;

import java.io.Serializable;
import java.util.List;

/**
 * DFAlbumBucket.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-4-18
 * @lastUpdate 2014-4-18
 */
public class ZRAlbumBucket implements Serializable {

    private static final long serialVersionUID = -6306771797624555411L;

    private int mCount = 0;
    private String mBucketName;
    private List<ZRImageInfo> mImageList;

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    public String getBucketName() {
        return mBucketName;
    }

    public void setBucketName(String mBucketName) {
        this.mBucketName = mBucketName;
    }

    public List<ZRImageInfo> getImageList() {
        return mImageList;
    }

    public void setImageList(List<ZRImageInfo> mImageList) {
        this.mImageList = mImageList;
    }

}
