class Person {
    id;
    name;
    address;
    go() {
        console.log("I can go!!");
    }
    showInfo() {
        // console.log("id:"+ this.id + " name:" + this.name + " address:" + this.address);
        // Template string: ``  ${}
        console.log(`id: ${this.id}, name: ${this.name}, address: ${this.address}`);
    }
}
var myName = "iamvu";
export { Person, myName };
