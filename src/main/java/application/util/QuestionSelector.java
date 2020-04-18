package application.util;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import application.exception.CustomException;
import application.property.bean.QuestionPropertyBean;

public class QuestionSelector {
	public static QuestionPropertyBean randomSelectQuestion(int currentQuestion) throws IOException, CustomException {
		List<QuestionPropertyBean> listAllQuestions = QuestionScanner.loadAllQuestions();
		
		if (listAllQuestions == null || listAllQuestions.size() == 0)
			return null;
		
		if (listAllQuestions.size() == 1)
			return listAllQuestions.get(0);
		
		Random r = new Random();
		int index = r.nextInt(listAllQuestions.size());
		int count = 0;
		while(listAllQuestions.get(index).getNumber() == currentQuestion) {
			index = r.nextInt(listAllQuestions.size());
			if (count == 100000)
				break;
			count++;
		}
		
		return listAllQuestions.get(index);
	}
}
