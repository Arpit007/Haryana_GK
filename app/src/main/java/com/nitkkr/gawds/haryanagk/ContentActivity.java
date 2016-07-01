package com.nitkkr.gawds.haryanagk;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setTitle(getResources().getString(R.string.GK_Topic));

        String[] options = getResources().getStringArray(R.array.Main_strings);
        TypedArray icons = getResources().obtainTypedArray(R.array.Main_image_list);

        ListView listView=(ListView)findViewById(R.id.main_content_list);
        /*listView.setAdapter(new Main_List_Adapter(this, R.layout.main_list_item,
                options, icons));*/
        listView.setAdapter(new MainListAdapter(this, R.layout.main_list_item,
                new String[0], null));
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);
        listView.setFastScrollEnabled(true);
        listView.setOverScrollMode(AbsListView.OVER_SCROLL_IF_CONTENT_SCROLLS);*/
    }
}
