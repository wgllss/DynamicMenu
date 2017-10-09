/**
 * 
 */
package com.example.dynamicmenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-1-10上午9:53:02
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class GridViewActivity extends Activity {
	final String arr[] = { "AAAAAAAAAAAAAAAAAA", "bbbbbbbbbbbbbbbbbbbb", "ccccccccccccccc", "ddddddddddddddd", "eeeeeeeeeeeeeee", "fff", "AAA", "bbbb", "ccc", "ddd", "eee", "fff", "AAA", "bbbb",
			"ccc", "ddd", "eee", "fff", "AAA", "bbbb", "ccc", "ddd", "eee", "fff", "AAA", "bbbb", "ccc", "ddd", "eee", "fff", "AAA", "bbbb", "ccc", "ddd", "eee", "fff" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_activity);
		GridView grid_view = (GridView) findViewById(R.id.grid_view);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, arr);
		// 设置水平横向滑动的参数
		int size = arr.length;
		int length = 80;
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int gridviewWidth = (int) (size * (length + 4) * density);
		int itemWidth = (int) (length * density);
		LinearLayout.LayoutParams params = new LayoutParams(gridviewWidth, LayoutParams.MATCH_PARENT);
		grid_view.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
		grid_view.setNumColumns(arr.length);
		// grid_view.setColumnWidth(itemWidth);
		grid_view.setAdapter(adapter);
	}
}
