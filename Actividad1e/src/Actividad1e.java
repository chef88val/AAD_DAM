import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JViewport;
import javax.swing.text.View;

public class Actividad1e extends JFrame{
	public static JFileChooser fichero=null;
	public static File currentDirectory = null;

	public static void main(String[] args) {
		//Me declaro un obejto que me he definido para el ejercicio
		vista1e ventana = new vista1e();
		
		//Declaro el directorio desde donde quiero comenzar la creacion y selección de ficheros
		currentDirectory = new File("/Users/js_cmd/");
		fichero = new JFileChooser(currentDirectory);
		
		//Establezco a visiblñe el JFrame
		ventana.setVisible(true);
	}

}
