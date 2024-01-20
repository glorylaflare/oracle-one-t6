//1
const olaMundo = () => {
    console.log('Olá mundo!');
};
olaMundo();

//2
const recebeNome = (nome) => {
    console.log(`Olá ${nome}!`);
}
recebeNome('João');

//3
const dobro = (num) => {
    return num * 2;
}
console.log(dobro(2));

//4
const calculaMedia = (n1,n2,n3) => {
    return media = (n1 + n2 + n3) / 3;
};
console.log(calculaMedia(5,7,9));

//5
const calculaMaiorNumero = (n1,n2) => {
    return n1 > n2 ? n1 : n2;
};
console.log(calculaMaiorNumero(11,9));

//6
const multiplicaPorEleMesmo = (num) => {
    return num * num;
};
console.log(multiplicaPorEleMesmo(5));