package com.nitkkr.gawds.haryanagk;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

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

        ImageView iv = (ImageView)convertView.findViewById(R.id.main_list_item_image);
        try
        {
            iv.setImageDrawable(Drawable.createFromStream(getContext().getAssets().open(Database.database.questionCategory.get(position).ImageFile), null));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        TextView tv = (TextView)convertView.findViewById(R.id.main_list_item_text);
        tv.setText(Database.database.questionCategory.get(position).Name);

        return convertView;
    }

}
