package by.tc.task03.validation.impl;

import by.tc.task03.parsing.impl.XMLReader;
import by.tc.task03.validation.FileValidator;

import java.io.File;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLValidator implements FileValidator
{
    private File fileToValidate;

    private static final String TAG = "(<.[^(><.)]+>)";
    private static final String SPACE_BETWEEN_ATTRIBUTES = " ";
    private static final String CLOSING_TAG = "</";

    private static final int START_OF_THE_STRING = 0;

    public XMLValidator(String path)
    {
        fileToValidate = new File(path);
    }

    private String getTagName(String tag)
    {
        String tagName;

        if(tag.lastIndexOf(SPACE_BETWEEN_ATTRIBUTES) != -1)
        {
            int attributesStartId = tag.lastIndexOf(SPACE_BETWEEN_ATTRIBUTES);
            tagName = tag.substring(START_OF_THE_STRING, attributesStartId);
        }
        else tagName = tag;

        return tagName;
    }

    public boolean validate()
    {
        XMLReader reader = new XMLReader(fileToValidate);
        String fullXML = reader.readFullFile();
        if(fullXML == null) return false;

        LinkedList<String> tagStack = new LinkedList<String>();

        Pattern tagPattern = Pattern.compile(TAG);
        Matcher tagMatcher = tagPattern.matcher(fullXML);

        int i = 0;

        while(tagMatcher.find(i))
        {
            String tag = fullXML.substring(tagMatcher.start()+1, tagMatcher.end()-1);
            i = tagMatcher.end();

            if(!tag.contains("/"))
            {
                String tagName = getTagName(tag);

                tagStack.push(tagName);

                if(fullXML.contains(CLOSING_TAG + tagName))
                    tagStack.pop();
            }
        }

        if(tagStack.isEmpty()) return true;
        else return false;
    }
}
