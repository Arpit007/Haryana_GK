package com.nitkkr.gawds.haryanagk;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;

public class SubContentActivity extends AppCompatActivity
{
    static int Index=0;
    String Questions[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_content);

        Intent SubCategoryIntent=getIntent();

        if(SubCategoryIntent.getAction()!=null)
            Index=SubCategoryIntent.getIntExtra("SubCategoryID",0);

        setTitle(Database.database.questionCategory.get(Index).Name);


        Questions=new String[Database.database.questionCategory.get(Index).questions.size()];

        for(int id=0;id<Database.database.questionCategory.get(Index).questions.size();id++)
        {
            Questions[id]=Database.database.questionCategory.get(Index).questions.get(id).getProblem();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.sub_item, Questions);
        ListView subList=(ListView)findViewById(R.id.SubList);
        try
        {
            subList.setAdapter(adapter);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }


        subList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent SubContent=new Intent("com.HaryanaGK.Question");
                SubContent.putExtra("SubCategoryID",Index);
                SubContent.putExtra("QuestionID",position);
                startActivity(SubContent);
            }
        });
    }
}
