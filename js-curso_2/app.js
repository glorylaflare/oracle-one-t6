const chute = document.querySelector("input");
const botaoChutar = document.querySelector("#chutar");
const botaoReiniciar = document.querySelector("#reiniciar");

let tentativas = 1;
let numeroMaxDeNumeros = 10;
let listaDeNumerosSorteados = [];

function gerarNumeroAleatorio() {
    let numeroAleatorio = parseInt(Math.random() * numeroMaxDeNumeros) + 1

    if(listaDeNumerosSorteados.length == numeroMaxDeNumeros) {
        listaDeNumerosSorteados = [];
    }

    if(listaDeNumerosSorteados.includes(numeroAleatorio)) {
        return gerarNumeroAleatorio();
    } else {
        listaDeNumerosSorteados.push(numeroAleatorio);
        return numeroAleatorio;
    }
};

function limparInput() {
    chute.value = "";
};

function exibirTextoNaTela(tag, texto) {
    let campo = document.querySelector(tag);
    campo.innerHTML = texto;

    responsiveVoice.speak(texto, 'Brazilian Portuguese Female', {rate:1.2});
};

function exibirMensagem() {
    exibirTextoNaTela("h1", "O Número Secreto");
    let mensagem = `Escolha um número entre 1 e ${numeroMaxDeNumeros}`
    exibirTextoNaTela("p", mensagem);
};

exibirMensagem();

let numeroAleatorio = gerarNumeroAleatorio();

botaoChutar.addEventListener("click", () => {
    if(numeroAleatorio == chute.value) {
        let palavraTentativas = tentativas > 1 ? "tentativas" : "tentativa";
        exibirTextoNaTela("h1", "Parabéns, você acertou!");
        let mensagem = `O número secreto era ${numeroAleatorio}, você acertou com ${tentativas} ${palavraTentativas}.`
        exibirTextoNaTela("p", mensagem);
        botaoChutar.disabled = true;
        botaoReiniciar.disabled = false;
    } else {
        if(chute.value > numeroAleatorio) {
            exibirTextoNaTela("p", "O número secreto é menor");
        } else {
            exibirTextoNaTela("p", "O número secreto é maior");
        }
        tentativas++;
        limparInput();
    }
});

botaoReiniciar.addEventListener("click", () => {
    tentativas = 1;
    numeroAleatorio = gerarNumeroAleatorio();
    exibirMensagem();
    botaoChutar.disabled = false;
    botaoReiniciar.disabled = true;
    limparInput();
});