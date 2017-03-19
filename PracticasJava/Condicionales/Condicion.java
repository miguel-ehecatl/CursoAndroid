import java.util.HashMap;
import java.util.ArrayList;

public class Condicion{
	
	public static void main(String[] args) {
	
		int mayoriaEdad = 18;
		int edad = 17;

		if(edad>mayoriaEdad){ //mayor que 18 
			System.out.println("Tienes "+edad+ " y eres mayor de edad");
		}else{
			System.out.println("Tienes "+edad+ " y eres menor de edad");
		}

		if(edad<mayoriaEdad){ //menor que 18 es decir 17 o menos
			System.out.println("Tienes "+edad+ " y eres menor de edad");
		}else{
			System.out.println("Tienes "+edad+ " y eres mayor de edad");
		}

		if(edad>=mayoriaEdad){ //mayor igual que 18 es decir o tienes 18 o tienes más
			System.out.println("Tienes "+edad+ " y eres mayor de edad");
		}else{
			System.out.println("Tienes "+edad+ " y eres menor de edad");	
		}

		if(edad<=mayoriaEdad-1){ //menor igual que 18-1= 17 es decir si tienes menos de 17 o 17 
			System.out.println("Tienes "+edad+ " y eres menor de edad");
		}else{
			System.out.println("Tienes "+edad+ " y eres mayor de edad");	
		}

		if(edad==mayoriaEdad){ //si edad es igual 18 eres mayor
			System.out.println("Tienes "+edad+ " y eres mayor de edad");	
		}else if(edad<18){ //si edad es menor de 18 es decir tienes 17 o menos
			System.out.println("Tienes "+edad+ " y eres menor de edad");
		}else if(edad>18){ //si edad es mayor de 18 es decir tienes 19 o más
			System.out.println("Tienes "+edad+ " y eres mayor de edad");
		}

		if(edad!=mayoriaEdad){ //si edad es diferente de 18
			System.out.println("No tienes "+mayoriaEdad+ " de edad");
		}

		if(edad==mayoriaEdad||edad>mayoriaEdad){ //si edad es igual a 18 ó edad es mayor que 18
			System.out.println("Tienes "+edad+ " y eres mayor de edad");
		}else{
			System.out.println("Tienes "+edad+ " y eres menor de edad");
		}

		if(edad>0&&edad>=mayoriaEdad){ //es una edad válida Y es mayor igual que 18
			System.out.println("Tienes "+edad+ " y eres mayor de edad");
		}

		if(!(edad==mayoriaEdad)){ //negación 
			System.out.println("No tienes "+mayoriaEdad+ " de edad");
		}

		String animal = "perro";

		if(animal.equals("perro")){ //String es un objeto por lo cual se compara con equals()
			System.out.println("Es un perro");
		}

		if(animal.equalsIgnoreCase("PERRO")){ //Compara sin diferenciar entre mayúsculas y mínusculas
			System.out.println("Es un perro");	
		}

		if(!animal.equalsIgnoreCase("perro")){ //Si no es igual a perro
			System.out.println("No es un perro");	
		}
	}

}