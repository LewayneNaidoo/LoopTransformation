package loopGUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class gui extends JFrame {
	
	private static final String FILENAME = "input.txt";

	public static void addComponentsToPane(Container pane) {
		
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
 
        JLabel header = new JLabel("Loop Transformation Helper");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextArea textbox = new JTextArea("Enter your loop here to be optimized.");
        
        JTextArea resultbox = new JTextArea("Result.");
        JScrollPane scrollPane = new JScrollPane(resultbox); 
        resultbox.setEditable(false);
        
        JButton submit = new JButton("Submit");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        //submit.setBackground(Color.GRAY);
        submit.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      	    getUserInput(textbox, resultbox);
      	  } 
      	} );
        
        JButton clear = new JButton("Clear");
        clear.setAlignmentX(Component.CENTER_ALIGNMENT);
        //clear.setBackground(Color.GRAY);
        clear.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    clearTextBoxes(textbox, resultbox);
        	  } 
        	} );
        
        pane.add(header);
        pane.add(textbox);
        pane.add(submit);
        pane.add(clear);
        pane.add(resultbox);
        
    }
 
    private static void GUI() {
        JFrame frame = new JFrame("Loop Transformation Helper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
    public static void clearTextBoxes(JTextArea a, JTextArea b){
    	a.setText("");
    	b.setText("");
    }
    
    public static void getUserInput(JTextArea a, JTextArea b){
    	BufferedWriter bw = null;
		FileWriter fw = null;
    	String inputLoop = a.getText();
    	System.out.println(inputLoop);
		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(inputLoop);
			System.out.println("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
    	b.setText(inputLoop);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI();
            }
        });
    }
}
