package gr.ioannidis.library.rs.exception;


public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3012202318453707937L;
	
	public ResourceNotFoundException() {
		
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
