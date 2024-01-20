alert("Bem vindo ao meu primeiro projeto do programa ONE");

const numeroMaxConvidados = 1500;
const numeroConvidados = parseInt(Math.random() * numeroMaxConvidados + 1);
// console.log(numeroConvidados);

let tentativas = 1, convidados;

while(convidados != numeroConvidados) {
    convidados = prompt(`Você irá se casar e quem está organizando tudo é seu parceiro(a), ao decidirem comprar o bolo de casamento, você pergunta quantos convidados irão para na festa, então o/a parceiro(a) decidem fazer uma brincadeira de adivinhação.\n\nQuantos convidados você acha que irá na festa? \nO número de convidados está entre 1 e ${numeroMaxConvidados}!`);

    if(convidados == numeroConvidados) {
        break;
    } else {
        if(convidados > numeroConvidados) {
            alert("O número de convidados é menor");
        } else {
            alert("O número de convidados é maior");
        }
        tentativas++;
    }
};

let pesoDoBolo = (convidados * 0.150) + 0.500;

let palavraTentativas = tentativas > 1 ? "tentativas" : "tentativa";
alert(`Mandou bem! Você acertou com ${tentativas} ${palavraTentativas}.
Então iremos precisar de um bolo com ${pesoDoBolo.toFixed(2)}kg.`);