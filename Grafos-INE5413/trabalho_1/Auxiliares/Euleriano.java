package Auxiliares;

import java.util.List;

import Grafo.Vertice;

public class Euleriano {
	private boolean existe;
	private List<Vertice> ciclo;
	
	public Euleriano(boolean existe, List<Vertice> ciclo) {
		this.existe = existe;
		this.ciclo = ciclo;
	}

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public List<Vertice> getCiclo() {
		return ciclo;
	}

	public void setCiclo(List<Vertice> ciclo) {
		this.ciclo = ciclo;
	}
	
	

}
