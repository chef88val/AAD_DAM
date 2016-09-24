package gestionficherosapp;

import gestionficheros.MainGUI;

public class GestionFicherosApp {

	public static void main(String[] args) {
		// Creo un objeto de la clase que gestionará la información
		GestionFicherosImpl getFicherosImpl = new GestionFicherosImpl();
		//Establezco la propiedad de ser visible a la aplicación
		new MainGUI(getFicherosImpl).setVisible(true);
	}

}
