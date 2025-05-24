package com.licencia.conducir.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TYPES")
@Entity(name = "TypesEntity")
public class TypesEntity {
	@Id
	@Column(name="ID", nullable=false)
    private Long id;
	
	@Column(name="NAME", nullable=false, length = 40)
    private String name;
}
