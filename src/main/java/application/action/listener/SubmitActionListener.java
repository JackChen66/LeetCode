package application.action.listener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.Main;
import application.compile.CompileCode;
import application.compile.TestCode;
import application.constant.FileName;
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
			MyFileUtil.writeAnswerContentToPackage(this.content);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		} catch (CustomException e1) {
			e1.printStackTrace();
			main.textAreaConsole.append(e1.getMessage());
			return;
		}
		
		CompileCode cc = new CompileCode(FileName.SOLUTION_JAVA_PATH);
		cc.run();
		main.textAreaConsole.append(cc.getConsoleOutput());
		
		if (!cc.isResult()) {
			main.textAreaConsole.append("Fail to compile, exit.");
			try {
				Color c = new Color(247, 111, 111, 100);
				updateSubmitResult(SubmitResultValue.FAIL);
				main.bSubmit.setBackground(c);
			} catch (IOException e1) {
				e1.printStackTrace();
				main.textAreaConsole.append(e1.getMessage());
			}
			return;
		}

		TestCode.runTest();
		String testOutput = TestCode.sb.toString();
		main.textAreaConsole.append(testOutput);
		try {
			if(parseResult(testOutput)) {
				updateSubmitResult(SubmitResultValue.PASS);
				Color c = new Color(176, 255, 161, 100);
				main.bSubmit.setBackground(c);
			
			} else {
				Color c = new Color(247, 111, 111, 100);
				updateSubmitResult(SubmitResultValue.FAIL);
				main.bSubmit.setBackground(c);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			main.textAreaConsole.append(e1.getMessage());
		}
	}
	
	private void updateSubmitResult(SubmitResultValue result) throws IOException {
		if (main.currentQuestion == null)
			return;
		if (main.currentQuestion.getSubmitResult() == null) {
			List<QuestionPropertySubmitResultBean> listSubmitResult = new ArrayList<QuestionPropertySubmitResultBean>();
			listSubmitResult.add(new QuestionPropertySubmitResultBean(new Date(), result.toString()));
			main.currentQuestion.setSubmitResult(listSubmitResult);
		} else {
			main.currentQuestion.getSubmitResult().add(new QuestionPropertySubmitResultBean(new Date(), result.toString()));
		}
		
		MyFileUtil.writeQuestionProperty(main.currentQuestion);
		
	}
	
	//total: 3, pass: 3, fail: 0
	private boolean parseResult(String output) {
		if (output == null)
			return false;
		String[] arrOutput = output.split("\n");
		String strResult = arrOutput[arrOutput.length - 1];
		
		String[] arrColonSpace = strResult.split(": ");
		String failNumber = arrColonSpace[arrColonSpace.length - 1];
		int intFailNubmer = Integer.parseInt(failNumber);
		
//		for(String s : arrColonSpace) {
//			if (s.contains(",")) {
//				String[] arr = s.split(",");
//				
//			}
//		}
		
		return intFailNubmer == 0;
	}

}
