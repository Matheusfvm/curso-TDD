package classes;

import excecoes.NumeroContaNaoEncontradoException;
import excecoes.ProblemaLeituraNumeroCartaoExeption;
import interfaces.Hardware;
import interfaces.ServicoRemoto;
import mocks.MockLeNumeroCartao;

public class CaixaEletronico {
	
	private String _numeroContaCartao;
	
	private ServicoRemoto _mockRecuperaDadosContaCorrente;
	
	private Hardware _mockLeNumeroCartao;

	public void adicionarMockRecuperaDadosContaCorrente(ServicoRemoto mockRecuperaDadosContaCorrente) {
		_mockRecuperaDadosContaCorrente = mockRecuperaDadosContaCorrente;
	}

	public void adicionaMockLeNumeroCartao(MockLeNumeroCartao mockLeNumeroCartao) {
		_mockLeNumeroCartao = mockLeNumeroCartao;				
	}

	public String logar() {
		try {
			String numeroContaCartao = _mockLeNumeroCartao.pegarNumeroDaContaCartao(get_numeroContaCartao());
			_mockRecuperaDadosContaCorrente.recuperarConta(numeroContaCartao);
			return "Usuário Autenticado";
		} catch (ProblemaLeituraNumeroCartaoExeption excecao) {
			return excecao.getMessage();
		} catch (NumeroContaNaoEncontradoException excecao) {
			return excecao.getMessage();
		}

	}

		
	// Getters
	
	public String get_numeroContaCartao() {
		return _numeroContaCartao;
	}

	public void set_numeroContaCartao(String _numeroContaCartao) {
		this._numeroContaCartao = _numeroContaCartao;
	}
}
