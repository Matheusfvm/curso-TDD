package mocks;

import excecoes.ProblemaLeituraNumeroCartaoExeption;
import interfaces.Hardware;

public class MockLeNumeroCartao implements Hardware{
	
	private String _numeroContaCartao;

	@Override
	public String pegarNumeroDaContaCartao(String numeroContaCartao) {
		if (numeroContaCartao.isBlank()) {
			throw new ProblemaLeituraNumeroCartaoExeption("Leitura do cartão comprometida");
		}
		set_numeroContaCartao(numeroContaCartao);
		return get_numeroContaCartao();
	}
	
	
	// Getters e Setters
	
	public String get_numeroContaCartao() {
		return _numeroContaCartao;
	}

	public void set_numeroContaCartao(String _numeroContaCartao) {
		this._numeroContaCartao = _numeroContaCartao;
	}

}
