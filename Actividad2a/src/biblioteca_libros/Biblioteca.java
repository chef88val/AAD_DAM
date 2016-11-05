package biblioteca_libros;

import biblioteca_libros.Almacen;
import biblioteca_libros.Libro;
import biblioteca_libros.controladorActividad2a;
import biblioteca_libros.vistaActividad2a;

public class Biblioteca {

	public static void main(String[] args) {
			
		//(Salida por consola)Ejemplo de recuperar las etiquetas desde un xml
		Almacen p=new Almacen();
		p.parseFicheroXml("biblioteca.xml");
		//Le paso el parametro 1 para que pueda leer el xml con el formato autor->nombre
		p.parseDocument(1);
		p.print();
		
		
		//Creo objeto de vista
		vistaActividad2a vista= new vistaActividad2a();
		//Creo objeto de un libro
		Libro p1=new Libro();
		//creamos un modelo de tipo almacen
		Almacen almacen=new Almacen();
		//Creamos un controlador al que le pasamos la vista, el libro y el almacen
		controladorActividad2a controller = new controladorActividad2a(vista, p1,almacen);
		//Añadimos un controlador a la vista para las acciones de los botones
		vista.setControlador(controller);
		//Mostramos la ventana
		vista.setVisible(true);
		

	}

}
