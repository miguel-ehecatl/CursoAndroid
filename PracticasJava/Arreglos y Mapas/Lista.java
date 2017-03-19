import java.util.ArrayList;

public class Lista{
	
	public static void main(String[] args) {
		
		ArrayList<String> vocales = new ArrayList<>(); //inicializa la lista

		vocales.add("a"); //agrega un elemento a la lista
		vocales.add("e");
		vocales.add("i");
		vocales.add("o");
		vocales.add("u");

		System.out.println("Lista original: "+vocales);

		String vocal = vocales.get(0); //obtiene un elemento de la lista con el índice
		
		System.out.println("Elemento 0: "+vocal); //imprime el elemento obtenido de la lista

		int size = vocales.size(); //obtiene el tamaño de la lista

		System.out.println("Tamano de la lista: "+size); //imprimimos el tamaño de la lista

		vocales.set(0,"x"); //cambiamos el valor del elemento en el índice 0 "a por x"

		System.out.println("Nuevo valor en indice 0: "+vocales);

		vocales.remove(0); //quita un elemento de la lista

		System.out.println("Elemento en 0 borrado: "+vocales);

		vocales.clear(); //limpiamos el ArrayList

		System.out.println("Lista limpia: "+vocales);

		boolean estaVacia = vocales.isEmpty(); //checamos si la lista esta vacía 
 
		System.out.println("Esta la lista vacia: "+estaVacia);
	}
}