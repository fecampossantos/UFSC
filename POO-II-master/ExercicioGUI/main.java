import javax.swing.JFrame;

import java.awt.Color;
import java.awt.color.*;

public class TesteGUI2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//instanciando um objeto da classe TesteFrame:
		
		
		TesteFrame2 testeFrame = new TesteFrame2();
		
		testeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testeFrame.setSize(900, 500);
		testeFrame.setLocationRelativeTo(null);
		testeFrame.setVisible(true);
		
//		Color cor;
//		cor = Color.GRAY;
//		testeFrame.getContentPane().setBackground(cor);
	}

}
