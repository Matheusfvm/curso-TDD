package mocks;

import excecoes.EnvelopeNaoRecebidoException;
import excecoes.ProblemaEntregaDinheiroSaqueException;
import excecoes.ProblemaLeituraNumeroCartaoExeption;
import interfaces.Hardware;

public class MockHardware implements Hardware{
	
	private String _numeroContaCartao;
	
	private Boolean _recebeEnvelopeDeposito = false;
	
	private Boolean _darProblema = false;

	@Override
	public String pegarNumeroDaContaCartao(String numeroContaCartao) {
		if (numeroContaCartao.isBlank()) {
			throw new ProblemaLeituraNumeroCartaoExeption("Leitura do cartão comprometida");
		}
		set_numeroContaCartao(numeroContaCartao);
		return get_numeroContaCartao();
	}
	
	@Override
	public void lerEnvelope() {
		if (_recebeEnvelopeDeposito) {
			throw new EnvelopeNaoRecebidoException("Insira o envelope");
		}
	}
	
	@Override
	public void entregarDinheiro() {
		if (_darProblema) {
			throw new ProblemaEntregaDinheiroSaqueException("Retire seu dinheiro");
		}
	}
	
	public void darProblemaNaEntregaDinheiro() {
		set_darProblema(true);
	}
	
	public void naoRecebeEnvelopeDeposito() {
		set_recebeEnvelopeDeposito(true);
	}
	
	
	// Getters e Setters
	
	public String get_numeroContaCartao() {
		return _numeroContaCartao;
	}

	public void set_numeroContaCartao(String numeroContaCartao) {
		this._numeroContaCartao = numeroContaCartao;
	}
	
	public void set_darProblema(Boolean darProblema) {
		this._darProblema = darProblema;
	}
		
	public void set_recebeEnvelopeDeposito(Boolean temEnvelope) {
		_recebeEnvelopeDeposito = temEnvelope;
	}
}
