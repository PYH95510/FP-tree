package ese566.fptree;
import java.util.HashMap;

import ese566.fptree.DataReader;
import ese566.fptree.FPTree;

public class App 
{
    public static void main(String[] args)
    {
        DataSet dataSet = DataReader.readNetDFile("/devel/FP-tree/data/anonymous-msweb.data");
        // System.out.println(dataSet.toString());
        FPTree fpTree = FPTree.generateTree(dataSet);
        fpTree.prune(50);
        StringBuilder sb = new StringBuilder();
        fpTree.printName(sb);
        System.out.println(sb.toString());

        HashMap<Integer, TreeNode> table = new HashMap<>();
        HashMap<Integer, Integer> support = new HashMap<>();

        fpTree.buildTable(table, support);
    }    
}
