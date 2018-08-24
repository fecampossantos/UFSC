
public class Comissionado<E> extends Funcionario{
	private double comissao; //comissao do funcionario é dada em x reais por venda
	private int vendas;
	
	public int getVendas() {
		return vendas;
	}

	public void setVendas(int vendas) {
		this.vendas = vendas;
	}
	
	public Comissionado() {
		
	}
	
	public Comissionado(int htrab, int valhora, E codigo, String nome, int vendas, double comissao){
		super(htrab, valhora, codigo, nome);
		this.vendas = vendas;
		this.comissao = comissao;
	}
	
	public void adicionarVenda(Comissionado funcionario) {
		funcionario.vendas += 1;
	}
	
	public double calcularSalario(Comissionado funcionario) {
		double salario = ( funcionario.getHtrab() * funcionario.getValhora() ) + (funcionario.vendas * funcionario.comissao);
		
		return salario;
	}
	
	public double getComissao() {
		return comissao;
	}

}
