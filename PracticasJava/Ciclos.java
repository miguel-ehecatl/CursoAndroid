import java.util.ArrayList;

public class Ciclos{
	
	public static void main(String[] args) {
	
		String[] nombres = {"juan","pedro","karen","denisse"}; //arreglo de nombres


		for(int i=0;i<nombres.length;i++){ //ciclo for
			System.out.println("Valor de i: "+i);
			System.out.println("Nombre: "+nombres[i]);
		}

		for(String nombre : nombres){ // ciclo for each
			System.out.println("Nombre: "+nombre); 
		}

		int j=0;
		while(j<nombres.length){ //ciclo while
			System.out.println("Valor de j: "+j);
			System.out.println("Nombre: "+nombres[j]);
			j++;
		}

		int k=0;

		do{ //ciclo do while
			System.out.println("Valor de k: "+k);
			System.out.println("Nombre: "+nombres[k]);
			k++;
		}while(k<nombres.length);
	}

}