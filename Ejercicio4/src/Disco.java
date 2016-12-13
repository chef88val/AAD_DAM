import java.io.Serializable;

public class Disco implements Serializable {
	//Declaro variables del modelo libro
	private String titulo=null;
	private String grupo=null;
	private String nacionalidad=null;
	private String lider=null;
	private int anyo=0; 
	//Creo el constructor sin parametros
			public Disco() {
			}
	public Disco(String titulo,String nacionalidad,String lider,String grupo, int anyo) {
		this.titulo = titulo;
		this.nacionalidad = nacionalidad;
		this.lider = lider;
		this.grupo = grupo;
		this.anyo = anyo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getLider() {
		return lider;
	}
	public void setLider(String lider) {
		this.lider = lider;
	}
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	
	public void print(){
		System.out.println("Titulo: "+titulo+", escrito por: "+grupo+", en el año: "+nacionalidad+
				", publicado por: "+lider+", y contiene "+anyo+" páginas");
	}
}
	 