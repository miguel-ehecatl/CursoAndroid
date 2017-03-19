import java.util.HashMap;
import java.util.ArrayList;

public class Mapas{
	public static void main(String[] args) {
		
		HashMap<String,ArrayList<String>> animales = new HashMap<>(); //inicializamos el mapa
		
		ArrayList<String> carnivoros = new ArrayList<>(); //creamos una lista con animales carnivoros
		ArrayList<String> herviboros = new ArrayList<>(); //creamos una lista con animales herviboros
		carnivoros.add("Leon"); //agregamos un nuevo elemento
		carnivoros.add("Oso");
		carnivoros.add("Cocodrilo");
		herviboros.add("Vaca");
		herviboros.add("Conejo");
		herviboros.add("Caballo");

		animales.put("carnivoros",carnivoros); //insertamos el arraylist de carnivoros al mapa
		animales.put("herviboros",herviboros);

		System.out.println("Mapa Original: "+animales); //imprimimos el mapa
		int size = animales.size(); //guardamos en un entero el tamaño del mapa
		System.out.println("Tamano Mapa: "+animales.size()); //imprimimos el tamaño del mapa

		boolean contieneCarnivoros = animales.containsKey("carnivoros"); //checamos si existe la llave

		System.out.println("Existe la llave herviboros: "+contieneCarnivoros); //imprimimos true o false si existe la llave

		ArrayList<String> nuevosCarnivoros = animales.get("carnivoros"); //obtenemos un valor con su llave

		System.out.println("Valores de la llave carnivoros: "+nuevosCarnivoros);

		nuevosCarnivoros.remove(0); //quitamos Leon de la lista

		System.out.println("Nuevo valor de la llave carnivoros: "+carnivoros);

		animales.put("carnivoros",nuevosCarnivoros); //cambiamos el valor de llave carnivoros

		System.out.println("Mapa modificado: "+animales);

		animales.remove("carnivoros"); // quitamos del mapa la llave carnivoros con sus valors

		System.out.println("Mapa sin carnivoros: "+animales);

		animales.clear(); //limpiamos el mapa 

		System.out.println("Mapa limpio: "+animales); //imprimimos mapa vacío

		boolean estaVacio = animales.isEmpty(); //checamos si el mapa esta vacío
 
		System.out.println("Esta la lista vacia: "+estaVacio);
	}
}