import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Primos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Escribe un número");
		Scanner sc = new Scanner(System.in);
		int num=(int) sc.nextInt();
		
		System.out.print(num+" = ");
		
		int divisor=2;
		List<Integer> lista = new ArrayList<Integer>();
		while(divisor<num) {
			
			if(num%divisor == 0) {
				lista.add(divisor);
				num =num/divisor;
			}else {
				divisor++;
			}
			
		}
		//System.out.print(lista);
		if(lista.size() > 0) {
			System.out.print(lista.get(0));
			for(int i=1; i< lista.size(); i++){
				System.out.print(" * "+lista.get(i));
			}
			if(num>0) {
				System.out.print(" * "+num);
			}
		}else {
			System.out.print("El número es un número primo");
		}
		
		sc.close();
		
		
		

	}

}
