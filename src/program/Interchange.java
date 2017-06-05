package program;

public class Interchange {

	private textExtractor t;
	
	Interchange(textExtractor t)
	{
		this.t = t;
	}
	
	public void run()
	{
		interchange();
	}
	public boolean legal(){
			
			int count = 0;	
			String name;
			String line;
			String left;
			String[] right;
			String lefti, leftj;		
			String righti, rightj;
			String n1, n2;
			int ileft, jleft;		
			int iright, jright;
			int idep, jdep;		
			
			for(int k = 0; k < t.getSize(); k++)
			{
				line = t.getLine(k);
				
				
				if (count >= 2 ) {
					if (line.contains("}")) {
						break;
					}
					
					ileft = 0;
					jleft = 0;
					
					line = line.replaceAll("\\s", "");
					
					right = line.split("=");
					left = right[0];
					right = right[1].split("]");
					
					name = left.substring(0, left.indexOf("["));
					n1 = t.getIteratorName(1);
					n2 = t.getIteratorName(2);
					
					if (left.contains(n1 + "+") || left.contains(n1 + "-")) {
						lefti = left.substring((left.indexOf(n1) + n1.length() - 1)+1, left.indexOf(","));
						ileft = Integer.parseInt(lefti);
					}
					
					if (left.contains(n2 + "+") || left.contains(n2 + "-")) {
						leftj = left.substring((left.indexOf(n2) + n2.length() - 1)+1, left.indexOf("]"));
						jleft = Integer.parseInt(leftj);
					}
					
					for (int i=0; i<right.length; i++){
						
						iright = 0;
						jright = 0;
						
						if (right[i].contains(";")) {
							break;
						}
						
	
						if (right[i].contains(name)) {
					
							if (right[i].contains(n1 + "+") || right[i].contains(n1 + "-")) {
								righti = right[i].substring((right[i].indexOf(n1) + n1.length() - 1)+1, right[i].indexOf(","));
								iright = Integer.parseInt(righti);						
							}
						
							if (right[i].contains(n2 + "+") || right[i].contains(n2 + "-")) {
								rightj = right[i].substring((right[i].indexOf(n2) + n2.length() - 1)+1, right[i].length());
								jright = Integer.parseInt(rightj);
							}
					
							idep = ileft - iright;
							jdep = jleft - jright;
							
							System.out.println("Dependence: " + idep + ", " +jdep);
						
							if (idep < 0 || jdep < 0) {
								return false;
	
							}					
						}
					}							
				}
				
				if (line.contains("{")) {
					count++;
				}
			}
			return true;
		}
	
	private void interchange() {
			
			int countline = 0;
			boolean f = false;
			int loopone = 0;
			int looptwo = 0;
			String temp1;
			String temp2;
			String line;
			
			for (int k = 0; k < t.getSize(); k++) {
				line = t.getLine(k);
				if (line.contains("for")) {
					
					if (!f) {
						loopone = countline;
						f = true;
					} else {
						looptwo = countline;
					}
				}
				countline++;
			}
			
			temp1 = t.getLine(looptwo);
			temp1 = temp1.trim();
			
			temp2 = t.getLine(loopone);
			temp2 = "\t" + temp2;
			
			t.removeLine(looptwo);
			t.removeLine(loopone);
			
			t.addLine(loopone, temp1);
			t.addLine(looptwo, temp2);
			
			for (int k = 0; k < t.getSize(); k++) {
				System.out.println(t.getLine(k));
			}
		}
}
