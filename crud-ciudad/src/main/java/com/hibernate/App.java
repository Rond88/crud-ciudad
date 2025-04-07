package com.hibernate;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.dao.CiudadDAO;
import com.hibernate.model.Ciudad;
import com.hibernate.util.HibernateUtil;

public class App {

	static CiudadDAO cDAO = new CiudadDAO();
	static List<Ciudad> ciudades = cDAO.selectAllCiudad();


	static void mostrarMenu() {
		System.out.println("------------------------------------");
		System.out.println("1. Insertar nueva ciudad");
		System.out.println("2. Borrar ciudad");
		System.out.println("3. Actualizar nombre de ciudad");
		System.out.println("4. Actualizar habitantes de ciudad");
		System.out.println("5. Ver todas las ciudades");
		System.out.println("6. Ver una ciudad en concreto");
		System.out.println("7. Seleccionar ciudades con más de 1 millón de habitantes");
		System.out.println("8. Seleccionar ciudad con menos habitantes que lo introducido");
		System.out.println("9. Mostrar habitantes del nombre de ciudad introducida");
		System.out.println("0. Salir");
	}

	public static void cambiarNombre(int id, String nombre) {
		for (Ciudad c : ciudades) {
			if (c.getId() == id) {
				c.setNombre(nombre);
				cDAO.updateCiudad(c);
				System.out.println("Nombre cambiado");
				break;
			} else {
				System.out.println("Id de ciudad no encontrado");
			}
		}
	}

	public static void cambiarHabitantes(int id, int nH) {
		for (Ciudad c : ciudades) {
			if (c.getId() == id) {
				c.setNumHabitantes(nH);
				cDAO.updateCiudad(c);
				System.out.println("Nombre cambiado");
			} else {
				System.out.println("Id de ciudad no encontrado");
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Ciudad c;
		int id;
		String nombre;
		int numHabitantes;

		int opc;

		do {
			mostrarMenu();
			opc = sc.nextInt();
			switch (opc) {
			case 0:
				System.out.println("Fin del programa");
				break;
			case 1:
				System.out.println("Introduce el nombre:");
				nombre = sc.next();
				System.out.println("Introduce el número de habitantes:");
				numHabitantes = sc.nextInt();
				c = new Ciudad(nombre, numHabitantes);
				cDAO.insertCiudad(c);
				break;
			case 2:
				System.out.println("Introduce el id:");
				cDAO.deleteCiudad(sc.nextInt());
				break;
			case 3:
				System.out.println("Introduce el id:");
				id = sc.nextInt();
				System.out.println("Introduce el nuevo nombre:");
				nombre = sc.next();
				cambiarNombre(id, nombre);
				break;
			case 4:
				System.out.println("Introduce el id:");
				id = sc.nextInt();
				System.out.println("Introduce el nuevo número de habitantes:");
				numHabitantes = sc.nextInt();
				cambiarHabitantes(id, numHabitantes);
				break;
			case 5:
				ciudades.forEach(ciudad -> System.out
						.println(ciudad.getId() + " " + ciudad.getNombre() + " " + ciudad.getNumHabitantes()));
				break;
			case 6:
				System.out.println("Introduce el id:");
				id=sc.nextInt();
				c=cDAO.selectCiudadById(id);
				System.out.println("Ciudad: "+c.getNombre()+" Habitantes: "+c.getNumHabitantes());
				
				break;
			case 7:
				ciudades=cDAO.selectAllCiudadMillon();
				ciudades.forEach(ciudad -> System.out
						.println(ciudad.getId() + " " + ciudad.getNombre() + " " + ciudad.getNumHabitantes()));
				break;
			case 8:
				System.out.println("Introduce la cantidad:");
				numHabitantes=sc.nextInt();
				ciudades=cDAO.selectCiudadByHabitantes(numHabitantes);
				ciudades.forEach(ciudad -> System.out
						.println(ciudad.getId() + " " + ciudad.getNombre() + " " + ciudad.getNumHabitantes()));
				break;
			case 9:
				System.out.println("Introduce el nombre de la ciudad");
				nombre=sc.next();
				c=cDAO.selectCiudadByNombre(nombre);
				
				break;
			default:
				System.out.println("Opción inválida");
			}
		} while (opc != 0);
	}

}
