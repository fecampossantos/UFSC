import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;


public class colorSelect extends JFrame {
	private GridLayout grid_layout;							//colocar borderlayout no frame principal
	private JCheckBox foregroundcheck, backgroundcheck;		//criar checkboxes
	private JButton okbutton, cancelbutton;					//criar buttons ok e cancel
	private static final String[] nomescor = {"Branco", "Preto", "Azul", "Amarelo", "Vermelho", "Verde", "Rosa"};
	private static final Color[] cores = {Color.WHITE, Color.BLACK, Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN, Color.PINK};
	private JPanel frame;
	private Container container1, container2, container3;	//containers que serão inseridos no frame
	private JComboBox combobox;								//lista de seleção de cores
	
	
	public colorSelect() {
		super("Color select");
		grid_layout = new GridLayout(3,0);					//3 linhas e 0 colunas
		setLayout(grid_layout);
		
		
		frame = new JPanel(grid_layout);
		
		
		
	
		//lista com o nome das cores no topo
		container1 = new Container();
		container1.setLayout(new FlowLayout());
		combobox = new JComboBox(nomescor);
		combobox.setMaximumRowCount(7);
		container1.add(combobox);
		add(container1);
		
		ouvidorComboBox ouvidorcombobox = new ouvidorComboBox();
		combobox.addItemListener(ouvidorcombobox);
		
		
		
		//checkbox de background ou foreground
		container3 = new Container();
		container3.setLayout(new FlowLayout());
		foregroundcheck = new JCheckBox("Foreground");
		backgroundcheck = new JCheckBox("Background");
		container3.add(foregroundcheck);
		container3.add(backgroundcheck);
		add(container3);
		
		ouvidorCheckBox ouvidorcheckbox = new ouvidorCheckBox();
		foregroundcheck.addItemListener(ouvidorcheckbox);
		backgroundcheck.addItemListener(ouvidorcheckbox);
		
		
		
		
		
		//botoes ok e cancel
		okbutton = new JButton("OK");
		cancelbutton = new JButton("Cancel");
		
		container2 = new Container();
		container2.setLayout(new FlowLayout());
		container2.add(okbutton);
		container2.add(cancelbutton);
		add(container2);
		
		okbutton.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(foregroundcheck.isSelected() && backgroundcheck.isSelected()) {
						foregroundcheck.setForeground(cores[combobox.getSelectedIndex()]);
						backgroundcheck.setForeground(cores[combobox.getSelectedIndex()]);
						
						combobox.setForeground(cores[combobox.getSelectedIndex()]);
						
						okbutton.setForeground(cores[combobox.getSelectedIndex()]);
						cancelbutton.setForeground(cores[combobox.getSelectedIndex()]);
						
						getContentPane().setBackground(cores[combobox.getSelectedIndex()]);
					}
					else if(foregroundcheck.isSelected()) {
						getContentPane().setForeground(cores[combobox.getSelectedIndex()]);
						
						okbutton.setForeground(cores[combobox.getSelectedIndex()]);
						cancelbutton.setForeground(cores[combobox.getSelectedIndex()]);
						
						combobox.setForeground(cores[combobox.getSelectedIndex()]);
						
						foregroundcheck.setForeground(cores[combobox.getSelectedIndex()]);
						backgroundcheck.setForeground(cores[combobox.getSelectedIndex()]);
						
					}
					else if(backgroundcheck.isSelected()) {
						getContentPane().setBackground(cores[combobox.getSelectedIndex()]);
						
					}
				}
			}
			
				
		);
		
		cancelbutton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				}
					
			);
		
		
		
		
	}
	
	
	public void ActionPerformed(ActionEvent e) {
		
	}
	
	
	private class ouvidorComboBox implements ItemListener{
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			combobox.getSelectedIndex();
			
		}
	}
	
	
private class ouvidorCheckBox implements ItemListener{
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			
		}
	}
	
	
	public static void main(String[] args) {
		colorSelect frame = new colorSelect();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 200);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
