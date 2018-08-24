//classe para teste
public class main {

	public static void main(String[] args) {
		
		System.out.println("----- TESTE DA CLASSE 'PESSOAS' -----");
		System.out.println();
		System.out.println();
		
		Fisica pessoafisica = new Fisica(1049, "Julio Fisica", 1010);
		Juridica juridica1 = new Juridica(877988971, "Marcos Juridica 1", 29482384);
		Juridica juridica2 = new Juridica(95834, "Roberto Juridica 2", 38472);
		
		System.out.println("O código da pessoa fisica é " + pessoafisica.getCodigo().toString());
		System.out.println("O nome da pessoa fisica é " + pessoafisica.getNome());
		System.out.println("O cpf da pessoa fisica é " + pessoafisica.getCpf().toString());
		System.out.println();
		System.out.println();
		
		Juridica[] array1 = juridica1.getArrayJuridicos();
		System.out.println("Imprimindo juridicos direto do objeto");
		System.out.println("O codigo da pessoa juridica 1 é " + juridica1.getCodigo().toString());
		System.out.println("O nome da pessoa juridica 1 é "+juridica1.getNome());
		System.out.println("O CNPJ da pessoa juridica 1 é ( sem .toString() )"+juridica1.getCnpj());
		
		/*
		*System.out.println();
		*System.out.println("Imprimindo juridicos pelo array");
		*System.out.println("O codigo da pessoa juridica 1 é " + array1[0].getCodigo());
		*System.out.println("O nome da pessoa juridica 1 é "+ array1[0].getNome());
		*System.out.println("O CNPJ da pessoa juridica 1 é "+ array1[0].getCnpj().toString());
		*/
		
		System.out.println();
		System.out.println();
		System.out.println("----- TESTE DA CLASSE 'FUNCIONARIO' -----");
		System.out.println();
		System.out.println();
		
		
		Comissionado comissio = new Comissionado(10, 5, 234, "Jair", 3, 10); //10h trab. 5R$/h. 3 vendas. codigo, nome,R$10 / venda
		
		NaoComissionado ncomissio = new NaoComissionado(12, 4, 123321, "Luis"); //12h trab. 4R$/h codigo, nome
		
		
		System.out.println("Funcionario comissionado: -nome: "+comissio.getNome()+"\n"
							+ " - codigo: "+comissio.getCodigo()+"\n"
									+ " - horas trabalhadas: "+comissio.getHtrab()+
									"\n - valor por hora: "+comissio.getValhora()+
									"\n - numero de vendas: "+comissio.getVendas()+
									"\n - comissao por venda (R$): "+comissio.getComissao()+
									"\n - salario: "+comissio.calcularSalario(comissio));
		System.out.println();
		System.out.println();
		System.out.println("Funcionario não comissionado: -nome: "+ncomissio.getNome()+"\n"
				+ " - codigo: "+ncomissio.getCodigo()+"\n"
						+ " - horas trabalhadas: "+ncomissio.getHtrab()+
						"\n - valor por hora: "+ncomissio.getValhora()+
						"\n - salario: "+ncomissio.calcularSalario(ncomissio));
		
		System.out.println();
		System.out.println();
		//System.out.println("----- TESTANDO CLASSE IMOVEIS -----	");
		
		//Terreno() terr = new Terreno(1234, "Rua cento e um", "Centro", 1400);
		//Casa() casa = new Casa(12,"Rua dois", "Suburbio", 1243 );
		
		
		Curto curto = new Curto().criarCurto(39284, "Pedro", 2018, 10, 2);
		Longo longo = new Longo().criarLongo(93482, "Thomas", 2017, 7, 5, 8);
		Anual anual = new  Anual().criarAnual(3432, "Gustavo", 2000, 10, 8, 4, 2);
		Semestral semestral = new Semestral().criarSemestral(42342, "Felipe", 2017, 2, 10, 10, 10);
		
		Aluno.imprimirMatriz();
		

	}

}
