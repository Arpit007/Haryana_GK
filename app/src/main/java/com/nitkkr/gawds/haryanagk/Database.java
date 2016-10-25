package com.nitkkr.gawds.haryanagk;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Database
{
	Context context;
	String FileName = "Questions/database.xml";
	public ArrayList<QuestionCategory> questionCategory;

	static Database database;

	public Database(Context context)
	{
		this.context = context;
		database = this;
		questionCategory = new ArrayList<>();
		getData();
	}

	private void getData()
	{
		try
		{
			DocumentBuilderFactory dbFactory
					= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(context.getAssets().open(FileName));
			doc.getDocumentElement().normalize();

			NodeList fileList = doc.getElementsByTagName("FileName");

			for (int FileIndex = 0; FileIndex < fileList.getLength(); FileIndex++)
			{
				String QuestionFileName = null;
				Node FileNode = fileList.item(FileIndex);

				if (FileNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) FileNode;
					QuestionFileName = element.getChildNodes().item(0).getTextContent();
				}

				if (QuestionFileName != null)
				{
					DocumentBuilderFactory dataFactory
							= DocumentBuilderFactory.newInstance();
					DocumentBuilder dataBuilder = dataFactory.newDocumentBuilder();
					Document datadoc = dataBuilder.parse(context.getAssets().open("Questions/" + QuestionFileName));
					datadoc.getDocumentElement().normalize();

					String Name, ImageFile;

					Node CategoryNode = datadoc.getChildNodes().item(0);

					if (CategoryNode.getNodeType() == Node.ELEMENT_NODE)
					{
						Element element = (Element) CategoryNode;
						Name = element.getAttribute("Name");
						if (Name.equals(""))
						{
							Name = QuestionFileName;
						}
						ImageFile = "Images/" + element.getAttribute("ImageName");

						QuestionCategory Category = new QuestionCategory(Name, ImageFile);

						NodeList QuestionList = element.getElementsByTagName("Item");

						String Question, Answer;

						for (int x = 0; x < QuestionList.getLength(); x++)
						{
							Node itemNode = QuestionList.item(x);
							if (itemNode.getNodeType() == Node.ELEMENT_NODE)
							{
								Element myelement = (Element) itemNode;
								Question = myelement.getElementsByTagName("Question").item(0).getTextContent().trim().replaceAll(" +", " ");
								Answer = ( myelement.getElementsByTagName("Answer").item(0).getTextContent() ).trim().replaceAll(" +", " ");
								Category.AddQuestion(Question, Answer);
							}
						}
						questionCategory.add(Category);
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


class QuestionCategory
{
	class Question
	{
		private String Problem;
		private String Solution;

		public Question(String Problem, String Solution)
		{
			this.Problem = Problem;
			this.Solution = Solution;
		}

		public String getProblem()
		{
			return Problem;
		}

		public String getSolution()
		{
			return Solution;
		}
	}

	ArrayList<Question> questions;
	String Name;
	String ImageFile;

	public QuestionCategory(String Name, String ImageFile)
	{
		questions = new ArrayList<>();
		this.Name = Name;
		this.ImageFile = ImageFile;
	}

	public void AddQuestion(String Question, String Answer)
	{
		questions.add(new Question(Question, Answer));
	}
}
