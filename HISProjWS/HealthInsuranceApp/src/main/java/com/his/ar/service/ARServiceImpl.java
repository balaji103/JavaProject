package com.his.ar.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.ar.dao.ARUserMasterDAO;
import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserModel;
import com.his.util.ArConstants;
import com.his.util.EmailService;
import com.his.util.PasswordUtil;

/**
 * This is the service class it provides the b.methods
 * @author nit 
 */
@Service("arService")
public class ARServiceImpl implements ARService {

	@Autowired(required = true)
	private ARUserMasterDAO arUserMasterDao;
	private EmailService emailService;

	/**
	 * This method is used to insert the case worker data into database
	 * @author nit 
	 * 
	 */
	@Override
	public int saveUser(UserModel model) {
		// Create entity class object
		ARUserMaster entity = new ARUserMaster();

		// Copy incoming model data into entity class object
		BeanUtils.copyProperties(model, entity);
		
		entity.setActiveSw("Y");
		entity.setCreatedBy("admin");
		
		//set created_date
		entity.setCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		
		//Encrypt and save password to db table
		entity.setUserPwd(PasswordUtil.encrypt(entity.getUserPwd()));

		System.out.println(entity);
		// Save the entity class using repository
		ARUserMaster savedEntity = arUserMasterDao.save(entity);
		
		//send registration mail to user
		if(savedEntity!=null) {
			String body=null;
			try {
				body=getEmailFormatBody(model);
				emailService.sendEmail(model.getUserEmail(),ArConstants.EMAIL_FROM,ArConstants.EMAIL_SUBJECT , body);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Save JPA generated genrator value of userId to model object
		//model.setUserId(savedEntity.getUserId());
		return savedEntity.getUserId();
		//return 1;
	}
	
	/**
	 * this method is used for format 
	 * the email body (Replace the all 
	 * placeholders by real values.)
	 * @param um
	 * @return String
	 */
	
	private String getEmailFormatBody(UserModel um) throws Exception {
		String fileName = "Registration_Email_Template.txt";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		StringBuilder mailBody = new StringBuilder("");
		
		while (line != null) {
			//processing mail body content
			if (line.contains("USER_NAME")) {
				line = line.replace("USER_NAME", um.getFirstName() + " " + um.getLastName());
			}
			if (line.contains("APP_URL")) {
				line = line.replace("APP_URL", "<a href='http://localhost:9090/HIS/loginForm'>RI HIP</a>");
			}
			if (line.contains("APP_USER_EMAIL")) {
				line = line.replace("APP_USER_EMAIL", um.getUserEmail());
			}
			if (line.contains("APP_USER_PWD")) {
				line = line.replace("APP_USER_PWD", um.getUserPwd());
			}
			// appending processed line to StringBulder object
			mailBody.append(line);
			//read next line
			line = br.readLine();
		}//while
		
		//close br and fr
		br.close();
		fr.close();
		//return mail body
		return mailBody.toString();
	}//getEmailFormatBody

	
	  @Override
	  public String checkUserMail(String emailId) {
		  Integer cnt = arUserMasterDao.findByUserEmail(emailId);
		  return(cnt!=0)?ArConstants.DUPLICATE : ArConstants.UNIQUE;
	  
			  
	  }
	 

}
