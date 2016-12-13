package biblioteca.libros;

import java.util.ArrayList;

import biblioteca.libros.*;
public class Ejercicio3 {
public static Libro libro;
public static Almacen almacen;
	public static void main(String[] args) {
		libro=new Libro();
		almacen = new Almacen();
	
		ArrayList<String> listaficheros = new ArrayList<String>();
		listaficheros.add("bibliotecaxx.dat");
		listaficheros.add("bibliotecaxx.dat");
		listaficheros.add("bibliotecaxx.dat");
		mostrarFechaArchivos(listaficheros);
			
	}
	
	public static void mostrarFechaArchivos(ArrayList<String> ficheros){
		String ficheroo = null;
		
		
		for(int i=0;i<ficheros.size();i++){
			System.out.println(ficheros.get(i));
			
			libro=almacen.recuperar(ficheros.get(i));
			System.out.println("Para el fichero: "+ficheros.get(i)+"la fecha es: "+libro.getFecha());
		}
	}

}
