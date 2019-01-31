package com.his.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DemoCont {

	/**
	 * This method is used for return registration form with UserModel object
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "/userReg", method = RequestMethod.GET)
	public String userRegister(Model model) {
		//create and return UserMoDel object
		
		
		return "NewFile";
	}

}
