import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Palindromo {
	
	public static boolean esPrimo(int numero) {
		int divisor=2;
		String lista="";
		while(divisor<numero) {
			
			if(numero%divisor == 0) {
				lista +=divisor+"";
				numero =numero/divisor;
			}else {
				divisor++;
			}
			
		}
		//System.out.print(lista);
		if(lista.length() == 0) {
			return true;
			
		}else {
			return false;
		}
	}
	public static boolean esPalindromo(int numero) {
		String num= numero+"";
		String num2= "";
		for(int i = num.length() -1; i>=0; i--) {
			num2 +=num.charAt(i);
		}
		if( num.equals(num2)) {
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int num = 1;
		while(num > 0) {
			System.out.println("Escribe un número positivo");
			num = sc.nextInt();
			if(num>0) {
				if(esPrimo(num)) {
					System.out.println("El número es primo");
				}else {
					System.out.println("El número no es primo");
				}
				if(esPalindromo(num)) {
					System.out.println("El número es palíndromo");
				}else {
					System.out.println("El número no es palíndromo");
				}
			}
			
		}
		sc.close();	
	}
}
