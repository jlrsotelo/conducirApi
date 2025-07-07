package com.licencia.conducir.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.licencia.conducir.entity.EstablishmentEntity;

public interface EstablishmentService {
	List<EstablishmentEntity> findAll() throws ServiceException;
	Page<EstablishmentEntity> findAllPagination(Pageable pageable) throws ServiceException;
	List<EstablishmentEntity> findByType(String type) throws ServiceException;
	EstablishmentEntity save(EstablishmentEntity establishmentEntity) throws ServiceException;
	EstablishmentEntity update(Long id, EstablishmentEntity establishmentEntity) throws ServiceException;
	void delete(Long id) throws ServiceException;
	Optional<EstablishmentEntity> findById(Long id) throws ServiceException;
	List<EstablishmentEntity> findByUbigeo(String type, String cubigeo) throws ServiceException;
}
