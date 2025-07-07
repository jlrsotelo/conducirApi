package com.licencia.conducir.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS") // DB
@Entity(name = "UserEntity") //Java
public class UserEntity {
	@Id // PK
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
    @SequenceGenerator(sequenceName = "SEQ_USER", allocationSize = 1, name = "seqUser")
    @Column(name = "CUSER",nullable = false, length = 4)
	private Long cUser; 
    
	@Column(name = "NOMUSER",nullable = false, length = 20, unique = true)
    private String nomUser;

	@Column(name = "PWDUSER",nullable = false, length = 150, unique = true)
    private String pwdUser;
		
	@Column(name = "STATE",nullable = false, length = 1)
    private String state;
}