"use strict";
function callAPICheckEmail() {
    let promise_checkEmail = new Promise((resolve, reject) => {
        // Call API to check email
        let data = false;
        if (data) {
            reject("Email is already taken");
        }
        else {
            resolve("Email is available");
        }
    });
    return promise_checkEmail;
}
function callAPICheckUsername() {
    // Call API to check username availability
    let promise_checkUsername = new Promise((resolve, reject) => {
        // Call API to check Username
        let data = false;
        if (data) {
            reject("Username is already taken");
        }
        else {
            resolve("Username is available");
        }
    });
    return promise_checkUsername;
}
function callAPICreateAccount() {
    // Call API to check create Account availability
    let promise_newAcount = new Promise((resolve, reject) => {
        // Call API to check Username
        let data = false;
        if (data) {
            resolve("create account successfully");
        }
        else {
            reject("create account failed");
        }
    });
    return promise_newAcount;
}
//
// callAPICheckEmail(),
//  callAPICheckUsername(),
//  callAPICreateAccount();
callAPICheckEmail()
    .then(function (result) {
    console.log(result);
    return callAPICheckUsername();
})
    .then(function (result) {
    console.log(result);
    return callAPICreateAccount();
})
    .then(function (result) {
    console.log(result);
})
    .catch(function (error) {
    console.log(error);
});
