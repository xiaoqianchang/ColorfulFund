package com.zritc.colorfulfund.data;

import android.text.TextUtils;

import java.util.List;

/**
 * 文件夹 Created by Nereo on 2015/4/7.
 */
public class ZRFolder {
	public String name;
	public String path;
	public ZRPhotoImage cover;
	public List<ZRPhotoImage> images;

	@Override
	public boolean equals(Object o) {
		try {
			ZRFolder other = (ZRFolder) o;
			return TextUtils.equals(other.path, path);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return super.equals(o);
	}
}
