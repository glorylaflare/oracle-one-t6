const titulo = document.querySelector("h1");
const consoleButton = document.querySelector(".console-button");
const alertaButton = document.querySelector(".alerta-button");
const promptButton = document.querySelector(".prompt-button");
const somaButton = document.querySelector(".soma-button");

titulo.innerHTML = "Hora do Desafio";

consoleButton.addEventListener("click", () => {
    console.log("O botão foi clicado");
});

alertaButton.addEventListener("click", () => {
    alert("Eu amo JS");
});

promptButton.addEventListener("click", () => {
    let cidade = prompt("Diga o nome de uma cidade do Brasil");

    alert(`Estive em ${cidade} e lembrei de você.`);
});

somaButton.addEventListener("click", () => {
    let num1 = parseInt(prompt("Digite um número"));
    let num2 = parseInt(prompt("Digite outro número"));

    alert(`A soma dos dois números é ${num1 + num2}`);
});