package ese566.fptree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class FPTree {
    private TreeNode root = new TreeNode(0);
    private DataSet m_dataset;
    private HashMap<Integer, TreeNode> m_table = new HashMap<Integer, TreeNode>();

    private FPTree() {

    }

    /**
     * @param item
     */
    static public FPTree create(DataSet input) {
        FPTree fpTree = new FPTree();
        fpTree.m_dataset = input;

        for (int key : input.getKeytransaction()) {
            // second iteration
            ArrayList<Integer> items = new ArrayList<Integer>(input.m_transactions.get(key)); // getting each value for
            TreeNode curNode = fpTree.root;
            // each key
            for (int j = 0; j < items.size(); j++) {
                int itemId = items.get(j);
                TreeNode nextNode = curNode.addChild(itemId, input, fpTree.m_table);
                curNode = nextNode;
            }
        }

        return fpTree;
    }

    public void prune(int minimumSupport) {
        root.pruneChildren(minimumSupport);
    }

    public void extractPattern(int minSupport) {
        for (var iter = m_dataset.iterateFromMinSupport(minSupport); iter.hasNext();) {
            HashMap<Integer, Integer> condTable = new HashMap<>();
            Integer itemId = iter.next();
            TreeNode curNode = m_table.getOrDefault(itemId, null);
            while (curNode != null) {
                condTable.put(itemId, condTable.getOrDefault(itemId, 0) + 1);
                TreeNode curParentNode = curNode.getParent();
                while (true) {
                    if (curParentNode.isRoot()) {
                        break;
                    }
                    condTable.put(curParentNode.getName(), condTable.getOrDefault(curParentNode.getName(), 0) + 1);
                    curParentNode = curParentNode.getParent();
                }

                curNode = curNode.getNext();
            }

            // Re-iterate the tree to gather the entries that have higher support than the
            // minimum
            HashSet<ArrayList<Integer>> entries = new HashSet<>();
            curNode = m_table.getOrDefault(itemId, null);
            while (curNode != null) {
                ArrayList<Integer> entry = new ArrayList<>();
                entry.add(curNode.getName());

                TreeNode curParentNode = curNode.getParent();
                while (true) {
                    if (curParentNode.isRoot()) {
                        break;
                    }

                    if (condTable.getOrDefault(curParentNode.getName(), 0) >= minSupport) {
                        entry.add(curParentNode.getName());
                    }
                    curParentNode = curParentNode.getParent();
                }
                // Only add if there are more than 1 item in the frequent set
                if (entry.size() > 1) {
                    entries.add(entry);
                }

                curNode = curNode.getNext();
            }

            // Print the result
            StringBuilder sb = new StringBuilder();
            for (var entry : entries) {
                if (entry.size() <= 1) {
                    continue;
                }
                sb.append("[");
                for (int i = 0; i < entry.size(); ++i) {
                    if (i != entry.size() - 1) {
                        sb.append(entry.get(i) + ", ");
                    } else {
                        sb.append(entry.get(i));
                    }
                }
                sb.append("] Confidence:" + m_dataset.getConfidence(entry) + "\n");
            }
            System.out.print(sb.toString());
        }
    }

    public void printName(StringBuilder sb) {
        root.printName(sb, 0);
    }
}