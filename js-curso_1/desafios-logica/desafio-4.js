// 1
console.log("Boas vindas!");

// 2
const nome = "Marcelo";
console.log("Olá, " + nome + "!");

//3
const nomeUsuario = "Marcelo";
alert(`Olá, ${nomeUsuario}!`);

// 4
let linguagem = prompt("Qual a linguagem de programação que você mais gosta?");
console.log(linguagem);

// 5
let valor1 = 5;
let valor2 = 20;
let resultadoSoma = valor1 + valor2;

console.log(`A soma de ${valor1} e ${valor2} é igual a ${resultadoSoma}.`);

// 6
let valor3 = 34;
let valor4 = 78;
let resultadoSub = valor3 - valor4;

console.log(`A diferença entre ${valor3} e ${valor4} é igual a ${resultadoSub}.`);

// 7
let idadeUsuario = prompt("Insira a sua idade");

if(idadeUsuario >= 18) {
    console.log("Você é maior de idade!");
} else {
    console.log("Você é menor de idade!");
};

// 8
let numeroPrompt = prompt("Insira um número aleatório");

if(numeroPrompt == 0) {
    console.log("O número é 0!");
} else {
    if(numeroPrompt > 0) {
        console.log("O número é positivo!");
    } else {
        console.log("O número é negativo!");
    } 
};

// 9
let contador = 0;

while (contador <= 10) {
    console.log(contador);
    contador++;
};

// 10
let nota = 5;

if(nota > 7) {
    console.log("Aprovado");
} else {
    console.log("Reprovado");
};

// 11
console.log(Math.random());

// 12
console.log(parseInt(Math.random() * 10 + 1));

// 13
console.log(parseInt(Math.random() * 1000 + 1));