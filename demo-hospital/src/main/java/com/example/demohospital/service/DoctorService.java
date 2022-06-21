package com.example.demohospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demohospital.entities.Doctor;
import com.example.demohospital.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	public List<Doctor>listarDoctores(){
		double bonoSupervivencia;
		
		
		List<Doctor>doctores=doctorRepository.findAll();
		
		for(Doctor doctor : doctores) {
			bonoSupervivencia=20*doctor.getEdad()+100*doctor.getTiempoExperiencia();
			doctor.setBonoSupervivencia(bonoSupervivencia);
			
		}
		
		
		return doctores;
	}
	
	
	//registrar doctor por el dni
	public int registrarDoctor(Doctor doctor) {
		int existeDoctor=doctorRepository.verificarExistenciaDoctor(doctor.getDni());
		if(existeDoctor==0)
			doctorRepository.save(doctor);
		
		return existeDoctor;
	}
	public List<Doctor>buscarDoctorPorDni(String dni){
		double bonoSupervivencia;
		
		
		List<Doctor>doctores=doctorRepository.buscarDoctor(dni);		
		for(Doctor doctor : doctores) {
			bonoSupervivencia=20*doctor.getEdad()+100*doctor.getTiempoExperiencia();
			doctor.setBonoSupervivencia(bonoSupervivencia);
			
		}
		
		
		return doctores;
	}
	
}
