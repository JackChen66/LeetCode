package application.compile;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

public class CompileCode implements Runnable {
	
	String filePath;
	
	String consoleOutput;
	
	boolean result = true;
	
	public CompileCode(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void run(){
	    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    try (StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null);
	         StringWriter out = new StringWriter();
	         PrintWriter outWriter = new PrintWriter(out)) {
	        Iterable<? extends JavaFileObject> input =
	                fm.getJavaFileObjects(filePath);
	        List<String> options = Arrays.asList("--release", "8", "-XDrawDiagnostics");

	        compiler.getTask(outWriter, fm, null, options, null, input).call();
	        this.consoleOutput = out.toString();
	        if (this.consoleOutput == null || this.consoleOutput.length() == 0)
	        	this.consoleOutput = "Compile successfully for file " + filePath + "\n";
	        else 
	        	this.result = false;
	        
	        copyClassFileToTarget();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	this.consoleOutput += "\n" + e.getMessage();
	    }
	}
	
	private void copyClassFileToTarget() throws IOException {
	 	File file = new File("");
		File destFile = new File(file.getAbsoluteFile().getPath() 
				+ "/target/classes/application/answer/running/solution.class");
		
		File srcFile = new File(filePath.replace("solution.java", "solution.class"));
		
		FileUtils.copyFile(srcFile, destFile);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getConsoleOutput() {
		return consoleOutput;
	}

	public void setConsoleOutput(String consoleOutput) {
		this.consoleOutput = consoleOutput;
	}

	public boolean isResult() {
		return result;
	}	
}
