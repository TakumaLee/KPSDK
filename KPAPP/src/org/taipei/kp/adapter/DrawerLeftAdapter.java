package org.taipei.kp.adapter;

import java.util.ArrayList;
import java.util.List;

import org.taipei.kp.R;
import org.taipei.kp.config.UIConfig;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerLeftAdapter extends BaseAdapter {
	
	private LayoutInflater inflater;
    private String[] title;
    private Integer[] icon;
    
    public DrawerLeftAdapter(Context context){
    	inflater = LayoutInflater.from(context);
    	title = context.getResources().getStringArray(R.array.tabs_name);
        icon = UIConfig.drawer_iconId;
    }

	@Override
	public int getCount() {
		return title.length;
	}

	@Override
	public Object getItem(int position) {
		return title[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.adapter_drawer_list_item, null);
		}
		ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView_DrawerListItem);
		TextView textView = (TextView) convertView.findViewById(R.id.textView_DrawerListItem);
//		TextView count = (TextView) convertView.findViewById(R.id.textView_Drawer_newCount);
		imageView.setImageResource(icon[position]);
		textView.setText(title[position]);
		return convertView;
	}

}
