package com.licencia.conducir.services;

import java.util.List;

import com.licencia.conducir.dto.Department;
import com.licencia.conducir.dto.District;
import com.licencia.conducir.dto.Province;

public interface UbigeoService {
	List<Department> findByDep() throws ServiceException;
	List<Province> findByProv(String cdepartamento) throws ServiceException;
	List<District> findByDist(String cdepartamento, String cprovincia) throws ServiceException;
}
