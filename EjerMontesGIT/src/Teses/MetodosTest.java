package Teses;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import Controlador.Metodos;
import Modelo.Monte;

class MetodosTest {
	Metodos mts = new Metodos();
	
	@Test
	void test_cargarMontesTXT() {
		Monte[] arrayMontes = new Monte[0];
		
		assertEquals(mts.cargarMontesTXT(arrayMontes)[0].getNombre(),"Aizkorri");
	}
	
	@Test
	void test_cargarMontesBD() {
	Monte[] arrayMontes = new Monte[0];
		
		assertEquals(mts.cargarMontesBD(arrayMontes)[0].getNombre(),"Aizkorri");
	}
	
	@Test
	void test_anadirMonte() {
		Monte[] arrayMontes = new Monte[0];
		arrayMontes = mts.cargarMontesTXT(arrayMontes);
		
		String nombre = "monte\nprov\n1000\ncoords\ncordillera\nruta\n";
		InputStream in = new ByteArrayInputStream(nombre.getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		assertEquals(mts.anadirMonte(sc, arrayMontes).length,arrayMontes.length+1);
	}
	
	
	@Test
	void test_mostrarMontes() {
		Monte[] arrayMontes = new Monte[0];
		arrayMontes = mts.cargarMontesTXT(arrayMontes);
		
		assertEquals(mts.mostrarMontes(arrayMontes),"----------------\n"
				+ "Toloino   Araba   1263   42°37'26?N 2°45'46?W   Toloño/Kantabria (Arabako Mendialdea)   Buradon Gatzaga (Ordu 2), Bastida (Ordu 2 eta 30 min), Ribasko mendatetik (Ordu 1 eta 15 min)");
	}
	
	
	@Test
	void test_mostrarMontesPorNombre() {
		Monte[] arrayMontes = new Monte[0];
		arrayMontes = mts.cargarMontesTXT(arrayMontes);
		
		String nombre = "Aizkorri\n";
		InputStream in = new ByteArrayInputStream(nombre.getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		assertEquals(mts.mostrarMontePorNombre(arrayMontes, sc),"----------------\n"+ "Aizkorri   Gipuzkoa   1528   42°57'08?N 02°19'00?W   Aizkorriko Macizo   Otsaurtetik, Arantzazutik eta Zegamatik");
	}

	@Test
	void test_eliminarMonte() {
		Monte[] arrayMontes = new Monte[0];
		arrayMontes = mts.cargarMontesTXT(arrayMontes);
		
		String nombre = "Aizkorri\n";
		InputStream in = new ByteArrayInputStream(nombre.getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		assertEquals(mts.eliminarMonte(arrayMontes, sc).length,arrayMontes.length-1);
	}
	
	@Test
	void test_guardarMontesBD() {
		Monte[] arrayMontes = new Monte[0];
		arrayMontes = mts.cargarMontesTXT(arrayMontes);
		
		mts.guardarMontesBD(arrayMontes);
		assertEquals(mts.cargarMontesBD(arrayMontes)[0].getNombre(),"Aizkorri");
	}
	
}
