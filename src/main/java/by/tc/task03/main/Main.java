package by.tc.task03.main;

import by.tc.task03.entity.Node;
import by.tc.task03.parsing.impl.XMLParser;
import by.tc.task03.print.impl.ErrorPrinter;
import by.tc.task03.print.impl.TreePrinter;
import by.tc.task03.validation.impl.XMLValidator;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            ErrorPrinter errorPrinter = new ErrorPrinter();

            XMLValidator validator = new XMLValidator(".\\resources\\doc.xml");
            if(validator.validate())
            {
                XMLParser parser = new XMLParser(".\\resources\\doc.xml");

                Node root = parser.parse();
                if(root == null) errorPrinter.print();

                TreePrinter treePrinter = new TreePrinter(root);
                treePrinter.print();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
