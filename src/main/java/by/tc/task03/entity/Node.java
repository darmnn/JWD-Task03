package by.tc.task03.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Node
{
    private String name;
    private ArrayList<Node> children;
    private ArrayList<Attribute> attributes;
    private String content;
    private Node parent;

    public Node()
    {
    }

    public Node(String name, ArrayList<Node> children, ArrayList<Attribute> attributes, String content, Node parent) {
        this.name = name;
        this.children = children;
        this.attributes = attributes;
        this.content = content;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", children=" + children +
                ", attributes=" + attributes +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name) &&
                children.equals(node.children) &&
                attributes.equals(node.attributes) &&
                content.equals(node.content) &&
                parent.equals(node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, attributes, content, parent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
