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
        HashMap<Integer, Integer> child = new HashMap<>();

    }

    /**
     * @param item
     */
    public void generateTree(DataSet input) {

        Set<Integer> keysets = new HashSet<Integer>();
        keysets.addAll(input.getKeytransaction());
        List<TreeNode> paths = new ArrayList<TreeNode>();

        for (int key : keysets) { // second iteration
            ArrayList<Integer> items = new ArrayList<Integer>(input.m_transactions.get(key)); // getting each value for
                                                                                              // each key
            fpTree children = new fpTree();
            for (int j = 0; j++ < items.size(); j++) {
                if (paths.size() == 0) {
                    paths.add(new TreeNode(items.get(0))); // if path is 0 add ith node into paths
                    fptree.addPath(paths);
                } else if (!((TreeNode) paths).hasPath(items.get(0))) { // if it is not included in the path
                    paths.add(new TreeNode(items.get(0)));
                    fptree.addPath(paths);
                } else if (((TreeNode) paths).hasPath(items.get(0)) == true) { // if it is already there, then just
                                                                               // increment count
                    int n = paths.indexOf(items.get(0));
                    paths.get(n).addCount();
                }
                if (j > 0) { // if it is not the starting node
                    if (fptree.hasChild(j) != -1) { // if there is child
                        fptree.insert(j, fptree, paths);
                    }
                }

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
