package com.nitkkr.gawds.haryanagk;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean exit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database.database =new Database(this);

        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    emailIntent.setType("vnd.android.cursor.item/email");
                    //TODO: Check email ID once
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {getResources().getString(R.string.email_address)});
                    startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
                }
            });

        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent=new Intent("com.HaryanaGK.About");
                startActivity(aboutIntent);
            }
        });

        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent=new Intent("com.HaryanaGK.GK");
                startActivity(aboutIntent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(exit)
            super.onBackPressed();
        else
        {
            exit = true;
            Toast.makeText(this,"Press Back Again to Exit",Toast.LENGTH_SHORT).show();
            Handler handler=new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    exit = false;
                }
            },5000);
        }
    }
}
