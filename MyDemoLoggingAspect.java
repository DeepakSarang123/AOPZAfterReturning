package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {
	
	//add a new advice for@AfterReturning on findAccounts method
	
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))" ,
			returning =  "result"   )
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
		
		//printout which method we are advising on
		String method = theJoinPoint.getSignature().toString();
		System.out.println("\n====>>>>Executing @AfterReturning on method:" + method);
		
		//printout it's results
		System.out.println("\n====>>>>result is:" + result);
		
		//post-process data ..modify it
		
		//convert the acc names to uppercase
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n====>>>>result is:" + result);
	}
	
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		//loop through accounts
		for(Account tempAccount: result) {
			
			//get uppercase version
		    String theUpperCaseName = tempAccount.getName().toUpperCase();
		
			//update name on accounts
		    tempAccount.setName(theUpperCaseName);
		
		}
	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDAOPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		
		System.out.println("\n====>>>>Executing @Before advice on addAccount()");
		
		//display method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method: " + methodSig);
		
		//display method arguments
		
		//get args
		Object[] args = theJoinPoint.getArgs();
		
		//loop through args
		for(Object tempArg : args) {
			System.out.println(tempArg);
			
			if(tempArg instanceof Account) {
				//doencast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				
				System.out.println("account name: " + theAccount.getName());
				System.out.println("acccount level: " + theAccount.getLevel());
				
			}
		}
	}

}
