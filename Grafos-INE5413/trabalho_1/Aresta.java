
public class Aresta {
	private Vertice vertice1;
	private Vertice vertice2;
	private double peso;
	
	public Aresta(Vertice v1, Vertice v2, double peso) {
		this.vertice1 = v1;
		this.vertice2 = v2;
		this.peso = peso;
	}

	public Vertice getVertice1() {
		return vertice1;
	}

	public void setVertice1(Vertice vertice1) {
		this.vertice1 = vertice1;
	}

	public Vertice getVertice2() {
		return vertice2;
	}

	public void setVertice2(Vertice vertice2) {
		this.vertice2 = vertice2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public boolean contains(String name) {
		if(this.vertice1.getName().equals(name) || this.vertice2.getName().equals(name)) {
			return true;
		}
		return false;
	}
	
	public boolean contains(Vertice v) {
		if(this.vertice1.equals(v) || this.vertice2.equals(v)) {
			return true;
		}
		return false;
	}

}
