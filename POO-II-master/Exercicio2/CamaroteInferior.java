
public class CamaroteInferior extends VIP {
	protected int[] local = new int[2];
	
	public CamaroteInferior() {
		
	}
	
	public CamaroteInferior(int valor, int valorAdicional, int[] local) {
		super(valor, valorAdicional);
		this.local = local;
	}
	
	public int[] getLocal() {
		return local;
	}
	
	public void imprimeLocal() {
		System.out.println("Seu assento é na linha " +local[0]+ " e coluna " +local[1]);
	}
	
	
	public String retornaLocal() {
		return "Seu assento é na linha " +local[0]+ " e coluna " +local[1];
		
	}
	public int retornValor() {
		int valorTotal = this.getValor() + this.getValorAdicional();
		return valorTotal;
	}

}
