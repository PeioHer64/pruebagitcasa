package Controlador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Modelo.Monte;

public class Metodos {
	
	
	
	private final String monte1 = "Nombre";
	private final String monte2 = "Provincia";
	private final String monte3 = "Altura";
	private final String monte4 = "Coordenadas";
	private final String monte5 = "Cordillera";
	private final String monte6 = "Ruta";
	
	private final String tablaM = "montes";
	
	private final String conexion1 = "jdbc:mysql://localhost:3306/montes";
	private final String conexion2 = "root";
	private final String conexion3 = "";
	
	public Monte[] cargarMontesBD(Monte[] arrayMontes) {
		Connection conexion;
		
		try {
			conexion=(Connection) DriverManager.getConnection(conexion1,conexion2,conexion3);
			Statement comando=(Statement) conexion.createStatement();
		
			ResultSet registroMontes = comando.executeQuery("select * from "+tablaM+"");
			int i=0;
			while (registroMontes.next()) {
				
				
				Monte mon = new Monte();
				mon.setNombre(registroMontes.getString(monte1));
				mon.setProvincia(registroMontes.getString(monte2));
				mon.setAltura(registroMontes.getInt(monte3));
				mon.setCoordenadas(registroMontes.getString(monte4));
				mon.setMacizo(registroMontes.getString(monte5));
				mon.setRuta(registroMontes.getString(monte6));

				arrayMontes = reescribirArrayMontes(arrayMontes);
				arrayMontes[i] = mon;
				i++;
			}
			registroMontes.close();
			
			comando.close();
			conexion.close();
		} catch(SQLException ex){
				ex.printStackTrace();

		}
		
		return arrayMontes;
	}
	
	
	
	public Monte[] cargarMontesTXT(Monte[] arrayMontes) {
		File file = new File("Mendiak.txt");
		BufferedReader fichero;
		try {
			fichero = new BufferedReader(new FileReader(file));
		int v = 0;
		String linea;
		
		String nombre = "";
		String provincia = "";
		int altura = 0;
		String coordenadas = "";
		String macizo = "";
		String ruta = "";
		
		while((linea = fichero.readLine())!=null){
//			if (linea.contains("Nombre")){
//				System.out.println(linea);
//				}
			
			if(linea.split(":").length>1){
				
				if (linea.contains("Nombre")){
					nombre = linea.split(":")[1];
				}
				if (linea.contains("Provincia")){
					provincia = linea.split(":")[1];
				}
				if (linea.contains("Altura")){
					altura = Integer.valueOf(linea.split(":")[1]);
				}
				if (linea.contains("Coordenadas")){
					coordenadas = linea.split(":")[1];
				}
				if (linea.contains("Macizo")){
					macizo = linea.split(":")[1];
				}
				if (linea.contains("RutaHabitual")){
					ruta = linea.split(":")[1];
				}
				
		
				}else{
					Monte monT = new Monte();
					monT.setNombre(nombre);
					monT.setProvincia(provincia);
					monT.setAltura(altura);
					monT.setCoordenadas(coordenadas);
					monT.setMacizo(macizo);
					monT.setRuta(ruta);
					
					arrayMontes = reescribirArrayMontes(arrayMontes);
					arrayMontes[v]= monT;
					v++;
					
					nombre = "";
					provincia = "";
					altura = 0;
					coordenadas = "";
					macizo = "";
					ruta = "";
			
				}
				
		}
		System.out.println("todo introducido bien");
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(arrayMontes[0].getNombre());
		return arrayMontes;
	}
	
	public Monte[] anadirMonte(Scanner sc, Monte[] arrayMontes) {
		Monte monAM = new Monte();
		
		monAM.setNombre(ValidarString(sc, 0, 50, "Introduzca un Nombre"));
		monAM.setProvincia(ValidarString(sc, 0, 50, "Introduzca una Provincia"));
		monAM.setAltura(ValidarEntero(sc, 100, 2000, "Introduzca una Altura"));
		monAM.setCoordenadas(ValidarString(sc, 0, 50, "Introduzca unas Coordenadas"));
		monAM.setMacizo(ValidarString(sc, 0, 50, "Introduzca un Macizo"));
		monAM.setRuta(ValidarString(sc, 0, 150, "Introduzca una Ruta"));
		
		arrayMontes = reescribirArrayMontes(arrayMontes);
		arrayMontes[arrayMontes.length-1] = monAM;
		return arrayMontes;
	}
	
	public String mostrarMontes(Monte[] arrayMontes) {
		String testString = "";
		System.out.println("Lista de Montes:");
		System.out.println("----------------");
		System.out.println("Nombre         Provincia           Altura          Coor           Macizo              Ruta");
		for(int f=0; f<arrayMontes.length;f++) {
			System.out.println(arrayMontes[f].toString());
			testString = arrayMontes[f].toString();
		}
		return testString;
	}
	
	public String mostrarMontePorNombre(Monte[] arrayMontes, Scanner sc) {
		String testString = "";
		String nombre = ValidarString(sc, 0, 50, "Introduzaca el nombre de un monte");
		int cont = 0;
		Monte monte = new Monte();
		do {
			monte = arrayMontes[cont];
			if(monte.getNombre().equals(nombre)) {
				System.out.println("Nombre         Provincia           Altura          Coor           Macizo              Ruta");
				System.out.println(monte.toString());
				testString = monte.toString();
			}else {
				cont++;
			}
		}while(cont < arrayMontes.length && !monte.getNombre().equals(nombre));
		return testString;
	}
	
	public Monte[] eliminarMonte(Monte[] arrayMontes, Scanner sc) {
		
		//busca y pone a null el que se quiere eliminar
		String nombre = ValidarString(sc, 0, 50, "Introduzaca el nombre de un monte");
		int cont = 0;
		Monte monte = new Monte();
		do {
			monte = arrayMontes[cont];
			if(monte.getNombre().equals(nombre)) {
				arrayMontes[cont] = null;
			}else {
				cont++;
			}
		}while(cont < arrayMontes.length && !monte.getNombre().equals(nombre));
		
		//mete en un arrayNuevo todo lo que no es null
		int pos =0;
		Monte[] arrayMontesNuevo = new Monte[0];
		for(int x=0;x<arrayMontes.length;x++){
			if(arrayMontes[x]!=null) {
				arrayMontesNuevo = reescribirArrayMontes(arrayMontesNuevo);
				arrayMontesNuevo[pos]=arrayMontes[x];
				pos++;
			}
			
	    }
		return arrayMontesNuevo;
	
	}
	
	public void guardarMontesBD(Monte[] arrayMontes) {
		//inserts
		try {
			Connection conexion = (Connection) DriverManager.getConnection(conexion1,conexion2,conexion3);
			Object insert = conexion.createStatement();
			((Statement) insert).executeUpdate("DELETE FROM `montes`");
			for(int x=0;x<arrayMontes.length;x++){
			((Statement) insert).executeUpdate("INSERT INTO `montes`(`Nombre`, `Provincia`, `Altura`, `Coordenadas`, `Cordillera`, `Ruta`) VALUES ('"+arrayMontes[x].getNombre()+"','"+arrayMontes[x].getProvincia()+"','"+arrayMontes[x].getAltura()+"','"+arrayMontes[x].getCoordenadas().replaceAll("'", "''")+"','"+arrayMontes[x].getMacizo()+"','"+arrayMontes[x].getRuta()+"')");
			}
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	public Monte[] reescribirArrayMontes(Monte[] arrayViejo) {
		// TODO Auto-generated method stub
		Monte[] arrayNuevo = new Monte[arrayViejo.length+1];
		for(int i =0;i<arrayViejo.length;i++)
		{
			arrayNuevo[i]=arrayViejo[i];
		}
		arrayViejo = arrayNuevo;
		return arrayNuevo;
	}
	
	
	public int ValidarEntero(Scanner sc, int min, int max, String texto) {
		int num = -1;
		boolean error = false;
		do {
			System.out.println(texto);
			String opcS = sc.nextLine();
			try {
				num = Integer.valueOf(opcS);
				if (num < min || num > max)
					error = true;
				else
					error = false;
			} catch (Exception ex) {
				error = true;
			}
		} while (error);
		return num;
	}
	
	public String ValidarString(Scanner sc, int min, int max, String texto) {
		String opcS = "";
		boolean error = false;
		do {
			System.out.println(texto);
			opcS = sc.nextLine();
			try {
				if (opcS.length() < min || opcS.length() > max)
					error = true;
				else
					error = false;
			} catch (Exception ex) {
				error = true;
			}
		} while (error);
		return opcS;
	}
	
	
	
	
}
