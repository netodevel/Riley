package br.com.rileyframework;

/**
 * Atributes of a request
 * 
 * @author NetoDevel
 */
public class UrlMapping {

	private String action;
	private String controllerAction;
	private String method;
	private String regex;
	
	public UrlMapping(){
	}

	public UrlMapping(String action, String controllerAction, String method) {
		super();
		this.action = action;
		this.controllerAction = controllerAction;
		this.method = method;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getControllerAction() {
		return controllerAction;
	}

	public void setControllerAction(String controllerAction) {
		this.controllerAction = controllerAction;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	public UrlMapping withRegex(String regex) {
		this.regex = regex;
		return this;
	}
	
}
