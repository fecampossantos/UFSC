package Grafo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;


import java.util.logging.*;

public class Grafo {
	private static String str_vertices = "*vertices";
	private static String str_edges = "*edges";

	private Logger log = Logger.getLogger(Grafo.class.getName());
	
	private String name;
	private List<Vertice> vertices = new ArrayList<>();
	private List<Aresta> arestas = new ArrayList<>(); // TODO MUDAR PARA HASHMAP
	private Double nmr_vertices = 0.0;
	private Double nmr_arestas= 0.0;
	
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
		this.nmr_vertices = Double.valueOf(""+vertices.size());
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(List<Aresta> arestas) {
		this.arestas = arestas;
		this.nmr_arestas = Double.valueOf(""+arestas.size());
	}
	
	
	public Grafo criaGrafo(String path_arquivo) {
		Grafo grafo = new Grafo();
		
		File arquivo = new File(path_arquivo);
		
		// checando se o arquivo existe
		if(!arquivo.exists()) {
			log.log(Level.SEVERE, "O arquivo não existe.");
			return grafo;
		}
		
		// se o arquivo existir, continua		
		try {
			String linha = "";
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			
			while(br.ready()) {	// enquanto ainda houver linhas para serem lidas
				linha = br.readLine();
				if(linha.contains(str_vertices)) {	//cria os vertices do grafo
					int n_vertices = Integer.parseInt(linha.split(" ")[1]); // recebe o numero de vertices
					for(int i = 0; i < n_vertices; i++) {
						String linha_lida = br.readLine();
						String nome = "";
						int index;
						if(linha_lida.contains("\"")) { // checa se a começa com aspas
							nome = linha_lida.split("\"")[1];
							index = Integer.valueOf(linha_lida.split("\"")[0].split(" ")[0]);
						} else {
							nome = linha_lida.split(" ")[1];
							index = Integer.valueOf(linha_lida.split(" ")[0]);
						}
						
						Vertice v_novo = new Vertice(nome, index);
						grafo.addVertice(v_novo);
					}
				} else if (linha.contains(str_edges)) {	// cria as arestas do grafo
					List<Vertice> vertices = grafo.getVertices();
					
					while(br.ready()) {
						linha = br.readLine();
						
						// criando as arestas
						Vertice v1 = vertices.get(Integer.parseInt(linha.split(" ")[0]) - 1);
						Vertice v2 = vertices.get(Integer.parseInt(linha.split(" ")[1]) - 1);
						double peso = Double.parseDouble(linha.split(" ")[2]);
						
						Aresta n_aresta = new Aresta(v1, v2, peso);
						
						// adicionando a aresta ao grafo
						grafo.addAresta(n_aresta);
						
						// referenciando vizinhos (o metodo ja aumenta o numero do grau)
						v1.addVizinho(v2);
						v2.addVizinho(v1);
						
						//System.out.println("Criada a aresta entre os vertices "+v1.getName()+" e "+v2.getName()+" de peso "+n_aresta.getPeso());
					} 
				} else {
					log.log(Level.WARNING, "Erro na formatação do arquivo.");
				}
			}
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io) {
			log.log(Level.WARNING, io.getMessage());
		}
		
		return grafo;
	}
	
	
	public void addVertice(Vertice vertice) {
		if(!existeVertice(vertice.getName())) {
			vertices.add(vertice);
			nmr_vertices++;
		} else {
			//System.out.println("Já existe um vertice com o mesmo nome.");
		}
	}
	
	public double qtdVertices() {
		return this.nmr_vertices;
	}
	
	public double qtdArestas() {
		return this.nmr_arestas;
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
	
	public double peso(Vertice v1, Vertice v2) {
		for(int a = 0; a < this.arestas.size(); a++) {
			Aresta aresta = this.arestas.get(a);
			if(aresta.contains(v1) && aresta.contains(v2)) {
				return aresta.getPeso();
			}
		}
		
		// se não existir a aresta, retorna infinito como o peso
		return Double.POSITIVE_INFINITY;
	}
	
	public Aresta pesoAresta(Vertice v1, Vertice v2) {
		for(int a = 0; a < this.arestas.size(); a++) {
			Aresta aresta = this.arestas.get(a);
			if(aresta.contains(v1) && aresta.contains(v2)) {
				return aresta;
			}
		}
		
		// se não existir a aresta, retorna null
		return new Aresta();
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
		this.nmr_arestas++;
	}
	
	public void setVisitadosFalso() {
		List<Vertice> vertices = this.getVertices();
		for(int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setVisitado(false);
		}
	}
	
	public void setDistanciaMax() {
		List<Vertice> vertices = this.getVertices();
		for(int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setDistancia(Double.MAX_VALUE);
		}
	}
	
	public void setAntecessoresNull() {
		List<Vertice> vertices = this.getVertices();
		for(int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setAntecessores(new ArrayList<Vertice>());
			
		}
	}
	
	public Aresta arestaDoVerticeMaisPerto(Vertice v) {
		Aresta menorAresta = new Aresta();
		List<Aresta> arestas = v.getArestasDoVertice();
		Double distancia = Double.MAX_VALUE;
		
		for (Aresta a : arestas) {
			if(a.getPeso() < distancia) {
				distancia = a.getPeso();
				menorAresta = a;
			}
		}
		
		return menorAresta;
		
	}
	
	public boolean existeAresta(Vertice v1, Vertice v2) {
		for(int a = 0; a < this.arestas.size(); a++) {
			Aresta aresta = this.arestas.get(a);
			if(aresta.contains(v1) && aresta.contains(v2)) {
				return true;
			}
		}
		
		// se não existir a aresta, retorna null
		return false;
	}
	
	public void removeAresta(Vertice v1, Vertice v2) {
		List<Aresta> arestas = this.arestas;
		for(int a = 0; a < arestas.size(); a++) {
			Aresta aresta = arestas.get(a);
			if(aresta.contains(v1) && aresta.contains(v2)) {
				arestas.remove(a);
				
			}
		}
	}
	
	public void setDistanciaMin() {
		List<Vertice> vertices = this.getVertices();
		for(int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setDistancia((double)0);
		}
	}
	
	public void setVisitadas(boolean visitadas) {
		for(Aresta a : this.arestas) {
			a.setVisitada(visitadas);
		}
	}
	
	public Vertice getVerticeImpar() {
		List<Vertice> vertices = this.getVertices();
		Vertice grauImpar = vertices.get(0);
		for(Vertice v : vertices) {
			if(v.getGrau() %2 == 1) {
				grauImpar = v;
				break;
			}
		}
		
		return grauImpar;
	}

}
