package ese566.fptree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ese566.fptree.DataReader;

public class fpTree {

    static Map<TreeNode, Integer> tree = new HashMap<TreeNode, Integer>();
    TreeNode fptree = new TreeNode(0);

    fpTree() {

    }

    /**
     * @param item
     */
    public void generateTree(DataSet input) {

        Set<Integer> keysets = new HashSet<Integer>();
        keysets.addAll(input.getKeytransaction());

        for (int key : keysets) { // second iteration
            ArrayList<Integer> items = new ArrayList<Integer>(input.m_transactions.get(key)); // getting each value for
            TreeNode curNode = fptree; // each key
            for (int j = 0; j++ < items.size(); j++) {
                int itemId = items.get(j);
                TreeNode nextNode = curNode.addChild(itemId);
                curNode = nextNode;
            }
        }

    }

    public static Map<TreeNode, Integer> addLeaf(DataSet data) {

        if (tree.size() == 0) {
            // if size 0 or not previously existed tree nodes, add it

        } else {
            // if it is already existed tree nodes, then just increment the number in the
            // value
        }

        return tree;
    }

}
