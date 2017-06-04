package program;

import java.util.ArrayList;

public class Dependencies {

	private textExtractor t;
	private ArrayList<ArrayList<Variable>> vwOuter = new ArrayList<ArrayList<Variable>>();
	ArrayList<Variable> vwInner = new ArrayList<Variable>();
	private ArrayList<ArrayList<Variable>> vrouter = new ArrayList<ArrayList<Variable>>();
	ArrayList<Variable> vrInner = new ArrayList<Variable>();
	
	Dependencies(textExtractor t)
	{
		this.t = t;
	}
	
	void Perform()
	{
		Variable v;
		String line;
		for(int i = t.getFirst(); i < t.getSize(); i++)
		{
			line = t.removeSpace(t.getLine(i));
			int j = 0;
			String name = null;
			String offsetS = null;
			int offset = 0;
			char current;
			
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
			offset = Integer.parseInt(offsetS);
			
			v = new Variable(name, offset, i);
			vwInner.add(v);
		}
	}
	
}
