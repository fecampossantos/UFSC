package Grafo;
import java.util.ArrayList;
import java.util.List;

public class Vertice {
	private String name;
	private int grau;
	private List<Vertice> vizinhanca = new ArrayList<>();
	private boolean visitado = false;
	private List<Vertice> antecessores = new ArrayList<>();
	private double distancia = Double.MAX_VALUE;
	private List<Aresta> arestasDoVertice = new ArrayList<>();
	private int index;
	
	public Vertice() {
		
	}
	
	public Vertice(String name, int index) {
		this.name = name;
		this.grau = 0;
		this.vizinhanca = new ArrayList<>();
		this.index = index;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrau() {
		return grau;
	}

	public void setGrau(int grau) {
		this.grau = grau;
	}

	public List<Vertice> getVizinhanca() {
		return vizinhanca;
	}

	public void setVizinhanca(List<Vertice> vizinhanca) {
		this.vizinhanca = vizinhanca;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public List<Vertice> getAntecessores() {
		return antecessores;
	}

	public void setAntecessores(List<Vertice> antecessores) {
		this.antecessores = antecessores;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	
	
	public void addVizinho(Vertice vizinho) {
		// adicionando a aresta à vizinhança
		this.vizinhanca.add(vizinho);
		// aumentando o grau do vertice
		this.grau++;
	}
	
	public void addAntecessor(Vertice antecessor) {
		this.antecessores.add(antecessor);
	}

	public List<Aresta> getArestasDoVertice() {
		return this.arestasDoVertice;
	}

	public void setArestasDoVertice(List<Aresta> arestasDoVertice) {
		this.arestasDoVertice = arestasDoVertice;
	}
	
	public void addArestaDoVertice(Aresta aresta) {
		this.arestasDoVertice.add(aresta);
	}
	
	public Aresta retornaArestaNaoVisitada() {
		List<Aresta> arestas = this.arestasDoVertice;
		Aresta naovisitada = null;
		for (Aresta a : arestas) {
			if(!a.getVisitada()) {
				naovisitada = a;
				break;
			}
		}
		return naovisitada;
	}
	
	public List<Aresta> getNaoVisitadas() {
		List<Aresta> arestas = this.arestasDoVertice;
		List<Aresta> naoVisitadas = new ArrayList<>();
		for (Aresta a : arestas) {
			if(!a.getVisitada()) {
				naoVisitadas.add(a);
			}
		}
		return naoVisitadas;
	}
	
	public List<Aresta> naoVisitadas() {
		List<Aresta> naoVisitadas = new ArrayList<>();
		List<Aresta> arestas = this.arestasDoVertice;
		
		for(Aresta a : arestas) {
			if(!a.getVisitada()) {
				naoVisitadas.add(a);
			}
		}
		
		return naoVisitadas;
	}

}
