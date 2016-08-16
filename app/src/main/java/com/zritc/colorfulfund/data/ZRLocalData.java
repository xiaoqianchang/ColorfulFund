package com.zritc.colorfulfund.data;

import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Data class for {@link UPDataEngine}.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRLocalData<T extends ZRDataCell> {
	public static final String DEFAULT_TIME_STAMP = "0001-01-01 00:00:00";
	public static final String TIME_STAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private List<T> mDatas = new ArrayList<T>();
	private String mTimestamp = DEFAULT_TIME_STAMP;

	private Class<T> mDataClass;

	public ZRLocalData(Class<T> dataClass) {
		mDataClass = dataClass;
	}

	public ZRDataCell getDataByID(String id) {
		return getDataByID(id, false);
	}

	public ZRDataCell getDataByID(String id, boolean addIfNotExist) {
		for (ZRDataCell data : mDatas) {
			if (id.equals(data.getID())) {
				return data;
			}
		}
		if (addIfNotExist) {
			try {
				T data = mDataClass.newInstance();
				data.setID(id);
				mDatas.add(data);
				return data;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void removeDataByID(String id) {
		Iterator<T> it = mDatas.iterator();
		while (it.hasNext()) {
			T data = it.next();
			if (id.equals(data.getID())) {
				it.remove();
			}
		}
	}

	public void removeDataWithIDNotIn(String[] ids) {
		Iterator<T> it = mDatas.iterator();
		while (it.hasNext()) {
			T data = it.next();
			int index = Arrays.binarySearch(ids, data.getID());
			if (0 > index) {
				it.remove();
			}
		}
	}

	public Object[] getDataList() {
		return mDatas.toArray();
	}

	public T getDataByIndex(int index) {
		return mDatas.get(index);
	}

	public void clearData() {
		mDatas.clear();
	}

	public void clearDataAndTimestamp() {
		mDatas.clear();
		mTimestamp = DEFAULT_TIME_STAMP;
	}

	public boolean hasData(String id) {
		for (T data : mDatas) {
			if (id.equals(data.getID())) {
				return true;
			}
		}
		return false;
	}

	public void addData(T data) {
		mDatas.add(data);
	}

	public void addData(int index, T data) {
		int size = mDatas.size();
		if (index < 0) {
			mDatas.add(0, data);
		} else if (index >= size) {
			mDatas.add(data);
		} else {
			mDatas.add(index, data);
		}
	}

	public void setData(int index, T data) {
		mDatas.set(index, data);
	}

	public void removeDataByIndex(int index) {
		mDatas.remove(index);
	}

	public void removeData(T data) {
		mDatas.remove(data);
	}

	public int getSize() {
		return mDatas.size();
	}

	public String getTimestamp() {
		return mTimestamp;
	}

	public void setTimestamp(String timestamp) {
		mTimestamp = timestamp;
	}

	public void updateTimestamp() {
		mTimestamp = ZRUtils.getCurrentTime(TIME_STAMP_FORMAT);
	}

	public void moveItemToIndex(int srcIndex, int desIndex) {
		T data = mDatas.remove(srcIndex);
		mDatas.add(desIndex, data);
	}

	public ZRLocalData<T> initFromJSONString(String str) throws JSONException {
		JSONObject json = new JSONObject(str);
		if (json.has(ZRConstant.KEY_TIMESTAMP)) {
			mTimestamp = json.getString(ZRConstant.KEY_TIMESTAMP);
		} else {
			updateTimestamp();
		}
		mDatas.clear();
		JSONArray data = json.optJSONArray(ZRConstant.KEY_CONTENT);
		if (null != data) {
			for (int i = 0; i < data.length(); ++i) {
				try {
					mDatas.add((T) mDataClass.newInstance().initFromJsonString(
							data.getString(i)));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}

	public String getJSONString() {
		JSONObject json = new JSONObject();
		try {
			json.put(ZRConstant.KEY_TIMESTAMP, mTimestamp);
			JSONArray data = new JSONArray();
			if (0 < mDatas.size()) {
				for (int i = 0; i < mDatas.size(); ++i) {
					data.put(new JSONObject(mDatas.get(i).getJsonString()));
				}
				json.put(ZRConstant.KEY_CONTENT, data);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
