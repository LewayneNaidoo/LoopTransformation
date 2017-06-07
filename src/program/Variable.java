package program;

/*
 	The for loop below is used to demonstrate the meaning of each attribute
 	for(i = 1; i < 3; i++)
	{
		X[a*i + b] = X[c*i + d] + 1;
	}
	
 */

public class Variable 
{
	private int offset; // the value of b or d
	private int mult; // the value of a or c
	private int lineNum; // the line number of the array
	
	Variable(int mult, int offset, int lineNum)
	{
		this.offset = offset;
		this.lineNum = lineNum;
		this.mult = mult;
	}
	
	int getOffSet()
	{
		return this.offset;
	}
	
	int getMult()
	{
		return this.mult;
	}
	
	int getLineNum()
	{
		return this.lineNum;
	}
}
