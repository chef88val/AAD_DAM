package biblioteca.libros;

	import java.io.Serializable;
	import java.util.Date;
	
	public class Libro implements Serializable {
		//Declaro variables del modelo libro
		private String titulo=null;
		private String autor=null;
		private String editor=null;
		private String fecha=null;
		private int nPag=0;
	
		//Creo el constructor sin parametros
		public Libro() {
		}
	
		//Creo el constructor con parametros
		public Libro(String titulo,String autor,String fecha,String editor, int nPag) {
			this.titulo = titulo;
			this.autor = autor;
			this.editor = editor;
			this.fecha = fecha;
			this.nPag = nPag;
		}
	//Getters & setters de cada propiedad
		/**
		 * @return the titulo
		 */
		public String getTitulo() {
			return titulo;
		}

		/**
		 * @param titulo the titulo to set
		 */
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		/**
		 * @return the autor
		 */
		public String getAutor() {
			return autor;
		}

		/**
		 * @param autor the autor to set
		 */
		public void setAutor(String autor) {
			this.autor = autor;
		}

		/**
		 * @return the editor
		 */
		public String getEditor() {
			return editor;
		}

		/**
		 * @param editor the editor to set
		 */
		public void setEditor(String editor) {
			this.editor = editor;
		}

		/**
		 * @return the fecha
		 */
		public String getFecha() {
			return fecha;
		}

		/**
		 * @param fecha the fecha to set
		 */
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		/**
		 * @return the nPag
		 */
		public int getnPag() {
			return nPag;
		}

		/**
		 * @param nPag the nPag to set
		 */
		public void setnPag(int nPag) {
			this.nPag = nPag;
		}
		
		//Funcion para mostrar por consola
		public void print(){
			System.out.println("Titulo: "+titulo+", escrito por: "+autor+", en el año: "+fecha+
					", publicado por: "+editor+", y contiene "+nPag+" páginas");
		}
	
	}
