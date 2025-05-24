package com.licencia.conducir.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.licencia.conducir.entity.TypesEntity;
import com.licencia.conducir.repository.TypesRepository;
import com.licencia.conducir.services.ServiceException;
import com.licencia.conducir.services.TypesService;

@Service
public class TypesServiceImpl implements TypesService{
	private final TypesRepository typesRepository;

	public TypesServiceImpl(TypesRepository typesRepository) {
		this.typesRepository = typesRepository;
	}

	@Override
	public List<TypesEntity> findAll() throws ServiceException {
		try {
			return this.typesRepository.findAll();
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}
}
