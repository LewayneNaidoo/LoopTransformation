package program;
public class Skewing {
	private textExtractor t;
	private String output;
	Skewing(textExtractor t)
	{
		this.t = t;
	}
	
	public void run()
	{
		String loop = t.getLines();
		int count = 0;
		for (int i = 0; i<loop.length();i++){
			if (loop.charAt(i) == '{'){
				count++;
			} else if (loop.charAt(i) == '}'){
				count = 3;
			}
			
			if (count == 1){
				if (loop.charAt(i) == '='){
					loop = loop.substring(0,i)+"=i+"+loop.substring(i+1);
					i += 2;
					
				} else if (loop.charAt(i) == '>'){
					loop = loop.substring(0,i)+">i+"+loop.substring(i+1);
					i += 2;
				} else if (loop.charAt(i) == '<'){
					loop = loop.substring(0,i)+"<i+"+loop.substring(i+1);
					i += 2;
				}
			}
			
			if (count == 2){
				if (loop.charAt(i) == ']'){
					loop = loop.substring(0,i)+"-i]"+loop.substring(i+1);
					i += 2;
				}
			}
		}
		output = loop;	
	}
	
	public String getOutput()
	{
		return output;
	}
}
