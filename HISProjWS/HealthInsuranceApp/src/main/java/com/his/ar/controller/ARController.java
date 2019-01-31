package com.his.ar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.ar.model.UserModel;
import com.his.ar.service.ARService;
import com.his.util.ArConstants;

/**
 * @author nit This is controller
 */
@Controller
public class ARController {

	@Autowired(required = true)
	public ARService service;
	/**
	 * This method is used for return registration form with UserModel object
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "/userReg", method = RequestMethod.GET)
	public String userRegister(Model model) {
		//create and return UserMoDel object
		UserModel userModel = new UserModel();
		model.addAttribute("formModel", userModel);
		//add user roles to userRole of form
		getUserRole(model);
		
		return "registration";
	}

	/**
	 * This method used for returning the userRole
	 * @param model
	 */
	private void getUserRole(Model model) {
		//add user roles to Model object
		List<String> userRole=new ArrayList<String>();
		userRole.add("Admin");
		userRole.add("Case Worker");
		model.addAttribute("userRole",userRole);
	}
	
	/**
	 * This method takes registration form , process the form field and
	 * save into the db
	 * @param userModel
	 * @param model
	 * @return String
	 */

	@RequestMapping(value = "/userReg", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("formModel") UserModel userModel, Model model) {
		// Use service
		int count = service.saveUser(userModel);
		if (count != 0) {
			model.addAttribute(ArConstants.SUCCESS, ArConstants.SUCCESS_MSG);
		} else {
			model.addAttribute(ArConstants.ERROR, ArConstants.ERROR_MSG);
		}
		//add user roles to userRole of form
				getUserRole(model);
		// LVN
		return "registration";
	}
	
	
	  @RequestMapping(value="/userReg/checkEmail") 
	  public @ResponseBody String checkUserEmail(@RequestParam("email") String email) { 
		 // System.out.println("emailCheck");
		  return service.checkUserMail(email); 
	  }
	 
}// class
