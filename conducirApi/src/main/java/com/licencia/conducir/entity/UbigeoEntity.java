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
@Table(name = "UBIGEO")
@Entity(name = "UbigeoEntity")
public class UbigeoEntity {
	@Id
	@Column(name="CUBIGEO", nullable=false, length = 6)
    private String cUbigeo;
	
	@Column(name="CDEPARTAMENTO", nullable=true, length = 2)
    private String cDepartamento;
	
	@Column(name="DEPARTAMENTO", nullable=true, length = 100)
    private String departamento;	
	
	@Column(name="CPROVINCIA", nullable=true, length = 2)
    private String cProvincia;
	
	@Column(name="PROVINCIA", nullable=true, length = 100)
    private String provincia;
	
	@Column(name="CDISTRITO", nullable=true, length = 2)
    private String cDistrito;
	
	@Column(name="DISTRITO", nullable=true, length = 100)
    private String distrito;	
}
