package com.nitkkr.gawds.haryanagk;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class CatQuestions extends AppCompatActivity
{
	int Index,SubIndex;
	float x1,x2;
	int numQuestion;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_view);

		Intent QuestionIntent=getIntent();

		Index=QuestionIntent.getIntExtra("QuestionID",0);
		SubIndex=QuestionIntent.getIntExtra("SubCategoryID",0);
		QuestionIntent.putExtra("QuestionID",-1);

		setTitle(Database.database.questionCategory.get(SubIndex).Name);

		numQuestion=Database.database.questionCategory.get(SubIndex).questions.size();

		ImageButton bckButton=(ImageButton)findViewById(R.id.backButton);
		ImageButton fwdButton=(ImageButton)findViewById(R.id.forwardButton);

		bckButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Index--;
				if(Index<0)
					Index=0;
				setQuestion(SubIndex,Index);
			}
		});
		fwdButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int q1=(numQuestion%2==0)?numQuestion/2:(numQuestion/2+1);
				if (Index+1 == q1)
				{
					return;
				}
				Index++;
				setQuestion(SubIndex,Index);
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
		setQuestion(SubIndex,Index);
	}

	void setQuestion(int SubCategoryID, int QuestionID)
	{
		ImageButton bckButton=(ImageButton)findViewById(R.id.backButton);
		ImageButton fwdButton=(ImageButton)findViewById(R.id.forwardButton);

		if(QuestionID==0)
			bckButton.setVisibility(View.INVISIBLE);
		else
			bckButton.setVisibility(View.VISIBLE);

		int q1=(numQuestion%2==0)?numQuestion/2:(numQuestion/2+1);

		if(Index+1==q1)
			fwdButton.setVisibility(View.INVISIBLE);
		else
			fwdButton.setVisibility(View.VISIBLE);

		((TextView)findViewById(R.id.QuestionNum)).setText(String.format("%02d",Index+1));

		int q=(numQuestion%2==0)?numQuestion/2:(numQuestion/2+1);
		((TextView)findViewById(R.id.QuestionLimit)).setText("/"+String.format("%02d",q));

		try
		{
			TextView questionText=(TextView)findViewById(R.id.QuestionText);
			TextView answerText=(TextView)findViewById(R.id.AnswerText);

			questionText.setText(Database.database.questionCategory.get(SubCategoryID).Name);
			String answer=Database.database.questionCategory.get(SubCategoryID).questions.get(2*QuestionID).getSolution();
			if(numQuestion>2*QuestionID+1)
				answer+=("\n\n"+Database.database.questionCategory.get(SubCategoryID).questions.get(2*QuestionID+1).getSolution());
			answerText.setText(answer);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
						int q1=(numQuestion%2==0)?numQuestion/2:(numQuestion/2+1);
						if (Index+1 == q1)
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
