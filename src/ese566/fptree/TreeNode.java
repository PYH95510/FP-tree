package ese566.fptree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeNode {

    int name;
    HashMap<Integer, TreeNode> children;

    TreeNode parent;
    TreeNode next;
    TreeNode prev;
    long count;
    int size;

    public TreeNode() {
        this.name = 0;
        this.count = 1;
        children = new HashMap<Integer, TreeNode>();

        next = null;
        parent = null;
    }

    public TreeNode(int name) {
        this.name = name;
        this.count = 1;
        children = new HashMap<Integer, TreeNode>();
        next = null;
        parent = null;
    }

    public TreeNode addChild(int item, DataSet dataSet) 
    {
        if (children.containsKey(item)) 
        {
            TreeNode curNode = children.get(item);
            curNode.count += 1;
            return curNode;
        } 
        else 
        {
            TreeNode newChild = new TreeNode(item);
            newChild.size = dataSet.getAttribCount(item);
            children.put(item, newChild);
            return newChild;
        }
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void addCount() {
        this.count += 1;
    }

    public void setNextNode(TreeNode next) {
        this.next = next;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getChilde(int index) {
        return children.get(index);
    }

    public int hasChild(int name) {
        for (int i = 0; i < children.size(); i++)
            if (children.get(i).name == (name))
                return i;
        return -1;
    }

    public void insert(int index, TreeNode root, List<TreeNode> node) {
        if (root.next == null) {
            root.next = node.get(index);
        } else {
            if (root.name == index) {
                root.addCount();
            } else {
                insert(index, root.next, node); // need method to add brother node
            }
        }
    }

    public void pruneChildren(int minimumSupport)
    {
        HashMap<Integer, TreeNode> tempChildrens = (HashMap<Integer, TreeNode>)children.clone();
        for (int childKey: children.keySet())
        {
            TreeNode childNode = children.get(childKey);
            if (childNode.count < minimumSupport)
            {
                tempChildrens.remove(childKey);
            }
            else
            {
                childNode.pruneChildren(minimumSupport);
            }
        }
        children = tempChildrens;
    }

    public int getSize() {
        return this.size;
    }

    public int getName() {
        return this.name;
    }

    public String toString() 
    {
        return "- Name: " + name + " FP-count: " + count + " Frequency: " + size;
    }

    public void printName(StringBuilder sb, int depth)
    {
        for (int i = 0; i < depth; ++i)
        {
            sb.append("  ");
        }
        sb.append(toString() + "\n");

        for (TreeNode childNode: children.values())
        {
            childNode.printName(sb, depth + 1);
        }
    }
}
