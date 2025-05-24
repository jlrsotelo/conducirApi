package com.licencia.conducir.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.licencia.conducir.dto.Department;
import com.licencia.conducir.dto.District;
import com.licencia.conducir.dto.Province;
import com.licencia.conducir.services.UbigeoService;

@RestController
@RequestMapping("/ubigeo")
public class UbigeoController {
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	private Map<String, String> map = new HashMap<>();
	private final UbigeoService ubigeoService;
	
	public UbigeoController(UbigeoService ubigeoService) {
		this.ubigeoService = ubigeoService;
	}

	@GetMapping("/by-department")
	public ResponseEntity<?> getDepartment() {
		try {
			List<Department> lstDep = this.ubigeoService.findByDep();
			if (lstDep.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstDep);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("/by-province")
	public ResponseEntity<?> getProvince(@RequestParam String cdep) {
		try {
			List<Province> lstProv = this.ubigeoService.findByProv(cdep);
			if (lstProv.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstProv);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("/by-district")
	public ResponseEntity<?> getDistrict(@RequestParam String cdep, @RequestParam String cprov) {
		try {
			List<District> lstDist = this.ubigeoService.findByDist(cdep, cprov);
			if (lstDist.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(lstDist);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
}
