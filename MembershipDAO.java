package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public boolean addMemberAccount() {
		
		System.out.println(getClass() + ":DOING Kelsa: ADDING A Membership ACCOUNT");
		return true;
	}
	
	
	public void goToSleep() {
		System.out.println(getClass() + ":Go to SLEEP");
	}
}
