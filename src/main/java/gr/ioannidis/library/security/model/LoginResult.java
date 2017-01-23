package gr.ioannidis.library.security.model;


public class LoginResult {
	
	private boolean error;
	private String token;
	private String errorMessage;
	
	
	private LoginResult(boolean error, String token, String errorMessage) {
		this.error = error;
		this.token = token;
		this.errorMessage = errorMessage;
	}

	public static LoginResult successfulLogin(String token) {
		return new LoginResult(false, token, null);
	}
	
	public static LoginResult failedLogin(String errorMessage) {
		return new LoginResult(true, null, errorMessage);
	}
	

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
