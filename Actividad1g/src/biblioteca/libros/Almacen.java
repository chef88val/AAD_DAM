package biblioteca.libros;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Almacen {
	
	//Creo el constructor por defecto
	public void Almacen(){
		
	}

	//Funcion para guardar un libro con un valor
	public boolean guardar(Libro p,String f){
		
		ObjectOutputStream out=null;
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(p);
			return true;
		} catch (IOException e) {			
			e.printStackTrace();
			return false;
		}finally{
			intentarCerrar(out);
		}
		
	}
	//Funcion para recuperar el valor de un libro
	public Libro recuperar(String f) {
		Libro p = null;
        ObjectInputStream in=null;
        try {
            in = new ObjectInputStream(new FileInputStream(f));
            p = (Libro) in.readObject();            
        } catch (ClassNotFoundException ex) {
            System.err.println("Error de fichero");
        } catch (IOException ex) {
        	System.err.println("Error IO");
        }finally{
            intentarCerrar(in);
        }
        return p;
	}

	//Cerrar fichero
	public void intentarCerrar(Closeable aCerrar) {
		try {
			if (aCerrar != null) {
				aCerrar.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}
	
}
