package ese566.fptree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    int name;
    List<TreeNode> children;
    TreeNode parent;
    TreeNode next;
    TreeNode prev;
    long count;

    public TreeNode() {
        this.name = 0;
        this.count = -1;
        children = new ArrayList<TreeNode>();
        next = null;
        parent = null;
    }

    public TreeNode(int name) {
        this.name = name;
        this.count = 1;
        children = new ArrayList<TreeNode>();
        next = null;
        parent = null;
    }

    public TreeNode(int name, long count) {
        this.name = name;
        this.count = count;
        children = new ArrayList<TreeNode>();
        next = null;
        parent = null;
    }

    public void addChild(TreeNode child) {
        children.add(child);
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

    public String toString() {
        return "name: " + name + " count: " + count +
                +children.size();
    }
}
