package program;

import java.util.ArrayList;

public class Dependencies {

	private textExtractor t;
	private ArrayList<ArrayList<Variable>> vwOuter = new ArrayList<ArrayList<Variable>>();
	ArrayList<Variable> vwInner = new ArrayList<Variable>();
	private ArrayList<ArrayList<Variable>> vrOuter = new ArrayList<ArrayList<Variable>>();
	ArrayList<Variable> vrInner = new ArrayList<Variable>();
	
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
	
	public void perform()
	{
		Variable v;
		String line;
		boolean mark;
		for(int i = t.getFirst(); i < t.getSize() - 1; i++)
		{
			mark = false;
			line = t.removeSpace(t.getLine(i));
			char current;
			for (int j = 0; j < line.length(); j++)
			{
				if(line.charAt(j) == '=' && !mark)
				{
					mark = true;
				}
				String name = "";
				String offsetS = "";
				int offset = 0;
				while(j < line.length() && (current = line.charAt(j)) != '[')
				{
					name += current;
					j++;
				}
				j += 2;
				while(j < line.length() && (current = line.charAt(j)) != ']')
				{
					offsetS += current;
					j++;
				}
				if(offsetS != "")
				{
					offset = Integer.parseInt(offsetS);
				}
				System.out.println("name: " + name + " offset: " + offset + " line: " + i);
				v = new Variable(name, offset, i);
				if(mark)
				{
					vrInner.add(v);
				}
				else
				{
					vwInner.add(v);
				}
				j++;
			}
		}
		vwOuter.add(vwInner);
		vrOuter.add(vrInner);
	}
	
}
