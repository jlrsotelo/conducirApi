package com.licencia.conducir.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.licencia.conducir.entity.EstablishmentEntity;

@Repository
public interface EstablishmentRepository extends JpaRepository<EstablishmentEntity, Long>{
	@Query(nativeQuery = true,  value = "select * from establishment where type=:type")
	List<EstablishmentEntity> findByType(@Param("type") String type);
	
	@Query(nativeQuery = true,  value = "select * from establishment where type=:type and cubigeo like concat(:cubigeo, '%')")
	List<EstablishmentEntity> findByUbigeo(@Param("type") String type, @Param("cubigeo") String cubigeo);
	
	@Query("select p from EstablishmentEntity p")
	Page<EstablishmentEntity> findAllPagination(Pageable pageable);
}
