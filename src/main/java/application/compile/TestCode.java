package application.compile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import application.answer.running.solution;
import application.constant.FileName;

public class TestCode {	
	public static StringBuilder sb = new StringBuilder();
	public static void runTest() {
		sb = new StringBuilder();
	    try {
	        runProcess("javac -cp src src/main/java/application/answer/running/solution.java");
	        
	        runProcess("java -classpath src/main/java application.answer.running.solution");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	private static void printLines(String cmd, InputStream ins) throws Exception {
	    String line = null;
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(ins));
	    
	    while ((line = in.readLine()) != null) {
	    	sb.append(line + "\n");
	    }
	  }
	
	  private static void runProcess(String command) throws Exception {
	    Process pro = Runtime.getRuntime().exec(command);
	    printLines(command + " stdout:", pro.getInputStream());
	    printLines(command + " stderr:", pro.getErrorStream());
	    pro.waitFor();
	    System.out.println(command + " exitValue() " + pro.exitValue());
	  }
	  
	  public static void main(String[] args) {
		runTest();
		System.out.println(sb.toString());
	  }
}
