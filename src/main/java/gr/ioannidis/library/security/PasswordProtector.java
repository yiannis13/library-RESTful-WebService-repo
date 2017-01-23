package gr.ioannidis.library.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;


@Service
public class PasswordProtector implements IPasswordProtector {

	@Override
	public String protect(String password) {
		if (password == null) {
			throw new IllegalArgumentException("password must not be null");
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = password.getBytes();
			bytes = md.digest(bytes);
			return Base64.encodeBase64String(bytes);
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
