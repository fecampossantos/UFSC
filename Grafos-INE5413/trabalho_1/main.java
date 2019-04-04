import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.List;
import java.util.logging.*;

public class main {
	private static String str_vertices = "*vertices";
	private static String str_edges = "*edges";

	public static void main(String[] args) {
		Logger log = Logger.getLogger(main.class.getName());
		
		// entrando com o path do arquivo 
		//String path_arquivo = System.in.toString();
		String path_arquivo = "C:\\Users\\felip\\Desktop\\UFSC\\Grafos\\arquivos_net\\facebook_santiago.net";
		File arquivo = new File(path_arquivo);
		
		// checando se o arquivo existe
		if(!arquivo.exists()) {
			log.log(Level.SEVERE, "O arquivo não existe.");
			return;
		}
		
		// se o arquivo existir, continua		
		try {
			String linha = "";
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			
			Grafo grafo = new Grafo();
			
			while(br.ready()) {	// enquanto ainda houver linhas para serem lidas
				linha = br.readLine();
				if(linha.contains(str_vertices)) {	//cria os vertices do grafo
					int n_vertices = Integer.parseInt(linha.split(" ")[1]); // recebe o numero de vertices
					for(int i = 0; i < n_vertices; i++) {
						String linha_lida = br.readLine();
						String nome = "";
						if(linha_lida.contains("\"")) { // checa se a começa com aspas
							nome = linha_lida.split("\"")[1];
						} else {
							nome = linha_lida.split(" ")[1];
						}
						
						Vertice v_novo = new Vertice(nome);
						grafo.addVertice(v_novo);
					}
				} else if (linha.contains(str_edges)) {	// cria as arestas do grafo
					List<Vertice> vertices = grafo.getVertices();
					
					while(br.ready()) {
						linha = br.readLine();
						
						// criando as arestas
						Vertice v1 = vertices.get(Integer.parseInt(linha.split(" ")[0]) - 1);
						Vertice v2 = vertices.get(Integer.parseInt(linha.split(" ")[1]) - 1);
						double peso = Double.parseDouble(linha.split(" ")[0]);
						
						Aresta n_aresta = new Aresta(v1, v2, peso);
						
						// adicionando a aresta ao grafo
						grafo.addAresta(n_aresta);
						
						// referenciando vizinhos (o metodo ja aumenta o numero do grau)
						v1.addVizinho(v2);
						v2.addVizinho(v1);
						
						System.out.println("Criada a aresta entre os vertices "+v1.getName()+" e "+v2.getName()+" de peso "+n_aresta.getPeso());
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
		
		
		 // fazer testes
		
	}

}
