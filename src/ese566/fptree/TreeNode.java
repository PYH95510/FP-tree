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
        size = 0;

    }

    public TreeNode(int name) {
        this.name = name;
        this.count = 1;
        children = new HashMap<Integer, TreeNode>();
        next = null;
        parent = null;
        size = 0;
    }

    public TreeNode(int name, long count) {
        this.name = name;
        this.count = count;
        children = new HashMap<Integer, TreeNode>();
        next = null;
        parent = null;
        size = 0;
    }

    public TreeNode addChild(int item) {
        if (children.containsKey(item)) {
            return children.get(item);
        } else {
            TreeNode newChild = new TreeNode(item);
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

    public int getSize() {
        return this.size;
    }

    public int getName() {
        return this.name;
    }

    public String toString() {
        return "name: " + name + " count: " + count +
                +children.size();
    }
}
