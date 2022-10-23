package ese566.fptree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class DataSet 
{
    public HashMap<Integer, Integer> m_supports;
    public HashMap<Integer, ArrayList<Integer>> m_transactions;

    public DataSet(
            HashMap<Integer, Integer> supports,
            HashMap<Integer, ArrayList<Integer>> transactions) 
    {
        m_supports = supports;
        m_transactions = transactions;
    }

    public void sortTransactions() 
    {
        for (var kvPair : m_transactions.entrySet()) 
        {
            var itemSet = kvPair.getValue();
            Collections.sort(itemSet, new Comparator<Integer>() 
            {
                @Override
                public int compare(Integer arg0, Integer arg1) 
                {
                    // If the attribute ID isn't in the list, then use -1 as the count. This
                    // shouldn't happen
                    // but is included for robustness
                    int arg0Count = m_supports.getOrDefault(arg0, -1);
                    int arg1Count = m_supports.getOrDefault(arg1, -1);

                    // Tie-breaker. If both counts are the same, then compare them by the IDs
                    if (arg0Count == arg1Count) 
                    {
                        return arg1 - arg0;
                    }
                    // Note that the return value doesn't have to be {-1, 0, 1}. Returning any
                    // negative value
                    // is equivalent to returning -1. Same goes for the positive values.

                    // Also note that Collections.sort() sorts in ascending order. However since we
                    // want to sort in descending order, swap arg1Count and arg0Count.
                    return arg1Count - arg0Count;
                }
            });
        }
    }

    public Iterator<Integer> iterateFromMinSupport(int minSupport)
    {
        // Create a sorted set that sorts based on the support of the item ID
        var prunedItemIds = new TreeSet<Integer>(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer arg0, Integer arg1)
            {
                int arg0Support = m_supports.get(arg0);
                int arg1Support = m_supports.get(arg1);
                // Tie-breaker
                if (arg0Support == arg1Support)
                {
                    return arg0 - arg1;
                }
                // Note that we need to sort in ascending order
                return arg0Support - arg1Support;
            }
        });
        // Remove items that does not meet the minimum support requirement
        for (Integer itemId : m_supports.keySet())
        {
            if (m_supports.getOrDefault(itemId, 0) >= minSupport)
            {
                prunedItemIds.add(itemId);
            }
        }
        return prunedItemIds.iterator();
    }

    public int gettransaction() 
    {
        return this.m_transactions.size();

    }

    public int getSupport(int attribId) 
    {
        return this.m_supports.getOrDefault(attribId, 0);
    }

    public Set<Integer> getKeytransaction() 
    {
        sortTransactions();
        return this.m_transactions.keySet();
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        var transIter = m_transactions.entrySet().iterator();
        while (transIter.hasNext()) 
        {
            var kvPair = transIter.next();
            int transactionID = kvPair.getKey();
            var items = kvPair.getValue();
            String line = String.format("Transaction ID %s: ", transactionID);

            var itemsetIter = items.iterator();
            while (itemsetIter.hasNext()) 
            {
                int itemID = itemsetIter.next();
                line += itemID + "(" + m_supports.getOrDefault(itemID, -1) + ") ";
            }
            line += "\n";
            sb.append(line);
        }
        return sb.toString();
    }
}