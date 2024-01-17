// 1. VERIFICA SE O DIA DA SEMANA É UM FIM DE SEMANA OU NÃO...
let diaDaSemana = prompt("Qual é o dia da semana?");

if(diaDaSemana.toLowerCase() == "sábado" || diaDaSemana.toLowerCase() == "domingo") {
    alert("Bom fim de semana!");
} else {
    alert("Boa semana!");
};

// 2. VERIFICA SE UM NÚMERO É POSITIVO OU NEGATIVO...
let verificaNumero = prompt("Digite um número positivo ou negativo");

if(verificaNumero >= 0) {
    alert("Número positivo!");
} else {
    alert("Número negativo!");
};

// 3. VERIFICA SE A PONTUAÇÃO É MAIOR OU IGUAL A 100...
let pontuacao = prompt("Qual a pontuação final?");

if(pontuacao >= 100) {
    alert("Parabéns, você venceu!");
} else {
    alert("Tente novamente para ganhar");
};

// 4. MOSTRA O SALDO DA CONTA ATUAL...
let saldoDaConta = 3450;
alert(`O seu saldo atual é de R$ ${saldoDaConta.toFixed(2)}`);

// 5. MOSRTA O NOME DO USUÁRIO NO ALERT...
let nomeDoUsuario = prompt("Insira o seu nome");

alert(`Boas vindas ${nomeDoUsuario}!`);
