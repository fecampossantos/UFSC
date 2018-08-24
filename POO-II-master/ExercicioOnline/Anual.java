/*Alunos de cursos anuais: matrícula, nome, ano de entrada, nota1, nota2, nota3 e nota4.
 * A média para esta categoria é obtida pela média aritmética ponderada das notas, sendo que
 * a nota2 e a nota4 representam 80% da nota final do aluno.
 * O aluno desta categoria é considerado aprovado se possuir média maior ou igual a 7;
 */

public class Anual extends Aluno{
	private double nota1, nota2, nota3, nota4;
	
	public Anual() {
		
	}
	
	public Anual(double matricula, String nome, int ano, double nota1, double nota2, double nota3, double nota4) {
		super(matricula, nome, ano);
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
		this.nota4 = nota4;
	}
	
	public Anual criarAnual(double matricula, String nome, int ano, double nota1, double nota2, double nota3, double nota4) {
		Anual anual = new Anual(matricula, nome, ano, nota1, nota2, nota3, nota4);
		Aluno[] matriz = Aluno.matrizalunos;
		matriz[Aluno.getContagem()] = anual;
		Aluno.setMatriz(matriz);
		return anual;
		
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

	public double getNota3() {
		return nota3;
	}

	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}

	public double getNota4() {
		return nota4;
	}

	public void setNota4(double nota4) {
		this.nota4 = nota4;
	}
	
	
	
	
	
	//-----------------------------------------------------------------------------
	public double calculaMedia() {
		double media = ((nota2 + nota4)*0.8) + ((nota1 + nota3)*0.2);
		return media;
		
	}
	
	public boolean checaAprovado() {
		boolean aprovado = false;
		double media = calculaMedia();
		
		if(media >= 7) {
			aprovado = true;
		}
		
		return aprovado;
		
	}

}
