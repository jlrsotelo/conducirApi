package com.licencia.conducir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.licencia.conducir.dto.Department;
import com.licencia.conducir.dto.District;
import com.licencia.conducir.dto.Province;
import com.licencia.conducir.entity.UbigeoEntity;

@Repository
public interface UbigeoRepository extends JpaRepository<UbigeoEntity, String>{	
	@Query(nativeQuery = true, value = "SELECT cdepartamento, departamento FROM ubigeo GROUP BY cdepartamento, departamento ORDER BY departamento")
	List<Department> findByDep();
	
	@Query(nativeQuery = true, value = "SELECT cprovincia, provincia FROM ubigeo WHERE cdepartamento=:cdepartamento GROUP BY cprovincia, provincia ORDER BY provincia")
	List<Province> findByProv(@Param("cdepartamento") String cdepartamento);

	@Query(nativeQuery = true, value = "SELECT cubigeo, distrito FROM ubigeo WHERE cdepartamento=:cdepartamento AND cprovincia=:cprovincia ORDER BY distrito")
	List<District> findByDist(@Param("cdepartamento") String cdepartamento, @Param("cprovincia") String cprovincia);	
}
