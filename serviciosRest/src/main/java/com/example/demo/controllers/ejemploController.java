package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.UserService;

@RestController
@Validated
@RequestMapping("/ejemplo")
public class ejemploController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getUser")
	public Object getUser() {
		return "hola bro";
	}

	@SuppressWarnings("serial")
	@PostMapping("/getUsers")
	public ResponseEntity<Object> getUsers() {
		HashMap<String, Object> users = new HashMap<>();
		users.put("Usuarios", new ArrayList<>(Arrays.asList("Nombre 1", "Córdoba", "La Plata")));		
		users.put("Usuarios_2", new HashMap<Integer, String>() {{
				put(1, "one");
				put(2, "two");
			}
		});		
		users.put("Usuarios 2", new HashMap<String, Object>());
		return new ResponseEntity<>(users, HttpStatus.OK);
	}	
	
	@PostMapping(value = "/getUsersDataBase")
	public Object getUsersDataBase() {
		return userService.getUsers();
	}
}
