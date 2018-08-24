
public class CamaroteSuperior extends VIP {
	protected int valorAdicionalSuperior;
	
	public CamaroteSuperior() {
		
	}
	
	public CamaroteSuperior(int valor, int valorAdicional, int valorAdicionalSuperior) {
		super(valor, valorAdicional);
		this.valorAdicionalSuperior = valorAdicionalSuperior;
	}
	
	public int getValorAdicionalSuperior() {
		return valorAdicionalSuperior;
	}

	public void setValorAdicionalSuperior(int valorAdicionalSuperior) {
		this.valorAdicionalSuperior = valorAdicionalSuperior;
	}

	
	public int retornaValor() {
		int valorTotal = ((this.valor) + (this.valorAdicional) + (this.valorAdicionalSuperior));
		return valorTotal;
	}

}
