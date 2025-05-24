package com.licencia.conducir.services;

import java.util.List;

import com.licencia.conducir.entity.TypesEntity;

public interface TypesService {
	List<TypesEntity> findAll() throws ServiceException;
}
