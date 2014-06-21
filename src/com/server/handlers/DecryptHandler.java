package com.server.handlers;

import com.common.utils.BCrypt;

public class DecryptHandler{

	ResponseHandler responseHandler = new ResponseHandler();

	public boolean decryptPassword(String username, String pass){
		String password = responseHandler.getUserPassword(username);
		if(BCrypt.checkpw(password, pass)){
			return true;
		}else{
			return false;
		}
	}

}
