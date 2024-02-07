package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.CaixaEletronico;
import excecoes.NumeroContaNaoEncontradoException;
import mocks.MockRecuperaDadosContaCorrente;

class TesteCaixaEletronico {
	
	private CaixaEletronico _caixaEletronico;
	
	
	@BeforeEach
	void inicializaCaixaEletronico() {
		_caixaEletronico = new CaixaEletronico();
	}
	
	@Test
	void testeRecuperaDadosConta() {
		MockRecuperaDadosContaCorrente mockRecuperaDadosContaCorrente = new MockRecuperaDadosContaCorrente();		
		_caixaEletronico.adicionarMockRecuperaDadosContaCorrente(mockRecuperaDadosContaCorrente);
		mockRecuperaDadosContaCorrente.recuperarConta("1");
		mockRecuperaDadosContaCorrente.verficaDadosContaCorrente("1", "Matheus", 1000);
	}
	
	@Test
	void testeRecuperaDadosContaComErro() {
		MockRecuperaDadosContaCorrente mockRecuperaDadosContaCorrente = new MockRecuperaDadosContaCorrente();		
		_caixaEletronico.adicionarMockRecuperaDadosContaCorrente(mockRecuperaDadosContaCorrente);
		try {
			mockRecuperaDadosContaCorrente.recuperarConta("2");
		} catch (NumeroContaNaoEncontradoException excecao) {}
	}
}
