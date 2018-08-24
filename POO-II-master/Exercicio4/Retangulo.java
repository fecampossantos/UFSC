import javax.swing.JOptionPane;

public class Retangulo implements Forma {
	double base, altura;
	String nome = "retangulo";
	
	public Retangulo() {
		
	}
	
	public Retangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double calculaPerimetro() {
		
		double perimetro = (base+altura)*2;
		
		return perimetro;
	}
	
	public double calculaArea() {
			//double base = Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho da base."));
			//double altura = Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho da altura."));
		
		double area = base*altura;
		
		return area;
	}

}
