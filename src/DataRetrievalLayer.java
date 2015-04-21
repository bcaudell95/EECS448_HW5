import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataRetrievalLayer 
{
	final static int dataLength = 44449;
	
	ArrayList<ArrayList<Double>> data;
	ArrayList<ArrayList<Double>> stats;
	
	DataRetrievalLayer()
	{	
		// Read the data in
		data = ReadData("Data/data.txt", dataLength);
		stats = ReadData("Data/stats.txt", 4);
	}
	
	private ArrayList<ArrayList<Double>> ReadData(String fileName, int rows)
	{
		ArrayList<ArrayList<Double>> tempData;
		File file = new File(fileName);
		Scanner scan;
		
		// Preallocate the arraylist to save time
		tempData = new ArrayList<ArrayList<Double>>(rows);
		
		// Open the file
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		String inputLine;
		String[] inputSplit;
		int lineCounter = 0;
		ArrayList<Double> tempLine;
		
		// While not at the end of the file
		while(scan.hasNext())
		{
			// Preallocate the arraylist
			tempLine = new ArrayList<Double>(16);
			
			// Grab the data
			inputLine = scan.nextLine();
			// Split it
			inputSplit = inputLine.split("\\t");
			
			int offset = inputSplit.length - 16;
			
			for(int i = offset; i < inputSplit.length; i++)
			{
				tempLine.add(i-offset, Double.parseDouble(inputSplit[i]));
			}
			
			tempData.add(lineCounter, tempLine);
			
			lineCounter++;
			
			// Safety net
			if(lineCounter >= rows)
				break;
		}
		
		scan.close();
		
		return tempData;
	}
	
	public ArrayList<ArrayList<Double>> GetData()
	{
		return data;
	}
	
	public ArrayList<ArrayList<Double>> GetStats()
	{
		return stats;
	}
	
	/*
	public void testPrint()
	{
		System.out.println("Data");
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				System.out.print(data.get(i).get(j) + "\t");
			}
			System.out.println("");
		}
		System.out.println("Stats");
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				System.out.print(stats.get(i).get(j) + "\t");
			}
			System.out.println("");
		}
	}
	*/
}
