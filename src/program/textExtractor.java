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
		t.numFor();
		if(t.getNumFor() == 2)
		{
			Interchange in = new Interchange(t);
			if (in.legal()) {
				System.out.println("Legal for interchange");
				in.run();
			} else {
				System.out.println("Illegal for interchange");
				// skewing
			}
		}
		else
		{
			Fission f = new Fission(t);
			f.run();	
		}
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
		String line, lineNS;
		int brack, count, length, newLength;
		String fileName = "loop2.txt";
		
		try {
	        FileReader fileReader = 
	            new FileReader(fileName);
	
	        BufferedReader br = new BufferedReader(fileReader);
	        
	        while( (line = br.readLine()) != null) {
	        	lineNS = removeSpace(line);
	        	if(lineNS.length() == 0)
    			{
	        		continue;
    			}
	        	
	        	if((brack = lineNS.indexOf("{")) != -1)
	        	{
	        		if(lineNS.length() != 1)
	        		{
        				length = line.length();
        				line = line.replace("{", "");
        				count = length - line.length();
        				System.out.println(count);
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
	        		else
	        		{
	        			lines.add(line);
	        			firstInner = lines.size();
	        		}
        			System.out.println("F" + firstInner);
	        	}
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
        			System.out.println("L" + lastInner);
	        	}
	        	else
	        	{
	        		lines.add(line);
	        	}
	        }
	        print();
	        
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
	private void numFor()
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
	
	public void print()
	{
		for(String line: lines)
		{
			System.out.println(line);
		}
	}
	
}