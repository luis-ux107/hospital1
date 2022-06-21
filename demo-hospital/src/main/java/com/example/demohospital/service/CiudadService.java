package com.example.demohospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demohospital.entities.Ciudad;
import com.example.demohospital.repository.CiudadRepository;

@Service
public class CiudadService {

	//esta anotacion sirve para inyectar la dependencia
	@Autowired
	private CiudadRepository ciudadRepository;
	
	//aqui solo necesito una lista para mandarsela al doctor cuando se registre
	public List<Ciudad>listarCiudades(){
		return ciudadRepository.findAll();
	}
}
