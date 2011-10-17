package eu.akka.labs.jms.common;

import java.io.Serializable;

public class MyMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MyMessage [message=" + message + "]";
	}
	
}
