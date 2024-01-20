//1
function calculaIMC(peso, altura) {
    return peso / (altura * altura)
};
console.log(calculaIMC(80, 1.80).toFixed(2));

//2
function calculaFatorial(n) {
    let fatorial = 1;
    for (let i = n; i > 1; i--) {
        fatorial *= i;
    }
    return fatorial;
};
console.log(calculaFatorial(5));

//3
function converterDolarParaReal(dolar) {
    return dolar * 4.80
};
console.log(converterDolarParaReal(10));

//4
function calculaAreaEPerimetroRetangulo(altura, largura) {
    return {
        area: altura * largura,
        perimetro: 2 * (altura + largura)
    }
};
console.log(calculaAreaEPerimetroRetangulo(10, 20));

//5
function calculaAreaEPerimetroCirculo(raio) {
    return {
        area: 3.14 * raio * raio,
        perimetro: 2 * 3.14 * raio
    }
};
console.log(calculaAreaEPerimetroCirculo(10));

//6
function tabuada(n) {
    let resultado = "";
    for (let i = 1; i <= 10; i++) {
        resultado += `${n} x ${i} = ${n * i}\n`;
    }
    return resultado;
};
console.log(tabuada(5));