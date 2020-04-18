package application.action.listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import application.Main;
import application.constant.FileName;
import application.exception.CustomException;
import application.property.bean.QuestionPropertyBean;
import application.util.ContentUtil;
import application.util.MyFileUtil;
import application.util.QuestionSelector;

public class NextActionListener implements ActionListener {
	
	Main main;
	
	public NextActionListener(Main main) {
		
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.main.currentQuestion == null)
				this.main.currentQuestion = QuestionSelector.randomSelectQuestion(-1);
			else 
				this.main.currentQuestion = QuestionSelector.randomSelectQuestion(this.main.currentQuestion.getNumber());
			
			if (this.main.currentQuestion == null) {
				main.textAreaConsole.append("QuestionPropertyBean is null for question number -1.");
				return;
			}
		
			String answerPreload = MyFileUtil.getAnswerPreLoad(this.main.currentQuestion.getNumber());
			main.textAreaAnswer.setText(answerPreload);
			main.textAreaAnswer.setCaretPosition(0);
			
			String question = MyFileUtil.getQuestionContent(this.main.currentQuestion.getNumber());
			main.textAreaQuestion.setText(question);
			main.textAreaQuestion.setCaretPosition(0);
			
			main.textAreaConsole.setText("");
		} catch (IOException | CustomException e1) {
			e1.printStackTrace();
			main.textAreaConsole.append(e1.getMessage());
		}
	}

}
