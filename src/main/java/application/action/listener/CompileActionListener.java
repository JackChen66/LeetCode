package application.action.listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import application.Main;
import application.compile.TestComplieCode;
import application.constant.FileName;
import application.util.ContentUtil;
import application.util.MyFileUtil;

public class CompileActionListener implements ActionListener {
	
	String content;
	Main main;
	
	public CompileActionListener(Main main) {
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		main.textAreaConsole.setText("");
		
		this.content = main.textAreaAnswer.getText();
		ContentUtil cu = new ContentUtil(content);
		cu.add();
		content = cu.getContent();
		
		
		try {
			MyFileUtil.writeAnswerContentToOutput(cu.getContent());
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		
		try {
			TestComplieCode tcc = new TestComplieCode();
			tcc.compile();
			main.textAreaConsole.append(tcc.getOutputAndError());
		} catch (Exception e1) {
			e1.printStackTrace();
			main.textAreaConsole.append(e1.getMessage());
		}
		
	}

}
