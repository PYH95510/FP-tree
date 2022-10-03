package ese566.fptree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DataReader {
	static HashMap<Integer, Integer> dataNums = new HashMap<Integer, Integer>();

	public static void readNetDFile(String fileName) {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line;
			String comma = ",";

			reader.readLine();// ignore 0

			line = reader.readLine();
			while (line != null) {
				if (line.contains(" v ")) { // if line has v
					StringTokenizer st = new StringTokenizer(line, comma);
					int data = Integer.parseInt(st.nextToken());

					dataNums.put(data, dataNums.getOrDefault(data, 1) + 1);
				}
				
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
