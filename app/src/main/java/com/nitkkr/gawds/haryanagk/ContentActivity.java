package com.nitkkr.gawds.haryanagk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setTitle(getResources().getString(R.string.GK_Topic));


        ListView listView=(ListView)findViewById(R.id.main_content_list);

        listView.setAdapter(new MainListAdapter(this, R.layout.main_list_item,
                new String[0], null));
        listView.setFastScrollEnabled(true);
        listView.setOverScrollMode(AbsListView.OVER_SCROLL_IF_CONTENT_SCROLLS);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent SubContent=new Intent("com.HaryanaGK.SubContent");
                SubContent.putExtra("SubCategoryID",position);
                startActivity(SubContent);
            }
        });
    }
}
