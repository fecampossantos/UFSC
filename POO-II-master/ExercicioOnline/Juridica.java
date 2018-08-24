
public class Juridica<T, E> extends Pessoa{
	private Juridica<T, E>[] arrayjuridicos = new Juridica[10];
	private static int contagem = 0;
	private T cnpj;
	
	
	public Juridica() {
		
	}
	
	public Juridica(E codigo, String nome, T cnpj) {
		super(codigo, nome);
		this.cnpj = cnpj;
		
	}
	
	public Juridica criaJuridica(E codigo, String nome, T cnpj) {
		Juridica jur = new Juridica(codigo, nome, cnpj);
		arrayjuridicos[contagem] = jur;
		contagem++;
		
		return jur;
		
	}

	public Juridica[] getArrayJuridicos() {
		return arrayjuridicos;
	}

	public void setArrayJuridicos(Juridica[] arrayJuridicos) {
		this.arrayjuridicos = arrayJuridicos;
	}

	public T getCnpj() {
		return cnpj;
	}

	public void setCnpj(T cnpj) {
		this.cnpj = cnpj;
	}

}
