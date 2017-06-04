package program;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class textExtractor {
	private ArrayList<String> lines = new ArrayList<String>();
	private int firstInner;
	private int lastInner;
	private int numFor;
	public static void main(String [] args) {
		textExtractor t = new textExtractor();
		t.parseString();
		Fission f = new Fission(t);
		f.run();
	}
	
	public String getLine(int lineNum)
	{
		return this.lines.get(lineNum);
	}
	
	public String getIteratorName()
	{
		String line = this.lines.get(0);
		String var = "";
		for(int i = line.indexOf("int") + 4;i < line.length();i++)
		{
			if(line.charAt(i) == ' ')
			{
				break;
			}
			
			var += line.charAt(i);
		}
		return var;
	}
	
	public String removeSpace(String line)
	{
		return line.replaceAll("\\s+","");
	}
	
	public int getFirst()
	{
		return firstInner;
	}
	
	public int getLast()
	{
		return lastInner;
	}
	
	public int getSize()
	{
		return this.lines.size();
	}
	
	private void parseString()
	{
		String line;
		String fileName = "loop2.txt";
		
		try {
	        FileReader fileReader = 
	            new FileReader(fileName);
	
	        BufferedReader br = new BufferedReader(fileReader);
	        
	        while( (line = br.readLine()) != null) {
	        	if(line.contains("{"))
	        	{
	        		firstInner = lines.size() + 1;
	        	}
	        	else if(line.contains("}"))
	        	{
	        		lastInner = lines.size();
	        	}
	            lines.add(line);
	        }
	        
	        br.close();         
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