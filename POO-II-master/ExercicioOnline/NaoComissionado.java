
public class NaoComissionado<E> extends Funcionario{
	
	public NaoComissionado() {
		
	}
	
	public NaoComissionado(int htrab, int valhora, E codigo, String nome) {
		super(htrab, valhora, codigo, nome);
	}
	
	public double calcularSalario(NaoComissionado funcionario) {
		double salario = funcionario.getHtrab() * funcionario.getValhora();
		
		return salario;
	}

}
