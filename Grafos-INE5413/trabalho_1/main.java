import java.util.List;

import Auxiliares.Euleriano;
import Grafo.*;
import Searches.*;

public class main {

	public static void main(String[] args) {
		Grafo gr = new Grafo();
		// entrando com o path do arquivo 
		//String path_arquivo = System.in.toString();
		
		/*
		 * dolphins.net
		 * facebook_santiago.net
		 * fln_pequena.net
		 * euleriano.net
		 * */
		
		String path_arquivo = "C:\\Users\\felip\\Desktop\\UFSC\\Grafos\\arquivos_net\\fln_pequena.net";
		
		Grafo grafo = gr.criaGrafo(path_arquivo);
		

		testes(grafo);
	}
	
	public static void testes(Grafo grafo) {
//		System.out.println("Numero de arestas criadas: "+grafo.qtdArestas());
//		
//		System.out.println("Número de vertices do grafo: "+grafo.qtdVertices());
//		System.out.println();
//		System.out.println();
//		
//		Buscas b = new Buscas();
//		System.out.println("Busca em largura");
//		b.BuscaEmLargura(grafo, 1);		// FUNCIONANDO
//		
//		System.out.println();
//		System.out.println();
		
		
		
		Ciclos c = new Ciclos();
		
//		System.out.println("Dijkstra");
//		c.dijkstra(grafo, 1);
//		System.out.println();
//		System.out.println();
		
//		System.out.println("Floyd-Warshall");
//		c.floydWarshall(grafo);
//		System.out.println();
//		System.out.println();
//		
		
		System.out.println("Hierholzer");
		Euleriano hierholzer = c.hierholzer(grafo);
		if (hierholzer.isExiste()) {
			List<Vertice> ciclo = hierholzer.getCiclo();
			System.out.println(1);
			for(int a = 0; a < ciclo.size(); a++) {
				System.out.print(ciclo.get(a).getName());
				if(a < ciclo.size()-1) {
					System.out.print(", ");
				}
			}
		} else {
			System.out.println(0);
		}
	}

}
