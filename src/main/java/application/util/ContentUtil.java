package application.util;

public class ContentUtil {
	String content;
	public ContentUtil(String content) {
		this.content = content;
	}
	
	public ContentUtil addPackageInfo() {
		this.content = "package application.answer.running;\n" + this.content;
		return this;
	}
	
	public ContentUtil addListInfo(String content) {
		this.content = "import java.util.List;\n"
				+ "import java.util.ArrayList;\n"
				+ "import java.util.LinkedList;\n" + this.content;
		return this;
	}
	
	public ContentUtil addStackInfo(String content) {
		this.content = "import java.util.Stack;\n" + this.content;
		return this;
	}
	
	public ContentUtil addQueueInfo(String content) {
		this.content = "import java.util.Queue;\n" + this.content;
		return this;
	}
	
	public ContentUtil addMapInfo(String content) {
		this.content = "import java.util.Map;\n"
				+ "import java.util.HashMap;\n"
				+ "import java.util.TreeMap;\n" + this.content;
		return this;
	}
	
	public ContentUtil addArraysInfo(String content) {
		this.content = "import java.util.Arrays;\n" + this.content;
		return this;
	}
	
	
	public ContentUtil addTreeNodeInfo(String content) {
		this.content = "util.TreeNode;\n" + this.content;
		return this;
	}
	
	public ContentUtil addCollectoinsInfo(String content) {
		this.content = "import java.util.Collections;\n" + this.content;
		return this;
	}
	
	
	
	public ContentUtil addJunitTestInfo() {
		this.content = "import static org.junit.Assert.assertEquals;\n"
				+ "import org.junit.Test;\n"
				+ "import static org.junit.Assert.assertTrue;\n"
				+ "import org.junit.internal.TextListener;\n" + 
				"import org.junit.runner.JUnitCore;\n" + this.content;
		return this;
	}
	

	public ContentUtil addRandomInfo(String content) {
		this.content = "import java.util.Random;\n" + this.content;
		return this;
	}
	
	public ContentUtil addTestFailExceptionInfo(String content) {
		this.content = "import application.exception.TestFailException;\n" + this.content;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	
	/**
	 * Add package and needed library
	 */
	public String add() {
		
		//this.addJunitTestInfo();
		
		if (content.contains("List"))
			this.addListInfo(this.content);
		
		if (content.contains("Stack"))
			this.addStackInfo(this.content);
		
		if (content.contains("Queue"))
			this.addQueueInfo(this.content);
		
		if (content.contains("Map"))
			this.addMapInfo(this.content);
		
		if (content.contains("Arrays"))
			this.addArraysInfo(this.content);
		
		if (content.contains("TreeNode"))
			this.addTreeNodeInfo(this.content);
		
		if (content.contains("Collections"))
			this.addCollectoinsInfo(this.content);
		
		if (content.contains("Random"))
			this.addRandomInfo(this.content);
		
//		if (content.contains("TestFailException"))
//			this.addTestFailExceptionInfo(this.content);
		this.addPackageInfo();
		
		return this.content;
	}
}
