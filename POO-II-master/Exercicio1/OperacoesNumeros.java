package exercicio1;

public class OperacoesNumeros {
	
	int positivoNegativo(int numero) {

		/*
		 * Se o numero recebido no parametro for positivo, o metodo
		 * retorna 1. Se for negativo, o metodo retorna zero.
		*/
		int valor = 0;
		if(numero >= 0) {
			valor = 1;
		}
		
		return valor;
	}
	
	
	int somaEntre(int numero1, int numero2) {
		/*O método recebe dois números e retorna a soma
		 * de todos os inteiros entre esses dois números
		 */
		int soma = 0;
		
		for(int a = numero1 + 1; a < numero2; a++ ) {
			soma += a;
		}
		
		return soma;
	}
	
	int somaDivisao(int divisor, int b, int c) {
		/*O método recebe tres números e soma todos os números
		 * entre b e c que sejam divididos por a
		 */
		int soma = 0;
		
		for (int aux = b; aux <= c; aux++) {
			if (aux % divisor == 0) {
				soma += aux;
			}
		}
		
		return soma;
	}
	
	void conversorSegundos (int segundos) {
		int horas = segundos / 3600;
		int minutos = (segundos%3600)/60;
		int segundosresto = segundos%60;
		
		
		/*convertendo os valores int em valores String
		 */
		
		String minutosS = Integer.toString(minutos);
		String horasS = Integer.toString(horas);
		String segundosrestoS = Integer.toString(segundosresto);
		
		System.out.println(horasS + " horas");
		System.out.println(minutosS + " minutos");
		System.out.println(segundosrestoS + " segundos");
	}
	
	
	

}
