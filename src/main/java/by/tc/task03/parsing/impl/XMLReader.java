package by.tc.task03.parsing.impl;

import by.tc.task03.parsing.Reader;

import java.io.*;

public class XMLReader implements Reader
{
    private File fileToParse;
    StringBuilder fullXML;

    private static final String LINES_DELIMITER = "\n";
    private static final String NOTHING = "";

    public XMLReader(File fileToParse)
    {
        this.fileToParse = fileToParse;
        this.fullXML = new StringBuilder();
    }

    public String readFullFile() throws FileNotFoundException, IOException
    {
        try
        {
            FileReader fileReader = new FileReader(fileToParse);
            BufferedReader reader = new BufferedReader(fileReader);

            String lineToParse = reader.readLine();
            while(lineToParse != null)
            {
                fullXML.append(lineToParse);
                lineToParse = reader.readLine();
            }
        }
        catch (FileNotFoundException ex)
        {
            throw ex;
        }
        catch (IOException exx)
        {
            throw exx;
        }


        return fullXML.toString().replaceAll(LINES_DELIMITER, NOTHING);
    }
}
