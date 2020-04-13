package application.exception;

public class TestFailException extends Exception {
	String messge;
	public TestFailException(String message) {
		this.messge = message;
	}
	public String getMessge() {
		return messge;
	}
}
