import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Skewing {

	public static void main(String[] args){
		String loop = new String();
		int count = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader("loop.txt"));
			
			try {
				for (String line; (line = in.readLine()) != null; loop += line + "\n");
				/*{
					if (count == 3){
						line = line.replace("j =", "j = i +");
						line = line.replace("j=", "j=i+");
						line = line.replace("j <", "j < i +");
						line = line.replace("j<", "j<i+");
						line = line.replace("j >", "j > i +");
						line = line.replace("j>", "j>i+");
					} else if (count >= 5){
						line = line.replace("j", "j-i");
					}
					count++;
				}*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("error saving to string");
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("error reading file");
		}
		//System.out.println(loop.length());
		for (int i=0; i<loop.length();i++){
			if (loop.charAt(i) == '{'){
				count++;
				//System.out.print("Count++ \n");
			} else if (loop.charAt(i) == '}'){
				count = 3;
			}
			
			if (count == 1){
				if (loop.charAt(i) == '='){
					// replace = with =i+
					//loop.replace("=", "=i+");
					//System.out.print("= after count = 1 \n");
					loop = loop.substring(0,i)+"=i+"+loop.substring(i+1);
					i += 2;
					
				} else if (loop.charAt(i) == '>'){
					// replace = with >i+
					//loop.replace(">", ">i+");
					//System.out.print("> after count = 1 \n");
					loop = loop.substring(0,i)+">i+"+loop.substring(i+1);
					i += 2;
				} else if (loop.charAt(i) == '<'){
					// replace = with <i+
					//loop.replace("<", "<i+");
					//System.out.print("< after count = 1 \n");
					loop = loop.substring(0,i)+"<i+"+loop.substring(i+1);
					i += 2;
				}
			}
			
			if (count == 2){
				if (loop.charAt(i) == ']'){
					// replace = with -i]
					//loop.replace("]", "-i]");
					//System.out.print("] after count = 2 \n");
					loop = loop.substring(0,i)+"-i]"+loop.substring(i+1);
					i += 2;
					//continue;
				}
			}
		}
		
		
		System.out.println(loop);
		
		//loop = loop.substring(0,4)+"-i]"+loop.substring(4+1);
		
		//.out.println(loop);
	}
	
}
