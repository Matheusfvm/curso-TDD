import java.util.ArrayList;
import java.util.List;

public class separaStringCamelCase {
	
	public static List<String> converterCamelCase(String original) {
		List<String> resultado = new ArrayList<>();
		StringBuilder elemento = new StringBuilder();
		if (consultaNumeroInicioDaFrase(original)) {
			resultado.add("Inválido → não deve começar com números");		
		} else if (consultaCaractereEspecial(original)) {
			resultado.add("Inválido → caracteres especiais não são permitidos, somente letras e números");
		} else {
			separaString(elemento, resultado, original);
		}return resultado;
	}	

	public static List<String> separaString(StringBuilder elemento, List<String> resultado, String frase) {
		Integer contadorPosicao = 0;
		for (char caractere : frase.toCharArray()) {
			if (Character.isUpperCase(caractere)  || Character.isDigit(caractere)) {
				separador(resultado, elemento, caractere, frase, contadorPosicao);
			} else {
				elemento.append(String.valueOf(caractere));}
			contadorPosicao++;}
		adicionaUltimoElemento(elemento, resultado);		
		return resultado;
	}

	public static void separador(List<String> resultado, StringBuilder elemento, char caractereAtual, String frase, Integer contadorPosicao) {
		if (elemento.length() > 0) {
			if (consultaUltimoCaractereDoElemento(elemento)) {
				consultaSigla(resultado, elemento, caractereAtual,  frase, contadorPosicao);
			} else {
				resultado.add(elemento.toString().toLowerCase());
				elemento.setLength(0);
				elemento.append(caractereAtual);}					
		} else {
			elemento.append(caractereAtual);}
	}
	
	public static boolean consultaUltimoCaractereDoElemento(StringBuilder elemento) {
		char ultimoCaractereDoElemento = elemento.charAt(elemento.length() - 1);
		return Character.isUpperCase(ultimoCaractereDoElemento) || Character.isDigit(ultimoCaractereDoElemento);
	}
	
	public static boolean consultaNumeroInicioDaFrase(String frase) {
		char primeiroCaractereDaFrase = frase.charAt(0);
		return Character.isDigit(primeiroCaractereDaFrase);
	}
	
	public static void adicionaUltimoElemento(StringBuilder elemento, List<String> resultado) {
		if (consultaUltimoCaractereDoElemento(elemento)) {
			resultado.add(elemento.toString());
		} else {
			resultado.add(elemento.toString().toLowerCase());}
	}
	
	public static boolean consultaCaractereEspecial(String frase) {
		for (char caractere : frase.toCharArray()) {
			if (!Character.isLetter(caractere) && !Character.isDigit(caractere)) {
				return true;
			}
		}
		return false;
	}
	
	public static void consultaSigla(List<String> resultado, StringBuilder elemento, char caractereAtual, String frase, Integer contadorPosicao) {
		if(caractereAtual == frase.charAt(frase.length() - 1)) {
			elemento.append(caractereAtual);
		} else {
			if (Character.isLowerCase(frase.charAt(contadorPosicao + 1))) {
				resultado.add(elemento.toString());
				elemento.setLength(0);
				elemento.append(caractereAtual);
			} else {
				elemento.append(caractereAtual);}}
	}
}
