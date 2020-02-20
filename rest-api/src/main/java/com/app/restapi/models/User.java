package com.app.restapi.models;

import java.io.Serializable;
import java.lang.annotation.Target;
import java.sql.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.glassfish.jersey.Beta;
import org.hibernate.validator.constraints.UniqueElements;

import com.app.restapi.Utils.AutoGenerateIdUtils;
import com.app.restapi.Utils.DateUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Getter
@Table(name = "users")
public class User {
//		public  User() {}	
//		public User(int id, String lastname, String firstname, @NonNull String email, int age, String tel,
//			String dateNaissance, String dateCreation, String dateModification) {
//		this.id = id;
//		this.lastname = lastname;
//		this.firstname = firstname;
//		this.email = email;
//		this.age = age;
//		this.tel = tel;
//		this.dateNaissance = dateNaissance;
//		this.dateCreation = dateCreation;
//		this.dateModification = dateModification;
//	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(insertable = false)
	private Integer id;
	
	private String lastname;
	
	private String firstname;

	private String email;
	
	private Integer age;
	
	private String tel;	
	
    private String dateNaissance;
	
    private String dateCreation;
	
    private String dateModification;


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the dateNaissance
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @return the dateCreation
	 */
	public String getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public String getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}
	
	
}
