alert("Boas vindas ao nosso site!");

let nome = prompt("Insira o seu nome");
let idade = prompt("Insira a sua idade");
let numeroDeVendas = 50;
let saldoDisponivel = 1000;

const mensagemDeErro = "Erro! Preencha todos os campos.";
alert(mensagemDeErro);

if(idade >= 18) {
    alert("Parabéns " + nome + ", você já pode tirar a habilitação!");
} else {
    alert(nome + ", você ainda não pode tirar a habilitação!");
};