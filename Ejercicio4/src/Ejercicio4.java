
public class Ejercicio4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Almacen p=new Almacen();
		p.parseFicheroXml("discos.xml");
		//Le paso el parametro 1 para que pueda leer el xml con el formato autor->nombre
		p.parseDocument(1);
		p.print();
	}

}
