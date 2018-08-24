
public abstract class Aluno {
	protected static Aluno[] matrizalunos = new Aluno[20];
	private static int contagem = 0;
	private double matricula;
	private String nome;
	private int anoentrada;
	
	public Aluno() {
		
	}
	
	public Aluno(double matricula, String nome, int ano) {
		this.matricula = matricula;
		this.nome = nome;
		this.anoentrada = ano;
	}
	
	public double getMatricula() {
		return matricula;
	}

	public void setMatricula(double matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnoentrada() {
		return anoentrada;
	}

	public void setAnoentrada(int anoentrada) {
		this.anoentrada = anoentrada;
	}
	
	public static void setMatriz(Aluno[] matrizaluno) {
		matrizalunos = matrizaluno;
		contagem++;
	}
	
	public Aluno[] getMatriz() {
		return matrizalunos;
	}

	public static int getContagem() {
		return contagem;
	}

	public static void aumentaContagem() {
		contagem++;
	}
	
	public static void imprimirMatriz() {
		System.out.println(contagem);
		for(int i = 0; i < contagem; i++) {
			System.out.println("Aluno "+(i+1)+":"+matrizalunos[i].getNome()+
								"\n Matricula: "+matrizalunos[i].getMatricula()+
								"\n Ano de entrada: "+matrizalunos[i].getAnoentrada()+
								"\n Media: "+matrizalunos[i].calculaMedia()+
								"\n Aprovado: "+matrizalunos[i].checaAprovado());
			System.out.println();
			System.out.println();
		}
	}

	public abstract double calculaMedia();
	public abstract boolean checaAprovado();

}
