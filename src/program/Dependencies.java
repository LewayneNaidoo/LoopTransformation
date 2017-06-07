package program;

import java.util.ArrayList;
public class Dependencies {

	private textExtractor t;
	private ArrayList<Pair> listR = new ArrayList<Pair>(); // List of Pair for array variables that are being read
	private ArrayList<Pair> listW = new ArrayList<Pair>(); // List of Pair for array variables that are being written
	
	Dependencies(textExtractor t)
	{
		this.t = t;
	}
	
	// count the number of open brackets
	private int countBrackets(String line)
	{
		int count = 0;
		for (char c : line.toCharArray())
		{
		  if(c == '[')
		  {
			  count++;
		  }
		}
		return count;
	}
	
	// sorts the lines of input into a list of variables
	public void sortIntoList()
	{
		Variable v;
		String line;
		boolean read;
		int bracketCount;
		// start at the first operation and loop through the rest of lines
		for(int i = t.getFirst(); i < t.getSize() - 1; i++)
		{
			read = false;
			line = t.removeSpace(t.getLine(i)); // remove all spaces from line for processing
			bracketCount = countBrackets(line);
			char current; // denotes the current character in a line
			
			// loop through a line of characters
			for (int j = 0; j < line.length(); j++)
			{
				// run only if line contains bracket
				if(bracketCount != 0)
				{
					// if equal sign is found then the operation change to be read (right hand side)
					if(line.charAt(j) == '=' && !read)
					{
						read = true;
						j++;
					}
					// ignore + and - signs
					else if(line.charAt(j) == '+' || line.charAt(j) == '-')
					{
						j++;
					}

					String name = "";
					String offsetS = "";
					String multS = "";
					int offset = 0;
					int mult = 1;
					// while open backet is not found add all characters to the name string
					while((current = line.charAt(j)) != '[')
					{
						name += current;
						j++;
					}
					j++;
					
					//while first character of the iterator name is not found
					while((current = line.charAt(j)) != t.getIteratorName(1).charAt(0))
					{
						if(current == '*')
						{
							j++;
							continue;
						}
						multS += current;
						j++;
					}
					j += t.getIteratorName(1).length(); // skip characters by the length of the iterator name
					
					// while the end is not reached and a close bracket is not found
					while(j < line.length() && (current = line.charAt(j)) != ']')
					{
						offsetS += current;
						j++;
					}
					if(offsetS != "")
					{
						offset = Integer.parseInt(offsetS);
					}
					if(multS != "")
					{
						mult = Integer.parseInt(multS);
					}
					v = new Variable(mult, offset, i); // create a new Variable with the attributes assigned to it
					
					// if a read operation then save to a read list otherwise save to write list
					if(read)
					{
						addToVr(name, v);
						//System.out.println("READ name: " + name + " mult:" + mult + " offset: " + offset + " line: " + i);
					}
					else
					{
						addToVw(name, v);
						//System.out.println("WRITE name: " + name + " mult:" + mult + " offset: " + offset + " line: " + i);
					}
					bracketCount--;
				}
			}
		}
	}
	
	// dependency analysis to determine constraints for transformation
	public void dependencyAnalysis(ArrayList<Integer> unMovableLines)
	{
		Gcd g = new Gcd();
		
		// iterate through write and read lists and compare only the same array i.e. X[a*i + b] with X[c*i + d]  
		for (Pair p: listR)
		{
			for(Pair p2: listW)
			{
				if(p.getFirst().equals(p2.getFirst()))
				{
					for(Variable vw: p2.getSecond())
					{
						for(Variable vr: p.getSecond())
						{
							// if GCDTest return true for inter-dependency then add the line to a list of constraints (can't be move)
							if(g.gcdTest(vw.getMult(), vw.getOffSet(), vr.getMult(), vr.getOffSet()))
							{
								addIfNotExist(unMovableLines, vw.getLineNum());
							}
							else
							{
								// if the writing line is above the reading line then add to list of constraints
								if(vw.getLineNum() < vr.getLineNum())
								{
									addIfNotExist(unMovableLines, vw.getLineNum());
								}
							}
						}
					}
				}
			}
		}
	}
	
	// Add an item to a list if it doesn't already exist in the list
	private void addIfNotExist(ArrayList<Integer> List, int lineNum)
	{
		if(!List.contains(lineNum))
		{
			List.add(lineNum);
		}
	}
	
	// add Variable to the read list
	private void addToVr(String name, Variable v)
	{
		for (Pair p: listR)
		{
			if(p.getFirst().equals(name))
			{
				p.getSecond().add(v);
				return;
			}
		}
		Pair p1 = new Pair(name, new ArrayList<Variable>());
		p1.getSecond().add(v);
		listR.add(p1);
	}
	
	// add Variable to the write list
	private void addToVw(String name, Variable v)
	{
		for (Pair p: listW)
		{
			if(p.getFirst().equals(name))
			{
				p.getSecond().add(v);
				return;
			}
		}
		Pair p1 = new Pair(name, new ArrayList<Variable>());
		p1.getSecond().add(v);
		listW.add(p1);
	}
	
}
