package program;

import java.util.ArrayList;
public class Pair {
	private String name;
	private ArrayList<Variable> vInner;
	
	Pair(String name, ArrayList<Variable> vInner)
	{
		this.name = name;
		this.vInner = vInner;
	}
	
	public String getFirst()
	{
		return this.name;
	}
	
	public ArrayList<Variable> getSecond()
	{
		return this.vInner;
	}
	
}
