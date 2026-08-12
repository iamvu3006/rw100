//var let đều sử dụng để khai báo biến 
//reDeclare: var cho phép khai báo lại 
//Hosting: var có thể được gọi trước khi khai báo, let không thể
//block scope: let chỉ hoạt động trong khối code mà nó được khai báo

var myName = "BaVu"; // khai báo biến myName với var

console.log("My name: " + myName); //bavu

var myName = "BaVu_2"; //khai báo lại biến myName với var
console.log("My name: " + myName); //bavu_2

//Hosting
// console.log("my age: " + myAge); //undefined

// var myAge = 20; //khai báo biến myAge

let myAge = 20; 

console.log("my age: " + myAge); //20

// export{};
