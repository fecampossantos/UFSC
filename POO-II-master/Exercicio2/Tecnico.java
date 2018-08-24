
public class Tecnico extends Assistente {
	private int bonusSalarial;
	
	public Tecnico() {
		
	}
	
	public Tecnico(String nome, String cpf, double salario, String numeroDeMatricula, int bonusSalarial) {
		super(nome, cpf, salario, numeroDeMatricula);
		this.bonusSalarial = bonusSalarial;
	}

}
