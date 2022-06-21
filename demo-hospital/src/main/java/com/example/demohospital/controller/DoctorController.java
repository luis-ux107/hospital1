package com.example.demohospital.controller;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demohospital.entities.Doctor;
import com.example.demohospital.service.CiudadService;
import com.example.demohospital.service.DoctorService;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private CiudadService ciudadService;
	
	@GetMapping("list")
	public String listarDoctores(Model model) {
		
		//ni bien carga la lista, ya le esta enviando un objeto doctor por el model
		Doctor doctor=new Doctor();
		model.addAttribute("doctor",doctor);
		model.addAttribute("doctores",doctorService.listarDoctores());
		
		return "doctor/list";
	}
	
	@GetMapping("nuevo")
	public String registrarDoctorForm(Model model) {
		
		//ni bien carga la lista, ya le esta enviando un objeto doctor por el model
		Doctor doctor=new Doctor();
		model.addAttribute("doctor",doctor);
		model.addAttribute("ciudades",ciudadService.listarCiudades());
		
		return "doctor/form";
	}
	
	@PostMapping("registrar")
	public String registrarDoctor(@Validated @ModelAttribute Doctor doctor,BindingResult result,Model model) {
	
		int rpta;
		if(result.hasErrors()) {
			model.addAttribute("ciudades",ciudadService.listarCiudades());
			return "doctor/form";
		}
		
		rpta=doctorService.registrarDoctor(doctor);
		
		if(rpta>0) {
			//ya existe un doctor con ese dni
			model.addAttribute("mensaje","El numero de dni ya existe");
			model.addAttribute("ciudades",ciudadService.listarCiudades());
			
			
			
		}else {
			//no existe
			model.addAttribute("mensaje","Se registro nuevo doctor");
			model.addAttribute("doctor",new Doctor());
			model.addAttribute("ciudades",ciudadService.listarCiudades());
		}
		
		return "doctor/form";
	}
	
	//los objetos que vienen desde un formulario se reciben con @modelatribute
	//usamos post porque recibieremos algo de un formaulario
	@PostMapping("buscar")
	public String buscarDoctor(Model model,@ModelAttribute Doctor doctor ) {
		
		model.addAttribute("doctores",doctorService.buscarDoctorPorDni(doctor.getDni()));
		return "doctor/list";
		
	}

}
