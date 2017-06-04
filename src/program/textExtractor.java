package program;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class textExtractor {
	private ArrayList<String> lines = new ArrayList<String>();
	private int firstInner;
	private int numFor;
	public static void main(String [] args) {
		textExtractor t = new textExtractor();
		t.parseString();
		Dependencies d = new Dependencies(t);
	}
	
	public String getLine(int lineNum)
	{
		return this.lines.get(lineNum);
	}
	
	public String removeSpace(String line)
	{
		return line.replaceAll("\\s+","");
	}
	
	public int getFirst()
	{
		return firstInner;
	}
	
	public int getSize()
	{
		return this.lines.size();
	}
	
	private void parseString()
	{
		String line;
		String fileName = "loop.txt";
		
		try {
	        FileReader fileReader = 
	            new FileReader(fileName);
	
	        BufferedReader br = new BufferedReader(fileReader);
	        FileWriter fw = new FileWriter("outfile.txt"); 
	        
	        while( (line = br.readLine()) != null) {
	        	if(line.contains("{"))
	        	{
	        		firstInner = lines.size() + 1;
	        	}
	            lines.add(line);
	        }
	        
	        br.close();         
	        fw.close();
	    }
	    catch(FileNotFoundException ex) 
	    {
	        System.out.println(
	            "Unable to open file '" + 
	            fileName + "'");                
	    }
	    catch(IOException ex) 
	    {
	        System.out.println(
	            "Error reading file '" 
	            + fileName + "'");                  
	    }
	}
	private void NumFor()
	{
		for (String line: lines)
		{
		    if(line.contains("for"))
		    {
		    	numFor++;
		    }
		}
	}
	
	public int getNumFor()
	{
		return numFor;
	}
	
}