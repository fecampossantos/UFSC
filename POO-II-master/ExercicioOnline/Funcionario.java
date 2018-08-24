
public abstract class Funcionario<E> {
	private int htrab, valhora;
	private E codigo;
	private String nome;
	
	public Funcionario() {
		
	}
	
	public Funcionario(int htrab, int valhora, E codigo, String nome) {
		this.htrab = htrab;
		this.valhora = valhora;
		this.codigo = codigo;
		this.nome = nome;
	}

	public E getCodigo() {
		return codigo;
	}

	public void setCodigo(E codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getHtrab() {
		return htrab;
	}

	public void setHtrab(int htrab) {
		this.htrab = htrab;
	}

	public int getValhora() {
		return valhora;
	}

	public void setValhora(int valhora) {
		this.valhora = valhora;
	}
}
