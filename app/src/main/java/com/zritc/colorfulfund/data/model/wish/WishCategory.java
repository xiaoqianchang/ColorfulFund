package com.zritc.colorfulfund.data.model.wish;

import java.io.Serializable;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/9/13.
 *
 * @version 1.0
 */
public class WishCategory implements Serializable {

    public String wishTypeId;
    public int imgUrl;
    public String name;

    public WishCategory() {
    }

    public WishCategory(String wishTypeId, int imgUrl, String name) {
        this.wishTypeId = wishTypeId;
        this.imgUrl = imgUrl;
        this.name = name;
    }
}
