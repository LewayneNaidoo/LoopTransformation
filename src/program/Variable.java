package program;

public class Variable 
{
	private int offset;
	private int lineNum;
	
	Variable(int offset, int lineNum)
	{
		this.offset = offset;
		this.lineNum = lineNum;
	}
	
	int getOffSet()
	{
		return this.offset;
	}
	
	int getLineNum()
	{
		return this.lineNum;
	}
}
