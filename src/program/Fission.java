package program;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Fission {
	
	ArrayList<Integer> unMovableLines;
	textExtractor t;
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
        FileWriter fw;
		try {
			fw = new FileWriter("outfile.txt");
			
			if(!unMovableLines.isEmpty())
			{
				fw.append(t.getLine(0) + System.getProperty("line.separator"));
				if(!t.getLine(0).contains("{"))
				{
					fw.append(t.getLine(t.getFirst() - 1) + System.getProperty("line.separator"));
				}
				
				for(int lineNum: unMovableLines)
				{
					
					fw.append(t.getLine(lineNum) + System.getProperty("line.separator"));
				}
				if(!t.getLine(0).contains("}"))
				{
					fw.append('}');
				}
				
				fw.append(System.getProperty("line.separator"));
				
				for(int i = 0; i < t.getSize(); i++)
				{
					if(!unMovableLines.contains(i))
					{
						fw.append(t.getLine(i) + System.getProperty("line.separator"));
					}
				}
			}
			else
			{
				fw.append("the loop can not be fissioned");
			}
			
	        fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
