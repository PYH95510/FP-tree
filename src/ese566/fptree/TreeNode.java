package ese566.fptree;

import java.util.HashMap;
import java.util.List;

public class TreeNode 
{
    private int m_name;
    private HashMap<Integer, TreeNode> m_children;

    private TreeNode m_parent;
    private TreeNode m_next;
    private long m_count;
    private int m_size;

    public TreeNode() 
    {
        this.m_name = 0;
        this.m_count = 1;
        m_children = new HashMap<Integer, TreeNode>();

        m_next = null;
        m_parent = null;
    }

    public TreeNode(int name) 
    {
        this.m_name = name;
        this.m_count = 1;
        m_children = new HashMap<Integer, TreeNode>();
        m_next = null;
        m_parent = null;
    }

    public TreeNode addChild(int item, DataSet dataSet) 
    {
        if (m_children.containsKey(item)) 
        {
            TreeNode curNode = m_children.get(item);
            curNode.m_count += 1;
            return curNode;
        } 
        else 
        {
            TreeNode newChild = new TreeNode(item);
            newChild.m_size = dataSet.getAttribCount(item);
            m_children.put(item, newChild);
            return newChild;
        }
    }

    public void addCount(int count) 
    {
        this.m_count += count;
    }

    public void addCount() 
    {
        this.m_count += 1;
    }

    public void setNextNode(TreeNode next) 
    {
        this.m_next = next;
    }

    public void setParent(TreeNode parent) 
    {
        this.m_parent = parent;
    }

    public TreeNode getChilde(int index) 
    {
        return m_children.get(index);
    }

    public int hasChild(int name) 
    {
        for (int i = 0; i < m_children.size(); i++)
            if (m_children.get(i).m_name == (name))
                return i;
        return -1;
    }

    public void insert(int index, TreeNode root, List<TreeNode> node) 
    {
        if (root.m_next == null) {
            root.m_next = node.get(index);
        } else {
            if (root.m_name == index) {
                root.addCount();
            } else {
                insert(index, root.m_next, node); // need method to add brother node
            }
        }
    }

    public void pruneChildren(int minimumSupport) 
    {
        HashMap<Integer, TreeNode> tempChildrens = (HashMap<Integer, TreeNode>) m_children.clone();
        for (int childKey : m_children.keySet()) 
        {
            TreeNode childNode = m_children.get(childKey);
            if (childNode.m_count < minimumSupport) 
            {
                tempChildrens.remove(childKey);
            } 
            else 
            {
                childNode.pruneChildren(minimumSupport);
            }
        }
        m_children = tempChildrens;
    }

    public int getSize() 
    {
        return this.m_size;
    }

    public int getName() 
    {
        return this.m_name;
    }

    public String toString() 
    {
        return "Name: " + m_name + " FP-count: " + m_count + " Frequency: " + m_size;
    }

    public void printName(StringBuilder sb, int depth) 
    {
        for (int i = 0; i < depth; ++i)
        {
            sb.append("  ");
        }
        sb.append(toString() + "\n");
        for (TreeNode childNode : m_children.values()) 
        {
            childNode.printName(sb, depth + 1);
        }
    }

    public void buildTable(HashMap<Integer, TreeNode> table, HashMap<Integer, Integer> support)
    {
        if (!table.containsKey(m_name))
        {
            support.put(m_name, 0);
            table.put(m_name, this);
        }
        else
        {
            TreeNode node = table.get(m_name);
            m_next = node;
            table.put(m_name, this);
        }
        support.put(m_name, support.get(m_name) + 1);

        for (TreeNode childNode: m_children.values())
        {
            childNode.buildTable(table, support);
        }
    }
}
