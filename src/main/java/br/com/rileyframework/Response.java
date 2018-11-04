package br.com.rileyframework;

import com.google.gson.Gson;
import lombok.Data;

import java.io.PrintWriter;

@Data
public class Response {
	
	private PrintWriter printWriter;
	private int code;
	
	public Response json(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		printWriter.print(json);
		return this;
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
