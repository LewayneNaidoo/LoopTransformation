package program;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class gui extends JFrame {
	private static final String FILENAME = "input.txt";

	public static void addComponentsToPane(Container pane) {
		
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JPanel buttonGrid = new JPanel();
        buttonGrid.setLayout(new GridLayout(1,2));
 
        JLabel header = new JLabel("Loop Transformation Helper");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setBackground(Color.BLACK);
        header.setPreferredSize(new Dimension(50, 50));
        header.setFont(new Font("Tahoma", Font.BOLD, 18));
        header.setForeground(Color.WHITE);
        
        JTextArea textbox = new JTextArea("Enter your loop here to be optimized.");
        Border border = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK);
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
        submit.setBackground(new Color(0, 0, 153));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setPreferredSize(new Dimension(50, 50));
        submit.setFont(new Font("Tahoma", Font.BOLD, 18));
        submit.setBorder(BorderFactory.createCompoundBorder(border, 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        submit.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      	    getUserInput(textbox, resultbox);
      	  } 
      	} );
        
        JButton clear = new JButton("Clear");
        clear.setAlignmentX(Component.LEFT_ALIGNMENT);
        clear.setBackground(new Color(153, 153, 255));
        clear.setForeground(Color.WHITE);
        clear.setFocusPainted(false);
        clear.setPreferredSize(new Dimension(50, 50));
        clear.setFont(new Font("Tahoma", Font.BOLD, 18));
        clear.setBorder(BorderFactory.createCompoundBorder(border, 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        clear.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    clearTextBoxes(textbox, resultbox);
        	  } 
        	} );        
        
        buttonGrid.add(submit);
        buttonGrid.add(clear);
        
        pane.add(header);
        //pane.add(textbox);
        pane.add(scrollPaneTop);
        //pane.add(submit);
        //pane.add(clear);
        pane.add(buttonGrid);
        //pane.add(resultbox);
        pane.add(scrollPane);
        pane.setBackground(Color.BLACK);
        
    }
	
    private static void GUI() {
        JFrame frame = new JFrame("Loop Transformation Helper");
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
			} else {
				System.out.println("Illegal for interchange");
				Skewing sk = new Skewing(t);
				sk.run();
				output = sk.getOutput();
			}
		}
		else
		{
			Fission f = new Fission(t);
			f.run();	
			output = f.getOutput();
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
