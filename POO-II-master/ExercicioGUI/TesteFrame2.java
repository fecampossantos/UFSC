import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TesteFrame2 extends JFrame{
	private JLabel campoLabel, labelcombobox;
	private JTextField campoText, naoEditavelCheck, naoEditavelRadio;
	private JButton botao, botaocopia;
	private JCheckBox checkBold, checkItalic;
	private JRadioButton JRadioNegrito, JRadioItalico, JRadioNormal;
	private ButtonGroup grupo;
	private JComboBox combobox;
	private static final String[] nomes = {"fb.png", "ig.png", "tumblr.png"};
	private Icon[] icones = {new ImageIcon(getClass().getResource(nomes[0])), 
			new ImageIcon(getClass().getResource(nomes[1])), new ImageIcon(getClass().getResource(nomes[2]))};
	private JList listacores, listacoresnomes, listacopia;
	private static final String[] nomescor = {"Branco", "Preto", "Azul", "Amarelo", "Vermelho", "Verde", "Rosa"};
	private static final Color[] cores = {Color.WHITE, Color.BLACK, Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN, Color.PINK};

	public TesteFrame2(){

		//construtor herdado da superclasse
		super("Meu Frame");
		
		setLayout(new FlowLayout());
		
		OuvidorTexto listener = new OuvidorTexto();
		
		campoLabel = new JLabel("Escreva seu nome"); //campo label
		campoLabel.setToolTipText("Escreva seu nome");
		
		campoText = new JTextField(15); //campo de texto com 15 colunas
		campoText.addActionListener(listener);
		
		botao = new JButton("Clique Aqui");
		botao.addActionListener(listener);
		
		add(campoLabel);
		add(campoText);
		add(botao);
		
		
		
		
		//------------------------------ JCHECKBOX ----------------------------------------//
		
		naoEditavelCheck = new JTextField(20);
		naoEditavelCheck.setEditable(false);
		naoEditavelCheck.setFont(new Font("Serif", Font.PLAIN, 14));
		add(naoEditavelCheck);
		
		checkBold = new JCheckBox("Negrito");
		checkItalic = new JCheckBox("Itálico");
		add(checkBold);
		add(checkItalic);
		
		ouvidorCheckBox ouvidorCheck = new ouvidorCheckBox();
		checkBold.addItemListener(ouvidorCheck);
		checkItalic.addItemListener(ouvidorCheck);
		
		
		
		
		//------------------------------ JRADIO ----------------------------------------//
		
		naoEditavelRadio = new JTextField("Texto para os Radio Buttons");
		naoEditavelRadio.setFont(new Font("Arial", Font.PLAIN, 20));
		add(naoEditavelRadio);
		//criando os JRadioButtons
		 JRadioNormal = new JRadioButton("Normal");
		 JRadioNegrito = new JRadioButton("Negrito");
		 JRadioItalico = new JRadioButton("Itálico");
		 add(JRadioNormal);
		 add(JRadioNegrito);
		 add(JRadioItalico);
		 
		 grupo = new ButtonGroup();
		 grupo.add(JRadioItalico);
		 grupo.add(JRadioNegrito);
		 grupo.add(JRadioNormal);
		 
		 ouvidorRadioButton ouvidorRadio = new ouvidorRadioButton();
		JRadioNormal.addItemListener(ouvidorRadio);
		JRadioNegrito.addItemListener(ouvidorRadio);
		JRadioItalico.addItemListener(ouvidorRadio);
		
		
		
		
		
		//------------------------------ COMBO BOX ----------------------------------------//
		
		combobox = new JComboBox(nomes);
		combobox.setMaximumRowCount(3);
		add(combobox);
		
		
		ouvidorComboBox ouvidorcombobox = new ouvidorComboBox();
		combobox.addItemListener(ouvidorcombobox);
		labelcombobox = new JLabel("ComboBox com icone", icones[0], SwingConstants.RIGHT);
		add(labelcombobox);
		
		
		//------------------------------ JLIST unica ----------------------------------------//
		
		listacores = new JList(nomescor);
		listacores.setVisibleRowCount(4);
		listacores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(listacores));
		listacores.addListSelectionListener(
					new ListSelectionListener()
					{
						@Override
						public void valueChanged(ListSelectionEvent arg0) {
							getContentPane().setBackground(cores[listacores.getSelectedIndex()]);
						}
					}
		
		);
		
		
		//---------------------------- JLIST multipla ---------------------------------//
		
		listacoresnomes = new JList(nomescor);
		listacoresnomes.setVisibleRowCount(5);
		listacoresnomes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(listacoresnomes));
		
		botaocopia = new JButton("Cópia >>>>");
		add(botaocopia);
		
		listacopia = new JList();
		listacopia.setVisibleRowCount(5);
		listacopia.setFixedCellHeight(15);
		listacopia.setFixedCellHeight(50);
		listacopia.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		add(new JScrollPane(listacopia));
		
		botaocopia.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						listacopia.setListData(listacoresnomes.getSelectedValues());
					}
				}
				
				);
		

	}
	
	
	
	//----------------------------------------------------CLASSES INTERNAS-----------------------------------------------------------//
	
	//classe interna
	private class OuvidorTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
		if(event.getSource() == campoText){
			String frase = String.format("Nome: %s", event.getActionCommand());
			JOptionPane.showMessageDialog(null, frase);
		}
		if(event.getSource() == botao) {
			String nome = campoText.getText();
			String frase = String.format("O seu nome e: "+nome, event.getActionCommand());
			JOptionPane.showMessageDialog(null, frase);
		}
		}
	}
	
	
	
	//classe interna
	private class ouvidorCheckBox implements ItemListener{
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			Font font = null; //font vazia armazenada
			String texto = "";
			
			//vendo qual checkbox for marcada e alterando a font
			if(checkBold.isSelected() && checkItalic.isSelected()) {
				texto = "Negrito e Itálico";
				font = new Font("Arial", font.BOLD + font.ITALIC, 14);
				
			}
			else if(checkBold.isSelected()) {
				texto = "Negrito";
				font = new Font("Serif", font.BOLD, 14);
			}
			else if(checkItalic.isSelected()) {
				texto = "Itálico";
				font = new Font("Courier New", font.ITALIC, 14);
			}
			else{
				font = new Font("Serif", font.PLAIN, 14);
				texto = "";
			}
			
			naoEditavelCheck.setText(texto);
			naoEditavelCheck.setFont(font);
			
		}
	}
	
	private class ouvidorRadioButton implements ItemListener{
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			Font font = null;
			
			if(JRadioNegrito.isSelected()) {
				font = new Font("Arial", font.BOLD, 20);
			}
			else if(JRadioItalico.isSelected()){
				font = new Font("Comic Sans", font.ITALIC, 20);
			}
			else if(JRadioNormal.isSelected()) {
				font = new Font("Courier New", font.PLAIN, 20);
			}
			else {
				font = new Font("Serif", font.PLAIN, 14);
			}
			
			naoEditavelRadio.setFont(font);
		}
	}
	
	private class ouvidorComboBox implements ItemListener{
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			labelcombobox.setIcon(icones[combobox.getSelectedIndex()]);
		}
	}
}
