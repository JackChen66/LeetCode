package application.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.constant.FileName;
import application.exception.CustomException;
import application.property.bean.QuestionPropertyBean;

public class QuestionScanner {
	public static List<QuestionPropertyBean> loadAllQuestions() throws IOException, CustomException {
		List<QuestionPropertyBean> list = new ArrayList<QuestionPropertyBean>();
		File file = new File(FileName.QUESTION_FOLDER_PATH);
		if (file.exists() && file.isDirectory()) {
			String[] directories = file.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
			
			for(String dir : directories) {
				list.add(MyFileUtil.getQuestionProperty(dir));
			}
		} 
		return list;
	}
}
