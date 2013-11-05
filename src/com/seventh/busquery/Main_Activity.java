package com.seventh.busquery;
/**
 * 公交查询
 * 主界面
 */

import com.seventh.busquery.db.busSQLiteOpenHelper;
import com.seventh.busquery.tab.Change_Query;
import com.seventh.busquery.tab.Stop_Query;
import com.seventh.busquery.tab.Route_Query;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class Main_Activity extends TabActivity implements OnTouchListener,
		OnGestureListener {
	private static final int FLING_MIN_DISTANCE = 20;
	private static final int FLING_MIN_VELOCITY = 0;
	private TabHost tabHost;
	private GestureDetector mGestureDetector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		mGestureDetector = new GestureDetector(this);
		LinearLayout ll = (LinearLayout) findViewById(R.id.linew);
		ll.setOnTouchListener(this);
		ll.setLongClickable(true);

		Resources res = getResources();
		tabHost = getTabHost();

		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, Change_Query.class);

		spec = tabHost
				.newTabSpec("change")
				.setIndicator("换乘查询", res.getDrawable(R.drawable.ic_tab_change))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, Stop_Query.class);
		spec = tabHost.newTabSpec("stop")
				.setIndicator("站点查询", res.getDrawable(R.drawable.ic_tab_stop))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, Route_Query.class);
		spec = tabHost.newTabSpec("route")
				.setIndicator("线路查询", res.getDrawable(R.drawable.ic_tab_route))
				.setContent(intent);
		tabHost.addTab(spec);

		TabWidget tabWidget = tabHost.getTabWidget();
		int count = tabWidget.getChildCount();

		tabHost.setCurrentTab(0);
		
		//创建数据库
		busSQLiteOpenHelper bussqlhepler =new busSQLiteOpenHelper(this);
		bussqlhepler.copyDataBase();
		SQLiteDatabase db=bussqlhepler.getWritableDatabase();
	}
	
	private void createTab(String text, Intent intent) {
		tabHost.addTab(tabHost.newTabSpec(text)
				.setIndicator(createTabView(text)).setContent(intent));
	}

	private View createTabView(String text) {
		View view = LayoutInflater.from(this).inflate(R.layout.tab_indicator,
				null);
		TextView tv = (TextView) view.findViewById(R.id.tv_tab);
		tv.setText(text);
		return view;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}
	
	/**
	 * 滑动触屏
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int total = tabHost.getTabWidget().getChildCount();
		int current = tabHost.getCurrentTab();
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// 左滑动
			tabHost.setCurrentTab(current - 1 < 0 ? 0 : current - 1);
		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// 右滑动
			tabHost.setCurrentTab(current + 1 > total ? total - 1 : current + 1);
			
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	
	/**
	 * 监听按键事件
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN ) {
			// 需要监听的事件
			AlertDialog.Builder builder = new AlertDialog.Builder(
					Main_Activity.this)
					.setTitle("提示")
					.setMessage("您是否要退出?");
					builder.setPositiveButton("确定",
							new OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									Main_Activity.this.finish();
									
								}
							});
					builder.setNegativeButton("取消",
							new OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
			builder.create().show();
			return false;
		}
		return super.dispatchKeyEvent(event);
	}
}