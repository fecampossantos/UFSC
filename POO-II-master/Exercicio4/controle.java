import javax.swing.JOptionPane;

public class Controle {

	public static void main(String[] args) {
		double pa = 0;
		int contagem = Integer.parseInt(JOptionPane.showInputDialog("Quantas formas deseja criar?"));
		Forma[] vetor = new Forma[contagem];
		
		for(int i = 0; i < contagem; i++) {
			int escolha = 0;
			do{
				escolha = Integer.parseInt(JOptionPane.showInputDialog("Qual forma você deseja criar?\n"
																		+ "1 - Círculo\n"
																		+ "2 - Retangulo\n"
																		+ "3 - Quadrado\n"
																		+ "4 - Triangulo"));
				if (escolha < 1 || escolha > 4) {
					JOptionPane.showMessageDialog(null, "Digite uma escolha válida!");
				}
			}while(escolha < 1 || escolha > 4);
			
			
			switch(escolha) {
			case 1://circulo
				double raio = Double.parseDouble(JOptionPane.showInputDialog("Digite o raio do circulo."));
				Circulo circulonovo = new Circulo(raio);
				vetor[i] = circulonovo;
				break;
				
			case 2://retangulo
				double base = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da base."));
				double altura = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da altura."));
				Retangulo retangulonovo = new Retangulo(base, altura);
				vetor[i] = retangulonovo;
				break;
				
			case 3://quadrado
				double lado = Double.parseDouble(JOptionPane.showInputDialog("Digite o tamanho do lado"));
				Quadrado quadradonovo = new Quadrado(lado);
				vetor[i] = quadradonovo;
				break;
				
			case 4://triangulo
				double lado1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o tamanho do lado 1."));
				double lado2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o tamanho do lado 2."));
				double lado3 = Double.parseDouble(JOptionPane.showInputDialog("Digite o tamanho do lado 3."));
				boolean continua = false;
				
				do {
					Triangulo triangulonovo = new Triangulo(lado1, lado2, lado3);
				
					if(triangulonovo.checarExistencia(triangulonovo)) {
						continua = true;
						vetor[i] = triangulonovo;
					}
					else {
						JOptionPane.showMessageDialog(null, "O triangulo digitado nao existe.");
					}
				}while(continua == false);
				break;
				
			}//end switch case
			
			
		}//end for contagem
		
		for(int a = 0; a < contagem; a++) {
			System.out.println("Area do "+vetor[a].getNome()+" : "+vetor[a].calculaArea());
			System.out.println("Perimetro do "+vetor[a].getNome()+" : "+vetor[a].calculaPerimetro());
			System.out.println();
		}

	}

}
