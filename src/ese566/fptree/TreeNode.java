package ese566.fptree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    String idName;
    List<TreeNode> children;
    TreeNode parent;
    TreeNode next;
    long count;

    public TreeNode() {
        this.idName = null;
        this.count = -1;
        children = new ArrayList<TreeNode>();
        next = null;
        parent = null;
    }

    public TreeNode(String idName) {
        this.idName = idName;
        this.count = 1;
        children = new ArrayList<TreeNode>();
        next = null;
        parent = null;
    }

    public TreeNode(String idName, long count) {
        this.idName = idName;
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

    public int hasChild(String idName) {
        for (int i = 0; i < children.size(); i++)
            if (children.get(i).idName.equals(idName))
                return i;
        return -1;
    }

    public String toString() {
        return "id: " + idName + " count: " + count +
                +children.size();
    }
}
