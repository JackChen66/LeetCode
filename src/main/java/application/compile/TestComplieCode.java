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

import org.apache.commons.io.FileUtils;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import application.answer.running.solution;
import application.constant.FileName;
import application.constant.PropertyKey;
import application.exception.CustomException;
import application.util.MyFileUtil;
import application.util.PropertyFileUtil;

public class TestComplieCode {	
	public StringBuilder sbOut;
	public StringBuilder sbErr;
	private String pathFolder;
	private long runningTime = -1;
	public TestComplieCode() throws IOException, CustomException {
		sbOut = new StringBuilder();
		sbErr = new StringBuilder();
		pathFolder = PropertyFileUtil.readPathProperty(PropertyKey.PATH_P_OUTPUT);
		
		MyFileUtil.checkPathFolder(pathFolder);
	}
	
	public int compile() throws Exception {
		removeClassFile();
    	int exitCode = runProcess("javac -cp src " + pathFolder + "/" + FileName.SOLUTION_JAVA);
    	if (exitCode == 0)
    		sbOut.append("Compile OK.\n");
    	return exitCode;
	}
	
	public int runTest() throws Exception {
		runningTime = -1;
		removeClassFile();
		int existCode = compile();
		long startTime = System.nanoTime();
		
		if (existCode == 0) {
			int testExistCode = runProcess("java -classpath " + pathFolder + " solution");
			long endTime   = System.nanoTime();
			runningTime = (endTime - startTime) / 1000000;
			return testExistCode;
		}
		else 
			throw new CustomException("Fail to compile " + pathFolder + "/" + FileName.SOLUTION_JAVA + "file."
					+ " Exist code is " + existCode + "\n" + getOutputAndError());
	}
	
	private void removeClassFile() {
		File classFile = new File(pathFolder + "/" + FileName.SOLUTION_CLASS);
		if (classFile.exists()) {
			FileUtils.deleteQuietly(classFile);
		}
	}
	
	private int runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
	
		BufferedReader brStdOut = new BufferedReader(
		        new InputStreamReader(pro.getInputStream()));
		BufferedReader brStderr = new BufferedReader(
		        new InputStreamReader(pro.getErrorStream()));
		
		Thread tBufferReaderReaderStdOut = new Thread(new BufferReaderReader(brStdOut, sbOut));
		Thread tBufferReaderReaderStdErr = new Thread(new BufferReaderReader(brStderr, sbErr));
		
		tBufferReaderReaderStdOut.start();
		tBufferReaderReaderStdErr.start();
		tBufferReaderReaderStdOut.join();
		tBufferReaderReaderStdErr.join();
		pro.waitFor();
		return pro.exitValue();
	}
	  
	public String getOutputAndError() {
		StringBuilder sb = new StringBuilder();
		if (sbOut != null && sbOut.toString().length() > 0) {
			sb.append("Output:\n" + sbOut.toString());
		}
		if (sbErr != null && sbErr.toString().length() > 0) {
			sb.append("\nError:\n" + sbErr.toString());
		}
		 return sb.toString();
	}
	
	public long getRunningTime() {
		return runningTime;
	}
}
