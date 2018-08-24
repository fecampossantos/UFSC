package exercicio1;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		OperacoesNumeros opnum = new OperacoesNumeros();
		int escolha = 0;
		do{
			escolha = Integer.parseInt(JOptionPane.showInputDialog("1 - Positivo ou Negativo\n"
								  + "2 - Soma os inteiros entre dois valores\n"
								  + "3 - Soma os inteiros divisiveis\n"
								  + "4 - Conversor de segundos\n"
								  + "5 - Sair do programa."));
			if (escolha<1 || escolha>5) {
				JOptionPane.showMessageDialog(null, "Digite um número entre 1 e 5!");
			}
		}while(escolha<1 || escolha>5);
		
		if (escolha == 1) {
			int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite um número"));
			//int resposta = opnum.positivoNegativo(numero);
			System.out.println(opnum.positivoNegativo(numero));
		}
		
		if (escolha == 2) {
			int numero1 = 0, numero2 = 0;
			do {
				numero1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o número inicial."));
				if(numero1 < 0) {
					JOptionPane.showMessageDialog(null, "O número nao pode ser negativo!");
				}
			}while(numero1 < 0);
			
			do {
				numero2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o número final."));
				if(numero2 < 0) {
					JOptionPane.showMessageDialog(null, "O número nao pode ser negativo!");
				}
			}while(numero2 < 0);
			
			int soma = opnum.somaEntre(numero1, numero2);
			
			System.out.println("O total da soma é "+ soma);
		}
		
		if (escolha == 3) {
			int numero1 = 0, numero2 = 0, numero3 = 0;
			do {
				numero1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o divisor."));
				if(numero1 < 1) {
					JOptionPane.showMessageDialog(null, "O divisor tem que ser maior que 0!");
				}
			}while(numero1 < 1);
			
			do {
				numero2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o número inicial."));
				if(numero2 < 0) {
					JOptionPane.showMessageDialog(null, "O número final tem que ser maior que 0!");
				}
			}while(numero2 < 0);
			
			do {
				numero3 = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero final."));
				if(numero3 < numero2) {
					JOptionPane.showMessageDialog(null, "O numero final tem que ser maior que o inicial!");
				}
			}while(numero3 < numero2);
			
			int soma = opnum.somaDivisao(numero1, numero2, numero3);
			
			System.out.println("O total da soma é "+ soma);
		}
		
		if (escolha == 4) {
			int segundos = 0;
			do {
				segundos = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de segundos"));
				if(segundos < 0) {
					JOptionPane.showMessageDialog(null, "Os segundos tem que ser maior que 1!");
				}
			}while(segundos < 0);
			
			opnum.conversorSegundos(segundos);
			
		}
		
		if (escolha == 5) {
			System.exit(0);
		}

	}

}
