
public abstract class Pessoa<E> {
	public E getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	protected E codigo;
	private String nome;
	
	public Pessoa() {
		
	}
	
	public Pessoa(E cod, String nome) {
		this.codigo = cod;
		this.nome = nome;
	}

}
