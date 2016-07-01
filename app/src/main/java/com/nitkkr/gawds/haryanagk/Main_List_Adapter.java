package com.nitkkr.gawds.haryanagk;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Home Laptop on 01-Jul-16.
 */
public class Main_List_Adapter extends ArrayAdapter<String>
{
    private LayoutInflater mInflater;

    private String[] Item_Titles;
    private TypedArray Item_Images;

    private int mViewResourceId;

    public Main_List_Adapter(Context ctx, int viewResourceId,
                               String[] strings, TypedArray icons) {
        super(ctx, viewResourceId, strings);

        mInflater = (LayoutInflater)ctx.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        Item_Titles = strings;
        Item_Images= icons;

        mViewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return Item_Titles.length;
    }

    @Override
    public String getItem(int position) {
        return Item_Titles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        ImageView iv = (ImageView)convertView.findViewById(R.id.main_list_item_image);
        iv.setImageDrawable(Item_Images.getDrawable(position));

        TextView tv = (TextView)convertView.findViewById(R.id.main_list_item_text);
        tv.setText(Item_Titles[position]);

        TextView tv1 = (TextView)convertView.findViewById(R.id.main_list_item_text_desc);
        tv1.setText("Hello Peeps!!!!");

        return convertView;
    }

}
