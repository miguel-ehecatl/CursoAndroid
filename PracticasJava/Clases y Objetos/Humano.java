public class Humano{

	private String genero;
	private int edad;
	private String nombre;

	public Humano(String nombre,int edad,String genero){ //Constructor
		this.nombre=nombre;
		this.edad=edad;
		this.genero=genero;
	}

	/* Setters y getters para obtener o cambiar
	los valores del objeto. */

	public int getEdad(){
		return edad;
	}

	public void setEdad(int edad){
		this.edad=edad;
	}

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre=nombre;
	}

	public String getGenero(){
		return genero;
	}

	public void setGenero(String genero){
		this.genero=genero;
	}
}