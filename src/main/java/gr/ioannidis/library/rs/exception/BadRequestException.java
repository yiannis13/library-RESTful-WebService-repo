package gr.ioannidis.library.rs.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -6589354640508041198L;

	public BadRequestException() {

	}

	public BadRequestException(String msg) {
		super(msg);
	}

}
