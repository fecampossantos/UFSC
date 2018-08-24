
public abstract class Imovel<E> { //código, localização (rua, número, bairro e município), área e preço
	private int codigo;
	private E local;
	private String area;
	private double preco;
	
	public Imovel() {
		
	}
	
	public Imovel(int cod, E local, String area, double preco) {
		this.codigo = cod;
		this.local = local;
		this.area = area;
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public E getLocal() {
		return local;
	}

	public void setLocal(E local) {
		this.local = local;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
