package gr.ioannidis.library.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gr.ioannidis.library.rs.exception.AccessNotAllowedException;
import gr.ioannidis.library.security.ILoginManager;


@Component
@Aspect
public class SecurityAspect {
	
	@Autowired
	private ILoginManager loginManager;
	
	
	@Before("allCategoryRestMethods(token) || allBookRestMethods(token)") 
	public void loginAdvice(String token) {
		if (!loginManager.isValidToken(token)) {
			throw new AccessNotAllowedException("Access to the specified resource has been forbidden");
		}
	}
	
	
	@Pointcut("within(gr.ioannidis.library.rs.category.CategoryRestController) && args(token,..)")
	public void allCategoryRestMethods(String token){}
	
	
	@Pointcut("within(gr.ioannidis.library.rs.book.BookRestController) && args(token,..)")
	public void allBookRestMethods(String token){}
	
}
