package application.action.listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import application.Main;
import application.compile.CompileCode;
import application.constant.FileName;
import application.property.bean.QuestionPropertyBean;
import application.util.ContentUtil;
import application.util.MyFileUtil;
import application.util.QuestionSelector;

public class NextActionListener implements ActionListener {
	
	Main main;
	
	public NextActionListener(String content, Main main) {
		
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.main.currentQuestion = QuestionSelector.randomSelectQuestion(-1);
			
			if (this.main.currentQuestion == null) {
				main.textAreaConsole.append("QuestionPropertyBean is null for question number -1.");
				return;
			}
		
			String answerPreload = MyFileUtil.getAnswerPreLoad(this.main.currentQuestion.getNumber());
			main.textAreaAnswer.setText(answerPreload);
			
			String question = MyFileUtil.getQuestionContent(this.main.currentQuestion.getNumber());
			main.textAreaQuestion.setText(question);
		} catch (IOException e1) {
			e1.printStackTrace();
			main.textAreaConsole.append(e1.getMessage());
		}
	}

}
