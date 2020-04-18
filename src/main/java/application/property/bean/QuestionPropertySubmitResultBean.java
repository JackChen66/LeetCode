package application.property.bean;

import java.util.Date;

public class QuestionPropertySubmitResultBean {
	Date time;
	String result;
	long runningTime;
	
	public long getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(long runningTime) {
		this.runningTime = runningTime;
	}
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
	
	
	public QuestionPropertySubmitResultBean(Date time, String result, long runningTime) {
		super();
		this.time = time;
		this.result = result;
		this.runningTime = runningTime;
	}
	public QuestionPropertySubmitResultBean() {
		super();
	}
	@Override
	public String toString() {
		return "QuestionPropertySubmitResultBean [time=" + time + ", result=" + result + ", runningTime=" + runningTime
				+ "]";
	}
	
	
}
