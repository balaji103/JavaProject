package com.his.ar.model;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 * @author nit This is a UserModel (Entity / Value Object) Used to transfer the
 *         data
 */
@Data
public class UserModel {

	private int userId;
	private String firstName;
	private String lastName;
	private String userEmail;
	private String userPwd;
	private String userDob;
	private String userPhno;
	private String userRole;
	private String activeSw;
	private Timestamp createDate;
	private Timestamp updatedDate;
	private String createdBy;
	private String updatedBy;
	
}//class
