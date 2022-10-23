package ese566.fptree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ese566.fptree.DataReader;

public class FPTree 
{
    TreeNode root = new TreeNode(0);

    private FPTree() 
    {

    }

    /**
     * @param item
     */
    static public FPTree create(DataSet input, HashMap<Integer, TreeNode> table) 
    {
        FPTree fpTree = new FPTree();

        for (int key : input.getKeytransaction()) 
        { // second iteration
            ArrayList<Integer> items = new ArrayList<Integer>(input.m_transactions.get(key)); // getting each value for
            TreeNode curNode = fpTree.root; // each key
            for (int j = 0; j < items.size(); j++) 
            {
                int itemId = items.get(j);
                TreeNode nextNode = curNode.addChild(itemId, input);
                curNode = nextNode;
            }
        }

        return fpTree;
    }

    public void prune(int minimumSupport) 
    {
        root.pruneChildren(minimumSupport);
    }

    public void buildTable(HashMap<Integer, TreeNode> table, HashMap<Integer, Integer> support)
    {
        root.buildTable(table, support);
    }

    public void printName(StringBuilder sb) 
    {
        root.printName(sb, 0);
    }
}