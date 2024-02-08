package testes;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.CaixaEletronico;
import classes.ContaCorrente;
import excecoes.EnvelopeNaoRecebidoException;
import excecoes.NumeroContaNaoEncontradoException;
import excecoes.ProblemaEntregaDinheiroSaqueException;
import excecoes.ProblemaLeituraNumeroCartaoExeption;
import excecoes.ProblemaSaldoException;
import excecoes.SaldoContaInsuficienteException;
import mocks.MockHardware;
import mocks.MockServicoRemoto;

class TesteCaixaEletronico {
	
	private CaixaEletronico _caixaEletronico;
	
	private MockServicoRemoto _mockServicoRemoto = new MockServicoRemoto();
	
	private MockHardware _mockHardware = new MockHardware();
	
	
	@BeforeEach
	void inicializaCaixaEletronico() {
		_caixaEletronico = new CaixaEletronico();		
		_caixaEletronico.adicionarMockServicoRemoto(_mockServicoRemoto);
		_caixaEletronico.adicionarMockHardware(_mockHardware);
	}
	
	@Test
	void testeRecuperaDadosContaComSucesso() {
		_mockServicoRemoto.recuperarConta("1");
		_mockServicoRemoto.verificarDadosContaCorrente("1", "Matheus", 1000);
	}
	
	@Test
	void testeRecuperaDadosContaComFalha() {
		try {
			_mockServicoRemoto.recuperarConta("2");
		} catch (NumeroContaNaoEncontradoException excecao) {}
	}
	
	@Test
	void testeLeituraNumeroCartaoComSucesso() {
		String numeroContaCartao = _mockHardware.pegarNumeroDaContaCartao("1");
		assertEquals(numeroContaCartao, "1");
	}
	
	@Test
	void testeLeituraNumeroCartaoComFalha() {
		try {
			_mockHardware.pegarNumeroDaContaCartao("");
		} catch (ProblemaLeituraNumeroCartaoExeption excecao) {}
	}
	
	@Test
	void testePersistirSaldoContaDepositoComSucesso() {
		_mockServicoRemoto.recuperarConta("1");
		_mockServicoRemoto.persistirConta(100);
		_mockServicoRemoto.verificarSaldoAposTransacao(1100);
	}
	
	@Test
	void testePersistirSaldoContaSaqueComSucesso() {
		_mockServicoRemoto.recuperarConta("1");
		_mockServicoRemoto.persistirConta(-100);
		_mockServicoRemoto.verificarSaldoAposTransacao(900);
	}	
	
	@Test
	void testeLoginComSucesso() {
		_caixaEletronico.set_numeroContaCartao("1");
		String mensagemLogin = _caixaEletronico.logar();
		assertEquals(mensagemLogin, "Usuário Autenticado");
	}
	
	@Test
	void testeLoginComFalha() {
		_caixaEletronico.set_numeroContaCartao("2");
		try {
			_caixaEletronico.logar();
		} catch (ProblemaLeituraNumeroCartaoExeption excecao) {
		} catch (NumeroContaNaoEncontradoException excecao) {}
	}
	
	@Test
	void testeSacarComSucesso() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();		
		String mensagemSaque = _caixaEletronico.sacar(100);
		assertEquals(mensagemSaque, "Retire seu dinheiro");
	}
	
	@Test
	void testeSacarComFalhaSaldo() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();
		try {
			_caixaEletronico.sacar(1001);
		} catch (SaldoContaInsuficienteException excecao) {}
	}
	
	@Test
	void testeSacarComFalhaEntregaDinheiro() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();
		try {
			_caixaEletronico.sacar(100);
		} catch (ProblemaEntregaDinheiroSaqueException excecao) {}
	}
	
	@Test
	void testeDepositoComSucesso() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();
		String mensagemDeposito = _caixaEletronico.depositar(100);
		assertEquals(mensagemDeposito, "Depósito recebido com sucesso");
	}
	
	@Test
	void testeDepositoComFalha() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();
		_mockHardware.naoRecebeEnvelopeDeposito();
		try {
			_caixaEletronico.depositar(100);
		} catch (EnvelopeNaoRecebidoException excecao) {}
	}
	
	@Test 
	void testeSaldoComSucesso() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();
		String mensagemSaldo = _caixaEletronico.saldo();
		assertEquals(mensagemSaldo, "O saldo é R$1000,00");
	}
	
	@Test 
	void testeSaldoComFalha() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();
		_caixaEletronico.darProblemaSaldo();
		try {
			_caixaEletronico.saldo();
		} catch (ProblemaSaldoException excecao) {}
	}
	
	@Test
	void consultarSaldoAposSaque() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();
		_caixaEletronico.sacar(100);
		String mensagemSaldo = _caixaEletronico.saldo();
		assertEquals(mensagemSaldo, "O saldo é R$900,00");
	}
	
	@Test
	void consultarSaldoAposDeposito() {
		_caixaEletronico.set_numeroContaCartao("1");
		_caixaEletronico.logar();
		_caixaEletronico.depositar(100);
		String mensagemSaldo = _caixaEletronico.saldo();
		assertEquals(mensagemSaldo, "O saldo é R$1100,00");
	}
	
}
