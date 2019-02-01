package com.his.ar.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * @author nit
 * Entity class case worker registration
 * 
 */
@Entity
@Table(name="AR_USER_MASTER")
@Data
public class ARUserMaster {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AR_USER_MASTER_ID_SEQ")
	@Column(name="USER_ID" , length=10)
	private int userId;
	
	@Column(name="FIRST_NAME",length=30)
	private String firstName;
	@Column(name="LAST_NAME",length=30)
	private String lastName;
	@Column(name="EMAIL",length=30)
	private String userEmail;
	@Column(name="PASSWORD",length=100)
	private String userPwd;
	@Column(name="DOB")
	private String userDob;
	@Column(name="PHNO",length=20)
	private String userPhno;
	@Column(name="ROLE",length=20)
	private String userRole;
	@Column(name="ACTIVE_SW",length=10)
	private String activeSw;
	@Column(name="CRATED_DATE")
	private Timestamp createDate;
	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;
	@Column(name="CREATED_BY",length=30)
	private String createdBy;
	@Column(name="UPDATED_BY",length=30)
	private String updatedBy;
	
}//close class
