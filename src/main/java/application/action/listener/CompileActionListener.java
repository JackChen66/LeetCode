package application.action.listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import application.Main;
import application.compile.CompileCode;
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
		this.content = main.textAreaAnswer.getText();
		ContentUtil cu = new ContentUtil(content);
		cu.add();
		content = cu.getContent();
		
		try {
			MyFileUtil.writeAnswerContentToPackage(cu.getContent());
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		CompileCode cc = new CompileCode(FileName.SOLUTION_JAVA_PATH);
	
		cc.run();
		
		main.textAreaConsole.append(cc.getConsoleOutput());
	}

}
