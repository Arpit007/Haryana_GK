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
public class MainListAdapter extends ArrayAdapter<String>
{
    private LayoutInflater mInflater;

    private int mViewResourceId;

    public MainListAdapter(Context ctx, int viewResourceId,
                             String[] strings, TypedArray icons) {
        super(ctx, viewResourceId, strings);

        mInflater = (LayoutInflater)ctx.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        mViewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return Database.database.questionCategory.size();
    }

    @Override
    public String getItem(int position) {
        return Database.database.questionCategory.get(position).Name;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        String x[]=getContext().getResources().getStringArray(R.array.ImageID);
        int y=R.drawable.a1;

        ImageView iv = (ImageView)convertView.findViewById(R.id.main_list_item_image);
        iv.setImageDrawable(getContext().getResources().obtainTypedArray(R.array.ImageID).getDrawable(Database.database.questionCategory.get(position).ImageId));

        TextView tv = (TextView)convertView.findViewById(R.id.main_list_item_text);
        tv.setText(Database.database.questionCategory.get(position).Name);

        TextView tv1 = (TextView)convertView.findViewById(R.id.main_list_item_text_desc);
        tv1.setText(Database.database.questionCategory.get(position).Description);

        return convertView;
    }

}
