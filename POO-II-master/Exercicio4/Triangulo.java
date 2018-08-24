import javax.swing.JOptionPane;

public class Triangulo implements Forma {
	double lado1, lado2, lado3;
	String nome = "triangulo";
	
	public Triangulo() {
		
	}
	
	public Triangulo(double lado1, double lado2, double lado3) {
		this.lado1 = lado1;
		this.lado2 = lado2;
		this.lado3 = lado3;
	}
	
	public double getLado1() {
		return lado1;
	}

	public double getLado2() {
		return lado2;
	}

	public double getLado3() {
		return lado3;
	}
	
	public String getNome() {
		return nome;
	}

	public double calculaPerimetro() {
		double perimetro = lado1 + lado2 + lado3;
		return perimetro;
	}
	
	public double calculaArea() {
		
		double area = (lado1*lado2)/2;
		
		return area;
	}
	
	public boolean checarExistencia(Triangulo tri) {
		boolean existe = false;
		double maiorlado = 0;
		
		
		double[] lados = {tri.lado1, tri.lado2, tri.lado3};
		if(lados[0] > lados[1] && lados[1] > lados[2]) { //(3, 2, 1)
			double aux1 = lados[2];
			lados[2] = lados[0];
			lados[0] = aux1;
		}
		
		else {
			if(lados[0] < lados[1] && lados[1] > lados[2]) {//(1, 3, 2)
				double aux2 = lados[1];
				lados[1] = lados[2];
				lados[2] = aux2;
			}
			
			else {
				if(lados[0] > lados[1] && lados[1] < lados[3]) {//(2, 1, 3)
					double aux4 = lados[0];
					lados[0] = lados[1];
					lados[1] = aux4;
				}
				
				else {
					if(lados[0] < lados[1] && lados[1] > lados[2]) {//(2, 3, 1)
						double aux5 = lados[0], aux6 = lados[1];
						lados[0] = lados[2];
						lados[2] = aux6;
						lados[1] = aux5;
					}
					
					else {//(3, 1, 2)
						double aux7 = lados[0], aux8 = lados[1];
						lados[0] = aux8;
						lados[1] = lados[2];
						lados[2] = aux7;
					}
				}
			}
		}
		
		
		//checando existencia
		
		if(lados[0] - lados[1] < lados[2] && lados[2] < lados[0] + lados[1]) {
			existe = true;
		}
		
		
		return existe;
	}
}
