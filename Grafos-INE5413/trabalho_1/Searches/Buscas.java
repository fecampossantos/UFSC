package Searches;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Grafo.*;

public class Buscas {
	
	public Buscas() {
		
	}
	
	public void BuscaEmLargura(Grafo grafo, int indexVertice) {
		/* configurando todos os vertices */
		List<Vertice> vertices = grafo.getVertices();
		double distancia = Double.MAX_VALUE; //infinito positivo
		Vertice origem = vertices.get(indexVertice);
		//System.out.println("Vertice de origem: "+origem.getName());
		
		
		/* configurando o vertice de origem */
		origem.setVisitado(true);
		distancia = 0;
		origem.setDistancia(distancia);
		
		/* preparando fila de visitas*/
		List<Vertice> visitas = new LinkedList<>();
		visitas.add(origem);
		
		
		/* propagação das visitas */
		System.out.print("0: "+origem.getName());
		boolean primeiro = true;
		double ult_dist = 0;
		String name = "";
		while (!visitas.isEmpty()) {
			Vertice u = visitas.remove(0);		// pop do ultimo elemento da lista
		
			if(u.getDistancia() > ult_dist) {
				System.out.print("\n"+u.getDistancia().intValue()+": ");
			} else if (u.getDistancia() == ult_dist && !primeiro) {
				System.out.print(", ");
			}
			
			if(primeiro) {
				name = "";
				primeiro = false;
			} else {
				name = (u.getName());
			}
			System.out.print(name);
			
			
			ult_dist = u.getDistancia() ;
			List<Vertice> vizinhanca = u.getVizinhanca();
			
			for(int i = 0; i < vizinhanca.size(); i++) {
				Vertice v = vizinhanca.get(i);
				if(!v.isVisitado()) {			// se o vertice ainda não foi visitado
					v.setVisitado(true);
					v.addAntecessor(u);
					v.setDistancia(u.getDistancia() + 1);
					//System.out.println("distancia do vertice "+v.getName()+" setada para "+v.getDistancia());
					visitas.add(v);
				}
			}
		}
		
		System.out.println();
		
	}
	
	public void BuscaEmLarguraNOPRINT(Grafo grafo, int indexVertice) {
		/* configurando todos os vertices */
		List<Vertice> vertices = grafo.getVertices();
		double distancia = Double.MAX_VALUE; //infinito positivo
		Vertice origem = vertices.get(indexVertice);
//		System.out.println("Vertice de origem: "+origem.getName());
		
		
		/* configurando o vertice de origem */
		origem.setVisitado(true);
		distancia = 0;
		origem.setDistancia(distancia);
		
		/* preparando fila de visitas*/
		List<Vertice> visitas = new LinkedList<>();
		visitas.add(origem);
		
		
		/* propagação das visitas */
		boolean primeiro = true;
		double ult_dist = 0;
		String name = "";
		while (!visitas.isEmpty()) {
			Vertice u = visitas.remove(0);		// pop do ultimo elemento da lista		
			
			ult_dist = u.getDistancia() ;
			List<Vertice> vizinhanca = u.getVizinhanca();
			
			for(int i = 0; i < vizinhanca.size(); i++) {
				Vertice v = vizinhanca.get(i);
				if(!v.isVisitado()) {			// se o vertice ainda não foi visitado
					v.setVisitado(true);
					v.addAntecessor(u);
					v.setDistancia(u.getDistancia() + 1);
					//System.out.println("distancia do vertice "+v.getName()+" setada para "+v.getDistancia());
					visitas.add(v);
				}
			}
		}
		
	}

}
