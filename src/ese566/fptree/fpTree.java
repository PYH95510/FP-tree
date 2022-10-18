package ese566.fptree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ese566.fptree.DataReader;

public class fpTree {

    static Map<TreeNode, Integer> tree = new HashMap<TreeNode, Integer>();

    fpTree() {
        HashMap<Integer, Integer> child = new HashMap<>();

    }

    public void generateTree(DataSet item) {
        if (tree.size() == 0) {

            for (HashMap.Entry<Integer, ArrayList<Integer>> ent : item.m_transactions.entrySet()) {

            }
        }

    }

    public static Map<TreeNode, Integer> addLeaf(DataSet data) {

        if (tree.size() == 0) {
            // if size 0 or not previously existed tree nodes, add it
            tree.add();
        } else {
            // if it is already existed tree nodes, then just increment the number in the
            // value
        }

        return tree;
    }

}
