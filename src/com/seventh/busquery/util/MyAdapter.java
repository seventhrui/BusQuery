package com.seventh.busquery.util;

import java.util.HashMap;
import java.util.List;

import com.seventh.busquery.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private List<HashMap<String, String>> dataList;

	public MyAdapter(Context context, List<HashMap<String, String>> itemMap) {
		this.context = context;
		this.dataList = itemMap;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int itemId) {
		return itemId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentView) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LinearLayout
					.inflate(context, R.layout.listview, null);
			holder = new ViewHolder();
			holder.textItem = (TextView) convertView
					.findViewById(R.id.testItem);
			holder.textStatus = (TextView) convertView
					.findViewById(R.id.testStatus);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.textItem.setText(dataList.get(position).get("testItem"));
		holder.textStatus.setText(dataList.get(position).get("testStatus"));
		holder.textItem.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景颜色（白）
		holder.textStatus.setBackgroundColor(Color.parseColor("#FFFFFF"));
		holder.textStatus.setTextColor(Color.BLACK);//设置字体颜色
		holder.textItem.setTextColor(Color.CYAN);
		return convertView;
	}

	/**
	 * 定义ListView要获取的控件
	 */
	class ViewHolder {
		TextView textItem;
		TextView textStatus;
	}
}