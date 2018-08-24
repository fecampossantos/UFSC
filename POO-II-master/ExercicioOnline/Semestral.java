/*Aluno de cursos semestrais: matrícula, nome, semestre e ano de entrada, nota1, nota2 e nota3.
 * A média para esta categoria é obtida pela média aritmética ponderada das notas, sendo que a nota3
 * representa a metade da nota final do aluno.
 * O aluno desta categoria é considerado aprovado se possuir média maior ou igual a 6;
 */

public class Semestral extends Aluno {
	private double nota1, nota2, nota3;
	private int semestre;
	
	public Semestral() {
		
	}
	
	public Semestral(double matricula, String nome, int ano, int semestre, double nota1, double nota2, double nota3) {
		super(matricula, nome, ano);
		this.semestre = semestre;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
	}
	
	public Semestral criarSemestral(double matricula, String nome, int ano, int semestre, double nota1, double nota2, double nota3) {
		Semestral semestral = new Semestral(matricula, nome, ano, semestre, nota1, nota2, nota3);
		Aluno[] matriz = Aluno.matrizalunos;
		matriz[Aluno.getContagem()] = semestral;
		Aluno.setMatriz(matriz);
		return semestral;
		
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

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	
	
	//--------------------------------------------------
	
	public double calculaMedia() {
		double media = (nota1 + nota2 + 2*nota3)/4;
		return media;
	}
	
	public boolean checaAprovado() {
		boolean aprovado = false;
		double media = calculaMedia();
		if(media >= 6) {
			aprovado=true;
		}
		return aprovado;
	} 

}
