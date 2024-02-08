package mocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import classes.ContaCorrente;
import excecoes.NumeroContaNaoEncontradoException;
import interfaces.ServicoRemoto;

public class MockServicoRemoto implements ServicoRemoto {
	
	private ContaCorrente _contaCorrenteNoBancoDados = new ContaCorrente("1", "Matheus", 1000);
	
	private ContaCorrente _contaCorrenteRecuperada = new ContaCorrente(null, null, null);
	
	
	@Override
	public ContaCorrente recuperarConta(String numeroConta) {
		if (numeroConta != _contaCorrenteNoBancoDados.get_numeroContaCorrente()) {
			throw new NumeroContaNaoEncontradoException("Nenhuma conta encontrada");			
		}
		_contaCorrenteRecuperada.set_numeroContaCorrente(_contaCorrenteNoBancoDados.get_numeroContaCorrente());
		_contaCorrenteRecuperada.set_nomeCorrentista(_contaCorrenteNoBancoDados.get_nomeCorrentista());
		_contaCorrenteRecuperada.set_saldoConta(_contaCorrenteNoBancoDados.get_saldoConta());
		return _contaCorrenteRecuperada;
	}
	
	@Override
	public void persistirConta(Integer valorTransacao) {
		Integer saldoContaCorrente = _contaCorrenteRecuperada.get_saldoConta();
		saldoContaCorrente += valorTransacao;
		_contaCorrenteNoBancoDados.set_saldoConta(saldoContaCorrente);
		_contaCorrenteRecuperada.set_saldoConta(saldoContaCorrente);
	}

	public void verificarDadosContaCorrente(String numeroContaCorrente, String nomeCorrentista, Integer saldoContaCorrente) {
		assertEquals(_contaCorrenteRecuperada.get_numeroContaCorrente(), numeroContaCorrente);
		assertEquals(_contaCorrenteRecuperada.get_nomeCorrentista(), nomeCorrentista);
		assertEquals(_contaCorrenteRecuperada.get_saldoConta(), saldoContaCorrente);
	}
	
	public void verificarSaldoAposTransacao(Integer saldoContaCorrenteAposTransacao) {
		assertEquals(_contaCorrenteRecuperada.get_saldoConta(), saldoContaCorrenteAposTransacao);
	}
	
	public Integer recuperaSaldoContaCorrenteBancoDados() {
		return _contaCorrenteNoBancoDados.get_saldoConta();
	}
		
}
