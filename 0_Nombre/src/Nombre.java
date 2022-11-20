import java.util.Scanner;

public class Nombre {
	public static void main(String[]args) {
		System.out.println("Escribe tu nombre");
		Scanner sc = new Scanner(System.in);
		String nombre=sc.nextLine();
		int veces = 0;
		while(veces<5){
			System.out.println(nombre);
			veces ++;
		}
		sc.close();
	
	}
	
}
