package classes;

public class ContaCorrente {
	
	private String _numeroContaCorrente;
	
	private String _nomeCorrentista;
	
	private Integer _saldoConta;

	public ContaCorrente (String numeroContaCorrente, String nomeCorrentista, Integer saldoConta) {
		_numeroContaCorrente = numeroContaCorrente;
		_nomeCorrentista = nomeCorrentista;
		_saldoConta = saldoConta;
	}	
	
	// Getters
	
	public String get_numeroContaCorrente() {
		return _numeroContaCorrente;
	}

	public String get_nomeCorrentista() {
		return _nomeCorrentista;
	}

	public Integer get_saldoConta() {
		return _saldoConta;
	}


	public void set_numeroContaCorrente(String _numeroContaCorrente) {
		this._numeroContaCorrente = _numeroContaCorrente;
	}


	public void set_nomeCorrentista(String _nomeCorrentista) {
		this._nomeCorrentista = _nomeCorrentista;
	}


	public void set_saldoConta(Integer _saldoConta) {
		this._saldoConta = _saldoConta;
	}
	
	

}
