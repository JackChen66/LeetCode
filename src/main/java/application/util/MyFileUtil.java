package application.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import application.constant.FileName;
import application.exception.CustomException;
import application.property.bean.QuestionPropertyBean;

public class MyFileUtil {
	public static String getAnswerPreLoad(int questionNumber) throws IOException {
		String answerPreload = FileName.QUESTION_FOLDER_PATH + "/" + questionNumber 
				+ "/" + FileName.ANSWER_PRELOAD;
		
		File fAnswerPreload = new File(answerPreload);
		if (fAnswerPreload.exists()) {
			return FileUtils.readFileToString(fAnswerPreload, "utf-8");
		}
		return null;
		
	}
	
	public static String getQuestionContent(int questionNumber) throws IOException {
		String sQuestionFilePath = FileName.QUESTION_FOLDER_PATH + "/" + questionNumber 
				+ "/" + FileName.QUESTION;
		
		File fQuestion = new File(sQuestionFilePath);
		if (fQuestion.exists()) {
			return FileUtils.readFileToString(fQuestion, "utf-8");
		}
		return null;	
	}
	
	
	public static QuestionPropertyBean getQuestionProperty(String questionPath) throws IOException {
		String propertyFilePath = questionPath + "/" + FileName.PROPERTY_JSON;
		
		File fProperty = new File(propertyFilePath);
		
		if(!fProperty.exists())
			return null;
		String fileContent = FileUtils.readFileToString(fProperty, "utf-8");
		QuestionPropertyBean qpb = JsonUtil.fromJson(fileContent, QuestionPropertyBean.class);
		return qpb;		
	}
	
	public static void writeQuestionProperty(QuestionPropertyBean q) throws IOException {
		String propertyFilePath = FileName.QUESTION_FOLDER_PATH + "/" + q.getNumber() 
				+ "/" +FileName.PROPERTY_JSON;
		
		File fProperty = new File(propertyFilePath);
		
		String sContent = JsonUtil.toJson(q);

		if (fProperty.exists()) {
			
			FileUtils.write(fProperty, "", Charset.defaultCharset());
			
			FileUtils.write(fProperty, sContent, Charset.defaultCharset());
		} else {
			fProperty.createNewFile();
			FileUtils.write(fProperty, sContent, Charset.defaultCharset());
		}
	}
	
	public static void writeAnswerContentToPackage(String content) throws IOException {		
		File solutionFile = new File(FileName.SOLUTION_JAVA_PATH);
		
		File applicationAnswerRunning = new File(FileName.APPLICATION_ANSWER_RUNNING);
		FileUtils.cleanDirectory(applicationAnswerRunning);
		if (solutionFile.exists())
			FileUtils.deleteQuietly(solutionFile);
		
		solutionFile.createNewFile();
		FileUtils.write(solutionFile, content, Charset.defaultCharset());
		
		//clearTargetSolutionFiles();
	}
	
	private static void clearTargetSolutionFiles() throws IOException {
		File file = new File("");
		String classFileDir = file.getAbsoluteFile().getPath() + "/target/classes/application/answer/running";
	
		FileUtils.cleanDirectory(new File(classFileDir));
	}
	
	
	public static void copyTestCasesToPackage(int questionNumber) throws IOException, CustomException {
		File testDestFile = new File(FileName.SOLUTION_TEST_JAVA_PATH);
		if (testDestFile.exists())
			FileUtils.deleteQuietly(testDestFile);
		
		if(questionNumber < 1) {
			throw new CustomException("Question Number to copy test case cannot be smaller than 1."
					+ " Atual is " + questionNumber);
		}
		File testSrcFile = new File(FileName.QUESTION_FOLDER_PATH + "/" 
						+ questionNumber + "/" + FileName.TEST_CASES);
		FileUtils.copyFile(testSrcFile, testDestFile);
		
		if (!testDestFile.exists()) {
			throw new CustomException(testDestFile.getPath() + " does not exist.");
		}
	}
	
	public static String copyTestCasesToFile(int questionNumber, String content) throws IOException, CustomException {
		if(questionNumber < 1) {
			throw new CustomException("Question Number to copy test case cannot be smaller than 1."
					+ " Atual is " + questionNumber);
		}
		File testSrcFile = new File(FileName.QUESTION_FOLDER_PATH + "/" 
						+ questionNumber + "/" + FileName.TEST_CASES);
		String testCaseContent = FileUtils.readFileToString(testSrcFile, "utf-8");
		
		if (content == null || content.length() == 0)
			throw new CustomException("Input content is null or empty.");
		
		content = content.trim();
		int lastIndex = 0;
		for(int i = content.length() - 1; i >= 0; i--) {
			if (content.charAt(i) == '}') {
				lastIndex = i;
				break;
			}
		}
		
		content = content.substring(0, lastIndex);
		return  content + "\n" + testCaseContent + "}";
	}
}
