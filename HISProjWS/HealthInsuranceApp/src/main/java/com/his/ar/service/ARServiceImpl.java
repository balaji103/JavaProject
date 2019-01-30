package com.his.ar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.ar.dao.ARUserMasterDAO;
import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserModel;
import com.his.util.ArConstants;
import com.his.util.PasswordUtil;

/**
 * This class is service class
 * @author nit 
 */
@Service("arService")
public class ARServiceImpl implements ARService {

	@Autowired(required = true)
	public ARUserMasterDAO arUserMasterDao;

	/**
	 * This method is used to insert the user data into database
	 * @author nit 
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

		// Save JPA generated genrator value of userId to model object
		//model.setUserId(savedEntity.getUserId());
		return savedEntity.getUserId();
		//return 1;
	}

	
	  @Override
	  public String checkUserMail(String emailId) {
		  Integer cnt = arUserMasterDao.findByUserEmail(emailId);
		  return(cnt!=0)?ArConstants.DUPLICATE : ArConstants.UNIQUE;
	  
			  
	  }
	 

}
