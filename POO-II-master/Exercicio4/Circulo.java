import javax.swing.JOptionPane;

public class Circulo implements Forma {
	double raio;
	String nome = "circulo";
	
	public Circulo() {
		
	}
	
	public Circulo(double raio) {
		this.raio = raio;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double calculaPerimetro() {
		
		double perimetro = 2*3.1415*raio;
		
		return perimetro;
	}
	
	public double calculaArea() {
		
		double area = 3.1415*raio*raio;
		
		return area;
	}

}
