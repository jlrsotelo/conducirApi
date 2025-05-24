package com.licencia.conducir.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.licencia.conducir.dto.Department;
import com.licencia.conducir.dto.District;
import com.licencia.conducir.dto.Province;
import com.licencia.conducir.repository.UbigeoRepository;
import com.licencia.conducir.services.ServiceException;
import com.licencia.conducir.services.UbigeoService;

@Service
public class UbigeoServiceImpl implements UbigeoService{
	private final UbigeoRepository ubigeoRepository;
	
	public UbigeoServiceImpl(UbigeoRepository ubigeoRepository) {
		this.ubigeoRepository = ubigeoRepository;
	}

	@Override
	public List<Department> findByDep() throws ServiceException {
		try {
			return this.ubigeoRepository.findByDep();
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Province> findByProv(String cdepartamento) throws ServiceException {
		try {
			return this.ubigeoRepository.findByProv(cdepartamento);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<District> findByDist(String cdepartamento, String cprovincia) throws ServiceException {
		try {
			return this.ubigeoRepository.findByDist(cdepartamento, cprovincia);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

}
