package com.nitkkr.gawds.haryanagk;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;

public class QuestionViewActivity extends AppCompatActivity
{
	int Index, SubIndex;
	float x1, x2;
	int numQuestion;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_view);

		Intent QuestionIntent = getIntent();

		Index = QuestionIntent.getIntExtra("QuestionID", 0);
		SubIndex = QuestionIntent.getIntExtra("SubCategoryID", 0);
		QuestionIntent.putExtra("QuestionID", -1);


		numQuestion = Database.database.questionCategory.get(SubIndex).questions.size();

		setTitle(Database.database.questionCategory.get(SubIndex).Name);

		findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Index--;
				if (Index < 0)
					Index = 0;
				setQuestion(SubIndex, Index);
			}
		});
		findViewById(R.id.forwardButton).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (Index + 1 == numQuestion)
					return;
				Index++;
				setQuestion(SubIndex, Index);
			}
		});

		try
		{
			( (ImageView) findViewById(R.id.QuestionImage) ).
					setImageDrawable(Drawable.createFromStream(getAssets().open(Database.database.questionCategory.get(SubIndex).ImageFile), null));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		setQuestion(SubIndex, Index);
	}

	void setQuestion(int SubCategoryID, int QuestionID)
	{
		ImageButton bckButton = (ImageButton) findViewById(R.id.backButton);
		ImageButton fwdButton = (ImageButton) findViewById(R.id.forwardButton);

		if (QuestionID == 0)
		{
			bckButton.setVisibility(View.INVISIBLE);
		}
		else
		{
			bckButton.setVisibility(View.VISIBLE);
		}

		if (QuestionID + 1 == numQuestion)
		{
			fwdButton.setVisibility(View.INVISIBLE);
		}
		else
		{
			fwdButton.setVisibility(View.VISIBLE);
		}

		( (TextView) findViewById(R.id.QuestionNum) ).setText(String.format("%02d", Index + 1));
		( (TextView) findViewById(R.id.QuestionLimit) ).setText("/" + String.format("%02d", numQuestion));

		( (TextView) findViewById(R.id.QuestionText) ).setText(Database.database.questionCategory.get(SubCategoryID).questions.get(QuestionID).getProblem());
		( (TextView) findViewById(R.id.AnswerText) ).
				setText(getResources().getString(R.string.Ans) + ": " + Database.database.questionCategory.get(SubCategoryID).questions.get(QuestionID).getSolution());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				x1 = event.getX();
				break;
			case MotionEvent.ACTION_UP:
				TypedValue typedValue=new TypedValue();
				int height=0;
				if(getTheme().resolveAttribute(android.R.attr.actionBarSize,typedValue,true))
					height = TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
				if(event.getY()<height)
					return true;
				x2 = event.getX();
				float delta = x2 - x1;
				if (Math.abs(delta) > 150)
				{
					if (x2 > x1)
					{
						if (Index == 0)
						{
							return true;
						}
						Index--;
						setQuestion(SubIndex, Index);
						return true;
					}
					else
					{
						if (Index + 1 == numQuestion)
						{
							return true;
						}
						Index++;
						setQuestion(SubIndex, Index);
						return true;
					}
				}
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		if(onTouchEvent(ev))
			return true;
		return super.dispatchTouchEvent(ev);
	}
}
