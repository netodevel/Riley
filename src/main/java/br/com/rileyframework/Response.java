package br.com.rileyframework;

import java.io.PrintWriter;

import com.google.gson.Gson;

public class Response {
	
	private PrintWriter printWriter;
	
	private int code;
	
	public Response json(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		printWriter.print(json);
		return this;
	}

	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
	
	public Response status(int code) {
		this.setCode(code);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
