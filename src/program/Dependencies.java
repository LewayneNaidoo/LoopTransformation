package program;

import java.util.ArrayList;
public class Dependencies {

	private textExtractor t;
	private ArrayList<Pair> listR = new ArrayList<Pair>();
	private ArrayList<Pair> listW = new ArrayList<Pair>();
	
	Dependencies(textExtractor t)
	{
		this.t = t;
	}
	
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
	
	public void sortIntoList()
	{
		Variable v;
		String line;
		boolean read;
		int bracketCount;
		for(int i = t.getFirst(); i < t.getSize() - 1; i++)
		{
			read = false;
			line = t.removeSpace(t.getLine(i));
			bracketCount = countBrackets(line);
			char current;
			for (int j = 0; j < line.length(); j++)
			{
				if(bracketCount != 0)
				{
					if(line.charAt(j) == '=' && !read)
					{
						read = true;
						j++;
					}
					else if(line.charAt(j) == '+' || line.charAt(j) == '-')
					{
						j++;
					}

					String name = "";
					String offsetS = "";
					int offset = 0;
					while((current = line.charAt(j)) != '[')
					{
						name += current;
						j++;
					}
					j += 1 + t.getIteratorName(1).length();
					while(j < line.length() && (current = line.charAt(j)) != ']')
					{
						offsetS += current;
						j++;
					}
					if(offsetS != "")
					{
						offset = Integer.parseInt(offsetS);
					}
					v = new Variable(offset, i);
					if(read)
					{
						addToVr(name, v);
						System.out.println("READ name: " + name + " offset: " + offset + " line: " + i);
					}
					else
					{
						addToVw(name, v);
						System.out.println("WRITE name: " + name + " offset: " + offset + " line: " + i);
					}
					bracketCount--;
				}
			}
		}
	}
	
	public void dependencyAnalysis(ArrayList<Integer> unMovableLines)
	{
		ArrayList<Integer> movableLines = new ArrayList<Integer>();
		for (Pair p: listR)
		{
			for(Pair p2: listW)
			{
				if(p.getFirst().equals(p2.getFirst()))
				{
					for(Variable vr: p.getSecond())
					{
						for(Variable vw: p2.getSecond())
						{
								if(vr.getOffSet() != vw.getOffSet())
								{
									if(vr.getLineNum() == vw.getLineNum())
									{
										System.out.println(p2.getFirst() + vw.getLineNum() + " and " + p.getFirst() + vr.getLineNum());
										addIfNotExist(unMovableLines, vr.getLineNum());
									}
								}
								else
								{
									if(vw.getLineNum() < vr.getLineNum())
									{
										//if(vr.getLineNum() > )
										//addIfNotExist(movableLines, vw.getLineNum());
										System.out.println(p2.getFirst() + vw.getLineNum() + " and " + p.getFirst() + vr.getLineNum());
											addIfNotExist(unMovableLines, vw.getLineNum());
											addIfNotExist(unMovableLines, vr.getLineNum());
									}
								}
							}
					}
				}
			}
		}
		//System.out.println(unMovableLines.get(0));
		//System.out.println(unMovableLines.size());
	}
	
	private void addIfNotExist(ArrayList<Integer> List, int lineNum)
	{
		if(!List.contains(lineNum))
		{
			List.add(lineNum);
		}
	}
	
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
