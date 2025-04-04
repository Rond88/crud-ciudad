package com.hibernate;

import java.util.List;
import java.util.Scanner;

import com.hibernate.dao.CiudadDAO;
import com.hibernate.model.Ciudad;

public class App {

	static CiudadDAO cDAO = new CiudadDAO();

	static void mostrarMenu() {
		System.out.println("------------------------------------");
		System.out.println("1. Insertar nueva ciudad");
		System.out.println("2. Borrar ciudad");
		System.out.println("3. Actualizar nombre de ciudad");
		System.out.println("4. Actualizar habitantes de ciudad");
		System.out.println("5. Ver todas las ciudades");
		System.out.println("6. Ver una ciudad en concreto");
		System.out.println("0. Salir");
	}

	public static void cambiarNombre(List<Ciudad> ciudades, int id, String nombre) {
		for (Ciudad c : ciudades) {
			if (c.getId() == id) {
				c.setNombre(nombre);
				cDAO.updateCiudad(c);
				System.out.println("Nombre cambiado");
			} else {
				System.out.println("Id de ciudad no encontrado");
			}
		}
	}

	public static void cambiarHabitantes(List<Ciudad> ciudades, int id, int nH) {
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
		List<Ciudad> ciudades = cDAO.selectAllCiudad();

		int id;
		String nombre;
		int numHabitantes;

		int opc;

		do {
			mostrarMenu();
			opc = sc.nextInt();
			switch (opc) {
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
				cambiarNombre(ciudades, id, nombre);
				break;
			case 4:
				System.out.println("Introduce el id:");
				id = sc.nextInt();
				System.out.println("Introduce el nuevo número de habitantes:");
				numHabitantes = sc.nextInt();
				cambiarHabitantes(ciudades, id, numHabitantes);
				break;
			case 5:
				ciudades.forEach(ciudad -> System.out
						.println(ciudad.getId() + " " + ciudad.getNombre() + " " + ciudad.getNumHabitantes()));
				break;
			case 6:
				System.out.println("Introduce el id:");
				cDAO.selectCiudadById(sc.nextInt());
				break;
			}
		} while (opc != 0);
	}

}
