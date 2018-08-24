
public class Imovel {
	protected String endereco;
	protected int preco;
	
	public Imovel() {
		
	}
	
	public Imovel(String endereco, int preco) {
		this.endereco = endereco;
		this.preco = preco;
	}
	
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getPreco() {
		return preco;
	}
	public void setPreco(int preco) {
		this.preco = preco;
	}
	
	

}
