function sum(a: number, b: number): number {
    return a + b;
}

function minus(a: number, b: number): number {
    return a - b;
}

function multiply(a: number, b: number): number {
    return a * b;
}

let result_sum = sum(2, 3); // Output: 5
let result_minus = minus(12, 3); // Output: 9

//
let calculate = function (
    a: number,
    b: number,
    f: (x: number, y: number) => number,
) {
    let result = f(a, b);
    return result;
};

//
let calculate_sum = calculate(10, 20, sum);
console.log(`calculate_sum = ${calculate_sum}`); // Output: 30

let calculate_minus = calculate(50, 30, minus);
console.log(`calculate_minus = ${calculate_minus}`); // Output: 20
