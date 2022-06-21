package com.example.demohospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demohospital.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{

	//necesito un metodo para verificar por el dni la existencia de un doctor
	//usare un entero y se hara una busqueda
	//contara cuantos doctores tienen ese dni
	//si encuentra más de uno, enntonces me dara un valor mayor a cero
	//sino encuentra me dara un valor de cero o negativo, entonces podre registrar un doctor nuevo
	
	//ese ?1 hace referencia al primer atributo 
	@Query("select count(d) from Doctor d where d.dni=?1")
	int verificarExistenciaDoctor(String dni);
	
	//ahora necesito encontrar el doctor por el dni, esto sera una lista aunque solo haya uno
	
	@Query("select d from Doctor d where d.dni=?1")
	List<Doctor>buscarDoctor(String dni);
	
}
