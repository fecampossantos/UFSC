
public class Fisica<T, E> extends Pessoa{
	private T cpf;
	
	
	public Fisica() {
		
	}
	
	public Fisica(E codigo, String nome, T cpf) {
		super(codigo, nome);
		this.cpf = cpf;
		
	}

	public T getCpf() {
		return cpf;
	}

	public void setCpf(T cpf) {
		this.cpf = cpf;
	}
	
	
	

}
