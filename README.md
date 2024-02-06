# Quebra de Strings com CamelCase

## Instruções

Criar utilizando TDD um método que transforma uma cadeia de caracteres em [camel case](https://pt.wikipedia.org/wiki/CamelCase) em uma lista de Strings com as palavras. O método deve possuir a seguinte assinatura: "public static List < String > converterCamelCase(String original)".

Abaixo seguem alguns exemplos de entrada e saída que você pode usar como base para os seus testes (crie adicionais ou diferentes se achar necessário):

- nome - “nome” 

- Nome - “nome” 

- nomeComposto - “nome”, “composto” 

- NomeComposto - “nome”, “composto” 

- CPF - “CPF” 

- numeroCPF - “numero”, “CPF” 

- numeroCPFContribuinte - “numero”, “CPF”, “contribuinte” 

- recupera10Primeiros - “recupera”, “10”, “primeiros” 

- 10Primeiros - Inválido → não deve começar com números 

- nome#Composto - Inválido → caracteres especiais não são permitidos, somente letras e números

É permitida a criação de métodos auxiliares. Para ficar mais divertido e praticar a refatoração, nenhum método pode possuir mais de dez linhas de código em seu corpo. Não vale “roubar” e incluir vários comandos em uma mesma linha de código!

Para cada refatoração que precisar fazer nesse processo, adicione uma seção no relatório o seguinte: como estava o código antes, como ficou o código depois da refatoração e uma pequena descrição textual do que foi feito.

No método desenvolvido é permitida somente a utilização de classes da API básica da linguagem Java. Se você utilizar algum componente externo que facilite demais sua tarefa, estará tirando o propósito do exercício!