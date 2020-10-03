package com.lti.repository;

public interface AdminRepository extends GenericRepository {
	
	boolean isAdminExists(int id);
	
	int getAdminIdByUsernamePassword(String uname, String password);

}
