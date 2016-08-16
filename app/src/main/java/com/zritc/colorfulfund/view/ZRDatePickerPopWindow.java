package com.zritc.colorfulfund.view;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.adapter.datepicker.NumericWheelAdapter;
import com.zritc.colorfulfund.view.datepicker.OnWheelScrollListener;
import com.zritc.colorfulfund.view.datepicker.WheelView;

public class ZRDatePickerPopWindow extends PopupWindow implements
		OnClickListener {

	private Context context;
	private String startTime;
	private int serverStartTime = 8;
	private int serverStopTime = 23;// 小时滚轮省略时间

	private Date date;
	private int curYear, curMonth;
	private LayoutInflater mInflater;
	private View dateView;
	private WheelView yearView;
	private WheelView monthView;
	private WheelView dayView;
	private WheelView hourView;
	private WheelView minView;
	private WheelView secView;
	private Button btnCancel;
	private Button btnOK;
	private int[] timeInt;
	private String birthday;

	public ZRDatePickerPopWindow(Context context, String startTime) {
		this.context = context;
		this.startTime = startTime;
		this.serverStopTime = 0;
		setStartTime();
		initWindow();
	}

	public ZRDatePickerPopWindow(Context context, String startTime,
			int serverStartTime, int serverStopTime) {
		this.context = context;
		this.startTime = startTime;
		if (serverStartTime > 0 && serverStopTime <= 24 && serverStartTime < serverStopTime) {
			this.serverStartTime = serverStartTime;
			this.serverStopTime = serverStopTime;
		}
		setStartTime();
		initWindow();
	}

	private void setStartTime() {
		timeInt = new int[6];
		timeInt[0] = Integer.valueOf(startTime.substring(0, 4));
		timeInt[1] = Integer.valueOf(startTime.substring(4, 6));
		timeInt[2] = Integer.valueOf(startTime.substring(6, 8));
		int currentHour =  Integer.valueOf(startTime.substring(8, 10));
		if(currentHour < serverStartTime || currentHour > serverStopTime){
			timeInt[3] = serverStartTime;
			timeInt[4] = 0;
			timeInt[5] = 0;
		}else{
			timeInt[3] = currentHour;
			timeInt[4] = Integer.valueOf(startTime.substring(10, 12));
			timeInt[5] = Integer.valueOf(startTime.substring(12, 14));
		}
	}

	private void initWindow() {
		mInflater = LayoutInflater.from(context);
		dateView = mInflater.inflate(R.layout.wheel_date_picker, null);
		yearView = (WheelView) dateView.findViewById(R.id.year);
		monthView = (WheelView) dateView.findViewById(R.id.month);
		dayView = (WheelView) dateView.findViewById(R.id.day);
		hourView = (WheelView) dateView.findViewById(R.id.time);
		minView = (WheelView) dateView.findViewById(R.id.min);
		secView = (WheelView) dateView.findViewById(R.id.sec);
		secView.setVisibility(View.GONE);

		btnCancel = (Button) dateView.findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(this);

		btnOK = (Button) dateView.findViewById(R.id.btn_ok);
		btnOK.setOnClickListener(this);

		initWheel();
	}

	private void initWheel() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;

		NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(
				context, curYear, curYear + 1);
		numericWheelAdapter1.setLabel("");
		yearView.setViewAdapter(numericWheelAdapter1);
		yearView.setCyclic(false);
		yearView.addScrollingListener(scrollListener);

		NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(
				context, 1, 12, "%02d");
		numericWheelAdapter2.setLabel("");
		monthView.setViewAdapter(numericWheelAdapter2);
		monthView.setCyclic(false);
		monthView.addScrollingListener(scrollListener);

		NumericWheelAdapter numericWheelAdapter3 = new NumericWheelAdapter(
				context, 1, getDay(curYear, curMonth), "%02d");
		numericWheelAdapter3.setLabel("");
		dayView.setViewAdapter(numericWheelAdapter3);
		dayView.setCyclic(false);
		dayView.addScrollingListener(scrollListener);

		NumericWheelAdapter numericWheelAdapter4 = new NumericWheelAdapter(
				context, serverStartTime, serverStopTime - 1, "%02d");
		numericWheelAdapter4.setLabel("");
		hourView.setViewAdapter(numericWheelAdapter4);
		hourView.setCyclic(false);
		hourView.addScrollingListener(scrollListener);

		NumericWheelAdapter numericWheelAdapter5 = new NumericWheelAdapter(
				context, 0, 59, "%02d");
		numericWheelAdapter5.setLabel("");
		minView.setViewAdapter(numericWheelAdapter5);
		minView.setCyclic(false);
		minView.addScrollingListener(scrollListener);

		yearView.setCurrentItem(timeInt[0] - curYear);
		monthView.setCurrentItem(timeInt[1] - 1);
		dayView.setCurrentItem(timeInt[2] - 1);
		hourView.setCurrentItem(timeInt[3] - serverStartTime);
		minView.setCurrentItem(timeInt[4]);

		yearView.setVisibleItems(7);
		monthView.setVisibleItems(7);
		dayView.setVisibleItems(7);
		hourView.setVisibleItems(7);
		minView.setVisibleItems(7);

		setContentView(dateView);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
		setBackgroundDrawable(dw);
		setFocusable(true);
	}

	private DatePickerChanged mDatePickerChanged;

	public void setCallback(DatePickerChanged datePickerChanged) {
		mDatePickerChanged = datePickerChanged;
	}

	public interface DatePickerChanged {
		public void datePickerChangedCallback(String birthday);

		public void datePickerCancelCallback();

		public void datePickerConfirmCallback();

	}

	OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			int n_year = yearView.getCurrentItem() + curYear;//
			int n_month = monthView.getCurrentItem() + 1;//

			initDay(n_year, n_month);

			// getCurrentItemTime();
			// mDatePickerChanged.datePickerChangedCallback(birthday);
		}

	};

	private void getCurrentItemTime() {
		birthday = new StringBuilder()
				.append((yearView.getCurrentItem() + curYear))
				.append("-")
				.append((monthView.getCurrentItem() + 1) < 10 ? "0"
						+ (monthView.getCurrentItem() + 1) : (monthView
						.getCurrentItem() + 1))
				.append("-")
				.append(((dayView.getCurrentItem() + 1) < 10) ? "0"
						+ (dayView.getCurrentItem() + 1) : (dayView
						.getCurrentItem() + 1))
				.append(" ")
				.append(((hourView.getCurrentItem()+serverStartTime) < 10) ? "0"
						+ (hourView.getCurrentItem()+serverStartTime) : (hourView.getCurrentItem()+serverStartTime))
				.append(":")
				.append((minView.getCurrentItem() < 10) ? "0"
						+ minView.getCurrentItem() : minView.getCurrentItem())
				.append(":00").toString();
	}

	private void initDay(int arg1, int arg2) {
		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(
				context, 1, getDay(arg1, arg2), "%02d");
		numericWheelAdapter.setLabel("");
		dayView.setViewAdapter(numericWheelAdapter);
	}

	private int getDay(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
		case 0:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			day = flag ? 29 : 28;
			break;
		default:
			day = 30;
			break;
		}
		return day;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_cancel:
			mDatePickerChanged.datePickerCancelCallback();
			break;
		case R.id.btn_ok:
			mDatePickerChanged.datePickerConfirmCallback();
			getCurrentItemTime();
			mDatePickerChanged.datePickerChangedCallback(birthday);
			break;
		}
	}
}
