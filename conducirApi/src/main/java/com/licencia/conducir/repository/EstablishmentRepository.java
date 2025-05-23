package com.licencia.conducir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.licencia.conducir.entity.EstablishmentEntity;

@Repository
public interface EstablishmentRepository extends JpaRepository<EstablishmentEntity, Long>{
	@Query(value = "select p from EstablishmentEntity p where p.type=:type") // JPQL ( Java Persistence Query Language)
	List<EstablishmentEntity> findByType(@Param("type") String type);
}
