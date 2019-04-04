import java.util.ArrayList;
import java.util.List;

public class Vertice {
	private String name;
	private static int grau;
	private List<Vertice> vizinhanca = new ArrayList<>();
	
	public Vertice() {
		
	}
	
	public Vertice(String name) {
		this.name = name;
		this.grau = 0;
		this.vizinhanca = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getGrau() {
		return grau;
	}

	public static void setGrau(int grau) {
		Vertice.grau = grau;
	}

	public List<Vertice> getVizinhanca() {
		return vizinhanca;
	}

	public void setVizinhanca(List<Vertice> vizinhanca) {
		this.vizinhanca = vizinhanca;
	}

	public void addVizinho(Vertice vizinho) {
		// adicionando a aresta à vizinhança
		this.vizinhanca.add(vizinho);
		// aumentando o grau do vertice e do seu vizinho
		this.grau++;
	}

}
