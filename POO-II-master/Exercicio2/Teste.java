import javax.swing.JOptionPane;

public class Teste {

	public static void main(String[] args) {
		/*Crie	um	assistente administrativo	e	um	técnico.	Imprima	o	número	de	matrícula
		 * 	e	o	nome	de	cada	um	deles.	
		 */
		Administrativo admins = new Administrativo("Fulano", "11927497294", 668.90 , "9308", "noturno");
		Tecnico tecni = new Tecnico("Ciclano", "39245839840", 660.70, "4529", 120);
		
		JOptionPane.showMessageDialog(null, "O Assistente Administrativo "+admins.getNome()+ " tem numero de matricula "+admins.getNumeroDeMatricula());
		JOptionPane.showMessageDialog(null, "O Assistente Tecnico "+tecni.getNome()+ " tem numero de matricula "+tecni.getNumeroDeMatricula());
		
		
		/*Crie	um	ingresso.	Peça para	o	usuário digitar	1	para	normal	e	2	para	VIP.
		 * Conforme	a	escolha	do	usuário,	diga	se	o ingresso é	do	tipo	normal	ou	VIP.
		 * Se	for	VIP,	peça para ele digitar	1	para camarote	superior	e	2	para camarote inferior.
		 * Conforme	a	escolha	do	usuário,	diga	se	que	o	VIP	é camarote	superior	ou	inferior.
		 * Imprima	o	valor	do	ingresso.
		 */
		int escolha = 0;
		do {
			escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite:\n"
																	+ "1 - para ingresso normal.\n"
																	+ "2 - para ingresso VIP."));
			if (escolha != 1 && escolha != 2) {
				JOptionPane.showMessageDialog(null, "Por favor, digite uma opcão válida!");
			}
		}while(escolha != 1 && escolha != 2);
		switch(escolha) {
		case 1:
			Normal ingressoNormal = new Normal(100);
			JOptionPane.showMessageDialog(null, "Obrigado por escolher um ingresso normal! O valor do ingresso é " +ingressoNormal.getValor());
			break;
		case 2:
			int escolha2 = 0;
			do {
				escolha2 = Integer.parseInt(JOptionPane.showInputDialog("Digite:\n"
																		+ "1 - para Camarote Superior.\n"
																		+ "2 - para Camarote Inferior."));
				if (escolha2 != 1 && escolha2 != 2) {
					JOptionPane.showMessageDialog(null, "Por favor, digite uma opcão válida!");
				}
			}while(escolha2 != 1 && escolha2 != 2);
			switch(escolha2) {
			case 1:
				CamaroteSuperior ingressoSuperior = new CamaroteSuperior(100, 50, 30);
				JOptionPane.showMessageDialog(null, "Obrigado por escolher um Camarote Superior! O valor do ingresso é " +ingressoSuperior.retornaValor());
				break;
				
			case 2:
				int[] local = {1, 2};
				CamaroteInferior ingressoInferior = new CamaroteInferior(100, 30, local);
				JOptionPane.showMessageDialog(null, "Obrigado por escolher um Camarote Inferior! O valor do ingresso é " +ingressoInferior.retornValor());
				JOptionPane.showMessageDialog(null, ingressoInferior.retornaLocal());
			break;
			}
		}
		
		
		
		
		/*Crie	um	imóvel.	Peça para	o	usuário digitar	1	para	novo	e	2	para velho.
		 * Conforme	a	definição	do	usuário,	imprima o	valor	final	do	imóvel.
		 */
		int escolha3 = 0;
		do {
			escolha3 = Integer.parseInt(JOptionPane.showInputDialog("Digite:\n"
																	+ "1 - para imovel novo.\n"
																	+ "2 - para imovel velho."));
			if (escolha3 != 1 && escolha3 != 2) {
				JOptionPane.showMessageDialog(null, "Por favor, digite uma opcão válida!");
			}
		}while(escolha3 != 1 && escolha3 != 2);
		
		switch(escolha3) {
		case 1:
			Novo imonovo = new Novo("Rua 1", 1000000, 800000);
			JOptionPane.showMessageDialog(null, "O valor do imóvel é "+imonovo.retornaTotal());
			break;
			
		case 2:
			Velho imovelho = new Velho("Rua 1", 1000000);
			JOptionPane.showMessageDialog(null, "O valor do imóvel é "+imovelho.getValorFinal());
			break;
		}
	}

}
