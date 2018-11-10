package br.com.rileyframework;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

import java.io.PrintWriter;
import java.util.Map;

@Data
@Builder
public class Response {
	
	private PrintWriter printWriter;
	private int code;
	private String html;
	private Map<String, Object> modelAndView;

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

	public Response html(String html) {
		this.html = html;
		return this;
	}

	public Response modelAndView(Map<String, Object> modelAndView) {
		this.modelAndView = modelAndView;
		return this;
	}
}
