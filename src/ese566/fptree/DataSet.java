package ese566.fptree;

import java.util.ArrayList;
import java.util.HashMap;

public class DataSet {
    private HashMap<Integer, Integer> m_attribCount;
    public HashMap<Integer, ArrayList<Integer>> m_transactions;

    public DataSet(
            HashMap<Integer, Integer> attribCount,
            HashMap<Integer, ArrayList<Integer>> transactions) {
        m_attribCount = attribCount;
        m_transactions = transactions;
    }
}
