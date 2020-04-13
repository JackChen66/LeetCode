package application.property.bean;

import java.util.Date;
import java.util.List;

public class QuestionPropertyBean {
	String name;
	String level;
	Date createDate;
	int number;
	List<QuestionPropertySubmitResultBean> SubmitResult;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<QuestionPropertySubmitResultBean> getSubmitResult() {
		return SubmitResult;
	}
	public void setSubmitResult(List<QuestionPropertySubmitResultBean> submitResult) {
		SubmitResult = submitResult;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "QuestionPropertyBean [name=" + name + ", level=" + level + ", createDate=" + createDate + ", number="
				+ number + ", SubmitResult=" + SubmitResult + "]";
	}
	
	
	 
}
