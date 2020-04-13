package application.property.bean;

import java.util.Date;

public class QuestionPropertySubmitResultBean {
	Date time;
	String result;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public QuestionPropertySubmitResultBean(Date time, String result) {
		super();
		this.time = time;
		this.result = result;
	}
	public QuestionPropertySubmitResultBean() {
		super();
	}
	@Override
	public String toString() {
		return "QuestionPropertySubmitResultBean [time=" + time + ", result=" + result + "]";
	}	
	
}
