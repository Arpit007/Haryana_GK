package com.nitkkr.gawds.haryanagk;

/**
 * Created by Home Laptop on 01-Jul-16.
 */

import android.content.Context;
import android.content.res.AssetManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Database
{
    int ImageCount;
    Context context;
    String FileName="database.xml";
    public ArrayList<QuestionCategory> questionCategory;

    static Database database;

    public Database(Context context)
    {
        this.context=context;
        ImageCount=context.getResources().obtainTypedArray(R.array.ImageID).length();
        database=this;
        questionCategory=new ArrayList<QuestionCategory>();
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

                    NodeList nList = doc.getElementsByTagName("Category");

                    String Name,Description;
                    int ImageId;

                    for (int temp = 0; temp < nList.getLength(); temp++)
                    {

                        Node nNode = nList.item(temp);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;

                            Name=eElement.getAttribute("Name");
                            Description=eElement.getAttribute("Description");
                            ImageId=Integer.parseInt(eElement.getAttribute("ImageID"));

                            QuestionCategory Category=new QuestionCategory(Name,Description,ImageId);

                            NodeList itemList=((Element) nNode).getElementsByTagName("Item");

                            String Question, Answer;

                            for(int x=0;x<itemList.getLength();x++)
                            {
                                Node itemNode=itemList.item(x);
                                if(itemNode.getNodeType()==Node.ELEMENT_NODE)
                                {
                                    Element element=(Element)itemNode;
                                    Question=element.getElementsByTagName("Question").item(0).getTextContent();
                                    Answer=element.getElementsByTagName("Answer").item(0).getTextContent();
                                    Category.AddQuestion(Question,Answer);
                                }
                            }
                            questionCategory.add(Category);
                        }
                    }
                    int u=Integer.parseInt("12");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

    }

    public int getImageID(int ImageID)
    {
        if(ImageID>=ImageCount)
            return 0;
        return ImageID;
    }
}


class QuestionCategory
{
    private class Question
    {
        String Problem;
        String Solution;

        public Question(String Problem, String Solution)
        {
            this.Problem=Problem;
            this.Solution=Solution;
        }
    }

    ArrayList<Question> questions;
    String Name;
    String Description;
    int ImageId;

    public QuestionCategory(String Name, String Description, int ImageId)
    {
        questions=new ArrayList<Question>();
        this.Name=Name;
        this.Description=Description;
        this.ImageId=Database.database.getImageID(ImageId);
    }

    public void AddQuestion(String Question, String Answer)
    {
        questions.add(new Question(Question,Answer));
    }
}
