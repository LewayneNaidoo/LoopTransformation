package program;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class gui extends JFrame {
	private static final String FILENAME = "input.txt";
	private static String summaryString = "Summary unavaliable: no loop transformation has been run";
	public static void addComponentsToPane(Container pane) {
		
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JPanel buttonGrid = new JPanel();
        JPanel headerGrid = new JPanel();
        buttonGrid.setLayout(new GridLayout(1,2));
        headerGrid.setLayout(new GridLayout(1,4));
 
        Border border = BorderFactory.createMatteBorder(10, 10, 10, 10, (new Color(51,0,0)));
        Border borderButton = BorderFactory.createMatteBorder(10, 10, 10, 10, (new Color(0, 0, 0)));

        //final JPopupMenu menu = new JPopupMenu("Choose your configuration:");
        //menu.add("Manual");
        //menu.add("Smart");
        
        JButton summary = new JButton("Summary");
        summary.setAlignmentX(Component.RIGHT_ALIGNMENT);
        summary.setBackground(new Color(102, 0, 0));
        summary.setForeground(Color.WHITE);
        summary.setFocusPainted(false);
        summary.setPreferredSize(new Dimension(20, 20));
        summary.setFont(new Font("Arial", Font.BOLD, 9));
        summary.setBorder(BorderFactory.createCompoundBorder(borderButton, 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        summary.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  JOptionPane.showMessageDialog(null, summaryString, "Summary of transformation:", JOptionPane.PLAIN_MESSAGE);   		  
        	  } 
        	} );
        
        JButton blank = new JButton("");
        blank.setAlignmentX(Component.RIGHT_ALIGNMENT);
        blank.setBackground(new Color(0, 0, 0));
        blank.setForeground(Color.WHITE);
        blank.setFocusPainted(false);
        blank.setPreferredSize(new Dimension(20, 20));
        blank.setFont(new Font("Arial", Font.BOLD, 9));
        //blank.setToolTipText("Manchester");
        blank.setBorder(BorderFactory.createCompoundBorder(borderButton, 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        blank.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		  //dispose and reopen      		  
      	  } 
      	} );
        
        JButton help = new JButton("Help");
        help.setAlignmentX(Component.RIGHT_ALIGNMENT);
        help.setBackground(new Color(102, 0, 0));
        help.setForeground(Color.WHITE);
        help.setFocusPainted(false);
        help.setPreferredSize(new Dimension(20, 20));
        help.setFont(new Font("Arial", Font.BOLD, 9));
        help.setBorder(BorderFactory.createCompoundBorder(borderButton, 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        help.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  JOptionPane.showMessageDialog(null, "Summary tab will give you a summary of what transformation your input loop went through and how it has been optimised. \n"
        		  		+ "In the text box, input a valid string to be transformed. Valid strings must: \n "
        		  		+ "		- not have any errors \n"
        		  		+ "		- be either a single for or single nested for loop \n", "Help", JOptionPane.INFORMATION_MESSAGE);
        	  } 
        	} );
        
        
        JTextArea textbox = new JTextArea("Enter your loop here to be optimized.");
        JScrollPane scrollPaneTop = new JScrollPane(textbox); 
        scrollPaneTop.setBorder(BorderFactory.createCompoundBorder(border, 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        scrollPaneTop.setPreferredSize(new Dimension(150, 150));
        
        JTextArea resultbox = new JTextArea("Result.");
        resultbox.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultbox); 
        scrollPane.setBorder(BorderFactory.createCompoundBorder(border, 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        scrollPane.setPreferredSize(new Dimension(150, 150));
        
        JButton submit = new JButton("Submit");
        submit.setAlignmentX(Component.RIGHT_ALIGNMENT);
        submit.setBackground(new Color(102, 0, 0));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setPreferredSize(new Dimension(50, 50));
        submit.setFont(new Font("Arial", Font.BOLD, 18));
        submit.setBorder(BorderFactory.createCompoundBorder(borderButton, 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        submit.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      	    getUserInput(textbox, resultbox);
      	  } 
      	} );
        
        JButton clear = new JButton("Clear");
        clear.setAlignmentX(Component.LEFT_ALIGNMENT);
        clear.setBackground(new Color(204, 102, 102));
        clear.setForeground(Color.WHITE);
        clear.setFocusPainted(false);
        clear.setPreferredSize(new Dimension(50, 50));
        clear.setFont(new Font("Arial", Font.BOLD, 18));
        clear.setBorder(BorderFactory.createCompoundBorder(borderButton, 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        clear.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    clearTextBoxes(textbox, resultbox);
        	  } 
        	} );        
        
        buttonGrid.add(submit);
        buttonGrid.add(clear);
        
        headerGrid.add(summary);
        headerGrid.add(blank);
        headerGrid.add(help);
        
        //pane.add(header);
        pane.add(headerGrid);
        //pane.add(textbox);
        pane.add(scrollPaneTop);
        //pane.add(submit);
        //pane.add(clear);
        pane.add(buttonGrid);
        //pane.add(resultbox);
        pane.add(scrollPane);
        //pane.setBackground((new Color(75, 87, 170)));
        pane.setBackground((new Color(0, 0, 0)));
        
    }
	
    private static void GUI() {
        JFrame frame = new JFrame("Loop Transformation Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.setSize(500, 500);
        frame.setResizable(true);
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
    	//System.out.println(inputLoop);
		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(inputLoop);
			//System.out.println("done");
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
    	b.setText(transform());
    }
    
    public static String transform()
    {
    	String output = "";
		textExtractor t = new textExtractor(FILENAME);
		t.parseString();
		t.numFor();
		if(t.getNumFor() == 2)
		{
			Interchange in = new Interchange(t);
			if (in.legal()) {
				System.out.println("Legal for interchange");
				in.run();
				output = t.getLines();
				summaryString = "Interchange algorithm is ran on the input" + System.getProperty("line.separator") + output;
			} else {
				System.out.println("Illegal for interchange");
				Skewing sk = new Skewing(t);
				sk.run();
				output = sk.getOutput();
				summaryString = "Skewing and interchange algorithm are ran on the input" + System.getProperty("line.separator") + output;
			}
		}
		else if(t.getNumFor() == 1)
		{
			Fission f = new Fission(t);
			f.run();	
			output = f.getOutput();
			summaryString = "Fission algorithm is ran on the input" + System.getProperty("line.separator") + output;
		}
		else
		{
			output = "Invalid input.";
		}
		return output;
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI();
            }
        });
	}
}
