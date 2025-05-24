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
@Table(name = "ESTABLISHMENT")
@Entity(name = "EstablishmentEntity")
public class EstablishmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEstablishment")
	@SequenceGenerator(sequenceName = "SEQ_ESTABLISHMENT", allocationSize = 1, name = "seqEstablishment")
	@Column(name="CESTABLISHMENT", nullable=false)
    private Long cEstablishment;
	
	@Column(name="CUBIGEO", nullable=false, length = 6)
	private String cUbigeo;
	
	@Column(name="NRUC", nullable=false, length = 11, unique = true)
    private String nRuc;
	
	@Column(name="TYPE", nullable=false, length = 1)
    private String type;
	
	@Column(name="NAME", nullable=false, length = 100, unique = true)
    private String name;
	
	@Column(name="ADDRESS", nullable=false, length = 150)
    private String address;
	
	@Column(name="STATE", nullable=false, length = 1)
    private String state;
	
	@Column(name="EMAIL", nullable=true, length = 20)
    private String email;
	
	@Column(name="PHONE", nullable=true, length = 20)
    private String phone;
}
