import java.util.ArrayList;

public class FabricaDeHumanos{

	public static void main(String[] args) {

		Humano nuevoHumano = new Humano("juan",18,"masculino"); //estamos inicializando un nuevo objeto del tipo humano		

		System.out.println(nuevoHumano.getNombre()); //obtenemos su nombre atrevés del getter

		ArrayList<Humano> listaDeHumanos = new ArrayList<>();

		listaDeHumanos.add(nuevoHumano);

		for(int i=0;i<10;i++){
			Humano nuevo = new Humano("humana numero: "+i,i,"femenino");
			listaDeHumanos.add(nuevo);
		}

		System.out.println("***Lista de Humanos: ***");
		
		for(Humano humano : listaDeHumanos){
			System.out.println("*****************************");
			System.out.println("Nombre: "+humano.getNombre());
			System.out.println("Edad: "+humano.getEdad());
			System.out.println("Genero: "+humano.getGenero());
		}
	}

}