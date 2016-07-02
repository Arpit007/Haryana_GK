# Haryana_GK
Hello, Welcome to our new App

//////////////////////////////////////////////////////////////////// Introduction ////////////////////////////////////////////////////////////////////////////////////////////


The App is divided into Categories:

1. Main Page
    The Page navigates to 3 different Views
        1.Contact Us
            Redirects the User to Mail App to email the client at given email address.
        2. About Us
            A page for credits to the client and the developers
        3. General Knowledge
           1. The Page is divided among different Categories of GK like Modern Era, Medivial etc...
                Each Category is shown in a form of card, with a relative Image, Name of Category, and short descriptive Text
                1. Each Card Redirects to Page containing list of Questions related to that GK category
                    1. Each item of List then hence navigates to Question Page, showing both Question and Answer
                        It also features to move back and forth among the questions of the given category


///////////////////////////////////////////////////////////////////// Feeding Data to App ////////////////////////////////////////////////////////////////////////////////////

The App feeds on Data in XML form

Directory Structure
1. All XML Data files to be stored in App's assets/Questions folder
2. ALl related Images to be storedin App's assets/Images folder

///////////////////////////////////////////////////////////////////// Data Format ///////////////////////////////////////////////////////////////////////////////////////////

Question of each category go in their own seperate XML file.
These filenames to be added in the file "database.xml"

For example, my QuestionBank files are File1.xml, File2.xml, File3.xml, then
Contents of "database.xml" are:

        <?xml version="1.0"?>
        <QuestionBank>
            <FileName>File1.xml</FileName>
            <FileName>File2.xml</FileName>
            <FileName>File3.xml</FileName>
        </QuestionBank>

For each Question Category file, each Category has a Name, Description, and an Image for its Main Card

    For example <Category Name="Modern Haryana" Description="1900AD-Present" ImageName="a1.jpg">

    Each Question is in an Item tag.
    Example Question:
        <Item>
            <Question>When was Haryana formed?</Question>
            <Answer>1st November, 1966</Answer>
        </Item>

So sample file File1.xml

<?xml version="1.0"?>
    <Category Name="Modern Haryana" Description="1900AD-Present" ImageName="a1.jpg">
        <Item>
            <Question>When was Haryana formed?</Question>
            <Answer>1st November, 1966</Answer>
        </Item>
        <Item>
            <Question>I am?</Question>
            <Answer>Indian</Answer>
        </Item>
        <Item>
            <Question>My Name is?</Question>
            <Answer>Random123</Answer>
        </Item>
        <Item>
            <Question>I live in?</Question>
            <Answer>Gurgaon, Haryana</Answer>
        </Item>
    </Category>
