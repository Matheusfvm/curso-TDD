import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class TesteSeparaStringCamelCase {
		
	@Test 
	void testeStringSemCamelCase() {
		String frase = "nomecomposto";
		List<String> resultado = List.of("nomecomposto");		
		assertEquals(resultado, separaStringCamelCase.converterCamelCase(frase));
	}
	
	@Test
	void testeStringComCamelCase() {
		String fraseSemSigla = "nomeComposto";
		String fraseQueComecaComCaixaAlta = "NomeComposto";
		List<String> resultadoSemSigla = List.of("nome", "composto");
		assertEquals(resultadoSemSigla, separaStringCamelCase.converterCamelCase(fraseSemSigla));
		assertEquals(resultadoSemSigla, separaStringCamelCase.converterCamelCase(fraseQueComecaComCaixaAlta));
		String fraseComSigla = "numeroCPFContribuinte";
		List<String> resultadoComSigla = List.of("numero", "CPF", "contribuinte");
		assertEquals(resultadoComSigla, separaStringCamelCase.converterCamelCase(fraseComSigla));
	}
	
	@Test
	void testeStringComecaComNumero() {
		String fraseSemComecarComNumero = "Finais100";
		List<String> resultadoSemComecarComNumero = List.of("finais", "100");
		assertEquals(resultadoSemComecarComNumero, separaStringCamelCase.converterCamelCase(fraseSemComecarComNumero));
		String fraseQueComecaComNumero = "10Primeiros";
		List<String> resultadoQueComecaComNumero = List.of("Inválido → não deve começar com números");
		assertEquals(resultadoQueComecaComNumero, separaStringCamelCase.converterCamelCase(fraseQueComecaComNumero));
	}
	
	@Test
	void testeStringComCaractereEspecial() {
		String fraseComCaractereEspecial = "nome#Composto";
		List<String> resultadoComCaractereEspecial = List.of("Inválido → caracteres especiais não são permitidos, somente letras e números");
		assertEquals(resultadoComCaractereEspecial, separaStringCamelCase.converterCamelCase(fraseComCaractereEspecial));
		String fraseSemCaractereEspecial = "nomeComposto";
		List<String> resultadoSemCaractereEspecial = List.of("nome", "composto");
		assertEquals(resultadoSemCaractereEspecial, separaStringCamelCase.converterCamelCase(fraseSemCaractereEspecial));
	}
}
