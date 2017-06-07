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
		interchange();
	
	}
	
	private void interchange() {
		String[] line;
		
		line = output.split("\\n");
		
		int countline = 0;
		boolean f = false;
		int loopone = 0;
		int looptwo = 0;
		String temp1;
		String temp2;
		String n = "", m = "", io = "";
		
		for (int i = 0; i < line.length; i++) {

			if (line[i].contains("for")) {
				
				if (!f) {
					loopone = countline;
					io = line[i].substring(line[i].indexOf("=")+1, line[i].indexOf(";"));
					n = line[i].substring(line[i].indexOf("<")+2, line[i].lastIndexOf(";"));
					f = true;
				} else {
					looptwo = countline;
					m = line[i].substring(line[i].indexOf("<")+4, line[i].lastIndexOf(";"));
				}
			}
			countline++;
		}
		
		temp1 = line[looptwo];
		temp1 = temp1.trim();
		temp1 = temp1.replaceFirst("i", io);
		temp1 = temp1.replaceFirst("i", n);
		
		temp2 = line[loopone];
		temp2 = "\t" + temp2;
		temp2 = temp2.replace("=", "= max(");
		temp2 = temp2.replaceFirst(";", ", j - " + m + ");");
		temp2 = temp2.replace(temp2.substring(temp2.indexOf("<")+2, temp2.lastIndexOf(";")+1), "min(" + n + ", j -" + io + ");");
		
		line[loopone] = temp1;
		line[looptwo] = temp2;
		
		output = "";
		for (int j = 0; j < line.length; j++){
			output += line[j] + System.getProperty("line.separator");
		}
	}
	
	public String getOutput()
	{
		return output;
	}
}
