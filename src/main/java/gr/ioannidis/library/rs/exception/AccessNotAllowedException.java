package gr.ioannidis.library.rs.exception;

public class AccessNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = -4518787337583782899L;

	public AccessNotAllowedException() {

	}

	public AccessNotAllowedException(String msg) {
		super(msg);
	}

}
