package Vista;
import java.util.Scanner;

import Controlador.Metodos;
import Modelo.Monte;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Metodos mts = new Metodos();
		Monte[] arrayMontes = new Monte[0];
		
		int opcionMonte = 0;
		
		do {
		System.out.println("Menu principal\r\n"
				+ "1.- Cargar montes (txt)\r\n"
				+ "2.- Cargar montes (bd)\r\n"
				+ "3.- Añadir monte\r\n"
				+ "4.- Mostrar montes\r\n"
				+ "5.- Mostrar monte por nombre\r\n"
				+ "6.- Eliminar monte.\r\n"
				+ "7.- Guardar montes (bd)\r\n"
				+ "0.- Fin");
		
		opcionMonte=mts.ValidarEntero(sc, 0, 7, "Seleccione una opcion");
		
		if(opcionMonte==1) {
			arrayMontes = mts.cargarMontesTXT(arrayMontes);
		}
		else if(opcionMonte==2) {
			arrayMontes = mts.cargarMontesBD(arrayMontes);
		}
		else if(opcionMonte==3) {
			arrayMontes = mts.anadirMonte(sc, arrayMontes);
		}
		else if(opcionMonte==4) {
			mts.mostrarMontes(arrayMontes);
		}
		else if(opcionMonte==5) {
			mts.mostrarMontePorNombre(arrayMontes, sc);
		}
		else if(opcionMonte==6) {
			arrayMontes = mts.eliminarMonte(arrayMontes, sc);
		}
		else if(opcionMonte==7) {
			mts.guardarMontesBD(arrayMontes);
		}
		
		}while(opcionMonte!=0);
		
		
		sc.close();
		
	}

}
