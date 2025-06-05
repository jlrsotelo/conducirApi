package com.licencia.conducir.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.licencia.conducir.entity.EstablishmentEntity;
import com.licencia.conducir.entity.TypesEntity;
import com.licencia.conducir.entity.UbigeoEntity;
import com.licencia.conducir.repository.EstablishmentRepository;
import com.licencia.conducir.repository.TypesRepository;
import com.licencia.conducir.repository.UbigeoRepository;
import com.licencia.conducir.services.EstablishmentService;
import com.licencia.conducir.services.ServiceException;

@Service
public class EstablishmentServiceImpl implements EstablishmentService{
	private final EstablishmentRepository establishmentRepository;
	private final UbigeoRepository ubigeoRepository;
	private final TypesRepository typesRepository;

	public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository, UbigeoRepository ubigeoRepository, TypesRepository typesRepository) {
		this.establishmentRepository = establishmentRepository;
		this.ubigeoRepository = ubigeoRepository;
		this.typesRepository = typesRepository;
	}

	@Override
	public List<EstablishmentEntity> findAll() throws ServiceException {
		try {
			return this.establishmentRepository.findAll();
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<EstablishmentEntity> findByType(String type) throws ServiceException {
		try {
			return this.establishmentRepository.findByType(type);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public EstablishmentEntity save(EstablishmentEntity establishmentEntity) throws ServiceException {
		try {
			Optional<UbigeoEntity> optUbigeoEntity=ubigeoRepository.findById(establishmentEntity.getCUbigeo().getCUbigeo());
			if (optUbigeoEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe el ubigeo con el id %s", establishmentEntity.getCUbigeo().getCUbigeo()));
			}
			establishmentEntity.setCUbigeo(optUbigeoEntity.get());
			
			Optional<TypesEntity> optTypesEntity=typesRepository.findById(establishmentEntity.getType().getId());
			if (optTypesEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe el tipo con el id %s", establishmentEntity.getType().getId()));
			}
			establishmentEntity.setCUbigeo(optUbigeoEntity.get());
			establishmentEntity.setType(optTypesEntity.get());
			return this.establishmentRepository.save(establishmentEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public EstablishmentEntity update(Long id, EstablishmentEntity establishmentEntity) throws ServiceException {
		try {

			Optional<EstablishmentEntity> optEstablishmentEntity = establishmentRepository.findById(id);

			if (optEstablishmentEntity.isPresent()) {

				EstablishmentEntity oEstablishmentEntity = optEstablishmentEntity.get();
				oEstablishmentEntity.setCEstablishment(id);
				Optional<UbigeoEntity> optUbigeoEntity=ubigeoRepository.findById(establishmentEntity.getCUbigeo().getCUbigeo());
				
				if (optUbigeoEntity.isEmpty()) {
					throw new ServiceException(String.format("No existe el ubigeo con el id %s", oEstablishmentEntity.getCUbigeo().getCUbigeo()));
				}
				
				Optional<TypesEntity> optTypesEntity=typesRepository.findById(establishmentEntity.getType().getId());
				if (optTypesEntity.isEmpty()) {
					throw new ServiceException(String.format("No existe el tipo con el id %s", establishmentEntity.getType().getId()));
				}
				
				oEstablishmentEntity.setCUbigeo(optUbigeoEntity.get());
				oEstablishmentEntity.setNRuc(establishmentEntity.getNRuc());
				establishmentEntity.setType(optTypesEntity.get());
				oEstablishmentEntity.setName(establishmentEntity.getName());
				oEstablishmentEntity.setAddress(establishmentEntity.getAddress());
				oEstablishmentEntity.setState(establishmentEntity.getState());
				oEstablishmentEntity.setEmail(establishmentEntity.getEmail());
				oEstablishmentEntity.setPhone(establishmentEntity.getPhone());
				return this.establishmentRepository.save(oEstablishmentEntity);
			}
			return this.establishmentRepository.save(establishmentEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			Optional<EstablishmentEntity> optEditorialEntity = establishmentRepository.findById(id);
			if (optEditorialEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe establecimiento con el id %s", id));
			}
			establishmentRepository.deleteById(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<EstablishmentEntity> findById(Long id) throws ServiceException {
		try {
			return this.establishmentRepository.findById(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
