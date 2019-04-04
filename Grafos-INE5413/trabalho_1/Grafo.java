import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grafo {
	private String name;
	private List<Vertice> vertices = new ArrayList<>();
	private List<Aresta> arestas = new ArrayList<>();
	
	public Grafo() {
		
	}
	
	public Grafo(String name, List<Vertice> vertices, List<Aresta> arestas) {
		this.name = name;
		this.vertices = vertices;
		this.arestas = arestas;
	}
	
	public Grafo(String name) {
		this.name = name;
		this.vertices = new ArrayList<>();
		this.arestas = new ArrayList<>();
	}
	
	public Grafo(String name, Vertice raiz) {
		this.name = name;
		this.vertices.add(raiz);
		this.arestas = new ArrayList<>();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertice> vertices) {
		this.vertices = vertices;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(List<Aresta> arestas) {
		this.arestas = arestas;
	}

	
//	public void addVertice(int name, Vertice vizinho, short peso) {
//		if(!existeVertice(name)) {
//			// criando o vertice
//			Vertice verticeNovo = new Vertice(name);
//			
//			// adicionando o vizinho ao vertice criado
//			verticeNovo.addVizinho(vizinho);
//			
//			// criando a aresta
//			Aresta aresta = new Aresta(verticeNovo, vizinho, peso);
//			
//			// adicionando a aresta às arestas do grafo
//			this.arestas.add(aresta);
//			
//			// adicionando o vertice novo aos vertices do grafo
//			this.vertices.add(verticeNovo);
//			
//		} else {
//			System.out.println("Nome escolhido para o vertice ja existe.");
//		}
//		
//		
//	}
	
	public void addVertice(Vertice vertice) {
		if(!existeVertice(vertice.getName())) {
			vertices.add(vertice);
		} else {
			//System.out.println("Já existe um vertice com o mesmo nome.");
		}
	}
	
	public int qtdVertices() {
		return this.vertices.size();
	}
	
	public int qtdArestas() {
		return this.arestas.size();
	}
	
	public int grau(String name) {
		Vertice v = new Vertice();
		try {
			v = procuraVertice(name);
		} catch (NullPointerException e) {
			System.out.println("Não existe vertice com esse nome");
		}
		
		return v.getGrau();
	}
	
	public int grau(Vertice v) {
		return v.getGrau();
	}
	
	public String rotulo(String name) {
		Vertice v = new Vertice();
		try {
			v = procuraVertice(name);
		} catch (NullPointerException e) {
			System.out.println("Não existe vertice com esse nome");
		}
		
		return v.getName();
	}
	
	public String rotulo(Vertice v) {
		return v.getName();
	}
	
	public List vizinhos(String name) {
		Vertice v = new Vertice();
		try {
			v = procuraVertice(name);
		} catch (NullPointerException e) {
			System.out.println("Não existe vertice com esse nome");
		}
		
		return v.getVizinhanca();
	}
	
	public List vizinhos(Vertice v) {
		return v.getVizinhanca();
	}
	
	public boolean haAresta(List<Vertice> vertices) {
		for(int a = 0; a < this.arestas.size(); a++) {
			Aresta aresta = this.arestas.get(a);
			if(aresta.contains(vertices.get(0)) || aresta.contains(vertices.get(1))) {
				return true;
			}
		}
		
		return false;
	}
	
	public double peso(List<Vertice> vertices) {
		for(int a = 0; a < this.arestas.size(); a++) {
			Aresta aresta = this.arestas.get(a);
			if(aresta.contains(vertices.get(0)) || aresta.contains(vertices.get(1))) {
				return aresta.getPeso();
			}
		}
		
		// se não existir a aresta, retorna infinito como o peso
		return Double.POSITIVE_INFINITY;
	}
	
	public void imprimeArestas() {
		for (int a = 0; a < arestas.size(); a++) {
			String nameV1 = arestas.get(a).getVertice1().getName();
			String nameV2 = arestas.get(a).getVertice2().getName();
			double peso = arestas.get(a).getPeso();
			
			String p = "" + nameV1 + ", " + nameV2 + ", " + peso + "/n";
			System.out.println(p);
		}
	}
	
	public boolean existeVertice(String name) {
		for(int a = 0; a < this.vertices.size(); a++) {
			if (name.equals(vertices.get(a).getName())) {
				return true;
			}
		}
		return false;
	}
	
	public Vertice procuraVertice(String name) {
		for(int a = 0; a < this.vertices.size(); a++) {
			if (name.equals(vertices.get(a).getName())) {
				return vertices.get(a);
			}
		}
		return null;
	}
	
	public void addAresta(Aresta aresta) {
		this.arestas.add(aresta);
	}

}
