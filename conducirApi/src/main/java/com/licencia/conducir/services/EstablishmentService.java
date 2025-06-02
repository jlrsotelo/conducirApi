package com.licencia.conducir.services;

import java.util.List;
import java.util.Optional;

import com.licencia.conducir.entity.EstablishmentEntity;

public interface EstablishmentService {
	List<EstablishmentEntity> findAll() throws ServiceException;
	List<EstablishmentEntity> findByType(String type) throws ServiceException;
	EstablishmentEntity save(EstablishmentEntity establishmentEntity) throws ServiceException;
	EstablishmentEntity update(Long id, EstablishmentEntity establishmentEntity) throws ServiceException;
	void delete(Long id) throws ServiceException;
	Optional<EstablishmentEntity> findById(Long id) throws ServiceException;
}
