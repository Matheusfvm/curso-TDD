package interfaces;

import classes.ContaCorrente;

public interface ServicoRemoto {
	
	public ContaCorrente recuperarConta(String numeroConta);
	
	public void persistirConta(Integer valorTransacao);
}
