package ese566.fptree;
import java.util.ArrayList;
import java.util.HashMap;

import ese566.fptree.DataReader;
import ese566.fptree.FPTree;

public class App 
{
    public static void main(String[] args)
    {
        DataSet dataSet = DataReader.readNetDFile("/devel/FP-tree/data/anonymous-msweb.data");

        FPTree fpTree = FPTree.create(dataSet);
        
        StringBuilder sb = new StringBuilder();
        fpTree.printName(sb);
        System.out.println(sb.toString());

        fpTree.extractPattern(220);
    }    
}
