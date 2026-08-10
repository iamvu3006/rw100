class Person {
    id: number;
    name: string;
    address: string;

    go(): void {
        console.log("I can go!!");
    }

    showInfo(): void {
        // console.log("id:"+ this.id + " name:" + this.name + " address:" + this.address);
        // Template string: ``  ${}
        console.log(
            `id: ${this.id}, name: ${this.name}, address: ${this.address}`,
        );
    }

    constructor(id: number = 0, name: string = "", address: string = "") {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}

var myName = "iamvu";

export { Person, myName };
