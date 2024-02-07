package classes;

import interfaces.ServicoRemoto;

public class CaixaEletronico {
	
	private ServicoRemoto _mockRecuperaDadosContaCorrente;

	public void adicionarMockRecuperaDadosContaCorrente(ServicoRemoto mockRecuperaDadosContaCorrente) {
		_mockRecuperaDadosContaCorrente = mockRecuperaDadosContaCorrente;
	}

}
