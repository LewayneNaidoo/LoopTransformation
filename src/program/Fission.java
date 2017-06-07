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
	// run the fission algorithm
	public void run()
	{
		Dependencies d = new Dependencies(this.t);
		d.sortIntoList();
		d.dependencyAnalysis(this.unMovableLines);
		writeLoop();
	}
	
	// write the transformed loop to a string or print error message
	private void writeLoop()
	{
			// write only if there are items in the lists
			if(!(t.getLast() - t.getFirst() == unMovableLines.size()) && unMovableLines.size() != 0)
			{
				output += t.getLine(0) + System.getProperty("line.separator");
				if(!t.getLine(0).contains("{"))
				{
					output += t.getLine(t.getFirst() - 1) + System.getProperty("line.separator");
				}
				
				// write all items in the list
				for(int lineNum: unMovableLines)
				{
					
					output += t.getLine(lineNum) + System.getProperty("line.separator");
				}
				if(!t.getLine(0).contains("}"))
				{
					output += '}' + System.getProperty("line.separator");
				}
				
				output += System.getProperty("line.separator");
				
				for(int i = 0; i < t.getSize(); i++)
				{
					if(!unMovableLines.contains(i))
					{
						output += t.getLine(i) + System.getProperty("line.separator");
					}
				}
			}
			// out error if can't be fission
			else
			{
				output += "Fission cannot be applied to this loop";
			}
	}
	
	public String getOutput()
	{
		return output;
	}
}
