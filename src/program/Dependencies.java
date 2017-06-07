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
					String multS = "";
					int offset = 0;
					int mult = 1;
					while((current = line.charAt(j)) != '[')
					{
						name += current;
						j++;
					}
					j++;
					
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
					j += t.getIteratorName(1).length();
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
					v = new Variable(mult, offset, i);
					if(read)
					{
						addToVr(name, v);
						System.out.println("READ name: " + name + " mult:" + mult + " offset: " + offset + " line: " + i);
					}
					else
					{
						addToVw(name, v);
						System.out.println("WRITE name: " + name + " mult:" + mult + " offset: " + offset + " line: " + i);
					}
					bracketCount--;
				}
			}
		}
	}
	
	public void dependencyAnalysis(ArrayList<Integer> unMovableLines)
	{
		Gcd g = new Gcd();
		ArrayList<Integer> w = new ArrayList<Integer>();
		ArrayList<Integer> r = new ArrayList<Integer>();
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
							if(g.gcdTest(vw.getMult(), vw.getOffSet(), vr.getMult(), vr.getOffSet()))
							{
								addIfNotExist(unMovableLines, vw.getLineNum());
							}
							else
							{
								if(vw.getLineNum() < vr.getLineNum())
								{
									if(r.contains(vw.getLineNum()))
									{
										addIfNotExist(unMovableLines, vw.getLineNum());
									}
									else
									{
										addIfNotExist(r, vr.getLineNum());
										addIfNotExist(w, vw.getLineNum());
									}
								}
							}
						}
					}
				}
			}
		}
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
