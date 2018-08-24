
public class VIP extends Ingresso {
	protected int valorAdicional;
	
	
	public VIP() {
		
	}
	
	public VIP(int valor, int valorAdicional) {
		super(valor);
		this.valorAdicional = valorAdicional;
	}
	
	
	public int getValorAdicional() {
		return valorAdicional;
	}

	public void setValorAdicional(int valorAdicional) {
		this.valorAdicional = valorAdicional;
	}
	
	public int retornaValorVIP() {
		int valorTotal = (this.valor) + (this.valorAdicional);
		return valorTotal;
	}

}
