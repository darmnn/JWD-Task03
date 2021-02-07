package by.tc.task03.parsing.impl;

import by.tc.task03.entity.Attribute;
import by.tc.task03.entity.Node;
import by.tc.task03.parsing.FileParser;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser implements FileParser
{
    private File fileToParse;
    private Node parsedRoot;
    private String fullXML;

    private static final String TAG = "(<.[^(><.)]+>)";
    private static final String ATTRIBUTE_DELIMITER = "=";
    private static final String QUOTE = "\"";
    private static final String NOTHING = "";
    private static final String SPACE_BETWEEN_ATTRIBUTES = " ";
    private static final String ALL_SPACES = "\\s+";
    private static final String OPENING_TAG = "<";
    private static final String CLOSING_TAG = "</";

    private static final int ATTR_NAME_ID = 0;
    private static final int ATTR_VALUE_ID = 1;
    private static final int START_OF_THE_STRING = 0;
    private static final int SKIP_OPENING_AND_CLOSING_TAGS = 2;

    public XMLParser(String path)
    {
       fileToParse = new File(path);
       parsedRoot = null;
       fullXML = null;
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


    private ArrayList<Attribute> getAttributes(String tag)
    {
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();

        int attributesStartId = tag.lastIndexOf(SPACE_BETWEEN_ATTRIBUTES);

        if(attributesStartId != -1)
        {
            String[] allAttributes = tag.substring(attributesStartId+1).trim().split(ALL_SPACES);

            for(int i = 0; i < allAttributes.length; i++)
            {
                String fullAttribute = allAttributes[i];
                String attrName = fullAttribute.split(ATTRIBUTE_DELIMITER)[ATTR_NAME_ID].trim();
                String attrValue = fullAttribute.split(ATTRIBUTE_DELIMITER)[ATTR_VALUE_ID].trim().replaceAll(QUOTE, NOTHING);

                Attribute attribute = new Attribute(attrName, attrValue);
                attributes.add(attribute);
            }

        }

        return attributes;
    }

    private String getContent(String textToParse, String tagName, int contentStart)
    {
        int contentEnd = textToParse.indexOf(CLOSING_TAG + tagName, contentStart);

        String content = textToParse.substring(contentStart, contentEnd);

        return content;
    }

    private void buildTree(String textToParse, Node parent)
    {
        Pattern tagPattern = Pattern.compile(TAG);
        Matcher tagMatcher = tagPattern.matcher(textToParse);

        String tag;
        int i = 0;
        ArrayList<Node> children = new ArrayList<Node>();

        while(tagMatcher.find(i))
        {
            Node currentNode = new Node();
            currentNode.setParent(parent);

            children.add(currentNode);
            parent.setChildren(children);

            tag = textToParse.substring(tagMatcher.start()+1, tagMatcher.end()-1);

            String tagName = getTagName(tag);
            currentNode.setName(tagName);

            ArrayList<Attribute> attributes = getAttributes(tag);
            currentNode.setAttributes(attributes);

            int contentStart = tagMatcher.end();
            String content = getContent(textToParse, currentNode.getName(), contentStart);

            i = contentStart + content.length() + SKIP_OPENING_AND_CLOSING_TAGS;

            if(!content.contains(OPENING_TAG))
            {
                currentNode.setContent(content.trim());
            }
            else
            {
                buildTree(content, currentNode);
            }
        }

    }

    public Node parse() throws FileNotFoundException, IOException
    {
        try
        {
            XMLReader reader = new XMLReader(fileToParse);
            fullXML = reader.readFullFile();
            if(fullXML == null) return null;

            parsedRoot = new Node();
            buildTree(fullXML, parsedRoot);

            parsedRoot = parsedRoot.getChildren().get(0);

            return parsedRoot;
        }
        catch (FileNotFoundException ex)
        {
            throw ex;
        }
        catch (IOException ex)
        {
            throw ex;
        }
    }
}
