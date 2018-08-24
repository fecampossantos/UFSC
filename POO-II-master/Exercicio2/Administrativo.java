
public class Administrativo extends Assistente{
	private String turno;
	
	public Administrativo() {
		
	}
	
	public Administrativo(String nome, String cpf, double salario, String numeroDeMatricula, String turno) {
		super(nome, cpf, salario, numeroDeMatricula);
		this.turno = turno;
	}

}
