package com.zritc.colorfulfund.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zritc.colorfulfund.adapter.ZRFolderAdapter;
import com.zritc.colorfulfund.adapter.ZRImageGridAdapter;
import com.zritc.colorfulfund.data.ZRFolder;
import com.zritc.colorfulfund.data.ZRPhotoImage;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRFileUtils;
import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.zritc.colorfulfund.utils.ZRStrings;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择Fragment Created by Nereo on 2015/4/7.
 */
public class ZRFragmentMultiImageSelector extends Fragment {

	public static final String TAG = "me.nereo.multi_image_selector.ZRFragmentMultiImageSelector";

	private static final String KEY_TEMP_FILE = "key_temp_file";

	/** 最大图片选择次数，int类型 */
	public static final String EXTRA_SELECT_COUNT = "max_select_count";
	/** 图片选择模式，int类型 */
	public static final String EXTRA_SELECT_MODE = "select_count_mode";
	/** 是否显示相机，boolean类型 */
	public static final String EXTRA_SHOW_CAMERA = "show_camera";
	/** 默认选择的数据集 */
	public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_result";
	/** 适配器数据来自手机内存 */
	public static final String EXTRA_EXTERNAL_LIST = "external_list";
	/** 外部数据 */
	public static final String EXTRA_DATA_FROM_MOBILE = "data_from_mobile";
	/** 单选 */
	public static final int MODE_SINGLE = 0;
	/** 多选 */
	public static final int MODE_MULTI = 1;
	// 不同loader定义
	private static final int LOADER_ALL = 0;
	private static final int LOADER_CATEGORY = 1;
	// 请求加载系统照相机
	private static final int REQUEST_CAMERA = 100;

	// 初始化外部数据
	private ArrayList<String> externalList = new ArrayList<String>();
	// 结果数据
	private ArrayList<String> resultList = new ArrayList<String>();
	// 文件夹数据
	private ArrayList<ZRFolder> mResultFolder = new ArrayList<ZRFolder>();

	// 图片Grid
	private GridView mGridView;
	private Callback mCallback;

	private ZRImageGridAdapter mImageAdapter;
	private ZRFolderAdapter mFolderAdapter;

	private ListPopupWindow mFolderPopupWindow;

	// 类别
	private TextView mCategoryText;
	// 预览按钮
	private TextView mTextPreview;
	// 已选几张
	private TextView mTextSelected;
	// 底部View
	private View mPopupAnchorView;

	private int mDesireImageCount;

	private boolean hasFolderGened = false;
	private boolean mIsShowCamera = false;
	// 适配器数据来自手机内存
	private boolean isDataFromMobile = true;

	private File mTmpFile;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallback = (Callback) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"The Activity must implement ZRFragmentMultiImageSelector.Callback interface...");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(ZRResourceManager.getResourceID(
				"fragment_multi_image", "layout"), container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// 选择图片数量
		mDesireImageCount = getArguments().getInt(EXTRA_SELECT_COUNT);

		// 图片选择模式
		final int mode = getArguments().getInt(EXTRA_SELECT_MODE);

		// 默认选择
		if (mode == MODE_MULTI) {
			ArrayList<String> tmp = getArguments().getStringArrayList(
					EXTRA_DEFAULT_SELECTED_LIST);
			if (tmp != null && tmp.size() > 0) {
				resultList = tmp;
			}
		}

		// 是否显示照相机
		mIsShowCamera = getArguments().getBoolean(EXTRA_SHOW_CAMERA, true);
		mImageAdapter = new ZRImageGridAdapter(getActivity(), mIsShowCamera, 3);
		// 是否显示选择指示器
		mImageAdapter.showSelectIndicator(mode == MODE_MULTI);
		// 数据是否来自手机内存
		isDataFromMobile = getArguments().getBoolean(EXTRA_DATA_FROM_MOBILE, true);
		// 数据是否是外部数据
		if (mode == MODE_MULTI) {
			externalList = getArguments().getStringArrayList(EXTRA_EXTERNAL_LIST);
			if (externalList != null && externalList.size() > 0) {
				setExternalList(externalList); // 外部数据
			}
		}
		mImageAdapter.setDataFromMobile(isDataFromMobile);

		mPopupAnchorView = view.findViewById(ZRResourceManager.getResourceID(
				"footer", "id"));

		mCategoryText = (TextView) view.findViewById(ZRResourceManager
				.getResourceID("category_btn", "id"));
		// 初始化，加载所有图片
		mCategoryText.setText(ZRStrings.get(getActivity(), "folder_all"));
		mCategoryText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (mFolderPopupWindow == null) {
					createPopupFolderList();
				}

				if (mFolderPopupWindow.isShowing()) {
					mFolderPopupWindow.dismiss();
				} else {
					mFolderPopupWindow.show();
					int index = mFolderAdapter.getSelectIndex();
					index = index == 0 ? index : index - 1;
					mFolderPopupWindow.getListView().setSelection(index);
				}
			}
		});

		mTextSelected = (TextView) view.findViewById(ZRResourceManager
				.getResourceID("text_selected", "id"));
		mTextSelected.setText("已选 " + resultList.size() + "/"
				+ mDesireImageCount);
		mTextPreview = (TextView) view.findViewById(ZRResourceManager
				.getResourceID("text_preview", "id"));
		mTextPreview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 预览
				if (resultList == null || resultList.size() <= 0) {

				}
			}
		});

		mGridView = (GridView) view.findViewById(ZRResourceManager
				.getResourceID("grid", "id"));
		mGridView.setAdapter(mImageAdapter);
		mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int i, long l) {
				if (mImageAdapter.isShowCamera()) {
					// 如果显示照相机，则第一个Grid显示为照相机，处理特殊逻辑
					if (i == 0) {
						showCameraAction();
					} else {
						// 正常操作
						ZRPhotoImage image = (ZRPhotoImage) adapterView.getAdapter().getItem(
								i);
						selectImageFromGrid(image, mode);
					}
				} else {
					// 正常操作
					ZRPhotoImage image = (ZRPhotoImage) adapterView.getAdapter().getItem(i);
					selectImageFromGrid(image, mode);
				}
			}
		});
		mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == SCROLL_STATE_FLING) {
					Picasso.with(view.getContext()).pauseTag(TAG);
				} else {
					Picasso.with(view.getContext()).resumeTag(TAG);
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});

		mFolderAdapter = new ZRFolderAdapter(getActivity());
	}

	/**
	 * 创建弹出的ListView
	 */
	private void createPopupFolderList() {
		Point point = ZRDeviceInfo.getScreenSize(getActivity());
		int width = point.x;
		int height = (int) (point.y * (4.5f / 8.0f));
		mFolderPopupWindow = new ListPopupWindow(getActivity());
		mFolderPopupWindow
				.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		mFolderPopupWindow.setAdapter(mFolderAdapter);
		mFolderPopupWindow.setContentWidth(width);
		mFolderPopupWindow.setWidth(width);
		mFolderPopupWindow.setHeight(height);
		mFolderPopupWindow.setAnchorView(mPopupAnchorView);
		mFolderPopupWindow.setModal(true);
		mFolderPopupWindow
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView,
							View view, int i, long l) {

						mFolderAdapter.setSelectIndex(i);

						final int index = i;
						final AdapterView v = adapterView;

						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								mFolderPopupWindow.dismiss();

								if (index == 0) {
									if (isDataFromMobile) {
										getActivity().getSupportLoaderManager()
												.restartLoader(LOADER_ALL, null,
														mLoaderCallback);
									}
									mCategoryText.setText(ZRStrings.get(
											getActivity(), "folder_all"));
									if (mIsShowCamera) {
										mImageAdapter.setShowCamera(true);
									} else {
										mImageAdapter.setShowCamera(false);
									}
								} else {
									ZRFolder folder = (ZRFolder) v.getAdapter()
											.getItem(index);
									if (null != folder) {
										mImageAdapter.setData(folder.images);
										mCategoryText.setText(folder.name);
										// 设定默认选择
										if (resultList != null
												&& resultList.size() > 0) {
											mImageAdapter
													.setDefaultSelected(resultList);
										}
									}
									mImageAdapter.setShowCamera(false);
								}

								// 滑动到最初始位置
								mGridView.smoothScrollToPosition(0);
							}
						}, 100);

					}
				});
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(KEY_TEMP_FILE, mTmpFile);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		if (savedInstanceState != null) {
			mTmpFile = (File) savedInstanceState.getSerializable(KEY_TEMP_FILE);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// 首次加载所有图片
		// new LoadImageTask().execute();
		if (isDataFromMobile) {
			getActivity().getSupportLoaderManager().initLoader(LOADER_ALL, null,
					mLoaderCallback);
		} else {
			// 设定默认选择
			if (resultList != null && resultList.size() > 0) {
				mImageAdapter.setDefaultSelected(resultList);
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 相机拍照完成后，返回图片路径
		if (requestCode == REQUEST_CAMERA) {
			if (resultCode == Activity.RESULT_OK) {
				if (mTmpFile != null) {
					if (mCallback != null) {
						mCallback.onCameraShot(mTmpFile);
					}
				}
			} else {
				while (mTmpFile != null && mTmpFile.exists()) {
					boolean success = mTmpFile.delete();
					if (success) {
						mTmpFile = null;
					}
				}
			}
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (mFolderPopupWindow != null) {
			if (mFolderPopupWindow.isShowing()) {
				mFolderPopupWindow.dismiss();
			}
		}
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * 选择相机
	 */
	private void showCameraAction() {
		// 跳转到系统照相机
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
			// 设置系统相机拍照后的输出路径
			// 创建临时文件
			try {
				mTmpFile = ZRFileUtils.createTmpFile(getActivity());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (mTmpFile != null && mTmpFile.exists()) {
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(mTmpFile));
				startActivityForResult(cameraIntent, REQUEST_CAMERA);
			} else {
				Toast.makeText(getActivity(), "图片错误", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getActivity(),
					ZRStrings.get(getActivity(), "msg_no_camera"),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 选择图片操作
	 * 
	 * @param image
	 */
	private void selectImageFromGrid(ZRPhotoImage image, int mode) {
		if (image != null) {
			// 多选模式
			if (mode == MODE_MULTI) {
				if (resultList.contains(image.path)) {
					resultList.remove(image.path);
					if (resultList.size() != 0) {
						mTextSelected.setText("已选 " + resultList.size() + "/"
								+ mDesireImageCount);
					} else {
						mTextSelected.setText("已选 0/" + mDesireImageCount);
					}
					if (mCallback != null) {
						mCallback.onImageUnselected(image.path);
					}
				} else {
					// 判断选择数量问题
					if (mDesireImageCount == resultList.size()) {
						Toast.makeText(
								getActivity(),
								ZRStrings
										.get(getActivity(), "msg_amount_limit"),
								Toast.LENGTH_SHORT).show();
						return;
					}
					resultList.add(image.path);
					mTextSelected.setText("已选 " + resultList.size() + "/"
							+ mDesireImageCount);
					if (mCallback != null) {
						mCallback.onImageSelected(image.path);
					}
				}
				mImageAdapter.select(image);
			} else if (mode == MODE_SINGLE) {
				// 单选模式
				if (mCallback != null) {
					mCallback.onSingleImageSelected(image.path);
				}
			}
		}
	}

	private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

		private final String[] IMAGE_PROJECTION = {
				MediaStore.Images.Media.DATA,
				MediaStore.Images.Media.DISPLAY_NAME,
				MediaStore.Images.Media.DATE_ADDED,
				MediaStore.Images.Media.MIME_TYPE,
				MediaStore.Images.Media.SIZE, MediaStore.Images.Media._ID };

		@Override
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			if (id == LOADER_ALL) {
				CursorLoader cursorLoader = new CursorLoader(getActivity(),
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						IMAGE_PROJECTION, IMAGE_PROJECTION[4] + ">0 AND "
								+ IMAGE_PROJECTION[3] + "=? OR "
								+ IMAGE_PROJECTION[3] + "=? ", new String[] {
								"image/jpeg", "image/png" },
						IMAGE_PROJECTION[2] + " DESC");
				return cursorLoader;
			} else if (id == LOADER_CATEGORY) {
				CursorLoader cursorLoader = new CursorLoader(getActivity(),
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						IMAGE_PROJECTION, IMAGE_PROJECTION[4] + ">0 AND "
								+ IMAGE_PROJECTION[0] + " like '%"
								+ args.getString("path") + "%'", null,
						IMAGE_PROJECTION[2] + " DESC");
				return cursorLoader;
			}

			return null;
		}

		private boolean fileExist(String path) {
			if (!TextUtils.isEmpty(path)) {
				return new File(path).exists();
			}
			return false;
		}

		@Override
		public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
			if (data != null) {
				if (data.getCount() > 0) {
					List<ZRPhotoImage> images = new ArrayList<ZRPhotoImage>();
					data.moveToFirst();
					do {
						String path = data.getString(data
								.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
						String name = data.getString(data
								.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
						long dateTime = data.getLong(data
								.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
						ZRPhotoImage image = null;
						if (fileExist(path)) {
							image = new ZRPhotoImage(path, name, dateTime);
							images.add(image);
						}
						if (!hasFolderGened) {
							// 获取文件夹名称
							File folderFile = new File(path).getParentFile();
							if (folderFile != null && folderFile.exists()) {
								String fp = folderFile.getAbsolutePath();
								ZRFolder f = getFolderByPath(fp);
								if (f == null) {
									ZRFolder folder = new ZRFolder();
									folder.name = folderFile.getName();
									folder.path = fp;
									folder.cover = image;
									List<ZRPhotoImage> imageList = new ArrayList<ZRPhotoImage>();
									imageList.add(image);
									folder.images = imageList;
									mResultFolder.add(folder);
								} else {
									f.images.add(image);
								}
							}
						}

					} while (data.moveToNext());

					mImageAdapter.setData(images);
					// 设定默认选择
					if (resultList != null && resultList.size() > 0) {
						mImageAdapter.setDefaultSelected(resultList);
					}

					if (!hasFolderGened) {
						mFolderAdapter.setData(mResultFolder);
						hasFolderGened = true;
					}

				}
			}
		}

		@Override
		public void onLoaderReset(Loader<Cursor> loader) {

		}
	};

	private ZRFolder getFolderByPath(String path) {
		if (mResultFolder != null) {
			for (ZRFolder folder : mResultFolder) {
				if (TextUtils.equals(folder.path, path)) {
					return folder;
				}
			}
		}
		return null;
	}

	/**
	 * 适配器数据是否来自手机内存
	 *
	 * @param dataFromMobile
     */
	public void setDataFromMobile(boolean dataFromMobile) {
		isDataFromMobile = dataFromMobile;
	}

	/**
	 * 外部数据
	 *
	 * @param externalList
     */
	public void setExternalList(List<String> externalList) {
		List<ZRPhotoImage> images = new ArrayList<>();
		if (externalList != null && externalList.size() > 0) {
			for (String imgPath : externalList) {
				images.add(new ZRPhotoImage(imgPath));
			}
		}
		if (mImageAdapter != null) {
			mImageAdapter.setData(images);
		}
	}

	/**
	 * 设置最大图片选择次数
	 *
	 * @param mDesireImageCount
     */
	public void setDesireImageCount(int mDesireImageCount) {
		this.mDesireImageCount = mDesireImageCount;
	}

	/**
	 * 设置全选
	 */
	public void setSelectAll() {
		for (int i = 0; i < externalList.size(); i++) {
			if (!resultList.contains(externalList.get(i))) {
				resultList.add(externalList.get(i));
				if (mCallback != null) {
					mCallback.onImageSelected(externalList.get(i));
				}
				mImageAdapter.select(new ZRPhotoImage(externalList.get(i)));
			}
		}
	}

	/**
	 * 回调接口
	 */
	public interface Callback {
		void onSingleImageSelected(String path);

		void onImageSelected(String path);

		void onImageUnselected(String path);

		void onCameraShot(File imageFile);
	}
}
