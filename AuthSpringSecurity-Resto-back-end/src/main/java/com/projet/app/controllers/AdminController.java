package com.projet.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.projet.app.model.Admin;

import com.projet.app.services.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService aS;
	
	
	@PostMapping
	public Admin addAdmin(@RequestBody Admin admin) {
		return aS.create(admin);
	}
	
	@GetMapping
	public List<Admin> getAllAdmin(){
		return aS.getAllAdmins();
	}
	
	@GetMapping("/{id}")
	public Admin getById(@PathVariable ("id") Long id) {
		return aS.readById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAdmin(@PathVariable ("id") Long id) {
		aS.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Admin> update(@PathVariable("id") Long id,@RequestBody Admin admin){
		Admin ad=aS.update(id, admin);
		if(ad == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(ad);
	}

}
