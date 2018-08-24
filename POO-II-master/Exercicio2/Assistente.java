
public class Assistente extends Funcionario { //subclasse de Funcionario
	private String numeroDeMatricula;
	
	public Assistente() {
		
	}
	
	public Assistente(String nome, String cpf, double salario, String numeroDeMatricula) {
		super(nome, cpf, salario);
		this.numeroDeMatricula = numeroDeMatricula;
	}

	public String getNumeroDeMatricula() {
		return numeroDeMatricula;
	}

	public void setNumeroDeMatricula(String numeroDeMatricula) {
		this.numeroDeMatricula = numeroDeMatricula;
	}
	
	//----------------------------------MÉTODOS-------------------------------//
	
	@Override   //sobrescrevendo o metodo
	public void exibeDados() {
		
	}

}
