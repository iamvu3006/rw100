"use strict";
const pi = 3.14;
console.log(pi); //3.14
// pi = 3.14159;
// console.log(pi); //error because pi is constant
const account = {
    id: 1,
    name: "BaVu",
    balance: 1000
};
console.log(account); //1 Bavu 1000
account.balance = 2000;
console.log(account); //1 Bavu 2000
// account = {
//     id: 2,
//     name: "BaVu_2",
//     balance: 2000
// };
console.log(account); //1 Bavu 2000
