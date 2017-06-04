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
	
	public void perform()
	{
		Variable v;
		String line;
		for(int i = t.getFirst(); i < t.getSize() - 1; i++)
		{
			line = t.removeSpace(t.getLine(i));
			char current;
			for (int j = 0; j < line.length(); j++)
			{
				String name = "";
				String offsetS = "";
				int offset = 0;
				while((current = line.charAt(j)) != '[')
				{
					name += current;
					j++;
				}
				j += 2;
				while((current = line.charAt(j)) != ']')
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
				vwInner.add(v);
				j++;
			}
		}
		vwOuter.add(vwInner);
		vrOuter.add(vrInner);
	}
	
}
