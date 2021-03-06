package com.kakao.corp.model;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
	
	private int id;
	private String username;
	private String password;
	private String carrier;
	private String phoneNumber;
	private String email;
	private String address;
	private String userProfile;
	private String userRole;
	private String userBirth;
	private Timestamp createDate;
}
