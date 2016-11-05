package biblioteca_libros;

import biblioteca_libros.Marshaller;

import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

import biblioteca_libros.Libro;
import biblioteca_libros.controladorActividad2b;
import biblioteca_libros.vistaActividad2b;

public class Biblioteca {

	public static void main(String[] args) {
		//Creo objeto de vista
		vistaActividad2b vista= new vistaActividad2b();
		ArrayList<Libro> libros = new ArrayList<Libro>();
		
		//Creo objeto de un libro
		Libro p1=new Libro();
		
		//creamos un modelo de tipo almacen
		Marshaller almacen=new Marshaller(libros);
		
		//Creamos un controlador al que le pasamos la vista, el libro y el almacen
		controladorActividad2b controller = new controladorActividad2b(vista, p1,almacen);
		//Añadimos un controlador a la vista para las acciones de los botones
		vista.setControlador(controller);
		//Mostramos la ventana
		vista.setVisible(true);
		

	}

}
