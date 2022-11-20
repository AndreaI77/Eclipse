import java.util.Scanner;
public class Switch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int num = 1;
		while( num >0 && num <13) {
			System.out.println("Introduce un número entre 1 y 12");
			num = sc.nextInt();
			String mes="";
			switch(num) {
				case 1: mes = "Enero";break;
				case 2: mes = "Febrero"; break;
				case 3: mes ="marzo"; break;
				case 4: mes = "Abril"; break;
				case 5: mes = "Mayo"; break;
				case 6: mes = "Junio"; break;
				case 7: mes = "Julio"; break;
				case 8: mes = "Agosto"; break;
				case 9: mes = "Septiembre"; break;
				case 10: mes = "Octubre"; break;
				case 11: mes = "Noviembre"; break;
				case 12: mes = "Diciembre"; break;
				
			}
			System.out.println(mes);
			
		}
		
		sc.close();
	}

}
