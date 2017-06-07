package program;

public class Variable 
{
	private int offset;
	private int lineNum;
	private int mult;
	
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
