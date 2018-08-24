
public class Novo extends Imovel {
	protected int adicional;
	
	public Novo(){
		
	}
	
	public Novo(String endereco, int preco, int adicional) {
		super(endereco, preco);
		this.adicional = adicional;
	}

	public int getAdicional() {
		return adicional;
	}

	public void setAdicional(int adicional) {
		this.adicional = adicional;
	}
	
	public int retornaAdicional() {
		return this.adicional;
	}
	
	public int retornaTotal() {
		int total = this.preco + this.adicional;
		return total;
	}
	
	public void imprimeAdicional() {
		System.out.println("O valor adicional da casa, por ser nova, é " +this.adicional);
	}
	
	public void imprimeTotal() {
		System.out.println("O valor total da casa (base + adicional) é " +(this.preco + this.adicional));
	}

}
