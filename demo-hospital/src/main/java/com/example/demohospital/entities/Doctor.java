package com.example.demohospital.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "doctores")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//validacion: mensaje en caso se deje en blanco
	@NotEmpty(message = "Debe ingresar el nombre completo*")
	//le damos un nombre, no admite nulos
	@Column(name = "nombre_completo",nullable = false,length = 45)
	private String nombreCompleto;
	
	//le damos maximo y minimo
	@Size(min = 8,max = 8,message = "El Dni debe tener 8 digitos")
	@NotEmpty(message = "Debe ingresar el dni*")
	@Column(name = "dni",nullable = false,length = 8)
	private String dni;
	
	//verifica si la fecha es correcta
	@Past(message = "Fecha de colegiatura no correcta")
	//como quieres guardarla en el bd
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@Temporal(TemporalType.TIMESTAMP)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_colegiatura",nullable = false)
	private Date fechaColegiatura;
	
	//valor de edad maximo
	@Max(100)
	//valor de edad minimo
	@Min(23)
	@Column(name = "edad",nullable = false)
	private int edad;
	
	//valor de edad maximo
	@Max(100)
	//valor de edad minimo
	@Min(0)
	@Column(name = "tiempo_experiencia",nullable = false)
	private int tiempoExperiencia;
	
	//esto permite que no se registre en la bd
	//solo servira para calculo y mostrar en html
	@Transient
	private double bonoSupervivencia;

	//conexión
	//muchos doctores vienen de una ciudad
	
	@ManyToOne
	@JoinColumn(name = "ciudad_id",nullable = false)
	private Ciudad ciudad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	

	public Date getFechaColegiatura() {
		return fechaColegiatura;
	}

	public void setFechaColegiatura(Date fechaColegiatura) {
		this.fechaColegiatura = fechaColegiatura;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTiempoExperiencia() {
		return tiempoExperiencia;
	}

	public void setTiempoExperiencia(int tiempoExperiencia) {
		this.tiempoExperiencia = tiempoExperiencia;
	}

	public double getBonoSupervivencia() {
		return bonoSupervivencia;
	}

	public void setBonoSupervivencia(double bonoSupervivencia) {
		this.bonoSupervivencia = bonoSupervivencia;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
