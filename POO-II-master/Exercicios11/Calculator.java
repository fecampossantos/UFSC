import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	private Container cont1, cont2;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bsum, bsub, bdiv, bmult, bequals, breset;
	private JTextField screen;
	private int[] elementos;  //elementos da equacao
	private static Color cor;
	private static int elementocontagem = 0, operacao = 0;
	private static int resultado;
	
	
	public Calculator() {
		super("Calculator");
		setLayout(new BorderLayout());
		
		
		elementos = new int[2];
		
		//container da tela de resultado
		screen = new JTextField("", 20);
		screen.setEditable(false);
		cont1 = new Container();
		cont1.setLayout(new FlowLayout());
		cont1.add(screen);
		add(cont1, BorderLayout.NORTH);
		
		
		
		
		//container dos botoes
		cont2 = new Container();
		cont2.setLayout(new GridLayout(4, 4, 4, 4));  //4 linhas e 4 colunas, com 4 "espacos" entre os botoes (horizontal e verticalmente)
		
		Font font1 = new Font("Arial", Font.BOLD, 20);
		
		b1 = new JButton("1");
		b1.setFont(font1);
		cont2.add(b1);
		
		b2 = new JButton("2");
		b2.setFont(font1);
		cont2.add(b2);
		
		b3 = new JButton("3");
		b3.setFont(font1);
		cont2.add(b3);
		
		bsum = new JButton("+");
		bsum.setFont(font1);
		cont2.add(bsum);
		
		
		b4 = new JButton("4");
		b4.setFont(font1);
		cont2.add(b4);
		
		b5 = new JButton("5");
		b5.setFont(font1);
		cont2.add(b5);
		
		b6 = new JButton("6");
		b6.setFont(font1);
		cont2.add(b6);
		
		bsub = new JButton("-");
		bsub.setFont(font1);
		cont2.add(bsub);
		
		
		b7 = new JButton("7");
		b7.setFont(font1);
		cont2.add(b7);
		
		b8 = new JButton("8");
		b8.setFont(font1);
		cont2.add(b8);
		
		b9 = new JButton("0");
		b9.setFont(font1);
		cont2.add(b9);
		
		bdiv = new JButton("/");
		bdiv.setFont(font1);
		cont2.add(bdiv);
		
		
		b0 = new JButton("0");
		b0.setFont(font1);
		cont2.add(b0);
		
		breset = new JButton("AC");
		breset.setFont(font1);
		cont2.add(breset);
		
		bequals = new JButton("=");
		bequals.setFont(font1);
		cont2.add(bequals);
		
		bmult = new JButton("*");
		bmult.setFont(font1);
		cont2.add(bmult);
		
		
		add(cont2, BorderLayout.CENTER);
		
		//botoes
		b0.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 0;
						updateScreen("0");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "0");
						elementos[elementocontagem] = Integer.parseInt(aux);
						
					}
					
				}
			);
		
		b1.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 1;
						updateScreen("1");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "1");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
			);
		
		b2.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 2;
						updateScreen("2");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "2");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
				);
		
		b3.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 3;
						updateScreen("3");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "3");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
				);
		
		b4.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 4;
						updateScreen("4");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "4");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
				);
		
		b5.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 5;
						updateScreen("5");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "5");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
				);
		
		b6.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 6;
						updateScreen("6");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "6");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
				);
		
		b7.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 7;
						updateScreen("7");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "7");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
				);
		
		b8.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 8;
						updateScreen("8");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "8");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
				);
		
		b9.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int valor = 9;
						updateScreen("9");
						
						String aux = String.valueOf(elementos[elementocontagem]);
						aux = (aux + "9");
						elementos[elementocontagem] = Integer.parseInt(aux);
					}
					
				}
				);
		
		bsum.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						elementocontagem++;
						updateScreen("+");
						operacao = 1;
					}
					
				}
				);
		
		bsub.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						elementocontagem++;
						updateScreen("-");
						operacao = 2;
					}
					
				}
				);
		
		bdiv.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						elementocontagem++;
						updateScreen("/");
						operacao = 3;
					}
					
				}
				);
		
		bmult.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						elementocontagem++;
						updateScreen("*");
						operacao = 4;
					}
					
				}
				);
		
		breset.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						resetScreen();
						elementocontagem = 0;
						elementos[0] = 0;
						elementos[1] = 0;
						resultado = 0;
						
					}
					
				}
				);
		
		bequals.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						switch(operacao) {
						case 1: //soma
							resultado = elementos[0] + elementos[1];
							break;
							
						case 2: //subtracao
							resultado = elementos[0] - elementos[1];
							break;
							
						case 3: //divisao
							resultado = elementos[0]/elementos[1];
							break;
							
						case 4: //multiplicacao
							resultado = elementos[0]*elementos[1];
							break;
							
						}
						String result = String.valueOf(resultado);
						resetScreen();
						updateScreen(result);
						elementocontagem = 0;
						elementos[0] = Integer.parseInt(result);
						elementos[1] = 0;
						
						
					}
					
				}
				);
		
		
		
		
	}




//----------CLASSES INTERNAS-------------//

	public static void main(String[] args) {
		Calculator frame = new Calculator();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 350);
		frame.setVisible(true);
		
		frame.setLocationRelativeTo(null);
		
		cor = Color.GRAY;
		frame.getContentPane().setBackground(cor);
		
		
	}
	
	
	public void updateScreen(String update) {
		String update2 = screen.getText() + update;
		screen.setText(update2);
		
	}
	
	public void resetScreen() {
		screen.setText("");
	}
}
