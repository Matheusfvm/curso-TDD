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

}
