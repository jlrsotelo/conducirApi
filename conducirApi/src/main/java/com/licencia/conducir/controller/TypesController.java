package com.licencia.conducir.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.licencia.conducir.entity.TypesEntity;
import com.licencia.conducir.services.TypesService;

@RestController
@RequestMapping("/types")
public class TypesController {
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	private Map<String, String> map = new HashMap<>();
	private final TypesService typesService;
	
	public TypesController(TypesService typesService) {
		this.typesService = typesService;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		try {
			List<TypesEntity> lstTypesEntity = this.typesService.findAll();
			if (lstTypesEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstTypesEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
}
