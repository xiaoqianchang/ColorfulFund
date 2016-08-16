package com.zritc.colorfulfund.data;

import java.io.Serializable;

/**
 * DFImageInfo.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-4-18
 * @lastUpdate 2014-4-18
 */
public class ZRImageInfo implements Serializable {

    private static final long serialVersionUID = -3558004652732415906L;

    public static final int TOTAL_SELECTED_COUNT = 1;// 最多允许的选择的图片个数

    private String imageId;
    private String thumbnailPath;
    private String imagePath;
    private boolean isSelected = false;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
