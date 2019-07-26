package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/inseguros")
public class insegurosController {
	
	@PostMapping("/getObject")
	public ResponseEntity<Object> getUsers() {
		HashMap<String, Object> users = new HashMap<>();
		users.put("Usuarios", new ArrayList<>(Arrays.asList("Nombre 1", "Córdoba", "La Plata")));
		users.put("Usuarios 2", new HashMap<String, Object>());
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
