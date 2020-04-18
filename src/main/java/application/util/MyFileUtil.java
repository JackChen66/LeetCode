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
	
	
	public static QuestionPropertyBean getQuestionProperty(String questionNumber) throws IOException, CustomException {
		String propertyFilePath = FileName.QUESTION_FOLDER_PATH + "/" + questionNumber + "/" + FileName.PROPERTY_JSON;
		
		File fProperty = new File(propertyFilePath);
		
		if(!fProperty.exists()) {
			throw new CustomException(propertyFilePath + " does not exist.");
		}
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
	
	public static void writeAnswerContentToOutput(String content) throws IOException {	
		String outputFileDir = PropertyFileUtil.readPathProperty("output");
		File solutionFile = new File(outputFileDir + "/" + FileName.SOLUTION_JAVA);
		
		File applicationAnswerRunning = new File(outputFileDir);
		FileUtils.cleanDirectory(applicationAnswerRunning);
		if (solutionFile.exists())
			FileUtils.deleteQuietly(solutionFile);
		
		solutionFile.createNewFile();
		FileUtils.write(solutionFile, content, Charset.defaultCharset());
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
	
	/**
	 * Check input path folder is directory and exist, if not throws CustomException with error message
	 * @param pathFolder is path of a directory
	 * @throws CustomException while input pathFolder is not directory or not exists
	 */
	public static void checkPathFolder(String pathFolder) throws CustomException {
		File file = new File(pathFolder);
		if (!file.isDirectory()) {
			throw new CustomException(pathFolder + " is not directory.");
		}
		
		if (!file.exists()) {
			throw new CustomException(pathFolder + " does not exist.");
		}
	}
}
