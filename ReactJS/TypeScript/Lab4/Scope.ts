
//global scope
let myAddress = "DN";

console.log(myAddress); // DN

//function scope
function a(){
    let x = 10;
    console.log(x); //10
}
a();

// console.log(x); //error because x is block scoped

//block scope
if (myAddress == "DN"){
    let y = 20;
    console.log(y); //20
}

//Lexical scope
function x(){
    let x = 20;
    console.log(x); //20
    console.log(y); //30

    function y(){
        let y = 30;
        console.log(x); //20
        console.log(y); //30
    }
    y();
}
x();