package by.tc.task03.main;

import by.tc.task03.entity.Node;
import by.tc.task03.parsing.impl.XMLParser;
import by.tc.task03.print.impl.ErrorPrinter;
import by.tc.task03.print.impl.TreePrinter;
import by.tc.task03.validation.impl.XMLValidator;

public class Main
{
    public static void main(String[] args)
    {
        ErrorPrinter errorPrinter = new ErrorPrinter();

        XMLValidator validator = new XMLValidator("src\\main\\resources\\doc.xml");
        if(validator.validate())
        {
            XMLParser parser = new XMLParser("src\\main\\resources\\doc.xml");

            Node root = parser.parse();
            if(root == null) errorPrinter.print();

            TreePrinter treePrinter = new TreePrinter(root);
            treePrinter.print();
        }
    }
}
