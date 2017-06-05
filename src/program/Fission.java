package program;

import java.util.ArrayList;

public class Fission {
	
	private ArrayList<Integer> unMovableLines;
	private textExtractor t;
	private String output = "";
	Fission(textExtractor t)
	{
		this.unMovableLines = new ArrayList<Integer>();
		this.t = t;
	}
	
	public void run()
	{
		Dependencies d = new Dependencies(this.t);
		d.sortIntoList();
		d.dependencyAnalysis(this.unMovableLines);
		writeLoop();
	}
	
	private void writeLoop()
	{
			if(!(t.getLast() - t.getFirst() == unMovableLines.size()))
			{
				output += t.getLine(0) + System.getProperty("line.separator");
				if(!t.getLine(0).contains("{"))
				{
					output += t.getLine(t.getFirst() - 1) + System.getProperty("line.separator");
				}
				
				for(int lineNum: unMovableLines)
				{
					
					output += t.getLine(lineNum) + System.getProperty("line.separator");
				}
				if(!t.getLine(0).contains("}"))
				{
					output += '}' + System.getProperty("line.separator");
				}
				
				output +=(System.getProperty("line.separator"));
				
				for(int i = 0; i < t.getSize(); i++)
				{
					if(!unMovableLines.contains(i))
					{
						output += (t.getLine(i) + System.getProperty("line.separator"));
					}
				}
			}
			else
			{
				output += ("Fission cannot be applied to this loop");
			}
	}
	
	public String getOutput()
	{
		return output;
	}
}
