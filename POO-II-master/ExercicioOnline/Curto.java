/*Aluno de cursos curtos: matrícula, nome, mês e ano de entrada, nota1, nota2. A média para esta categoria
 * é obtida pela média aritmética da nota1 e da nota2.
 * O aluno é considerado aprovado se possuir média maior ou igual a 5;
 */


public class Curto extends Aluno {
	private double nota1, nota2, media;
	
	public Curto() {
		
	}
	
	public Curto(double matricula, String nome, int ano, double nota1, double nota2){
		super(matricula, nome, ano);
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.media = (nota1 + nota2)/2;
		
	}
	
	public Curto criarCurto(double matricula, String nome, int ano, double nota1, double nota2) {
		Curto curto = new Curto(matricula, nome, ano, nota1, nota2);
		Aluno[] matriz = Aluno.matrizalunos;
		matriz[Aluno.getContagem()] = curto;
		Aluno.setMatriz(matriz);
		return curto;
		
	}
	
	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double calculaMedia() {
		double media = (nota1 + nota2)/2;
		return media;
	}
	
	public boolean checaAprovado() {
		boolean aprovado = false;
		if(media >= 5) {
			aprovado = true;
		}
		
		return aprovado;
	}
}
