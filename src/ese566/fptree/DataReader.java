package ese566.fptree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DataReader 
{
	public static DataSet readNetDFile(String fileName) 
	{
		// <Attribute ID, Count>
		HashMap<Integer, Integer> attribCount = new HashMap<Integer, Integer>();
		// <Transaction ID, <Attribute ID>>
		HashMap<Integer, ArrayList<Integer>> transactions = new HashMap<>();

		BufferedReader reader = null;
		boolean complete = true;
		try 
		{
			reader = new BufferedReader(new FileReader(fileName));
			String line;
			String tokens = ",";
			int curTransactionID = -1;

			reader.readLine();// ignore 0
			line = reader.readLine();
			while (line != null) 
			{
				StringTokenizer st = new StringTokenizer(line, tokens);
				String typeStr = st.nextToken();
				if (typeStr.equals("C")) 
				{ 
					// If line is a transaction ("C")
					String transactionIDStr = st.nextToken();
					// Remove enclosing quotes
					transactionIDStr = transactionIDStr.replace("\"", "");
					curTransactionID = Integer.parseInt(transactionIDStr);
					transactions.put(curTransactionID, new ArrayList<Integer>());
				} 
				else if (typeStr.equals("V")) 
				{ 
					// If line is an item for a transaction ("V")
					if (curTransactionID < 0) 
					{
						// This shouldn't happen. All "V" should always come after "C" and therefore
						// curTransactionID should always be initialized
						throw new IllegalArgumentException("Data is incorrectly formatted");
					}
					// Attribute ID
					int attribID = Integer.parseInt(st.nextToken());
					attribCount.put(attribID, attribCount.getOrDefault(attribID, 0) + 1);

					var itemset = transactions.get(curTransactionID);
					itemset.add(attribID);
				}
				line = reader.readLine();
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
			complete = false;
		} finally 
		{
			try 
			{
				reader.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		if (complete) 
		{
			return new DataSet(attribCount, transactions);
		} else 
		{
			return null;
		}
	}
}
