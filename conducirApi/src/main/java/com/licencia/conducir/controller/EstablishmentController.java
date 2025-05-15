package com.licencia.conducir.controller;

import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.licencia.conducir.entity.EstablishmentEntity;
import com.licencia.conducir.services.EstablishmentService;
import com.licencia.conducir.services.ServiceException;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {
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
}
