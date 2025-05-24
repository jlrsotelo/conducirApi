package com.licencia.conducir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.licencia.conducir.entity.TypesEntity;

@Repository
public interface TypesRepository extends JpaRepository<TypesEntity, Long>{

}
