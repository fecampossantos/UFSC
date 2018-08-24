
public class Ingresso {
	protected int valor;
	
	public Ingresso() {
		
	}
	
	public Ingresso(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public void imprimeValor() {
		System.out.print(this.valor);
	}

}
