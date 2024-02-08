package classes;

import excecoes.NumeroContaNaoEncontradoException;
import excecoes.ProblemaLeituraNumeroCartaoExeption;
import excecoes.SaldoContaInsuficienteException;
import interfaces.Hardware;
import interfaces.ServicoRemoto;
import mocks.MockHardware;


public class CaixaEletronico {
	
	private String _numeroContaCartao;
		
	private ServicoRemoto _mockServicoRemoto;
	
	private ContaCorrente _contaCorrenteRecuperada;
	
	private Hardware _mockHardware;
	

	public void adicionarMockServicoRemoto(ServicoRemoto mockServicoRemoto) {
		_mockServicoRemoto = mockServicoRemoto;
	}

	public void adicionarMockHardware(MockHardware mockHardware) {
		_mockHardware = mockHardware;				
	}

	public String logar() {
		try {
			String numeroContaCartao = _mockHardware.pegarNumeroDaContaCartao(get_numeroContaCartao());
			_contaCorrenteRecuperada = _mockServicoRemoto.recuperarConta(numeroContaCartao);
			return "Usuário Autenticado";
		} catch (ProblemaLeituraNumeroCartaoExeption excecao) {
			return excecao.getMessage();
		} catch (NumeroContaNaoEncontradoException excecao) {
			return excecao.getMessage();
		}

	}

	public String sacar(Integer valorSaque) {
		if ((_contaCorrenteRecuperada.get_saldoConta() - valorSaque) < 0) {
			throw new SaldoContaInsuficienteException("Saldo Insuficiente");
		}
		_mockServicoRemoto.persistirConta(-valorSaque);
		return "Retire seu dinheiro";
	}
		
	// Getters
	
	public String get_numeroContaCartao() {
		return _numeroContaCartao;
	}

	public void set_numeroContaCartao(String _numeroContaCartao) {
		this._numeroContaCartao = _numeroContaCartao;
	}

}
