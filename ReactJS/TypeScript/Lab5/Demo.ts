//Destructuring: phá vỡ cấu trúc

let account = {
    id: 1,
    name: "BaVu",
    balance: 1000
};

// hãy tách giá trị của các biến account ra thành các biến riêng biệt
// let v_id = account.id;
// let v_name = account.name;
// let v_balance = account.balance;

//Destructuring:
let {id: v_id, name: v_name, balance: v_balance} = account;
console.log(`Id: ${v_id}, Name: ${v_name}, Balance: ${v_balance}`);

// Mảng
let name_railway = ["Ga Da Nang", "Ga Hue", "Ga Ho Chi Minh"];
let [v_ga1, v_ga2, v_ga3] = name_railway;
console.log(`Ga 1: ${v_ga1}, Ga 2: ${v_ga2}, Ga 3: ${v_ga3}`);