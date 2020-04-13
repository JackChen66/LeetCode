package application.compile;

import java.io.File;
import java.io.IOException;

import application.constant.FileName;
import application.exception.CustomException;

public class CompleCodeMaven {
	public static Object lock = new Object();
	
	public static void Compile() throws IOException, InterruptedException, CustomException {
		synchronized (lock) {
			File file = new File(FileName.RESOURCES + "build/build.sh");
			if (file.exists()) {
				Process p = new ProcessBuilder("/bin/sh", FileName.RESOURCES + "build/build.sh").start();

			} else {
				throw new CustomException("build.sh file not exist.");
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, CustomException {
		Compile();
	}
}
