package application.compile;

import java.io.BufferedReader;
import java.io.IOException;

public class BufferReaderReader implements Runnable {
	private BufferedReader br;
	private StringBuilder sb;
	private String errorMessage = null;
	public BufferReaderReader(BufferedReader br, StringBuilder sb) {
		this.br = br;
		this.sb = sb;
	}
	@Override
	public void run() {
		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

}
