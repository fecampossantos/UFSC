import javax.swing.JOptionPane;

public class Quadrado implements Forma{
	double lado;
	String nome = "quadrado";
	
	public Quadrado() {
		
	}
	
	public Quadrado(double lado) {
		this.lado = lado;
	}
	
	public String getNome() {
		return nome;
	}
	
	
	public double calculaPerimetro() {
		
		double perimetro = lado*4;
		
		return perimetro;
	}
	
	public double calculaArea() {
		
		double area = lado*lado;
		
		return area;
	}

}
