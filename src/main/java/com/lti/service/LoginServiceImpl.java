package com.lti.service;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import com.lti.entity.User;
import com.lti.exception.LoginServiceException;
import com.lti.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginRepository loginRepository;
	
	public User login(String email, String password) {
		 try {
			 if(!loginRepository.isUserPresent(email))
				 throw new LoginServiceException("not registered");
			 int id = loginRepository.findByEmailAndPassword(email, password);
			 return loginRepository.findById(id);
		 }
		 catch(LoginServiceException e){
			 throw new LoginServiceException("not valid email or password");
			 
		 }		 
	 }
}
