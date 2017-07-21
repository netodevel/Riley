package br.com.rileyframework;

import java.io.PrintWriter;

import com.google.gson.Gson;

public class Response {
	
	private PrintWriter printWriter;
	
	public void json(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		printWriter.print(json);
	}

	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
	
}
