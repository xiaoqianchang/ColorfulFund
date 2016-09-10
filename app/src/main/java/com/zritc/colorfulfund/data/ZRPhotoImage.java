package com.zritc.colorfulfund.data;

/**
 * 图片实体 Created by Nereo on 2015/4/7.
 */
public class ZRPhotoImage {
	public String path;
	public String name;
	public long time;

	public ZRPhotoImage(String path) {
		this.path = path;
	}

	public ZRPhotoImage(String path, String name, long time) {
		this.path = path;
		this.name = name;
		this.time = time;
	}

	@Override
	public boolean equals(Object o) {
		try {
			ZRPhotoImage other = (ZRPhotoImage) o;
			return this.path.equalsIgnoreCase(other.path);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return super.equals(o);
	}
}
