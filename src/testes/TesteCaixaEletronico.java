package testes;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.CaixaEletronico;
import excecoes.NumeroContaNaoEncontradoException;
import excecoes.ProblemaLeituraNumeroCartaoExeption;
import mocks.MockLeNumeroCartao;
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
		mockRecuperaDadosContaCorrente.recuperarConta("1");
		mockRecuperaDadosContaCorrente.verficaDadosContaCorrente("1", "Matheus", 1000);
	}
	
	@Test
	void testeRecuperaDadosContaComFalha() {
		MockRecuperaDadosContaCorrente mockRecuperaDadosContaCorrente = new MockRecuperaDadosContaCorrente();
		try {
			mockRecuperaDadosContaCorrente.recuperarConta("2");
		} catch (NumeroContaNaoEncontradoException excecao) {
			assertEquals(excecao.getMessage(), "Nenhuma conta encontrada");
		}
	}
	
	@Test
	void testeLeituraNumeroCartao() {
		MockLeNumeroCartao mockLeNumeroCartao = new MockLeNumeroCartao();
		String numeroContaCartao = mockLeNumeroCartao.pegarNumeroDaContaCartao("1");
		assertEquals(numeroContaCartao, "1");
	}
	
	@Test
	void testeLeituraNumeroCartaoComFalha() {
		MockLeNumeroCartao mockLeNumeroCartao = new MockLeNumeroCartao();
		try {
			mockLeNumeroCartao.pegarNumeroDaContaCartao("");
		} catch (ProblemaLeituraNumeroCartaoExeption excecao) {
			assertEquals(excecao.getMessage(), "Leitura do cartão comprometida");
		}
	}
	
	@Test
	void testeLogin() {
		MockRecuperaDadosContaCorrente mockRecuperaDadosContaCorrente = new MockRecuperaDadosContaCorrente();
		MockLeNumeroCartao mockLeNumeroCartao = new MockLeNumeroCartao();
		_caixaEletronico.adicionarMockRecuperaDadosContaCorrente(mockRecuperaDadosContaCorrente);
		_caixaEletronico.adicionaMockLeNumeroCartao(mockLeNumeroCartao);
		_caixaEletronico.set_numeroContaCartao("1");
		String mensagemLogin = _caixaEletronico.logar();
		assertEquals(mensagemLogin, "Usuário Autenticado");
	}
	
	@Test
	void testeLoginComFalha() {
		MockRecuperaDadosContaCorrente mockRecuperaDadosContaCorrente = new MockRecuperaDadosContaCorrente();
		MockLeNumeroCartao mockLeNumeroCartao = new MockLeNumeroCartao();
		_caixaEletronico.adicionarMockRecuperaDadosContaCorrente(mockRecuperaDadosContaCorrente);
		_caixaEletronico.adicionaMockLeNumeroCartao(mockLeNumeroCartao);
		_caixaEletronico.set_numeroContaCartao("2");
		try {
			_caixaEletronico.logar();
		} catch (ProblemaLeituraNumeroCartaoExeption excecao) {
			assertEquals(excecao.getMessage(), "Leitura do cartão comprometida");
		} catch (NumeroContaNaoEncontradoException excecao) {
			assertEquals(excecao.getMessage(), "Nenhuma conta encontrada");
		}
	}
}
