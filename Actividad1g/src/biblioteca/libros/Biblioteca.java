package biblioteca.libros;

import biblioteca.libros.*;
public class Biblioteca {

	public static void main(String[] args) {
		//Creo objeto de vista
				vistaActividad1g vista= new vistaActividad1g();
				//Creo objeto de un libro
				Libro p1=new Libro();
				//creamos un modelo de tipo almacen
				Almacen almacen=new Almacen();
				//Creamos un controlador al que le pasamos la vista, el libro y el almacen
				controladorActividad1g controller = new controladorActividad1g(vista, p1,almacen);
				//Añadimos un controlador a la vista para las acciones de los botones
				vista.setControlador(controller);
				//Mostramos la ventana
		vista.setVisible(true);

	}

}
