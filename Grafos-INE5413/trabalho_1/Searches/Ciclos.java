package Searches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import Auxiliares.Euleriano;
import Grafo.*;

public class Ciclos {
	private Buscas buscas = new Buscas();
	private Grafo grafoUni = new Grafo();
	private List<Vertice> caminho = new ArrayList<>();
	
	public Ciclos() {
	}
	
	public Euleriano hierholzer(Grafo g) {
		List<Vertice> ciclo = new ArrayList<>();
		g.setVisitadas(false);
		
		Vertice origem = g.getVerticeImpar();
		
		Euleriano euleriano = buscarSubCicloEuleriano(g, origem, g.getArestas());

		
		if (!euleriano.isExiste()) {
			return new Euleriano(false, ciclo);
		} else {
			for(Aresta a : g.getArestas()) {
				if(!a.getVisitada()) {
					return new Euleriano(false, ciclo);
				}
			}
		}
		
		return new Euleriano(true, euleriano.getCiclo());
	}
	
	public Euleriano buscarSubCicloEuleriano(Grafo g, Vertice v, List<Aresta> arestas) {
		List<Vertice> ciclo = new ArrayList<>();
		
		ciclo.add(v);
		
		Vertice origem = v;
		
		do {
			List<Aresta> naovisitadas = v.naoVisitadas();
			
			if(naovisitadas.isEmpty()) { // so continua se existir aresta nao visitada (aqui se assume que a aresta esta conectada ao vertice v)
				return new Euleriano(false, null);
			} else {
				Aresta a = naovisitadas.get(0);
				a.setVisitada(true);
				v = a.getVerticeComplementar(v, a); // retorna u, tal que {v, u} pertence as arestas acima
				ciclo.add(v);
			}
			
		} while (origem != v);
		
		int index = 0;
		for(Vertice x : ciclo) {
			List<Aresta> naovisitadas = x.getNaoVisitadas();
			if(naovisitadas.size() > 0) {
				Euleriano euler = buscarSubCicloEuleriano(g, x, naovisitadas);
				
				if(!euler.isExiste()) {
					return new Euleriano(false, null);
				}
				
				ciclo.addAll(index, euler.getCiclo());
				
				index++;
			}
			
		}
		
		
		return new Euleriano(true, ciclo);
	}
	
	
	
	public void dijkstra(Grafo g, int index) {

		g.setDistanciaMax();
		g.setAntecessoresNull();
		g.setVisitadas(false);
		
		List<Vertice> vertices = g.getVertices();
		
		Vertice origem = vertices.get(index);
		origem.setDistancia(0.0);
		
		for(Vertice v : vertices) {
			if(!v.isVisitado()) {
				for(Aresta a : v.getArestasDoVertice()) {
					Vertice u = a.getVerticeComplementar(v, a);
					
					List<Vertice> vizinhanca = u.getVizinhanca();
					
					for(Vertice n : vizinhanca) {
						if(n.getDistancia() > u.getDistancia() + g.peso(u, n) && !n.isVisitado()) {
							n.setDistancia(u.getDistancia() + g.peso(u, n));
							
							for(Vertice ant : u.getAntecessores()) {
								if(n.getAntecessores().contains(ant)) { 
									n.setAntecessores(new ArrayList<>()); // retira a ida e volta do ciclo
								}
								n.addAntecessor(ant);
							}
							n.addAntecessor(u);
						}
					}
				}
				
			}
		}
		
		for(Vertice vert : g.getVertices()) {
			// imprime o destino
			System.out.print(vert.getName()+": ");
			// imprime os antecessores
			for(int a = 0; a < vert.getAntecessores().size(); a++) {
				System.out.print(vert.getAntecessores().get(a).getName()+", ");
			}
			//imprime o destino no final dos antecessores
			System.out.print(vert.getName());
			// imprime a distancia
			System.out.print("\t\t\td = "+vert.getDistancia());
			
			System.out.println();
		}
	
	}
	
	public void floydWarshall(Grafo g) {		
		/* Cria Matriz com os pesos segundo o grafo*/
		Double[][] matriz = criaMatriz(g);
		
		for (int k = 0; k < g.qtdVertices(); k++) { // roda todos os vertices (linhas)			
			
            for (int i = 0; i < g.qtdVertices(); i++) { // roda linhas (vertices de origem)
            	
                for (int j = 0; j < g.qtdVertices(); j++) { // roda colunas (vertices de destino)
                    matriz[i][j] = Double.min(matriz[i][j], (matriz[i][k] + matriz[k][j]) );
                }
            } 
        }
		
		imprimeMatriz(matriz, g, g.getVertices());
	}
	
	public Double[][] criaMatriz(Grafo g) {
		List<Aresta> arestas = g.getArestas();
		List<Vertice> vertices = g.getVertices();
		int l = 1;
		
		Double[][] matriz3 = new Double[vertices.size()][vertices.size()];
		
		for(int i = 0; i < vertices.size(); i++) {
			Vertice v = vertices.get(i);
			for(int j = 0; j < vertices.size(); j++) {
				Vertice n = vertices.get(j);
				if( i == j ) {
					matriz3[i][j] = 0.0;
				} else {
					if(g.existeAresta(v, n)) {
						matriz3[i][j] = g.peso(n, v);
					} else {
						matriz3[i][j] = Double.MAX_VALUE;
					}
				}
				
			}
		}
		
		imprimeMatriz(matriz3, g, vertices);
		System.out.println("\n\n\n");
		
		return matriz3;
	}
	
	public void imprimeMatriz(Double[][] matriz, Grafo g, List<Vertice> vertices) {
		for(int i = 0; i < matriz.length; i++) { //linhas
			for(int j = 0; j < matriz[i].length; j++) { //colunas
				if(matriz[i][j] == Double.MAX_VALUE) {
					System.out.print("--\t");
				} else {
					System.out.print(matriz[i][j]+"\t");
				}
			}
			System.out.println();
		}
	}

	
}

