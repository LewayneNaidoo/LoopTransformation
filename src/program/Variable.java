package program;

public class Variable 
{
	private String name;
	private int offset;
	private int lineNum;
	
	Variable(String name, int offset, int lineNum)
	{
		this.name = name;
		this.offset = offset;
		this.lineNum = offset;
	}
	
	String getName()
	{
		return this.name;
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
