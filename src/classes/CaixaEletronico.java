package classes;

import excecoes.EnvelopeNaoRecebidoException;
import excecoes.NumeroContaNaoEncontradoException;
import excecoes.ProblemaLeituraNumeroCartaoExeption;
import excecoes.ProblemaSaldoException;
import excecoes.SaldoContaInsuficienteException;
import interfaces.Hardware;
import interfaces.ServicoRemoto;
import mocks.MockHardware;


public class CaixaEletronico {
	
	private String _numeroContaCartao;
	
	private ContaCorrente _contaCorrenteRecuperada;
	
	private Boolean _darProblema = false;
		
	private ServicoRemoto _mockServicoRemoto;
	
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
		_mockHardware.entregarDinheiro();
		return "Retire seu dinheiro";		
	}
	
	public String depositar(Integer valorDeposito) {
		_mockHardware.lerEnvelope();
		_mockServicoRemoto.persistirConta(valorDeposito);
		return "Depósito recebido com sucesso";
	}
	
	public String saldo() {		
		if (_darProblema) {
			throw new ProblemaSaldoException("Problema com a visualização do saldo");			
		}
		Integer saldoContaCorrente = _contaCorrenteRecuperada.get_saldoConta();
		float saldoContaCorrenteDecimal = (float) saldoContaCorrente;
		return "O saldo é R$" + String.format("%.2f", saldoContaCorrenteDecimal);
	}
	
	public void darProblemaSaldo() {
		_darProblema = true;
	}
	
		
	// Getters e setters
	
	public String get_numeroContaCartao() {
		return _numeroContaCartao;
	}

	public void set_numeroContaCartao(String _numeroContaCartao) {
		this._numeroContaCartao = _numeroContaCartao;
	}
}
