package com.licencia.conducir.controller;

import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.licencia.conducir.entity.EstablishmentEntity;
import com.licencia.conducir.services.EstablishmentService;
import com.licencia.conducir.services.ServiceException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {
	private static Logger logger = LoggerFactory.getLogger(EstablishmentController.class);
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	private final String MSG_BAD_REQUEST = "Operación no valida";
	private Map<String, String> map = new HashMap<>();
	private final EstablishmentService establishmentService;
	
	public EstablishmentController(EstablishmentService establishmentService) {
		this.establishmentService = establishmentService;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		try {
			List<EstablishmentEntity> lstEstablishmentEntity = this.establishmentService.findAll();
			if (lstEstablishmentEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstEstablishmentEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("/hateoas/all")
	CollectionModel<EntityModel<EstablishmentEntity>> getHateoasAll() throws ServiceException {
		List<EntityModel<EstablishmentEntity>> lstEstablishmentEntity = this.establishmentService.findAll().stream()
				.map(establishment -> {
					try {
						return EntityModel.of(establishment,
								linkTo(methodOn(EstablishmentController.class).findById(establishment.getCEstablishment())).withSelfRel(),
								linkTo(methodOn(EstablishmentController.class).getHateoasAll()).withRel("establishments"));
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());

		return CollectionModel.of(lstEstablishmentEntity, linkTo(methodOn(EstablishmentController.class).getHateoasAll()).withSelfRel());
	}	
	
	@GetMapping("/pagination")
	public ResponseEntity<?> getAllPagination(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size, @RequestParam(value = "field", defaultValue = "cEstablishment") String field, @RequestParam(value = "order", defaultValue = "ASC") String order) {
		Map<String, String> resp = new HashMap<String, String>();
		try {
			if (size<1){
				resp.put("err","El tamaño de la página(size) debe ser por lo menos uno(1)");
				return ResponseEntity.badRequest().body(resp);
			}
			PageRequest pageable = PageRequest.of((page - 1), size, Direction.valueOf(order.toUpperCase()), field);
			Page<EstablishmentEntity> establecimientos = establishmentService.findAllPagination(pageable);
			if (establecimientos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok().body(establecimientos);
		} catch (Exception e) {
			resp.put("error", "Error al listar establecimientos paginados");

			logger.error(e.getMessage());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);

		}
	}
	
	@GetMapping("/by-type")
	public ResponseEntity<?> findByType(@RequestParam String type){
		try {
			List<EstablishmentEntity> lstEstablishmentEntity = this.establishmentService.findByType(type);
			if (lstEstablishmentEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstEstablishmentEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}	

	@PostMapping
	public ResponseEntity<?> save(@RequestBody EstablishmentEntity establishmentEntity){
		try {
			EstablishmentEntity oEstablishmentEntity = this.establishmentService.save(establishmentEntity);
			if (isNull(oEstablishmentEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return new ResponseEntity<>(oEstablishmentEntity,HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody EstablishmentEntity establishmentEntity){

		try {
			EstablishmentEntity oEstablishmentEntity = this.establishmentService.update(id,establishmentEntity);
			if (isNull(oEstablishmentEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return ResponseEntity.ok(oEstablishmentEntity);
			}
		} catch (ServiceException e) {
			map.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			this.establishmentService.delete(id);
			return ResponseEntity.ok().build();
		} catch (ServiceException e) {
			map.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Optional<EstablishmentEntity> optEstablishmentForm = this.establishmentService.findById(id);
			if (optEstablishmentForm.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(optEstablishmentForm);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<EstablishmentEntity> getHateoasById(@PathVariable Long id) throws ServiceException {
		EstablishmentEntity establishment = this.establishmentService.findById(id).orElse(null);
		 return EntityModel.of(establishment, linkTo(methodOn(EstablishmentController.class).getHateoasById(id)).withSelfRel(),
			        linkTo(methodOn(EstablishmentController.class).getHateoasAll()).withRel("establishments"));
	}
	
	@GetMapping("/by-ubigeo")
	public ResponseEntity<?> findByUbigeo(@RequestParam String type, @RequestParam String cubigeo){
		try {
			List<EstablishmentEntity> lstEstablishmentEntity = this.establishmentService.findByUbigeo(type, cubigeo);
			if (lstEstablishmentEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstEstablishmentEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
}
