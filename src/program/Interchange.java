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
			
			//goes through every line of the input loop
			for(int k = 0; k < t.getSize(); k++)
			{
				line = t.getLine(k);
							
				//search for the loop body
				if (count >= 2) {							
					ileft = 0;
					jleft = 0;
					
					//break if it reaches the end of body
					if (line.contains("}")) {
						break;
					}
					
					//remove all spaces
					line = line.replaceAll("\\s", "");	
					
					//split the statement into left and right
					right = line.split("=");
					left = right[0];
					
					//split the right part into terms
					right = right[1].split("]");
					
					//save the variable name of the left
					name = left.substring(0, left.indexOf("["));
					
					//get iterator names
					n1 = t.getIteratorName(1);
					n2 = t.getIteratorName(2);
					
					//check if outer iterator changes on the left
					//if yes, find the offset and save it to an int
					if (left.contains(n1 + "+") || left.contains(n1 + "-")) {
						lefti = left.substring((left.indexOf(n1) + n1.length() - 1)+1, left.indexOf(","));
						ileft = Integer.parseInt(lefti);
					}
					
					//check if inner iterator changes on the left
					//if yes, find the offset and save it to an int
					if (left.contains(n2 + "+") || left.contains(n2 + "-")) {
						leftj = left.substring((left.indexOf(n2) + n2.length() - 1)+1, left.indexOf("]"));
						jleft = Integer.parseInt(leftj);
					}
					
					//goes through every term on the right
					for (int i=0; i<right.length; i++){
						
						iright = 0;
						jright = 0;
						
						//break if it reaches the end of line
						if (right[i].contains(";")) {
							break;
						}
						
						//compare variable name on the left and right
						if (right[i].contains(name)) {
					
							//check if outer iterator changes on the right
							//if yes, find the offset and save it to an int
							if (right[i].contains(n1 + "+") || right[i].contains(n1 + "-")) {
								righti = right[i].substring((right[i].indexOf(n1) + n1.length() - 1)+1, right[i].indexOf(","));
								iright = Integer.parseInt(righti);						
							}
						
							//check if inner iterator changes on the right
							//if yes, find the offset and save it to an int
							if (right[i].contains(n2 + "+") || right[i].contains(n2 + "-")) {
								rightj = right[i].substring((right[i].indexOf(n2) + n2.length() - 1)+1, right[i].length());
								jright = Integer.parseInt(rightj);
							}
					
							//calculate dependence distance on the left and right
							idep = ileft - iright;
							jdep = jleft - jright;
							
//							System.out.println("Dependence: " + idep + ", " +jdep);
						
							//return false if interchange is illegal
							if (idep < 0 || jdep != 0) {
								return false;
	
							}					
						}
					}							
				}				
				//counter to search for the loop body
				if (line.contains("{")) {
					count++;
				}
			}
			//return true if interchange is legal
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
			
			//goes through every line of the input loop
			for (int k = 0; k < t.getSize(); k++) {
				line = t.getLine(k);
				
				//look for the string "for"
				if (line.contains("for")) {
					
					//check and save the line number of the for loop statements
					if (!f) {
						loopone = countline;
						f = true;
					} else {
						looptwo = countline;
					}
				}
				countline++;
			}
			
			//put the second for loop into temp string
			//remove space in front
			temp1 = t.getLine(looptwo);
			temp1 = temp1.trim();
			
			//put the first for loop into temp string
			//add tab in front
			temp2 = t.getLine(loopone);
			temp2 = "\t" + temp2;
			
			//remove original for loop statements
			t.removeLine(looptwo);
			t.removeLine(loopone);
			
			//interchange and add new for loop statements
			t.addLine(loopone, temp1);
			t.addLine(looptwo, temp2);
			
/*			for (int k = 0; k < t.getSize(); k++) {
				System.out.println(t.getLine(k));
			}*/
		}
}
