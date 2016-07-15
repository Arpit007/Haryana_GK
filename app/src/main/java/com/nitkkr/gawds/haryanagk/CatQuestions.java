package com.nitkkr.gawds.haryanagk;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class CatQuestions extends AppCompatActivity
{
	int Index,SubIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_view);

		Intent QuestionIntent=getIntent();

		Index=QuestionIntent.getIntExtra("QuestionID",0);
		SubIndex=QuestionIntent.getIntExtra("SubCategoryID",0);
		QuestionIntent.putExtra("QuestionID",-1);

		ImageButton bckButton=(ImageButton)findViewById(R.id.backButton);
		ImageButton fwdButton=(ImageButton)findViewById(R.id.forwardButton);
		bckButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Index-=2;
				if(Index<0)
					Index=0;
				setQuestion(SubIndex,Index);
			}
		});
		fwdButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Index+=2;
				if(Index>=Database.database.questionCategory.get(SubIndex).questions.size())
					Index=Database.database.questionCategory.get(SubIndex).questions.size()-1;
				setQuestion(SubIndex,Index);
			}
		});

		setQuestion(SubIndex,Index);
	}

	void setQuestion(int SubCategoryID, int QuestionID)
	{
		setTitle(Database.database.questionCategory.get(SubCategoryID).Name);

		int numQuestion=Database.database.questionCategory.get(SubCategoryID).questions.size();

		ImageButton bckButton=(ImageButton)findViewById(R.id.backButton);
		ImageButton fwdButton=(ImageButton)findViewById(R.id.forwardButton);

		if(QuestionID==0)
			bckButton.setVisibility(View.INVISIBLE);
		else
			bckButton.setVisibility(View.VISIBLE);

		if(QuestionID+1==numQuestion)
			fwdButton.setVisibility(View.INVISIBLE);
		else
			fwdButton.setVisibility(View.VISIBLE);



		((TextView)findViewById(R.id.QuestionNum)).setText(String.format("%02d",Index+1));

		((TextView)findViewById(R.id.QuestionLimit)).setText("/"+String.format("%02d",numQuestion));

		try
		{
			ImageView iv = (ImageView)findViewById(R.id.QuestionImage);
			iv.setImageDrawable(Drawable.createFromStream(getAssets().open(Database.database.questionCategory.get(SubCategoryID).ImageFile), null));

			TextView questionText=(TextView)findViewById(R.id.QuestionText);
			TextView answerText=(TextView)findViewById(R.id.AnswerText);

			questionText.setText(Database.database.questionCategory.get(SubCategoryID).Name);
			String answer=Database.database.questionCategory.get(SubCategoryID).questions.get(QuestionID).getSolution();
			if(Database.database.questionCategory.get(SubCategoryID).questions.size()>QuestionID+1)
				answer+=("\n\n"+Database.database.questionCategory.get(SubCategoryID).questions.get(QuestionID+1).getSolution());
			answerText.setText(answer);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}

	}
}
