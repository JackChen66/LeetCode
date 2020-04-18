package application.action.listener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.Main;
import application.compile.TestComplieCode;
import application.constant.SubmitResultValue;
import application.exception.CustomException;
import application.property.bean.QuestionPropertySubmitResultBean;
import application.util.ContentUtil;
import application.util.MyFileUtil;

public class SubmitActionListener implements ActionListener {
	
	String content;
	Main main;
	
	public SubmitActionListener(Main main) {
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.content = main.textAreaAnswer.getText();
		main.textAreaConsole.setText("");
		
		try {
			this.content = MyFileUtil.copyTestCasesToFile(main.currentQuestion.getNumber(), this.content);
			this.content = new ContentUtil(this.content).add();
			MyFileUtil.writeAnswerContentToOutput(this.content);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		} catch (CustomException e1) {
			e1.printStackTrace();
			main.textAreaConsole.append(e1.getMessage());
			return;
		}

		try {
			TestComplieCode tcc = new TestComplieCode();
			int exitCode = tcc.runTest();
			main.textAreaConsole.append(tcc.getOutputAndError());
		
			Boolean testResult = null;
			if (exitCode == 0)
				testResult = parseResult(tcc.getOutputAndError());
			if (testResult == null) {
				return;
			}
			if(testResult) {
				updateSubmitResult(SubmitResultValue.PASS, tcc.getRunningTime());
				Color c = new Color(176, 255, 161, 100);
				main.bSubmit.setBackground(c);
			
			} else {
				Color c = new Color(247, 111, 111, 100);
				updateSubmitResult(SubmitResultValue.FAIL, tcc.getRunningTime());
				main.bSubmit.setBackground(c);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			main.textAreaConsole.append(e1.getMessage());
		} 
	}
	
	private void updateSubmitResult(SubmitResultValue result, long runningTime) throws IOException {
		if (main.currentQuestion == null)
			return;
		if (main.currentQuestion.getSubmitResult() == null) {
			List<QuestionPropertySubmitResultBean> listSubmitResult = new ArrayList<QuestionPropertySubmitResultBean>();
			listSubmitResult.add(new QuestionPropertySubmitResultBean(new Date(), result.toString(), runningTime));
			main.currentQuestion.setSubmitResult(listSubmitResult);
		} else {
			main.currentQuestion.getSubmitResult().add(new QuestionPropertySubmitResultBean(new Date(), result.toString(), runningTime));
		}
		
		MyFileUtil.writeQuestionProperty(main.currentQuestion);
	}
	
	//total: 3, pass: 3, fail: 0
	private Boolean parseResult(String output) {
		if (output == null)
			return false;
		String[] arrOutput = output.split("\n");
		
		String strResult = null;
		
		for (String s : arrOutput) {
			if (s.startsWith("total: ")) {
				strResult = s;
				break;
			}
		}

		if (strResult == null)
			return null;
		
		String[] arrColonSpace = strResult.split(": ");
		String failNumber = arrColonSpace[arrColonSpace.length - 1];
		
		
		int intFailNubmer = Integer.parseInt(failNumber);
		
		return intFailNubmer == 0;
	}
}
