package biblioteca_libros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;



public class Marshaller {

	private Document dom = null;
	private ArrayList<Libro> libros = null;

	private Element rootElement = null;
	private Element item;
	private Attr atributo;
	
	//Constructor con argumentos
		public Marshaller(ArrayList<Libro> l) {
			libros = l;//Inicializo el arrayList de tipo Libro
			 
		}
	//Constructor sin argumentos
	/*public Marshaller() {
		libros = new ArrayList<Libro>();//Inicializo el arrayList de tipo Libro
		 
	}*/



	public void crearDocumento() {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// creamos una instancia de DOM 
			dom = db.newDocument();		
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}

	}
	
	public void crearArbolDOM(ArrayList<Libro> libros) {

		// creamos el elemento ra�z "Libros"
		Element docEle = dom.createElement("Libros");
		dom.appendChild(docEle);

		// recorremos
		Iterator it = libros.iterator();
		while (it.hasNext()) {
			Libro e = (Libro) it.next();
			// para cada objeto Libro creamos el elemento <Libro> y lo a�adimos a la ra�z
			Element LibroEle = setLibro(e);
			docEle.appendChild(LibroEle);
		}

	}
	
	private Element setLibro(Libro p) {

		// creamos el elemento Libro
		Element LibroEle = dom.createElement("Libro");

		Text nombreTexto;
		// creamos el elemento nombre y el nodo de texto y lo a�adimos al elemento Libro
		Element tituloL = dom.createElement("titulo");	
		tituloL.setAttribute("fecha", p.getFecha());//Añado atributo a titulo
		nombreTexto = dom.createTextNode(p.getTitulo());
		tituloL.appendChild(nombreTexto);
		LibroEle.appendChild(tituloL);

		// creamos el elemento nombre y el nodo de texto y lo a�adimos al elemento Libro
		Element autorL = dom.createElement("autor");		
		 nombreTexto = dom.createTextNode(p.getAutor());
		autorL.appendChild(nombreTexto);
		LibroEle.appendChild(autorL);

		// creamos el elemento nombre y el nodo de texto y lo a�adimos al elemento Libro
		Element editorL = dom.createElement("editor");		
		 nombreTexto = dom.createTextNode(p.getEditor());
		editorL.appendChild(nombreTexto);
		LibroEle.appendChild(editorL);
		
		
		// creamos el elemento edad y el nodo de valor entero y lo a�adimos al elemento Libro
		Element edadEle = dom.createElement("edad");
		Text edadTexto = dom.createTextNode(Integer.toString(p.getnPag()));
		edadEle.appendChild(edadTexto);
		LibroEle.appendChild(edadEle);
		
		return LibroEle;
	}
	
	public void escribirDocumentAXml(File file) throws TransformerException {

		// creamos una instacia para escribir el resultado
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		// especificamos d�nde escribimos y la fuente de datos
		StreamResult result = new StreamResult(file);
		DOMSource source = new DOMSource(dom);
		trans.transform(source, result);

	}
	

}
