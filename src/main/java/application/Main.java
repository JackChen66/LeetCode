package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;

import application.action.listener.CompileActionListener;
import application.action.listener.SubmitActionListener;
import application.property.bean.QuestionPropertyBean;
import application.util.MyFileUtil;
import application.util.QuestionSelector;  
  
public class Main {  
	JFrame f;  
	public JTextArea textAreaConsole;
	public JTextArea textAreaQuestion;
	public JTextArea textAreaAnswer;
	public QuestionPropertyBean currentQuestion;
	public boolean firstTime = true;
	public JButton bSubmit;
	Main(){  
	    f = new JFrame();  
	    JScrollPane scrollableTextAreaAnswer = setUpAnswerPanel();
	    f.add(scrollableTextAreaAnswer);
	   
	    JPanel panelRest = new JPanel();
	    panelRest.setLayout(new GridLayout(3,1));
	    JScrollPane scrollableTextAreaQuestion = setUpQuestionPanel();
	    JScrollPane scrollableTextAreaConsole = setUpConsoleOutputPanel();
	    JPanel pButtons = new JPanel();
	    
	    
	    JButton bCompile = new JButton("Compile");
	    bCompile.addActionListener(new CompileActionListener(this));  
	    
	    JButton bClearConsole = new JButton("Clear Console");
	    bClearConsole.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	textAreaConsole.selectAll();
	        	textAreaConsole.replaceSelection("");
	        }
	    });
	    
	    bSubmit = new JButton("Submit");
	    bSubmit.addActionListener(new SubmitActionListener(this));  
	    
	    JButton bReset = new JButton("Reset");
	    bReset.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	textAreaConsole.setText("");
	        	try {
					textAreaAnswer.setText(MyFileUtil.getAnswerPreLoad(currentQuestion.getNumber()));
				} catch (IOException e1) {
					e1.printStackTrace();
					textAreaConsole.append(e1.getMessage());
				}
	        }
	    });
	    
	    JButton bNext = new JButton("Next");
	    
	    
	    JButton bChoose = new JButton("Choose...");
	    
	    
	    JButton bClose = new JButton("Close");
	  
	    
	    bClose.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	           f.dispose();
	        }
	    });
	    
	    pButtons.add(bCompile);
	    pButtons.add(bClearConsole);
	    pButtons.add(bSubmit);
	    pButtons.add(bReset);
	    pButtons.add(bNext);
	    pButtons.add(bChoose);
	    pButtons.add(bClose);
	    panelRest.add(scrollableTextAreaQuestion);
	    panelRest.add(scrollableTextAreaConsole);
	    panelRest.add(pButtons);
	   
	   
	    f.add(panelRest);
	    f.setLayout(new GridLayout(2,1));  
	  
	    f.setSize(1200,800);  
	    f.setVisible(true);  
	    
	    
	    f.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowOpened(WindowEvent we) {
	        	
	        }

	        @Override
	        public void windowActivated(WindowEvent we) {
	        	if (firstTime) {
		        	try {
		        		firstTime = false;
		        		currentQuestion = QuestionSelector.randomSelectQuestion(-1);
			    		
			    		if (currentQuestion == null) {
			    			textAreaConsole.append("Current Question is null for number -1.");
			    			return;
			    		}
		    		
		    		
		    			String answerPreload = MyFileUtil.getAnswerPreLoad(currentQuestion.getNumber());
		    			textAreaAnswer.setText(answerPreload);
		    			
		    			String question = MyFileUtil.getQuestionContent(currentQuestion.getNumber());
		    			textAreaQuestion.setText(question);
		    			
		    		} catch (IOException e1) {
		    			e1.printStackTrace();
		    		}
	        	}
	        }
	    });
	}  
	
	public JScrollPane setUpQuestionPanel() {
		textAreaQuestion = new JTextArea();  
	    textAreaQuestion.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
	    textAreaQuestion.setEditable(false); 
	    JScrollPane scrollableTextAreaQuestion = new JScrollPane(textAreaQuestion);  
	
	    scrollableTextAreaQuestion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
	    scrollableTextAreaQuestion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    
	    return scrollableTextAreaQuestion;
	}
	
	public JScrollPane setUpAnswerPanel() {
		textAreaAnswer = new JTextArea();  
	    JScrollPane scrollableTextAreaAnswer = new JScrollPane(textAreaAnswer);  
	    textAreaAnswer.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
	    scrollableTextAreaAnswer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
	    scrollableTextAreaAnswer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    return scrollableTextAreaAnswer;
	}
	
	public JScrollPane setUpConsoleOutputPanel() {
	  	textAreaConsole = new JTextArea();  
	    textAreaConsole.setText("Console Output: ");
	    textAreaConsole.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
	    textAreaConsole.setEditable(false); 
//	    Color c = new Color(179, 219, 255,100);
//	    textAreaConsole.setBackground(c);
	    JScrollPane scrollableTextAreaConsole = new JScrollPane(textAreaConsole);  
	
	    scrollableTextAreaConsole.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
	    scrollableTextAreaConsole.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    return scrollableTextAreaConsole;
	}
	
	public static void main(String[] args) {  
	    new Main();  
	}  
}  
