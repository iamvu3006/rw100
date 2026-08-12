// //hàm tổng
// function sum(a: number, b: number): number {
//     return a + b;
// }
// let result = sum(10, 5);


//Cách 2: (Expression Function): Tạo 1 biến tham chiếu đến hàm
// let v_sum = function (a: number, b: number): number {
//     return a + b;
// }


//Cách 3: (Arrow Function): Hàm mũi tên
let v_arrow_sum = (a: number, b: number): number => {
    return a + b;
}
console.log("Kết quả Arrow: " + v_arrow_sum(5, 3));
