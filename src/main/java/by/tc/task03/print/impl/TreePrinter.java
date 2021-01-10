package by.tc.task03.print.impl;

import by.tc.task03.entity.Node;
import by.tc.task03.print.Printer;

import java.util.ArrayList;

public class TreePrinter implements Printer
{
    private Node nodeToPrint;

    public TreePrinter(Node nodeToPrint)
    {
        this.nodeToPrint = nodeToPrint;
    }

    public void recursivePrint(Node nodeToPrint)
    {
        if(nodeToPrint != null)
        {
            Node root = nodeToPrint;

            if(root.getAttributes().size() > 0)
                System.out.println(root.getAttributes().get(0).getValue() + ".");

            if(root.getContent() != null)
                System.out.println("    " + root.getContent());

            ArrayList<Node> children = root.getChildren();

            if(children != null)
            {
                for(int i = 0; i < children.size(); i++)
                {
                    root = children.get(i);
                    if(root == null) return;
                    recursivePrint(root);
                }
            }
        }
        else return;
    }

    @Override
    public void print()
    {
        recursivePrint(nodeToPrint);
    }
}
