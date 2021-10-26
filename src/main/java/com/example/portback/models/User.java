package com.example.portback.models;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "PORTBACK_USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "USER_ID")
	private Long Id;
	
	@Column(name = "USER_NAME")
	private String name;
	
	@Column(name = "USER_PASSWORD")
	@Size(min = 6, max = 16)
	private String password;
	
	@ManyToMany(fetch = EAGER)
	private List<Role> roles;
	

}
