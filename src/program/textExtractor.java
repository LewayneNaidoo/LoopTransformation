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
	private String fileName;
	
	textExtractor(String fileName)
	{
		this.fileName = fileName;
	}
	
	public String getLine(int lineNum)
	{
		return this.lines.get(lineNum);
	}
	
	public void removeLine(int lineNum)
	{
		this.lines.remove(lineNum);
	}
	
	public void addLine(int lineNum, String line)
	{
		this.lines.add(lineNum, line);
	}
	
	// get the line number that contains "for" base on input (2 for inner loop and 1 for outer loop) 
	public int getForLine(int forNum)
	{
		for(int i = 0; i < lines.size(); i++)
		{
			if(lines.get(i).contains("for"))
			{
				if(forNum == 1)
				{
					return i;
				}
				forNum--;
			}
		}
		return -1;
	}
	
	// Returns the name of the iterator of an inner or outer loop (2 and 1 respectively)
	public String getIteratorName(int forNum)
	{
		String line = removeSpace(this.lines.get(getForLine(forNum)));
		String var = "";
		int index;
		if(line.contains("int"))
		{
			index = line.indexOf("int") + 3;
		}
		else
		{
			index = 4;
		}
		for(int i = index;i < line.length();i++)
		{
			if(line.charAt(i) == '=')
			{
				break;
			}
			var += line.charAt(i);
		}
		return var;
	}
	
	// remove all spaces from a string
	public String removeSpace(String line)
	{
		return line.replaceAll("\\s+","");
	}
	
	// get the first line with operations
	public int getFirst()
	{
		return firstInner;
	}
	
	// get the last line with operations
	public int getLast()
	{
		return lastInner;
	}
	
	public int getSize()
	{
		return this.lines.size();
	}
	
	// read the input from a text file, reformat and process it into a list of lines
	public void parseString()
	{
		// String for line and line without spaces
		String line, lineNS;
		int brack, count, length;
		
		try {
	        FileReader fileReader = 
	            new FileReader(fileName);
	
	        BufferedReader br = new BufferedReader(fileReader);
	        
	        while( (line = br.readLine()) != null) {
	        	lineNS = removeSpace(line);
	        	//If the line without space is empty then skip iteration
	        	if(lineNS.length() == 0)
    			{
	        		continue;
    			}
	        	
	        	// if a open bracket can be found
	        	if((brack = lineNS.indexOf("{")) != -1)
	        	{
	        		//if the line's length is not a single character
	        		if(lineNS.length() != 1)
	        		{
        				length = line.length();
        				line = line.replace("{", "");
        				count = length - line.length();
        				//System.out.println(count);
        				// if bracket is on the very right then move it down e.g X[i] = Y[i] {
	        			if(brack + 1 >= lineNS.length())
	        			{
	        				if(count == 2)
	        				{
	        					lines.add("{");
	        				}
		        			lines.add(line);
			        		lines.add("{");
			        		firstInner = lines.size();
	        			}
	        			// otherwise move it up if its on the very left e.g { X[i] = Y[i]
	        			else
	        			{
			        		lines.add("{");
		        			lines.add("\t" + line);
	        				if(count == 2)
	        				{
	        					lines.add("{");
	        				}
	            			firstInner = lines.size() - 1;
	        			}
	        		}
	        		// else just add the line
	        		else
	        		{
	        			lines.add(line);
	        			firstInner = lines.size();
	        		}
        			//System.out.println("F" + firstInner);
	        	}
	        	// same formatting method as to the open bracket but for close bracket instead
	        	else if((brack = lineNS.indexOf("}")) != -1)
	        	{
	        		if(lineNS.length() != 1)
	        		{
        				length = line.length();
        				line = line.replace("}", "");
        				count = length - line.length();
	        			if(brack + 1 >= lineNS.length())
	        			{
	        				if(count == 2)
	        				{
	        					lines.add("}");
	        				}
		        			lines.add(line);
			        		lines.add("}");
			        		lastInner = lines.size();
	        			}
	        			else
	        			{
			        		lines.add("}");
		        			lines.add("\t" + line);
	        				if(count == 2)
	        				{
	        					lines.add("}");
	        				}
	        				lastInner = lines.size() - 1;
	        			}
	        		}
	        		else
	        		{
	        			lines.add(line);
	        			lastInner = lines.size() - 1;
	        		}
        			//System.out.println("L" + lastInner);
	        	}
	        	else
	        	{
	        		lines.add(line);
	        	}
	        }
	        //print();
	        
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
	
	// counts the number of occurences of "for"
	public void numFor()
	{
		for (String line: lines)
		{
		    if(line.contains("for"))
		    {
		    	numFor++;
		    }
		}
	}
	
	// Get the number of for loops in the input
	public int getNumFor()
	{
		return numFor;
	}
	
	// Get all the lines of input as a single string
	public String getLines()
	{
		String allLines = "";
		for(String line: lines)
		{
			allLines += line + System.getProperty("line.separator");
		}
		return allLines;
	}
	
	// print the list of lines (for testing purposes)
	public void print()
	{
		for(String line: lines)
		{
			System.out.println(line);
		}
	}
	
}