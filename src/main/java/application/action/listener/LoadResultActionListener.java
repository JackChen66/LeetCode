package application.action.listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import application.Main;
import application.exception.CustomException;
import application.property.bean.QuestionPropertyBean;
import application.property.bean.QuestionPropertySubmitResultBean;
import application.util.MyFileUtil;

public class LoadResultActionListener implements ActionListener {
	Main main;
	
	public LoadResultActionListener(Main main) {
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (main.currentQuestion == null)
			main.textAreaConsole.append("Current Questio is null. No result.");
		
		int questionNumber = main.currentQuestion.getNumber();
		try {
			QuestionPropertyBean qpb = MyFileUtil.getQuestionProperty(questionNumber + "");
			if (qpb.getSubmitResult() == null || qpb.getSubmitResult().size() == 0)
				main.textAreaConsole.append("There is no result for question " + questionNumber);
			else {
				int index = 1;
				for(QuestionPropertySubmitResultBean qpsrb : qpb.getSubmitResult()) {
					main.textAreaConsole.append("\n" + index + " -- " + qpsrb.getTime() + " -- " + qpsrb.getResult() 
								+ " -- " + qpsrb.getRunningTime() + "\n");
					index++;
				}
			}
		} catch (IOException | CustomException e1) {
			e1.printStackTrace();
			main.textAreaConsole.append(e1.getMessage());
		} 
	}

}
