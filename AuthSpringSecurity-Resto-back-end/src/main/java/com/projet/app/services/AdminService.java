package com.projet.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.model.Admin;

import com.projet.app.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository ar;
	
	public Admin create(Admin admin) {
		return ar.save(admin);
	}
	public List<Admin> getAllAdmins(){
		return ar.findAll();
	}
	
	public void delete(long id) {
		this.ar.deleteById(id);
	}
	public Admin readById(Long id) {
		Optional<Admin> ad = ar.findById(id);
		if(ad.isPresent()) {
			return ad.get();
		}else {
			return null;
		}
	}
	
	public Admin update(long id, Admin admin) {
		Optional<Admin> chefOptional =ar.findById(id);
		if(chefOptional.isPresent()) {
			Admin existingAdmin= chefOptional.get();
			existingAdmin.setNom(admin.getNom());
			existingAdmin.setEmail(admin.getEmail());
			existingAdmin.setPrenom(admin.getPrenom());
			existingAdmin.setPassword(admin.getPassword());
			return ar.save(existingAdmin);
		}
		return null;
	}
}
