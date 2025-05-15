package com.licencia.conducir.services;

import java.util.List;

import com.licencia.conducir.entity.EstablishmentEntity;

public interface EstablishmentService {
	List<EstablishmentEntity> findAll() throws ServiceException;
	EstablishmentEntity save(EstablishmentEntity establishmentEntity) throws ServiceException;
	EstablishmentEntity update(Long id, EstablishmentEntity establishmentEntity) throws ServiceException;
	void delete(Long id) throws ServiceException;
}
