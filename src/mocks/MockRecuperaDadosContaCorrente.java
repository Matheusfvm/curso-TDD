package mocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import classes.ContaCorrente;
import excecoes.NumeroContaNaoEncontradoException;
import interfaces.ServicoRemoto;

public class MockRecuperaDadosContaCorrente implements ServicoRemoto {
	
	private String _numeroContaCorrente;
	
	private String _nomeCorrentista;
	
	private Integer _saldoContaCorrente;

	@Override
	public void recuperarConta(String numeroConta) {
		if (numeroConta != "1") {
			throw new NumeroContaNaoEncontradoException("Nenhuma conta encontrada");			
		}
		ContaCorrente contaCorrente = new ContaCorrente("1", "Matheus", 1000);
		set_numeroContaCorrente(contaCorrente.get_numeroContaCorrente());
		set_nomeCorrentista(contaCorrente.get_nomeCorrentista());
		set_saldoContaCorrente(contaCorrente.get_saldoConta());
		
	}

	public void verficaDadosContaCorrente(String numeroContaCorrente, String nomeCorrentista, int saldoContaCorrente) {
		assertEquals(_numeroContaCorrente, numeroContaCorrente);
		assertEquals(_nomeCorrentista, nomeCorrentista);
		assertEquals(_saldoContaCorrente, saldoContaCorrente);
	}
	
	// Getters e Setters
	
	public String get_numeroContaCorrente() {
		return _numeroContaCorrente;
	}

	public void set_numeroContaCorrente(String _numeroContaCorrente) {
		this._numeroContaCorrente = _numeroContaCorrente;
	}

	public String get_nomeCorrentista() {
		return _nomeCorrentista;
	}

	public void set_nomeCorrentista(String _nomeCorrentista) {
		this._nomeCorrentista = _nomeCorrentista;
	}

	public Integer get_saldoContaCorrente() {
		return _saldoContaCorrente;
	}

	public void set_saldoContaCorrente(Integer _saldoContaCorrente) {
		this._saldoContaCorrente = _saldoContaCorrente;
	}

}
